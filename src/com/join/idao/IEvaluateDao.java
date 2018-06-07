package com.join.idao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IEvaluateDao {
    public JSONArray getNoEvaluateInformationByUserId(String userId);
    public JSONArray getEvaluatedInformationByUserId(String userId);
    public boolean evaluateStadium(int stadiumId,double grade,int bookingId,String content,int userId,String evaluatetime);
    public boolean updateBookingEvaluate(int bookingId);
    public boolean updateBookingAllEvaluate(int bookingId);
    public double getEvaluateRating(int stadiumId);
    public boolean updateStadiumGrade(int stadiumId,double grade);
    public JSONObject evauate(int booking);
    public JSONArray getEvaluatedInformationByStadiumId(int stadiumId);//获取场馆评价
    public JSONArray getAllEvaluation();//获取所有评论

}
