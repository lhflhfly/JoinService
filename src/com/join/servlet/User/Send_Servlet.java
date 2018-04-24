package com.join.servlet.User;



import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

import static java.lang.System.out;
import static javafx.scene.input.KeyCode.F;


@WebServlet(name = "Send_Servlet",urlPatterns ={"/Send_Servlet"} )
public class Send_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String path;
    public Send_Servlet() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imgEncodedStr = request.getParameter("image");
        String fileName = request.getParameter("filename");
        if(imgEncodedStr != null){
            UploadImage.convertStringtoImage(imgEncodedStr, fileName);
            out.print("Image upload complete, Please check your directory");
        } else{
            out.print("Image is empty");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
