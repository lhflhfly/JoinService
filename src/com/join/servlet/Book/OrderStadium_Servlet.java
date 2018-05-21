package com.join.servlet.Book;

import com.join.factory.Factory;
import com.join.vo.Book;
import com.join.dao.BookDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "OrderStadium_Servlet",urlPatterns ={"/OrderStadium_Servlet"} )
public class OrderStadium_Servlet extends HttpServlet {
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
        BookDao bookDao = new BookDao();
        Book book = new Book();
        book.setUserId(Integer.parseInt(js.getString("userId")));
        book.setStadiumId(Integer.parseInt(js.getString("stadiumId")));
        book.setTime(js.getString("time"));
        book.setTime_order(js.getString("time_order"));
        book.setPlaceId(Integer.parseInt(js.getString("placeId")));
        book.setTel(js.getString("tel"));
        JSONObject results = new JSONObject();
//            boolean insert = bookDao.orderStadium(book);
        boolean insert = Factory.getBookDAOIpmlProxy().orderStadium(book);
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
