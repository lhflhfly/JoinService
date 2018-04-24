package com.join.servlet.Need;

import com.join.dao.NeedDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "JoinFind_Servlet",urlPatterns ={"/JoinFind_Servlet"} )
public class JoinFind_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private int num;
    private int num_join;
    private int userId;
    private int needId;

    public JoinFind_Servlet(){
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
        NeedDao needDao = new NeedDao();
        needId = Integer.parseInt(js.getString("needId"));
        userId = Integer.parseInt(js.getString("userId"));
        num = needDao.getNum(needId);
        num_join = needDao.getNumJoin(needId);
        JSONObject results = new JSONObject();
        if(needDao.isJoined(userId,needId)){
            results.put("result",3);
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.getWriter().append(results.toString()).flush();

        }else {
            if(num>num_join){

                boolean flag = needDao.updateUserJoinNum(needId);
                if (flag){
                    boolean flag2 = needDao.insertJoin(userId,needId);
                    if(flag2){
                        results.put("result",1);
                        response.setHeader("Content-type", "text/html;charset=UTF-8");
                        response.getWriter().append(results.toString()).flush();

                    }else {
                        results.put("result",0);
                        response.setHeader("Content-type", "text/html;charset=UTF-8");
                        response.getWriter().append(results.toString()).flush();

                    }
                }else {
                    results.put("result",0);
                    response.setHeader("Content-type", "text/html;charset=UTF-8");
                    response.getWriter().append(results.toString()).flush();
                }
            }else {
                results.put("result",2);
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.getWriter().append(results.toString()).flush();

            }
        }


        System.out.println("用");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
