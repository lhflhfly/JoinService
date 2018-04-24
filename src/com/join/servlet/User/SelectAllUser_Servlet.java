package com.join.servlet.User;

import com.join.bean.User;
import com.join.dao.UserDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "SelectAllUser_Servlet",urlPatterns ={"/SelectAllUser_Servlet"} )
public class SelectAllUser_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SelectAllUser_Servlet(){
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
        UserDao userDao = new UserDao();
        JSONArray jar = new JSONArray();
        jar = userDao.select_all_user();
        if(jar.isEmpty()){
            System.out.println("是空的");
        }
        String res = jar.toString();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().append(jar.toString()).flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
