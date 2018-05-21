package com.join.idao;

import com.join.vo.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IUserDao {
    public JSONObject getUser(User user);
    public JSONObject getUserByUsername(User user);
    public JSONObject getUserByUserId(int userId);
    public boolean insertUser(User user);
    public boolean isRepeatUser(String username);
    public boolean updateUser(User user,int userId);
    public JSONArray select_all_user();
    public boolean deleteUser(int userId);
    public boolean updatePassword(int userId,String password);
    public boolean updateProflie(int userId,String proflie);
}
