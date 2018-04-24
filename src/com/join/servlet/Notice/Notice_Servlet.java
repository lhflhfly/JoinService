package com.join.servlet.Notice;

import com.join.bean.User;
import com.join.dao.NoticeDao;
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

@WebServlet(name = "Notice_Servlet",urlPatterns ={"/Notice_Servlet"} )
public class Notice_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public Notice_Servlet(){
        super();
    }

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
        NoticeDao noticeDao = new NoticeDao();
        JSONArray jar = noticeDao.getNotice();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().append(jar.toString()).flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
