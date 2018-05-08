package com.join.dao;

import com.join.bean.Book;
import com.join.bean.User;
import com.join.util.JDBCConn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDao {

    public boolean orderStadium(Book book){
        Boolean flag=false;
        String sql = "INSERT INTO booking(UserId,StadiumId,PlaceId,Time,OrderTime,Tel) VALUES(?,?,?,?,?,?)";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setInt(1,book.getUserId());
            statement.setInt(2,book.getStadiumId());
            statement.setInt(3,book.getPlaceId());
            statement.setString(4,book.getTime());
            statement.setString(5,book.getTime_order());
            statement.setString(6,book.getTel());
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public JSONArray getOrderInformationByUserId(String userId){
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  booklist WHERE UserId=? AND Used=0";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setString(1,userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                js =new JSONObject();
                js.put("bookingId",rs.getInt("BookingId"));
                js.put("stadiumname",rs.getString("stadiumname"));
                js.put("placename",rs.getString("Placename"));
                js.put("time",rs.getString("Time"));
                js.put("time_order",rs.getString("OrderTime"));
                jar.add(js);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jar;
    }

    public JSONArray getOrderInformation_usedByUserId(String userId){
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  booklist WHERE UserId=? AND Used=1";
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
                jar.add(js);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jar;
    }

    public boolean deleteOrderInformation(int bookingId){
        Boolean flag=false;
        String sql = "DELETE  FROM booking WHERE BookingId=?";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setInt(1,bookingId);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }




}
