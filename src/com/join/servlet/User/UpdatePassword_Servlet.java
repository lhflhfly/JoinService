package com.join.servlet.User;

import com.join.dao.UserDao;
import com.join.factory.Factory;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
/*
修改密码
*/
@WebServlet(name = "UpdatePassword_Servlet", urlPatterns = {"/UpdatePassword_Servlet"})
public class UpdatePassword_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdatePassword_Servlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String req = stringBuilder.toString();
        System.out.println(req);//
        JSONObject js = JSONObject.fromObject(req);
        UserDao userdao = new UserDao();
        String userId = js.getString("userId");
        String password = js.getString("password");
        JSONObject results = new JSONObject();
        boolean update = Factory.getUserDAOIpmlProxy().updatePassword(Integer.parseInt(userId),password);
        if (update) {
            results.put("result", 1);
        }else {
            results.put("result", 0);
        }
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().append(results.toString()).flush();


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
