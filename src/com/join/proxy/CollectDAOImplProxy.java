package com.join.proxy;

import com.join.idao.ICollectDao;

import com.join.impl.CollectDAOImpl;
import com.join.util.JDBCConn;
import net.sf.json.JSONArray;

public class CollectDAOImplProxy implements ICollectDao{
    private JDBCConn jdbcConn;
    private CollectDAOImpl collectDAOImpl;
    public CollectDAOImplProxy(){
        jdbcConn = new JDBCConn();
        collectDAOImpl = new CollectDAOImpl(jdbcConn.getCon());
    }
    @Override
    public boolean insertCollect(int stadiumId, int userId) {
        boolean flag = collectDAOImpl.insertCollect(stadiumId,userId);
        return flag;
    }

    @Override
    public boolean deleteCollection(int stadiumId, int userId) {
        boolean flag = collectDAOImpl.deleteCollection(stadiumId,userId);
        return flag;
    }

    @Override
    public boolean isCollected(int stadiumId, int userId) {
        boolean flag = collectDAOImpl.isCollected(stadiumId,userId);
        return  flag;
    }

    @Override
    public JSONArray getCollectStdaiumByUserId(int userId) {
        JSONArray jsonArray = collectDAOImpl.getCollectStdaiumByUserId(userId);
        return jsonArray;
    }
}
