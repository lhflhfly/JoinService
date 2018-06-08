package com.join.impl;

import com.join.idao.IStadiumDao;
import com.join.util.JDBCConn;
import com.join.vo.Stadium;
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
                js.put("stadiumtypename",rs.getString("stadiumtypename"));
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
        String sql = "SELECT * FROM  stadium WHERE (stadiumname LIKE ? OR stadiumtypename LIKE ?) AND city=?  ORDER BY grade DESC";
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
                js.put("stadiumtypename",rs.getString("stadiumtypename"));
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

    @Override
    public int insertStadium(Stadium stadium) {
        int flag = 0;
        String sql = "INSERT INTO user(managername,managerpassword,city,adress,opentime,closetime,stadiumname,stadiumtypename,area,num,indoor,aircondition,stadiumtel,mainpicture) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setString(1, stadium.getManagername());
            statement.setString(2, stadium.getManagerpassword());
            statement.setString(3, stadium.getCity());
            statement.setString(4, stadium.getAdress());
            statement.setString(5, stadium.getOpentime());
            statement.setString(6, stadium.getClosetime());
            statement.setString(7, stadium.getStadiumname());
            statement.setString(8, stadium.getStadiumtype());
            statement.setString(9, stadium.getArea());
            statement.setString(10, stadium.getNum());
            statement.setInt(11, stadium.getIndoor());
            statement.setInt(12, stadium.getAircondition());
            statement.setString(13, stadium.getStadiumtel());
            statement.setString(14, stadium.getMainpicture());
            statement.executeUpdate();
            flag = 1;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = 0;

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
    public boolean isRepeatStadium(String managername) {
        Boolean flag = true;
        String sql = "SELECT * FROM stadium WHERE managername=?";
        try (Connection conn = JDBCConn.getCon()) {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setString(1, managername);
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
    public boolean updateStadium(Stadium stadium) {
        Boolean flag = false;
        String sql = "UPDATE stadium SET managername=?,adress=?,opentime=?,closetime=?,stadiumname=?,stadiumtypename=?,area=?,num=?,indoor=?,aircondition=?,stadiumtel=? WHERE stadiumId=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, stadium.getManagername());
            statement.setString(2, stadium.getAdress());
            statement.setString(3, stadium.getOpentime());
            statement.setString(4, stadium.getClosetime());
            statement.setString(5, stadium.getStadiumname());
            statement.setString(6, stadium.getStadiumtype());
            statement.setString(7, stadium.getArea());
            statement.setString(8, stadium.getNum());
            statement.setInt(9, stadium.getIndoor());
            statement.setInt(10, stadium.getAircondition());
            statement.setString(11, stadium.getStadiumtel());
            statement.setInt(12, stadium.getStadiumId());
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
    public int geticonnum(int stadiumId) {
        int num = 0;
        String sql = "select COUNT(icon) FROM stadiumicon WHERE stadiumId = ?";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, stadiumId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                num = rs.getInt("COUNT(icon)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            num = 0;

        } finally {
            try {
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return num;

    }
}
