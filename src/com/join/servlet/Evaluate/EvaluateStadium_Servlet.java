package com.join.servlet.Evaluate;

import com.join.dao.EvaluateDao;
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

@WebServlet(name = "EvaluateStadium_Servlet",urlPatterns ={"/EvaluateStadium_Servlet"} )
public class EvaluateStadium_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EvaluateStadium_Servlet(){
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
        String stadiumId = js.getString("stadiumId");
        String grade = js.getString("grade");
        System.out.println("------------>"+grade);
        String bookingId = js.getString("bookingId");
        String content = js.getString("content");
        String userId = js.getString("userId");
        String evaluatetime = js.getString("evaluatetime");
        EvaluateDao evaluateDao = new EvaluateDao();
        JSONObject results = new JSONObject();
//        boolean insert = evaluateDao.evaluateStadium(Integer.parseInt(stadiumId),Double.valueOf(grade),Integer.parseInt(bookingId),content);
        boolean insert = Factory.getEvaluateDAOIpmlProxy().evaluateStadium(Integer.parseInt(stadiumId),Double.valueOf(grade),Integer.parseInt(bookingId),content,Integer.parseInt(userId),evaluatetime);
        if(insert){
//            evaluateDao.updateBookingEvaluate(Integer.parseInt(bookingId));
            Factory.getEvaluateDAOIpmlProxy().updateBookingEvaluate(Integer.parseInt(bookingId));
            Factory.getEvaluateDAOIpmlProxy().updateBookingAllEvaluate(Integer.parseInt(bookingId));
            Double f = Factory.getEvaluateDAOIpmlProxy().getEvaluateRating(Integer.parseInt(stadiumId));
//            evaluateDao.getEvaluateRating(Integer.parseInt(stadiumId));
//            evaluateDao.updateStadiumGrade(Integer.parseInt(stadiumId),f);
            Factory.getEvaluateDAOIpmlProxy().updateStadiumGrade(Integer.parseInt(stadiumId),f);
            results.put("result",1);
        }
        System.out.println("即将发送给客户端的是:"+results.toString());
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().append(results.toString()).flush();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
