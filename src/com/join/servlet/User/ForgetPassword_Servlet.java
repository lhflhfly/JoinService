package com.join.servlet.User;

import com.join.factory.Factory;
import com.join.vo.User;
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
@WebServlet(name = "ForgetPassword_Servlet",urlPatterns ={"/ForgetPassword_Servlet"} )
public class ForgetPassword_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ForgetPassword_Servlet(){
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
        user.setTel(js.getString("tel"));
        user.setRealname(js.getString("realname"));
//        JSONObject getuser = userdao.getUser(user);
        //利用工厂类方法到数据库查询用户
        JSONObject getpassword = Factory.getUserDAOIpmlProxy().getPassword(user);
        System.out.println("用户id");
        //查询到用户如果有则userID大于0
        if(getpassword.getInt("userId")<1){
            getpassword.put("result",0);
        }else{
            getpassword.put("result",1);
        }
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        System.out.println(getpassword.toString());
        //将查询到的用户信息转化为JSON格式的字符串写入response中，返回客服端
        response.getWriter().append(getpassword.toString()).flush();



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
