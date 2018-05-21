package com.join.impl;

import com.join.idao.IBookDao;
import com.join.util.JDBCConn;
import com.join.vo.Book;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAOImpl implements IBookDao {
    private Connection conn;
    private PreparedStatement statement;

    public BookDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean orderStadium(Book book) {
        Boolean flag = false;
        String sql = "INSERT INTO booking(UserId,StadiumId,PlaceId,Time,OrderTime,Tel) VALUES(?,?,?,?,?,?)";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, book.getUserId());
            statement.setInt(2, book.getStadiumId());
            statement.setInt(3, book.getPlaceId());
            statement.setString(4, book.getTime());
            statement.setString(5, book.getTime_order());
            statement.setString(6, book.getTel());
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
    public JSONArray getOrderInformationByUserId(String userId) {
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  booklist WHERE UserId=? AND Used=0";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                js = new JSONObject();
                js.put("bookingId", rs.getInt("BookingId"));
                js.put("stadiumname", rs.getString("stadiumname"));
                js.put("placename", rs.getString("Placename"));
                js.put("time", rs.getString("Time"));
                js.put("time_order", rs.getString("OrderTime"));
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

    @Override
    public JSONArray getOrderInformation_usedByUserId(String userId) {
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  booklist WHERE UserId=? AND Used=1";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                js = new JSONObject();
                js.put("bookingId", rs.getInt("BookingId"));
                js.put("stadiumname", rs.getString("stadiumname"));
                js.put("placename", rs.getString("Placename"));
                js.put("time", rs.getString("Time"));
                js.put("time_order", rs.getString("OrderTime"));
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

    @Override
    public boolean deleteOrderInformation(int bookingId) {
        Boolean flag = false;
        String sql = "DELETE  FROM booking WHERE BookingId=?";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, bookingId);
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
}
