package com.join.proxy;

import com.join.idao.INeedDao;
import com.join.impl.NeedDAOImpl;
import com.join.util.JDBCConn;
import com.join.vo.Need;
import net.sf.json.JSONArray;

public class NeedDAOImplProxy implements INeedDao {
    private JDBCConn jdbcConn;
    private NeedDAOImpl needDAOImpl;
    public NeedDAOImplProxy(){
        jdbcConn = new JDBCConn();
        needDAOImpl = new NeedDAOImpl(jdbcConn.getCon());
    }
    @Override
    public boolean insertNeed(Need need) {
        boolean flag = needDAOImpl.insertNeed(need);
        return flag;
    }

    @Override
    public JSONArray getNeedInformationByUserId(String userId) {
        JSONArray jsonArray = needDAOImpl.getNeedInformationByUserId(userId);
        return jsonArray;
    }

    @Override
    public boolean deleteNeedrInformation(int needId) {
        boolean flag = needDAOImpl.deleteNeedrInformation(needId);
        return flag;
    }

    @Override
    public JSONArray getFindInformationByUserId() {
        JSONArray jsonArray = needDAOImpl.getFindInformationByUserId();
        return  jsonArray;
    }

    @Override
    public boolean updateUserJoinNum(int needId) {
        boolean flag = needDAOImpl.updateUserJoinNum(needId);
        return  flag;
    }

    @Override
    public boolean updateUserJoinNumDelete(int needId) {
        boolean flag = needDAOImpl.updateUserJoinNumDelete(needId);
        return flag;
    }

    @Override
    public int getNum(int needId) {
        int i = needDAOImpl.getNum(needId);
        return i;
    }

    @Override
    public int getNumJoin(int needId) {
        int i = needDAOImpl.getNumJoin(needId);
        return i;
    }

    @Override
    public boolean insertJoin(int userId, int needId) {
        boolean flag = needDAOImpl.insertJoin(userId,needId);
        return  flag;
    }

    @Override
    public boolean isJoined(int userId, int needId) {
        boolean flag = needDAOImpl.isJoined(userId,needId);
        return  flag;
    }

    @Override
    public JSONArray getJoinedNeedByUserId(int userId) {
        JSONArray jsonArray = needDAOImpl.getJoinedNeedByUserId(userId);
        return  jsonArray;
    }

    @Override
    public boolean deleteJoinedNeed(int userId, int needId) {
        boolean flag = needDAOImpl.deleteJoinedNeed(userId,needId);
        return  flag;
    }

    @Override
    public JSONArray getJoinedUserByneedId(int needId) {
        JSONArray jsonArray = needDAOImpl.getJoinedUserByneedId(needId);
        return  jsonArray;
    }
}
