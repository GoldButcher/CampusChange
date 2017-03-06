package com.campus.service;

import java.util.List;

import com.campus.entity.Good;

public interface IGoodsService extends IBaseService<Good, Integer>{
	public long getdealedGoods();
	public List<Good> getAllgoods(Integer userId);
	public Good getdetail(Integer goodId);
	public List<Good> getmygoods(Integer userId);
	public Good getchangegoods4orderId(Integer orderId,Integer isChange);
	
}
