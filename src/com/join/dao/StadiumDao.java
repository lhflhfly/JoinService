package com.join.dao;

import com.join.util.JDBCConn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StadiumDao {

    public JSONArray getStdaiumByCity(String city){
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  stadium WHERE city=?";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
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
                js.put("grade",rs.getDouble("grade"));
                jar.add(js);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jar;
    }

    public JSONArray getStdaiumByName(String stadiumname,String city){
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  stadium WHERE (stadiumname LIKE ? OR stadiumtypeId LIKE ?) AND city=? ";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
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
                js.put("grade",rs.getDouble("grade"));
                jar.add(js);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jar;
    }

    public JSONArray getPlaceByStadiumId(int stadiumId){
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  place WHERE StadiumId=? ";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setInt(1,stadiumId);
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
        }
        return jar;
    }





}
