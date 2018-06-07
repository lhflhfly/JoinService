package com.join.impl;

import com.join.idao.IEvaluateDao;
import com.join.util.JDBCConn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EvaluateDAOImpl implements IEvaluateDao {
    private PreparedStatement statement;
    private Connection conn;

    public EvaluateDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public JSONArray getNoEvaluateInformationByUserId(String userId) {
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  booklist WHERE UserId=? AND Evaluate=0 AND Used=1";
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
                js.put("mainpicture", rs.getString("mainpicture"));
                js.put("stadiumId", rs.getString("stadiumId"));
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
    public JSONArray getEvaluatedInformationByUserId(String userId) {
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  booklist WHERE UserId=? AND Evaluate=1 AND Used=1";
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
                js.put("mainpicture", rs.getString("mainpicture"));
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
    public boolean evaluateStadium(int stadiumId, double grade, int bookingId, String content,int userId,String evaluatetime) {
        Boolean flag = false;
        String sql = "INSERT INTO evaluation(StadiumId,grade,bookingId,content,userId,evaluatetime) VALUES(?,?,?,?,?,?)";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, stadiumId);
            statement.setDouble(2, grade);
            statement.setInt(3, bookingId);
            statement.setString(4, content);
            statement.setInt(5, userId);
            statement.setString(6, evaluatetime);
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
    public boolean updateBookingEvaluate(int bookingId) {
        Boolean flag = false;
        String sql = "UPDATE booking SET Evaluate=1 WHERE BookingId=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, bookingId);
            statement.executeUpdate();
            flag = true;
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
    public boolean updateBookingAllEvaluate(int bookingId) {
        Boolean flag = false;
        String sql = "UPDATE bookingall SET Evaluate=1 WHERE BookingId=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, bookingId);
            statement.executeUpdate();
            flag = true;
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
    public double getEvaluateRating(int stadiumId) {
        Double grade = null;
        String sql = "SELECT ROUND(AVG(grade),1) FROM evaluation WHERE StadiumId=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, stadiumId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                grade = rs.getDouble("ROUND(AVG(grade),1)");
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
        return grade;
    }

    @Override
    public boolean updateStadiumGrade(int stadiumId, double grade) {
        Boolean flag = false;
        String sql = "UPDATE stadium SET grade=? WHERE stadiumId=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setDouble(1, grade);
            statement.setInt(2, stadiumId);
            statement.executeUpdate();
            flag = true;
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
    public JSONObject evauate(int booking) {
        JSONObject js = new JSONObject();
        String sql = "SELECT * FROM evaluation WHERE bookingId=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, booking);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                js.put("grade", resultSet.getDouble("grade"));
                js.put("content", resultSet.getString("content"));
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
        return js;
    }

    @Override
    public JSONArray getEvaluatedInformationByStadiumId(int stadiumId) {
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM evaluationlist WHERE StadiumId=?";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, stadiumId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                js = new JSONObject();
                js.put("username", rs.getString("username"));
                js.put("proflie", rs.getString("userproflie"));
                js.put("content", rs.getString("content"));
                js.put("grade",rs.getDouble("grade"));
                js.put("placename", rs.getString("placename"));
                js.put("evaluatetime", rs.getString("evaluatetime"));
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
    public JSONArray getAllEvaluation() {
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM evaluationlist ";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                js = new JSONObject();
                js.put("username", rs.getString("username"));
                js.put("proflie", rs.getString("userproflie"));
                js.put("content", rs.getString("content"));
                js.put("grade",rs.getDouble("grade"));
                js.put("placename", rs.getString("placename"));
                js.put("evaluatetime", rs.getString("evaluatetime"));
                js.put("stadiumname", rs.getString("stadiumname"));
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
