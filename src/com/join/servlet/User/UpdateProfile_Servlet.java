package com.join.servlet.User;

import com.join.dao.UserDao;
import com.join.factory.Factory;
import com.join.util.Utils;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
修改头像
*/
@WebServlet(name = "UpdateProfile_Servlet", urlPatterns = {"/UpdateProfile_Servlet"})
public class UpdateProfile_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateProfile_Servlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("1");
        SmartUpload smartUpload = new SmartUpload();
        System.out.println("2");
        try {
            smartUpload.initialize(this.getServletConfig(), request, response);
            smartUpload.upload();
            File file = smartUpload.getFiles().getFile(0);
            String ext = file.getFileExt();
            String ip = request.getLocalAddr();
            String fileName = Utils.getPicName(ip) + "." + ext;
            file.saveAs("Proflie" + java.io.File.separator + fileName);
            String userId = smartUpload.getRequest().getParameter("userId");
            System.out.println(userId);
            Factory.getUserDAOIpmlProxy().updateProflie(Integer.parseInt(userId), fileName);
            System.out.println("9");
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
