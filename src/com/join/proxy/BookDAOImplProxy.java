package com.join.proxy;

import com.join.idao.IBookDao;
import com.join.impl.BookDAOImpl;
import com.join.util.JDBCConn;
import com.join.vo.Book;
import net.sf.json.JSONArray;

public class BookDAOImplProxy implements IBookDao {
    private JDBCConn jdbcConn;
    private BookDAOImpl bookDAOImpl;
    public BookDAOImplProxy(){
        jdbcConn = new JDBCConn();
        bookDAOImpl = new BookDAOImpl(jdbcConn.getCon());
    }
    @Override
    public boolean orderStadium(Book book) {
        boolean flag = bookDAOImpl.orderStadium(book);
        return  flag;
    }

    @Override
    public JSONArray getOrderInformationByUserId(String userId) {
        JSONArray jsonArray = bookDAOImpl.getOrderInformationByUserId(userId);
        return jsonArray;
    }

    @Override
    public JSONArray getOrderInformation_usedByUserId(String userId) {
        JSONArray jsonArray = bookDAOImpl.getOrderInformation_usedByUserId(userId);
        return  jsonArray;
    }

    @Override
    public boolean deleteOrderInformation(int bookingId) {
        boolean flag = bookDAOImpl.deleteOrderInformation(bookingId);
        return  flag;
    }
}
