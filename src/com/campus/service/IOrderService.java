package com.campus.service;

import java.util.List;

import com.campus.dto.OrderDto;
import com.campus.dto.OrderTableDto;
import com.campus.entity.Order;
import com.campus.model.DataTable2Order;

import sun.util.resources.cldr.tg.LocaleNames_tg;

public interface IOrderService extends IBaseService<Order, Integer>{

    public List<OrderDto> getMyOrders(Integer userId) throws Exception;
    
    public List<OrderDto> getSellOrders(Integer userId) throws Exception;
    
    public Long getorder(int orderStatus);
    
    public OrderTableDto getOrder4DT(DataTable2Order datatable);
    
}