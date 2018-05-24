package com.join.impl;

import com.join.idao.ICollectDao;
import com.join.util.JDBCConn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectDAOImpl implements ICollectDao {
    private Connection conn;
    private PreparedStatement statement;

    public CollectDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insertCollect(int stadiumId, int userId) {
        Boolean flag = false;
        String sql = "INSERT INTO collect(stadiumId,userId) VALUES(?,?)";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, stadiumId);
            statement.setInt(2, userId);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        } finally {
            try {
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public boolean deleteCollection(int stadiumId, int userId) {
        Boolean flag = false;
        String sql = "DELETE  FROM collect WHERE stadiumId=? AND userId=?";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, stadiumId);
            statement.setInt(2, userId);

            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        } finally {
            try {
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public boolean isCollected(int stadiumId, int userId) {
        Boolean flag = true;
        String sql = "SELECT * FROM collect WHERE userId=? AND stadiumId=?";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, stadiumId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                flag = false;
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
        }
        return flag;
    }

    @Override
    public JSONArray getCollectStdaiumByUserId(int userId) {
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  stadium WHERE stadiumId IN (SELECT stadiumId FROM collect WHERE userId=?)";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                js = new JSONObject();
                js.put("stadiumId", rs.getInt("stadiumId"));
                js.put("stadiumname", rs.getString("stadiumname"));
                js.put("stadiumtypename", rs.getString("stadiumtypename"));
                js.put("area", rs.getString("area"));
                js.put("num", rs.getString("num"));
                js.put("indoor", rs.getInt("indoor"));
                js.put("aircondition", rs.getInt("aircondition"));
                js.put("city", rs.getString("city"));
                js.put("mainpicture", rs.getString("mainpicture"));
                js.put("adress", rs.getString("adress"));
                js.put("opentime", rs.getString("opentime"));
                js.put("closetime", rs.getString("closetime"));
                js.put("grade", rs.getDouble("grade"));
                js.put("iconnum", rs.getInt("iconnum"));
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
        }
        return jar;
    }
}
