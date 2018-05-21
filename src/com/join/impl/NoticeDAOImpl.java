package com.join.impl;

import com.join.idao.INoticeDao;
import com.join.util.JDBCConn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NoticeDAOImpl implements INoticeDao {
    private Connection conn;
    private PreparedStatement statement;

    public NoticeDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public JSONArray getNotice() {
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM notice";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                js = new JSONObject();
                js.put("content", rs.getString("Content"));
                js.put("time", rs.getString("Time"));
                jar.add(js);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return jar;
        }
    }
}
