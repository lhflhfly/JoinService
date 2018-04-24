package com.join.bean;

public class Book {
    private int stadiumId;
    private int userId;
    private String placeName;
    private String time;
    private String tel;
    private String time_order;

    public String getTime_order() {
        return time_order;
    }

    public void setTime_order(String time_order) {
        this.time_order = time_order;
    }

    public int getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(int stadiumId) {
        this.stadiumId = stadiumId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
