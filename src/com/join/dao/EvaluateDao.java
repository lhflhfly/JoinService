package com.join.dao;

import com.join.util.JDBCConn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EvaluateDao {
    public JSONArray getNoEvaluateInformationByUserId(String userId){
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  booklist WHERE UserId=? AND Evaluate=0 AND Used=1";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setString(1,userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                js =new JSONObject();
                js.put("bookingId",rs.getInt("BookingId"));
                js.put("stadiumname",rs.getString("stadiumname"));
                js.put("placename",rs.getString("PlaceId"));
                js.put("time",rs.getString("Time"));
                js.put("time_order",rs.getString("OrderTime"));
                js.put("mainpicture",rs.getString("mainpicture"));
                js.put("stadiumId",rs.getString("stadiumId"));
                jar.add(js);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jar;
    }

    public JSONArray getEvaluatedInformationByUserId(String userId){
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  booklist WHERE UserId=? AND Evaluate=1 AND Used=1";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setString(1,userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                js =new JSONObject();
                js.put("bookingId",rs.getInt("BookingId"));
                js.put("stadiumname",rs.getString("stadiumname"));
                js.put("placename",rs.getString("PlaceId"));
                js.put("time",rs.getString("Time"));
                js.put("time_order",rs.getString("OrderTime"));
                js.put("mainpicture",rs.getString("mainpicture"));
                jar.add(js);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jar;
    }

    public boolean evaluateStadium(int stadiumId,double grade,int bookingId,String content){
        Boolean flag=false;
        String sql = "INSERT INTO evaluation(StadiumId,grade,bookingId,content) VALUES(?,?,?,?)";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setInt(1,stadiumId);
            statement.setDouble(2,grade);
            statement.setInt(3,bookingId);
            statement.setString(4,content);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public boolean updateBookingEvaluate(int bookingId){
        Boolean flag = false;
        String sql = "UPDATE booking SET Evaluate=1 WHERE BookingId=?";
        try(Connection conn = JDBCConn.getCon()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,bookingId);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;


    }

    public double getEvaluateRating(int stadiumId){
        Double grade=null;
        String sql = "SELECT ROUND(AVG(grade),1) FROM evaluation WHERE StadiumId=?";
        try(Connection conn = JDBCConn.getCon()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,stadiumId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                grade = rs.getDouble("ROUND(AVG(grade),1)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grade;
    }

    public boolean updateStadiumGrade(int stadiumId,double grade){
        Boolean flag = false;
        String sql = "UPDATE stadium SET grade=? WHERE stadiumId=?";
        try(Connection conn = JDBCConn.getCon()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDouble(1,grade);
            statement.setInt(2,stadiumId);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;


    }

    public JSONObject Evauate(int booking){
        JSONObject js = new JSONObject();
        String sql = "SELECT * FROM evaluation WHERE bookingId=?";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,booking);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                js.put("grade",resultSet.getDouble("grade"));
                js.put("content",resultSet.getString("content"));
            }
        } catch (SQLException e) {

            e.printStackTrace();

        }
        return js;
    }








}
