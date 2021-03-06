package com.join.servlet.Stadium;

import com.join.dao.StadiumDao;
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

@WebServlet(name = "PlaceName_Servlet",urlPatterns ={"/PlaceName_Servlet"} )
public class PlaceName_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PlaceName_Servlet(){
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
        String stadiumnId = js.getString("stadiumId");
        String time = js.getString("timeorder");
        StadiumDao stadiumDao = new StadiumDao();
//        JSONArray getPlace = stadiumDao.getPlaceByStadiumId(Integer.parseInt(stadiumnId),time);
        JSONArray getPlace = Factory.getStadiumDAOIpmlProxy().getPlaceByStadiumId(Integer.parseInt(stadiumnId),time);
        System.out.println("用");
        if(getPlace.size()==0){
            String res =null;
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.getWriter().append(res).flush();
        }else {
            String res = getPlace.toString();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.getWriter().append(res).flush();
            System.out.printf(res);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
