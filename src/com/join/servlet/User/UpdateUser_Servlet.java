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

@WebServlet(name = "UpdateUser_Servlet",urlPatterns ={"/UpdateUser_Servlet"} )
public class UpdateUser_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateUser_Servlet(){
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
        User user = new User();
        String userId =js.getString("userId");
        String lastname = js.getString("lastname");
        user.setUsername(js.getString("username"));
        user.setRealname(js.getString("realname"));
        user.setSex(js.getString("sex"));
        user.setTel(js.getString("tel"));
        JSONObject results = new JSONObject();
        if(user.getUsername().equals(lastname)){
            boolean update = userdao.updateUser(user,Integer.parseInt(userId));
            if (update){
                results = userdao.getUserByUsername(user);
                results.put("result",1);
            }else{
                results.put("result",2);
            } }
        else if(userdao.isRepeatUser(user.getUsername())){
            boolean update = userdao.updateUser(user,Integer.parseInt(userId));
            if (update){
                results = userdao.getUserByUsername(user);
                results.put("result",1);
            }else{
                results.put("result",2);
            }
        }else{
            results.put("result",0);
        }

        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().append(results.toString()).flush();



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
