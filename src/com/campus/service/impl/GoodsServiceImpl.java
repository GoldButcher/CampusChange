package com.campus.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.dao.IGoDao;
import com.campus.dao.IGoodsDao;
import com.campus.dao.IUserDao;
import com.campus.entity.Go;
import com.campus.entity.Good;
import com.campus.entity.User;
import com.campus.service.IGoodsService;
import com.campus.util.DateUtil;

@Transactional
@Service
public class GoodsServiceImpl extends BaseServiceImpl<Good, Integer> implements IGoodsService{
	
	@Resource
	private IGoodsDao goodsDao;
	
	@Resource
	private IUserDao userDao;
	
	
	@Resource
	private IGoDao goDao;
	
	@Resource
	public void setGoodsDao(IGoodsDao goodsDao) {
		super.setBaseDao(goodsDao);
	}

	@Override
	public long getdealedGoods() {
		return 	goodsDao.getdealedGoods();
	}

	@Override
	public List<Good> getAllgoods(Integer userId) {
		List<Good> goods = goodsDao.getAllgoods(userId);
		for (Good good : goods) {
			User user = userDao.get(good.getUserId());
			good.setUserName(user.getUserName());
		}
		return goods;
	}

	@Override
	public Good getdetail(Integer goodId) {
		Good good = goodsDao.get(goodId);
		User user = userDao.get(good.getUserId());
		good.setUserName(user.getUserName());
		good.setDatestr(DateUtil.date2String(good.getCreateDate()));
		return good;
	}

	@Override
	public List<Good> getmygoods(Integer userId) {
		List<Good> goods = goodsDao.getmygoods(userId);
		User user = userDao.get(userId);
		for (Good good : goods) {
			good.setDatestr(DateUtil.date2String(good.getCreateDate()));
			good.setUserName(user.getUserName());
		}
		return goodsDao.getmygoods(userId);
	}

	@Override
	public Good getchangegoods4orderId(Integer orderId,Integer isChange) {
		Go go = goDao.getchangego4orderId(orderId,isChange);
		Good good =goodsDao.get(go.getGoodId());
		return good;
	}

	
	
	
	
}
