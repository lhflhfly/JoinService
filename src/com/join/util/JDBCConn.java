package com.join.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConn {
    /*
    数据库连接方法
     */
    public static Connection getCon(){
        String driver = "com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/join2?characterEncoding=utf8";
        String username="root";
        String password="284691279l";
        Connection conn = null;

        try{
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            System.out.println("数据库驱动加载失败");
            e.printStackTrace();
        }

        try{
            conn = (Connection) DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return conn;
    }
}
