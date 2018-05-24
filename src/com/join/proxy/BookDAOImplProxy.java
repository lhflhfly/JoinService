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

    @Override
    public boolean updateBookState(int bookingId) {
        boolean flag = bookDAOImpl.updateBookState(bookingId);
        return  flag;
    }

    @Override
    public JSONArray getNoUseBookInformationByUserId(int stadiumId) {
        JSONArray jsonArray = bookDAOImpl.getNoUseBookInformationByUserId(stadiumId);
        return  jsonArray;
    }

    @Override
    public JSONArray getUsedBookInformationByUserId(int stadiumId) {
        JSONArray jsonArray = bookDAOImpl.getUsedBookInformationByUserId(stadiumId);
        return  jsonArray;
    }

    @Override
    public JSONArray getAllNoUseBookInformation() {
        JSONArray jsonArray = bookDAOImpl.getAllNoUseBookInformation();
        return  jsonArray;
    }

    @Override
    public JSONArray getAllUsedBookInformation() {
        JSONArray jsonArray = bookDAOImpl.getAllUsedBookInformation();
        return  jsonArray;
    }
}
