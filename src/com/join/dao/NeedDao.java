package com.join.dao;

import com.join.bean.Book;
import com.join.bean.Need;
import com.join.bean.User;
import com.join.util.JDBCConn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NeedDao {

    public boolean insertNeed(Need need){
        Boolean flag=false;
        String sql = "INSERT INTO need(StadiumId,UserId,Time,num_people,Remark,sportstypeId) VALUES(?,?,?,?,?,?)";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setInt(1,need.getStadiumId());
            statement.setInt(2,need.getUserId());
            statement.setString(3,need.getTime());
            statement.setInt(4,need.getNum());
            statement.setString(5,need.getRemark());
            statement.setString(6,need.getSporttype());
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public JSONArray getNeedInformationByUserId(String userId){
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  needlist WHERE UserId=? ";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setString(1,userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                js =new JSONObject();
                js.put("needId",rs.getInt("NeedId"));
                js.put("userId",rs.getString("UserId"));
                js.put("stadiumId",rs.getString("StadiumId"));
                js.put("time",rs.getString("Time"));
                js.put("username",rs.getString("username"));
                js.put("stadiumname",rs.getString("stadiumname"));
                js.put("num",rs.getInt("num_people"));
                js.put("num_join",rs.getInt("num_join"));
                js.put("remark",rs.getString("Remark"));
                js.put("sportstype",rs.getString("sportstypeId"));
                jar.add(js);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jar;
    }

    public boolean deleteNeedrInformation(int needId){
        Boolean flag=false;
        String sql = "DELETE  FROM need WHERE NeedId=?";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setInt(1,needId);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public JSONArray getFindInformationByUserId(){
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  needlist ";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                js =new JSONObject();
                js.put("needId",rs.getInt("NeedId"));
                js.put("userId",rs.getString("UserId"));
                js.put("stadiumId",rs.getString("StadiumId"));
                js.put("time",rs.getString("Time"));
                js.put("username",rs.getString("username"));
                js.put("stadiumname",rs.getString("stadiumname"));
                js.put("num",rs.getInt("num_people"));
                js.put("num_join",rs.getInt("num_join"));
                js.put("remark",rs.getString("Remark"));
                js.put("sportstype",rs.getString("sportstypeId"));
                jar.add(js);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jar;
    }

    public boolean updateUserJoinNum(int needId){
        Boolean flag = false;
        String sql = "UPDATE need SET num_join=num_join+1 WHERE NeedId=?";
        try(Connection conn = JDBCConn.getCon()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,needId);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean updateUserJoinNumDelete(int needId){
        Boolean flag = false;
        String sql = "UPDATE need SET num_join=num_join-1 WHERE NeedId=?";
        try(Connection conn = JDBCConn.getCon()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,needId);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public int getNum(int needId){
        int num=0;
        String sql = "SELECT num_people FROM need WHERE NeedId=?";
        try(Connection conn = JDBCConn.getCon()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,needId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                num = rs.getInt("num_people");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    public int getNumJoin(int needId){
        int num=0;
        String sql = "SELECT num_join FROM need WHERE NeedId=?";
        try(Connection conn = JDBCConn.getCon()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,needId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                num = rs.getInt("num_join");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    public boolean insertJoin(int userId,int needId){
        Boolean flag=false;
        String sql = "INSERT INTO joining(userId,needId) VALUES(?,?)";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setInt(1,userId);
            statement.setInt(2,needId);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;

        }
        return flag;
    }

    public boolean isJoined(int userId,int needId){
        Boolean flag=false;
        String sql = "SELECT * FROM joining WHERE userId=? AND needId=?";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setInt(1,userId);
            statement.setInt(2,needId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public JSONArray getJoinedNeedByUserId(int userId){
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  needlist WHERE needId IN (SELECT needId FROM joining WHERE userId=?)";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setInt(1,userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                js =new JSONObject();
                js.put("needId",rs.getInt("NeedId"));
                js.put("userId",rs.getString("UserId"));
                js.put("stadiumId",rs.getString("StadiumId"));
                js.put("time",rs.getString("Time"));
                js.put("username",rs.getString("username"));
                js.put("stadiumname",rs.getString("stadiumname"));
                js.put("num",rs.getInt("num_people"));
                js.put("num_join",rs.getInt("num_join"));
                js.put("remark",rs.getString("Remark"));
                js.put("sportstype",rs.getString("sportstypeId"));
                jar.add(js);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jar;
    }

    public boolean deleteJoinedNeed(int userId,int needId){
        Boolean flag=false;
        String sql = "DELETE  FROM joining WHERE needId=? AND userId=?";
        try(Connection conn = JDBCConn.getCon()){
            PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
            statement.setInt(1,needId);
            statement.setInt(2,userId);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }


}
