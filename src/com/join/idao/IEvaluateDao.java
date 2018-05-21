package com.join.idao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IEvaluateDao {
    public JSONArray getNoEvaluateInformationByUserId(String userId);
    public JSONArray getEvaluatedInformationByUserId(String userId);
    public boolean evaluateStadium(int stadiumId,double grade,int bookingId,String content,int userId);
    public boolean updateBookingEvaluate(int bookingId);
    public double getEvaluateRating(int stadiumId);
    public boolean updateStadiumGrade(int stadiumId,double grade);
    public JSONObject evauate(int booking);
    public JSONArray getEvaluatedInformationByStadiumId(int stadiumId);

}
