package com.join.idao;

import net.sf.json.JSONArray;

public interface ICollectDao {
    public boolean insertCollect(int stadiumId, int userId);

    public boolean deleteCollection(int stadiumId, int userId);

    public boolean isCollected(int stadiumId, int userId);

    public JSONArray getCollectStdaiumByUserId(int userId);
}
