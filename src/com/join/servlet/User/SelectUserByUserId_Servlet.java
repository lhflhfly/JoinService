package com.join.servlet.User;

import com.join.bean.User;
import com.join.dao.UserDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "SelectUserByUserId_Servlet",urlPatterns ={"/SelectUserByUserId_Servlet"} )
public class SelectUserByUserId_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SelectUserByUserId_Servlet(){
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
        UserDao userdao = new UserDao();
        String userId = js.getString("userId");
        JSONObject getuser = userdao.getUserByUserId(Integer.parseInt(userId));
        System.out.println("用户id");
        System.out.println(getuser.getInt("userId"));
        if(getuser.getInt("userId")<1){
            getuser.put("result",0);
        }else{
            getuser.put("result",1);
        }
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        System.out.println(getuser.toString());
        response.getWriter().append(getuser.toString()).flush();



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
