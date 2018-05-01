package com.join.servlet.User;

import com.join.bean.User;
import com.join.dao.UserDao;
import com.join.util.Utils;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "UpdateProfile_Servlet",urlPatterns ={"/UpdateProfile_Servlet"} )
public class UpdateProfile_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateProfile_Servlet(){
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("1");
        SmartUpload smartUpload = new SmartUpload();
        System.out.println("2");
        try {
            smartUpload.initialize(this.getServletConfig(),request,response);
            System.out.println("3");
            smartUpload.upload();
            System.out.println("4");
            File file = smartUpload.getFiles().getFile(0);
            System.out.println("5");
            String ext = file.getFileExt();
            System.out.println("6");
            String ip = request.getLocalAddr();
            System.out.println("7");
            String fileName = Utils.getPicName(ip)+"."+ext;
            System.out.println("8");
            file.saveAs("Proflie"+java.io.File.separator+fileName);
            UserDao userDao = new UserDao();
            String userId = smartUpload.getRequest().getParameter("userId");
            System.out.println(userId);
            userDao.updateProflie(Integer.parseInt(userId),fileName);
            System.out.println("9");
        }catch (SmartUploadException e) {
            e.printStackTrace();
        }

//        BufferedReader reader = request.getReader();
//        StringBuilder stringBuilder = new StringBuilder();
//        String line = null;
//        while ((line = reader.readLine())!=null){
//            stringBuilder.append(line);
//        }
//        String req = stringBuilder.toString();
//        System.out.println(req);//
//        JSONObject js = JSONObject.fromObject(req);
//        UserDao userdao = new UserDao();
//        User user = new User();
//        user.setUsername(js.getString("username"));
//        user.setPassword(js.getString("password"));
//        JSONObject getuser = userdao.getUser(user);
//        System.out.println("用户id");
//        System.out.println(getuser.getInt("userId"));
//        if(getuser.getInt("userId")<1){
//            getuser.put("result",0);
//        }else{
//            getuser.put("result",1);
//        }
//        response.setHeader("Content-type", "text/html;charset=UTF-8");
//        System.out.println(getuser.toString());
//        response.getWriter().append(getuser.toString()).flush();
//


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
