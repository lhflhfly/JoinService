package com.join.proxy;

import com.join.vo.User;
import com.join.idao.IUserDao;
import com.join.impl.UserDAOImpl;
import com.join.util.JDBCConn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserDAOImplProxy implements IUserDao{
    private JDBCConn jdbcConn;
    private UserDAOImpl userDAOImpl;
    public UserDAOImplProxy(){
        jdbcConn = new JDBCConn();
        userDAOImpl = new UserDAOImpl(jdbcConn.getCon());
    }

    @Override
    public JSONObject getUser(User user) {
        JSONObject js;
        js= userDAOImpl.getUser(user);
        return js;
    }

    @Override
    public JSONObject getUserByUsername(User user) {
        JSONObject js ;
        js = userDAOImpl.getUserByUsername(user);
        return js;
    }

    @Override
    public JSONObject getUserByUserId(int userId) {
        JSONObject js = userDAOImpl.getUserByUserId(userId);
        return js;
    }

    @Override
    public boolean insertUser(User user) {
        boolean flag = userDAOImpl.insertUser(user);
        return flag;
    }

    @Override
    public boolean isRepeatUser(String username) {
        boolean flag = userDAOImpl.isRepeatUser(username);
        return  flag;
    }

    @Override
    public boolean updateUser(User user, int userId) {
        boolean flag = userDAOImpl.updateUser(user,userId);
        return flag;
    }

    @Override
    public JSONArray select_all_user() {
        JSONArray jsonArray = userDAOImpl.select_all_user();
        return jsonArray;
    }

    @Override
    public boolean deleteUser(int userId) {
        boolean flag = userDAOImpl.deleteUser(userId);
        return flag;
    }

    @Override
    public boolean updatePassword(int userId, String password) {
        boolean flag = userDAOImpl.updatePassword(userId,password);
        return  flag;
    }

    @Override
    public boolean updateProflie(int userId, String proflie) {
        boolean flag = userDAOImpl.updateProflie(userId,proflie);
        return  flag;
    }
}
