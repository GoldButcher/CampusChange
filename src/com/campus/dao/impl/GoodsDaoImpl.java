package com.campus.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.campus.dao.IGoodsDao;
import com.campus.entity.Good;

@Repository
public class GoodsDaoImpl extends BaseDaoImpl<Good, Integer> implements IGoodsDao{

	@Override
	public long getdealedGoods() {
		String hql = "select count(*) from Good";
		Query query = (Query) getSession().createQuery(hql);
		return (long) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Good> getAllgoods(Integer userId) {
		String hql = "from Good as good where good.userId <> :id and good.isOrdered = 0";
		Query query = getSession().createQuery(hql);
		query.setInteger("id", userId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Good> getmygoods(Integer userId) {
		String hql = "from Good as good where good.userId = :userId and good.isOrdered = 0";
		Query query = getSession().createQuery(hql);
		query.setInteger("userId", userId);
		return query.list();
	}
	
}
