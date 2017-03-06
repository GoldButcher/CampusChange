package com.campus.dao;

import java.util.List;

import com.campus.dto.OrderDto;
import com.campus.entity.Order;
import com.campus.model.DataTable2Order;

public interface IOrderDao extends IBaseDao<Order, Integer> {

    public List<OrderDto> getMyOrders(Integer userId) throws Exception;

    public List<OrderDto> getSellOrders(Integer userId) throws Exception;
    
    public Long getorder(int orderStatus);
    
    public Long getorderCount();
    
    public List<OrderDto> getOrder4DT(DataTable2Order dataTables);
}
