package com.join.proxy;

import com.join.dao.StadiumDao;
import com.join.idao.IStadiumDao;
import com.join.impl.StadiumDAOImpl;
import com.join.util.JDBCConn;
import com.join.vo.Stadium;
import net.sf.json.JSONArray;

public class StadiumDAOImplProxy implements IStadiumDao {
    private JDBCConn jdbcConn;
    private StadiumDAOImpl stadiumDAOImpl;
    public StadiumDAOImplProxy(){
        jdbcConn = new JDBCConn();
        stadiumDAOImpl = new StadiumDAOImpl(jdbcConn.getCon());
    }
    @Override
    public JSONArray getStdaiumByCity(String city) {
        JSONArray jsonArray = stadiumDAOImpl.getStdaiumByCity(city);
        return jsonArray;
    }

    @Override
    public JSONArray getStdaiumByName(String stadiumname, String city) {
        JSONArray jsonArray = stadiumDAOImpl.getStdaiumByName(stadiumname,city);
        return jsonArray;
    }

    @Override
    public JSONArray getPlaceByStadiumId(int stadiumId, String time) {
        JSONArray jsonArray = stadiumDAOImpl.getPlaceByStadiumId(stadiumId,time);
        return jsonArray;
    }

    @Override
    public int selectPlaceOrdered(int stadiumId, String time) {
        int i = stadiumDAOImpl.selectPlaceOrdered(stadiumId,time);
        return i;
    }

    @Override
    public JSONArray getSports() {
        JSONArray jsonArray = stadiumDAOImpl.getSports();
        return jsonArray;
    }

    @Override
    public JSONArray getCity() {
        JSONArray jsonArray = stadiumDAOImpl.getCity();
        return jsonArray;
    }

    @Override
    public JSONArray getIcon(int stadiumId) {
        JSONArray jsonArray = stadiumDAOImpl.getIcon(stadiumId);
        return jsonArray;
    }

    @Override
    public int insertStadium(Stadium stadium) {
        int flag = stadiumDAOImpl.insertStadium(stadium);
        return flag;
    }

    @Override
    public boolean isRepeatStadium(String managername) {
        boolean flag = stadiumDAOImpl.isRepeatStadium(managername);
        return flag;
    }

    @Override
    public boolean updateStadium(Stadium stadium) {
        boolean flag = stadiumDAOImpl.updateStadium(stadium);
        return flag;
    }

    @Override
    public int geticonnum(int stadiumId) {
        int num = stadiumDAOImpl.geticonnum(stadiumId);
        return num;
    }
}
