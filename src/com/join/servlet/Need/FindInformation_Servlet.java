package com.join.servlet.Need;

import com.join.dao.NeedDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "FindInformation_Servlet", urlPatterns = {"/FindInformation_Servlet"})
public class FindInformation_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FindInformation_Servlet() {
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
        int method = js.getInt("method");
        String userId = js.getString("userId");
        NeedDao needDao = new NeedDao();
        if (method == 1) {
            JSONArray getfindinformation = needDao.getFindInformationByUserId();
            System.out.println("用");
            if (getfindinformation.size() == 0) {
                String res = null;
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.getWriter().append(res).flush();
            } else {
                String res = getfindinformation.toString();
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.getWriter().append(res).flush();
                System.out.printf(res);
            }
        } else if (method == 2) {
            JSONArray getfindinformation = needDao.getJoinedNeedByUserId(Integer.parseInt(userId));
            if (getfindinformation.size() == 0) {
                String res = null;
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.getWriter().append(res).flush();
            } else {
                String res = getfindinformation.toString();
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.getWriter().append(res).flush();
                System.out.printf(res);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
