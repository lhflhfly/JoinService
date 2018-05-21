package com.join.idao;

import com.join.vo.Book;
import net.sf.json.JSONArray;

public interface IBookDao {
    public boolean orderStadium(Book book);
    public JSONArray getOrderInformationByUserId(String userId);
    public JSONArray getOrderInformation_usedByUserId(String userId);
    public boolean deleteOrderInformation(int bookingId);

}
