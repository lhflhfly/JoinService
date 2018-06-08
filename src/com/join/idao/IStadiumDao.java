package com.join.idao;

import com.join.vo.Stadium;
import net.sf.json.JSONArray;

public interface IStadiumDao {
    public JSONArray getStdaiumByCity(String city);
    public JSONArray getStdaiumByName(String stadiumname,String city);
    public JSONArray getPlaceByStadiumId(int stadiumId,String time);
    public int selectPlaceOrdered(int stadiumId,String time);
    public JSONArray getSports();
    public JSONArray getCity();
    public JSONArray getIcon(int stadiumId);
    public int insertStadium(Stadium stadium);
    public boolean isRepeatStadium(String managername);
    public boolean updateStadium(Stadium stadium);
    public int geticonnum(int stadiumId);

}
