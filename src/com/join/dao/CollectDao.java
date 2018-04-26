package com.join.dao;

import com.join.util.JDBCConn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectDao {

    public boolean insertCollect(int stadiumId, int userId) {
        Boolean flag = false;
        String sql = "INSERT INTO collect(stadiumId,userId) VALUES(?,?)";
        try (Connection conn = JDBCConn.getCon()) {
            PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, stadiumId);
            statement.setInt(2, userId);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public boolean deleteCollection(int stadiumId, int userId) {
        Boolean flag = false;
        String sql = "DELETE  FROM collect WHERE stadiumId=? AND userId=?";
        try (Connection conn = JDBCConn.getCon()) {
            PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, stadiumId);
            statement.setInt(2, userId);

            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public boolean isCollected(int stadiumId,int userId){
        Boolean flag=true;
        String sql = "SELECT * FROM collect WHERE userId=? AND stadiumId=?";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setInt(1,userId);
            statement.setInt(2,stadiumId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                flag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public JSONArray getCollectStdaiumByUserId(int userId){
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  stadium WHERE stadiumId IN (SELECT stadiumId FROM collect WHERE userId=?)";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setInt(1,userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                js =new JSONObject();
                js.put("stadiumId",rs.getInt("stadiumId"));
                js.put("stadiumname",rs.getString("stadiumname"));
                js.put("stadiumtypeId",rs.getString("stadiumtypeId"));
                js.put("area",rs.getString("area"));
                js.put("num",rs.getString("num"));
                js.put("indoor",rs.getInt("indoor"));
                js.put("aircondition",rs.getInt("aircondition"));
                js.put("city",rs.getString("city"));
                js.put("mainpicture",rs.getString("mainpicture"));
                js.put("adress",rs.getString("adress"));
                jar.add(js);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jar;
    }
}
