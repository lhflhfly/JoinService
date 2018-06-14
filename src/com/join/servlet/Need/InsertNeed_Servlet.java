package com.join.servlet.Need;

import com.join.factory.Factory;
import com.join.vo.Need;
import com.join.dao.NeedDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "InsertNeed_Servlet",urlPatterns ={"/InsertNeed_Servlet"} )
public class InsertNeed_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine())!=null){
            stringBuilder.append(line);
        }
        String req = stringBuilder.toString();
        JSONObject js = JSONObject.fromObject(req);
        System.out.println(req);
        NeedDao needDao = new NeedDao();
        System.out.println("------------------------>1");
        Need need = new Need();
        System.out.println("------------------------>2");
        need.setStadiumId(Integer.parseInt(js.getString("stadiumId")));
        need.setUserId(Integer.parseInt(js.getString("userId")));
        System.out.println("------------------------>3");
        need.setTime(js.getString("time"));
        System.out.println("------------------------>4");
        need.setNum(Integer.parseInt(js.getString("num")));
        System.out.println("------------------------>5");
        need.setRemark(js.getString("remark"));
        System.out.println("------------------------>6");
        need.setSporttype(js.getString("stadiumtype"));
        System.out.println("------------------------>7");
        need.setReleasetime(js.getString("releasetime"));
        need.setTel(js.getString("tel"));
        JSONObject results = new JSONObject();
//            boolean insert = needDao.insertNeed(need);
        boolean insert = Factory.getNeedDAOIpmlProxy().insertNeed(need);

            if(insert){
                results.put("result",1);
            }
        System.out.println("即将发送给客户端的是:"+results.toString());
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().append(results.toString()).flush();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
