package com.join.dao;

import com.join.util.JDBCConn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NoticeDao {

    public JSONArray getNotice(){
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM notice";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                js =new JSONObject();
                js.put("content",rs.getString("Content"));
                js.put("time",rs.getString("Time"));
                jar.add(js);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jar;
    }

}
