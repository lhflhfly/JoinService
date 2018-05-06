package com.join.servlet.Evaluate;

import com.join.dao.EvaluateDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "SelectEvaluation_Servlet",urlPatterns ={"/SelectEvaluation_Servlet"} )
public class SelectEvaluation_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SelectEvaluation_Servlet(){
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
        EvaluateDao evaluateDao = new EvaluateDao();
        String bookingId = js.getString("bookingId");
        JSONObject getevaluation = evaluateDao.Evauate(Integer.parseInt(bookingId));
        if(getevaluation.isNullObject()){
            getevaluation.put("result",0);
        }else{
            getevaluation.put("result",1);
        }
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        System.out.println(getevaluation.toString());
        response.getWriter().append(getevaluation.toString()).flush();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
