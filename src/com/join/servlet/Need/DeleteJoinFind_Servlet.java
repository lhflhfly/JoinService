package com.join.servlet.Need;

import com.join.dao.NeedDao;
import com.join.factory.Factory;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "DeleteJoinFind_Servlet",urlPatterns ={"/DeleteJoinFind_Servlet"} )
public class DeleteJoinFind_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private int userId;
    private int needId;

    public DeleteJoinFind_Servlet(){
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine())!=null){
            stringBuilder.append(line);
        }
        String req = stringBuilder.toString();
        System.out.println(req);//
        JSONObject js = JSONObject.fromObject(req);
        needId = Integer.parseInt(js.getString("needId"));
        userId = Integer.parseInt(js.getString("userId"));
        JSONObject results = new JSONObject();
//        boolean flag = needDao.updateUserJoinNumDelete(needId);
        boolean flag = Factory.getNeedDAOIpmlProxy().updateUserJoinNumDelete(needId);
        if(flag){
            if(Factory.getNeedDAOIpmlProxy().deleteJoinedNeed(userId,needId)){
                results.put("result",1);
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.getWriter().append(results.toString()).flush();
            }else{
                results.put("result",0);
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.getWriter().append(results.toString()).flush();
            }
        }else{
            results.put("result",2);
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.getWriter().append(results.toString()).flush();
        }



        System.out.println("用");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
