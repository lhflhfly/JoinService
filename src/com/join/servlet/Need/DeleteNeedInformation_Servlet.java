package com.join.servlet.Need;

import com.join.dao.BookDao;
import com.join.dao.NeedDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "DeleteNeedInformation_Servlet",urlPatterns ={"/DeleteNeedInformation_Servlet"} )
public class DeleteNeedInformation_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteNeedInformation_Servlet(){
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
        int needId = Integer.parseInt(js.getString("needId"));
        JSONObject results = new JSONObject();
        if(needDao.deleteNeedrInformation(needId)){
            results.put("result",1);
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.getWriter().append(results.toString()).flush();
        }else{
            results.put("result",0);
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.getWriter().append(results.toString()).flush();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
