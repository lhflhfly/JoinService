package com.join.idao;

import com.join.vo.Book;
import net.sf.json.JSONArray;

public interface IBookDao {
    public boolean orderStadium(Book book);
    public JSONArray getOrderInformationByUserId(String userId);
    public JSONArray getOrderInformation_usedByUserId(String userId);
    public boolean deleteOrderInformation(int bookingId);
    public boolean updateBookState(int bookingId);//改变订单状态（未使用变为已使用）
    public JSONArray getNoUseBookInformationByUserId(int stadiumId);//场馆获取未使用订单信息
    public JSONArray getUsedBookInformationByUserId(int stadiumId);//场馆获取已使用订单信息
    public JSONArray getAllNoUseBookInformation();//管理员获取全部未使用订单信息
    public JSONArray getAllUsedBookInformation();//管理员获取全部已使用订单信息



}
