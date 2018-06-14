package com.join.impl;

import com.join.vo.User;
import com.join.idao.IUserDao;
import com.join.util.JDBCConn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements IUserDao {
    private Connection conn;
    private PreparedStatement statement;

    public UserDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public JSONObject getUser(User user) {
        JSONObject js = new JSONObject();
        String sql = "SELECT * FROM user WHERE username=? AND password=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                js.put("userId", resultSet.getInt("userId"));
                js.put("username", resultSet.getString("username"));
                js.put("password", resultSet.getString("password"));
                js.put("realname", resultSet.getString("realname"));
                js.put("sex", resultSet.getString("sex"));
                js.put("tel", resultSet.getString("tel"));
                js.put("myRight", resultSet.getString("myRight"));
                js.put("proflie", resultSet.getString("proflie"));
            } else {
                js.put("userId", 0);
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
    public JSONObject getUserByUsername(User user) {
        JSONObject js = new JSONObject();
        String sql = "SELECT * FROM user WHERE username=?";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                js.put("userId", resultSet.getInt("userId"));
                js.put("username", resultSet.getString("username"));
                js.put("password", resultSet.getString("password"));
                js.put("realname", resultSet.getString("realname"));
                js.put("sex", resultSet.getString("sex"));
                js.put("tel", resultSet.getString("tel"));
                js.put("myRight", resultSet.getString("myRight"));
                js.put("proflie", resultSet.getString("proflie"));
            } else {
                js.put("userId", 0);
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
    public JSONObject getUserByUserId(int userId) {
        JSONObject js = new JSONObject();
        String sql = "SELECT * FROM user WHERE userId=?";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                js.put("userId", resultSet.getInt("userId"));
                js.put("username", resultSet.getString("username"));
                js.put("password", resultSet.getString("password"));
                js.put("realname", resultSet.getString("realname"));
                js.put("sex", resultSet.getString("sex"));
                js.put("tel", resultSet.getString("tel"));
                js.put("myRight", resultSet.getString("myRight"));
                js.put("proflie", resultSet.getString("proflie"));
            } else {
                js.put("userId", 0);
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
    public boolean insertUser(User user) {
        Boolean flag = false;
        String sql = "INSERT INTO user(username,password,realname,sex,tel,myRight) VALUES(?,?,?,?,?,?)";
        try {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRealname());
            statement.setString(4, user.getSex());
            statement.setString(5, user.getTel());
            statement.setString(6, "普通用户");
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
    public boolean isRepeatUser(String username) {
        Boolean flag = true;
        String sql = "SELECT * FROM user WHERE username=?";
        try (Connection conn = JDBCConn.getCon()) {
            statement = (PreparedStatement) conn.prepareStatement(sql);
            statement.setString(1, username);
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
    public boolean updateUser(User user, int userId) {
        Boolean flag = false;
        String sql = "UPDATE user SET username=?,realname=?,sex=?,tel=? WHERE userId=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getRealname());
            statement.setString(3, user.getSex());
            statement.setString(4, user.getTel());
            statement.setInt(5, userId);
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
    public JSONArray select_all_user() {
        JSONArray jar = new JSONArray();
        JSONObject js = new JSONObject();
        String sql = "SELECT * from user";
        try {
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                js.put("userId", rs.getInt("userId"));
                js.put("username", rs.getString("username"));
                js.put("password", rs.getString("password"));
                js.put("realname", rs.getString("realname"));
                js.put("sex", rs.getString("sex"));
                js.put("tel", rs.getString("tel"));
                js.put("myright", rs.getString("myright"));
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
    public boolean deleteUser(int userId) {
        boolean flag = false;
        String sql = "DELETE from user WHERE userId =? ";
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.executeUpdate();
            if (statement.executeUpdate() == 1) {
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
    public boolean updatePassword(int userId, String password) {
        Boolean flag = false;
        String sql = "UPDATE user SET password=? WHERE userId=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, password);
            statement.setInt(2, userId);
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
    public boolean updateProflie(int userId, String proflie) {
        Boolean flag = false;
        String sql = "UPDATE user SET proflie=? WHERE userId=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, proflie);
            statement.setInt(2, userId);
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
    public JSONObject getPassword(User user) {
        JSONObject js = new JSONObject();
        String sql = "SELECT userId,password FROM user WHERE username=? AND tel=? AND realname=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getTel());
            statement.setString(3, user.getRealname());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                js.put("userId", resultSet.getInt("userId"));
                js.put("password", resultSet.getString("password"));
            } else {
                js.put("userId", 0);
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
}
