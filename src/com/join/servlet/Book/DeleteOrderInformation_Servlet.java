package com.join.servlet.Book;


import com.join.factory.Factory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "DeleteOrderInformation_Servlet",urlPatterns ={"/DeleteOrderInformation_Servlet"} )
public class DeleteOrderInformation_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteOrderInformation_Servlet(){
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
        System.out.println(req);
        JSONObject js = JSONObject.fromObject(req);
        int bookingId = Integer.parseInt(js.getString("bookingId"));
        JSONObject results = new JSONObject();
        if(Factory.getBookDAOIpmlProxy().deleteOrderInformation(bookingId)){
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
        doPost(request,response);
    }
}
