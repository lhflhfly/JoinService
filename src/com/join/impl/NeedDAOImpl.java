package com.join.impl;

import com.join.idao.INeedDao;
import com.join.util.JDBCConn;
import com.join.vo.Need;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NeedDAOImpl implements INeedDao {
    private Connection conn;
    private PreparedStatement statement;

    public NeedDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insertNeed(Need need) {
        Boolean flag = false;
        String sql = "INSERT INTO need(StadiumId,UserId,Time,num_people,Remark,sportstypeId,releasetime,tel) VALUES(?,?,?,?,?,?,?)";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, need.getStadiumId());
            statement.setInt(2, need.getUserId());
            statement.setString(3, need.getTime());
            statement.setInt(4, need.getNum());
            statement.setString(5, need.getRemark());
            statement.setString(6, need.getSporttype());
            statement.setString(7, need.getReleasetime());
            statement.setString(8, need.getTel());
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
            return flag;
        }
    }

    @Override
    public JSONArray getNeedInformationByUserId(String userId) {
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  needlist WHERE UserId=? ";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                js = new JSONObject();
                js.put("needId", rs.getInt("NeedId"));
                js.put("userId", rs.getString("UserId"));
                js.put("stadiumId", rs.getString("StadiumId"));
                js.put("time", rs.getString("Time"));
                js.put("username", rs.getString("username"));
                js.put("stadiumname", rs.getString("stadiumname"));
                js.put("num", rs.getInt("num_people"));
                js.put("num_join", rs.getInt("num_join"));
                js.put("remark", rs.getString("Remark"));
                js.put("sportstype", rs.getString("sportstypeId"));
                js.put("userproflie", rs.getString("proflie"));
                js.put("tel", rs.getString("tel"));
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
    public boolean deleteNeedrInformation(int needId) {
        Boolean flag = false;
        String sql = "DELETE  FROM need WHERE NeedId=?";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, needId);
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
    public JSONArray getFindInformationByCity(String city) {
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  needlist WHERE city=?";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setString(1,city);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                js = new JSONObject();
                js.put("needId", rs.getInt("NeedId"));
                js.put("userId", rs.getString("UserId"));
                js.put("stadiumId", rs.getString("StadiumId"));
                js.put("time", rs.getString("Time"));
                js.put("username", rs.getString("username"));
                js.put("stadiumname", rs.getString("stadiumname"));
                js.put("num", rs.getInt("num_people"));
                js.put("num_join", rs.getInt("num_join"));
                js.put("remark", rs.getString("Remark"));
                js.put("sportstype", rs.getString("sportstypeId"));
                js.put("userproflie", rs.getString("proflie"));
                js.put("releasetime", rs.getString("releasetime"));
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
    public boolean updateUserJoinNum(int needId) {
        Boolean flag = false;
        String sql = "UPDATE need SET num_join=num_join+1 WHERE NeedId=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, needId);
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
    public boolean updateUserJoinNumDelete(int needId) {
        Boolean flag = false;
        String sql = "UPDATE need SET num_join=num_join-1 WHERE NeedId=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, needId);
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
    public int getNum(int needId) {
        int num = 0;
        String sql = "SELECT num_people FROM need WHERE NeedId=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, needId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                num = rs.getInt("num_people");
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
        return num;
    }

    @Override
    public int getNumJoin(int needId) {
        int num = 0;
        String sql = "SELECT num_join FROM need WHERE NeedId=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, needId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                num = rs.getInt("num_join");
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
        return num;
    }

    @Override
    public boolean insertJoin(int userId, int needId) {
        Boolean flag = false;
        String sql = "INSERT INTO joining(userId,needId) VALUES(?,?)";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, needId);
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
    public boolean isJoined(int userId, int needId) {
        Boolean flag = false;
        String sql = "SELECT * FROM joining WHERE userId=? AND needId=?";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, needId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                flag = true;
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
    public JSONArray getJoinedNeedByUserId(int userId) {
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT * FROM  needlist WHERE needId IN (SELECT needId FROM joining WHERE userId=?)";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                js = new JSONObject();
                js.put("needId", rs.getInt("NeedId"));
                js.put("userId", rs.getString("UserId"));
                js.put("stadiumId", rs.getString("StadiumId"));
                js.put("time", rs.getString("Time"));
                js.put("username", rs.getString("username"));
                js.put("stadiumname", rs.getString("stadiumname"));
                js.put("num", rs.getInt("num_people"));
                js.put("num_join", rs.getInt("num_join"));
                js.put("remark", rs.getString("Remark"));
                js.put("sportstype", rs.getString("sportstypeId"));
                js.put("userproflie", rs.getString("proflie"));
                js.put("releasetime", rs.getString("releasetime"));
                js.put("tel", rs.getString("tel"));
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
    public boolean deleteJoinedNeed(int userId, int needId) {
        Boolean flag = false;
        String sql = "DELETE  FROM joining WHERE needId=? AND userId=?";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, needId);
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
    public JSONArray getJoinedUserByneedId(int needId) {
        JSONArray jar = new JSONArray();
        JSONObject js;
        String sql = "SELECT username,sex,tel,proflie FROM user WHERE userId IN (SELECT userId FROM joining WHERE needId=?)";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, needId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                js = new JSONObject();
                js.put("username", rs.getString("username"));
                js.put("sex", rs.getString("sex"));
                js.put("tel", rs.getString("tel"));
                js.put("userproflie", rs.getString("proflie"));
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
