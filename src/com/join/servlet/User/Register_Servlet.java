package com.join.servlet.User;

import com.join.vo.User;
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
普通用户注册服务
*/
@WebServlet(name = "Register_Servlet", urlPatterns = {"/Register_Servlet"})
public class Register_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //将收到的转化为字符串
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String req = stringBuilder.toString();
        //将JSON格式的字符串转为JSON对象
        JSONObject js = JSONObject.fromObject(req);
        System.out.println(req);
        User user = new User();
        user.setUsername(js.getString("username"));
        user.setPassword(js.getString("password"));
        user.setRealname(js.getString("realname"));
        user.setSex(js.getString("sex"));
        user.setTel(js.getString("tel"));
        JSONObject results = new JSONObject();
        //判断用户名是否已经存在
        if (Factory.getUserDAOIpmlProxy().isRepeatUser(user.getUsername())) {
            //将用户写入数据库
            boolean insert = Factory.getUserDAOIpmlProxy().insertUser(user);
            if (insert) {
                results.put("result", 1);
            }
        } else {
            results.put("result", 0);
        }
        System.out.println("即将发送给客户端的是:" + results.toString());
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        //写入成功返回result=1，失败返回0
        response.getWriter().append(results.toString()).flush();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
