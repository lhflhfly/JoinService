package com.join.servlet.Collect;

import com.join.dao.CollectDao;
import com.join.dao.StadiumDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "SearchCollectStadium_Servlet",urlPatterns ={"/SearchCollectStadium_Servlet"} )
public class SearchCollectStadium_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SearchCollectStadium_Servlet(){
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
        CollectDao collectDao = new CollectDao();
        String userId = js.getString("userId");
        JSONArray getstadium = collectDao.getCollectStdaiumByUserId(Integer.parseInt(userId));
        System.out.println("用");
        if(getstadium.size()==0){
            String res =null;
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.getWriter().append(res).flush();
        }else {
            String res = getstadium.toString();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.getWriter().append(res).flush();
            System.out.printf(res);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
