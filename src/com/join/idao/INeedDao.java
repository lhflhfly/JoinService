package com.join.idao;

import com.join.vo.Need;
import net.sf.json.JSONArray;

public interface INeedDao {
    public boolean insertNeed(Need need);
    public JSONArray getNeedInformationByUserId(String userId);
    public boolean deleteNeedrInformation(int needId);
    public JSONArray getFindInformationByUserId();
    public boolean updateUserJoinNum(int needId);
    public boolean updateUserJoinNumDelete(int needId);
    public int getNum(int needId);
    public int getNumJoin(int needId);
    public boolean insertJoin(int userId,int needId);
    public boolean isJoined(int userId,int needId);
    public JSONArray getJoinedNeedByUserId(int userId);
    public boolean deleteJoinedNeed(int userId,int needId);
    public JSONArray getJoinedUserByneedId(int needId);
}
