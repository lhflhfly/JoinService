package com.join.proxy;

import com.join.idao.IEvaluateDao;
import com.join.impl.EvaluateDAOImpl;
import com.join.util.JDBCConn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EvaluateDAOProxy implements IEvaluateDao {
    private JDBCConn jdbcConn;
    private EvaluateDAOImpl evaluateDAOImpl;
    public EvaluateDAOProxy(){
        jdbcConn = new JDBCConn();
        evaluateDAOImpl = new EvaluateDAOImpl(jdbcConn.getCon());
    }
    @Override
    public JSONArray getNoEvaluateInformationByUserId(String userId) {
        JSONArray jsonArray = evaluateDAOImpl.getNoEvaluateInformationByUserId(userId);
        return jsonArray;
    }

    @Override
    public JSONArray getEvaluatedInformationByUserId(String userId) {
        JSONArray jsonArray = evaluateDAOImpl.getEvaluatedInformationByUserId(userId);
        return  jsonArray;
    }

    @Override
    public boolean evaluateStadium(int stadiumId, double grade, int bookingId, String content,int userId) {
        boolean flag = evaluateDAOImpl.evaluateStadium(stadiumId,grade,bookingId,content,userId);
        return flag;
    }

    @Override
    public boolean updateBookingEvaluate(int bookingId) {
        boolean flag = evaluateDAOImpl.updateBookingEvaluate(bookingId);
        return flag;
    }

    @Override
    public double getEvaluateRating(int stadiumId) {
        double i = evaluateDAOImpl.getEvaluateRating(stadiumId);
        return i;
    }

    @Override
    public boolean updateStadiumGrade(int stadiumId, double grade) {
        boolean flag = evaluateDAOImpl.updateStadiumGrade(stadiumId,grade);
        return flag;
    }

    @Override
    public JSONObject evauate(int booking) {
        JSONObject jsonObject = evaluateDAOImpl.evauate(booking);
        return jsonObject;
    }

    @Override
    public JSONArray getEvaluatedInformationByStadiumId(int stadiumId) {
        JSONArray jsonArray = evaluateDAOImpl.getEvaluatedInformationByStadiumId(stadiumId);
        return jsonArray;
    }
}
