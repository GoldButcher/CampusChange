package com.campus.dao;

import java.util.List;

import com.campus.entity.Good;



public interface IGoodsDao extends IBaseDao<Good, Integer>{
	public long getdealedGoods();
	public List<Good> getAllgoods(Integer userId);
	public List<Good> getmygoods(Integer userId);
}
