package com.campus.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.dao.IGoDao;
import com.campus.dao.IGoodsDao;
import com.campus.dao.IOrderDao;
import com.campus.dao.IUserDao;
import com.campus.dto.OrderDto;
import com.campus.dto.OrderTableDto;
import com.campus.entity.Go;
import com.campus.entity.Good;
import com.campus.entity.Order;
import com.campus.entity.User;
import com.campus.model.DataTable2Order;
import com.campus.service.IOrderService;

@Transactional
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Integer> implements IOrderService {
	@Resource
	private IOrderDao orderDao;

	@Resource
	private IUserDao userDao;

	@Resource
	private IGoDao goDao;

	@Resource
	private IGoodsDao goodDao;

	@Resource
	public void setOrderDao(IOrderDao orderDao) {
		super.setBaseDao(orderDao);
	}

	@Override
	public List<OrderDto> getMyOrders(Integer userId) throws Exception {
		List<OrderDto> result = null;
		result = orderDao.getMyOrders(userId);
		for (OrderDto orderDto : result) {
			User user = userDao.get(orderDto.getSellerUserId());
			orderDto.setSellerName(user.getUserName());
			if (orderDto.getIsChange() == 2) {
				Go go = goDao.getchangego4orderId(orderDto.getOrderId(), 3);
				Good good = goodDao.get(go.getGoodId());
				orderDto.setChangegoodName(good.getGoodName());
				orderDto.setChangeprice(good.getPrice());
			}
		}
		return result;
	}

	@Override
	public List<OrderDto> getSellOrders(Integer userId) throws Exception {
		List<OrderDto> result = null;
		result = orderDao.getSellOrders(userId);
		User user = userDao.get(userId);
		for (OrderDto orderDto : result) {
			orderDto.setSellerName(user.getUserName());
			if (orderDto.getIsChange() == 3) {
				Go go = goDao.getchangego4orderId(orderDto.getOrderId(), 2);
				Good good = goodDao.get(go.getGoodId());
				orderDto.setChangegoodName(good.getGoodName());
				orderDto.setChangeprice(good.getPrice());
			}
		}
		return result;
	}

	@Override
	public Long getorder(int orderStatus) {
		return orderDao.getorder(orderStatus);
	}

	@Override
	public OrderTableDto getOrder4DT(DataTable2Order datatable) {
		List<OrderDto> orderDtos = orderDao.getOrder4DT(datatable);
		OrderTableDto orderTableDto = new OrderTableDto(datatable.getDraw(), orderDao.getorderCount(), orderDao.getorderCount(), orderDtos);
		return orderTableDto;
	}

}