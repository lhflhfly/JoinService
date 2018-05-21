package com.join.idao;

import net.sf.json.JSONArray;

public interface IStadiumDao {
    public JSONArray getStdaiumByCity(String city);
    public JSONArray getStdaiumByName(String stadiumname,String city);
    public JSONArray getPlaceByStadiumId(int stadiumId,String time);
    public int selectPlaceOrdered(int stadiumId,String time);
    public JSONArray getSports();
    public JSONArray getCity();
    public JSONArray getIcon(int stadiumId);

}
