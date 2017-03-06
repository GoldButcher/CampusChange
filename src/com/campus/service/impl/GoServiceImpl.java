package com.campus.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.dao.IGoDao;
import com.campus.dao.IGoodsDao;
import com.campus.entity.Go;
import com.campus.entity.Good;
import com.campus.service.IGoService;

@Transactional
@Service
public class GoServiceImpl extends BaseServiceImpl<Go, Integer> implements IGoService{
	@Resource
	private IGoDao goDao;

	@Resource
	public void setGoDao(IGoDao goDao) {
		super.setBaseDao(goDao);
	}

	@Override
	public Go getchangego4orderId(Integer goodId,Integer isChange) {
		return goDao.getchangego4orderId(goodId,isChange);
	}
	
	
}
