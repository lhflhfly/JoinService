package com.join.factory;

import com.join.impl.CollectDAOImpl;
import com.join.impl.StadiumDAOImpl;
import com.join.proxy.*;

public class Factory {
    public static UserDAOImplProxy getUserDAOIpmlProxy() {
        return new UserDAOImplProxy();
    }
    public static StadiumDAOImplProxy getStadiumDAOIpmlProxy(){
        return new StadiumDAOImplProxy();
    }
    public static BookDAOImplProxy getBookDAOIpmlProxy(){
        return new BookDAOImplProxy();
    }
    public static CollectDAOImplProxy getCollectDAOIpmlProxy(){
        return new CollectDAOImplProxy();
    }
    public static NeedDAOImplProxy getNeedDAOIpmlProxy(){
        return new NeedDAOImplProxy();
    }
    public static EvaluateDAOProxy getEvaluateDAOIpmlProxy(){
        return  new EvaluateDAOProxy();
    }
    public static NoticeDAOImplProxy getNoticeDAOIpmlProxy(){
        return new NoticeDAOImplProxy();
    }
}
