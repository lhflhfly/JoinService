package com.join.servlet.User;

import com.join.vo.User;
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
登录服务
*/
@WebServlet(name = "Login_Servlet",urlPatterns ={"/Login_Servlet"} )
public class Login_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login_Servlet(){
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将收到的转化为字符串
        request.setCharacterEncoding("UTF-8");
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine())!=null){
            stringBuilder.append(line);
        }
        String req = stringBuilder.toString();
        System.out.println(req);
        //将JSON格式的字符串转为JSON对象
        JSONObject js = JSONObject.fromObject(req);
        //闯将user对象将收到的用户名和密码赋值给user
        User user = new User();
        user.setUsername(js.getString("username"));
        user.setPassword(js.getString("password"));
//        JSONObject getuser = userdao.getUser(user);
        //利用工厂类方法到数据库查询用户
        JSONObject getuser = Factory.getUserDAOIpmlProxy().getUser(user);
        System.out.println("用户id");
        System.out.println(getuser.getInt("userId"));
        //查询到用户如果有则userID大于0
        if(getuser.getInt("userId")<1){
            getuser.put("result",0);
        }else{
            getuser.put("result",1);
        }
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        System.out.println(getuser.toString());
        //将查询到的用户信息转化为JSON格式的字符串写入response中，返回客服端
        response.getWriter().append(getuser.toString()).flush();



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
