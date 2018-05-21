package com.join.impl;

import com.join.idao.IStadiumDao;
import com.join.util.JDBCConn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StadiumDAOImpl implements IStadiumDao {
    private Connection conn;
    private PreparedStatement statement;
    public StadiumDAOImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public JSONArray getStdaiumByCity(String city) {
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  stadium WHERE city=? ORDER BY grade DESC";
        try{
            statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setString(1,city);
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
                js.put("opentime",rs.getString("opentime"));
                js.put("closetime",rs.getString("closetime"));
                js.put("grade",rs.getDouble("grade"));
                js.put("iconnum",rs.getDouble("iconnum"));
                jar.add(js);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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
    public JSONArray getStdaiumByName(String stadiumname, String city) {
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  stadium WHERE (stadiumname LIKE ? OR stadiumtypeId LIKE ?) AND city=?  ORDER BY grade DESC";
        try{
            statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setString(1,"%"+stadiumname+"%");
            statement.setString(2,"%"+stadiumname+"%");
            statement.setString(3,city);
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
                js.put("opentime",rs.getString("opentime"));
                js.put("closetime",rs.getString("closetime"));
                js.put("grade",rs.getDouble("grade"));
                js.put("iconnum",rs.getDouble("iconnum"));
                jar.add(js);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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
    public JSONArray getPlaceByStadiumId(int stadiumId, String time) {
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  place WHERE StadiumId=? AND PlaceId NOT IN (SELECT PlaceId FROM booking WHERE StadiumId=? AND OrderTime=?)";
        try{
            statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setInt(1,stadiumId);
            statement.setInt(2,stadiumId);
            statement.setString(3,time);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                js =new JSONObject();
                js.put("placeId",rs.getInt("PlaceId"));
                js.put("stadiumId",rs.getInt("StadiumId"));
                js.put("state",rs.getString("State"));
                js.put("material",rs.getString("Material"));
                js.put("placename",rs.getString("PlaceName"));
                jar.add(js);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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
    public int selectPlaceOrdered(int stadiumId, String time) {
        int placeId=-1;
        String sql = "SELECT PlaceId FROM booking WHERE StadiumId=? AND OrderTime=?";
        try{
            statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setInt(1,stadiumId);
            statement.setString(2,time);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                placeId=rs.getInt("PlaceId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return placeId;
    }

    @Override
    public JSONArray getSports() {
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT Sportstypename,Sportsicon FROM stadiumtype ";
        try{
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                js =new JSONObject();
                js.put("sportsname",rs.getString("Sportstypename"));
                js.put("sportsicon",rs.getString("Sportsicon"));
                jar.add(js);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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
    public JSONArray getCity() {
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT cityname FROM city ";
        try{
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                js =new JSONObject();
                js.put("cityname",rs.getString("cityname"));
                jar.add(js);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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
    public JSONArray getIcon(int stadiumId) {
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT icon FROM stadiumicon WHERE stadiumId = ?";
        try{
            statement = conn.prepareStatement(sql);
            statement.setInt(1,stadiumId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                js =new JSONObject();
                js.put("icon",rs.getString("icon"));
                jar.add(js);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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
