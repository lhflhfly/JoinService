package com.join.vo;

public class Need {
    private int userId;
    private int stadiumId;
    private String time;
    private int num;
    private String remark;
    private String sporttype;
    private int num_join;
    private String releasetime;
    private String tel;

    public String getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(String releasetime) {
        this.releasetime = releasetime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(int stadiumId) {
        this.stadiumId = stadiumId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSporttype() {
        return sporttype;
    }

    public void setSporttype(String sporttype) {
        this.sporttype = sporttype;
    }

    public int getNum_join() {
        return num_join;
    }

    public void setNum_join(int num_join) {
        this.num_join = num_join;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
