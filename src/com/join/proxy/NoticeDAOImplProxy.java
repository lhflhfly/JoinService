package com.join.proxy;

import com.join.idao.INoticeDao;
import com.join.impl.NoticeDAOImpl;
import com.join.util.JDBCConn;
import net.sf.json.JSONArray;

public class NoticeDAOImplProxy implements INoticeDao {
    private JDBCConn jdbcConn;
    private NoticeDAOImpl noticeDAOImpl;
    public NoticeDAOImplProxy(){
        jdbcConn = new JDBCConn();
        noticeDAOImpl = new NoticeDAOImpl(jdbcConn.getCon());
    }
    @Override
    public JSONArray getNotice() {
        JSONArray jsonArray = noticeDAOImpl.getNotice();
        return  jsonArray;
    }
}
