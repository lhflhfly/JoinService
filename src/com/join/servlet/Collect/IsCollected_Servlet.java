package com.join.servlet.Collect;

import com.join.dao.CollectDao;
import com.join.factory.Factory;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "IsCollected_Servlet", urlPatterns = {"/IsCollected_Servlet"})
public class IsCollected_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String req = stringBuilder.toString();
        JSONObject js = JSONObject.fromObject(req);
        System.out.println(req);
        String stadiumId = js.getString("stadiumId");
        String userId = js.getString("userId");
        CollectDao collectDao = new CollectDao();
        JSONObject results = new JSONObject();
//        boolean iscollected = collectDao.isCollected(Integer.parseInt(stadiumId), Integer.parseInt(userId));
        boolean iscollected = Factory.getCollectDAOIpmlProxy().isCollected(Integer.parseInt(stadiumId), Integer.parseInt(userId));
        if (iscollected) {
            results.put("result", 1);
        }else{
            results.put("result", 0);
        }
        System.out.println("即将发送给客户端的是:" + results.toString());
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().append(results.toString()).flush();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
