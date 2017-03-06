package com.campus.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.campus.dao.IGoDao;
import com.campus.entity.Go;
@Repository
public class GoDaoImpl extends BaseDaoImpl<Go, Integer> implements IGoDao{

	@Override
	public Go getchangego4orderId(Integer orderId,Integer isChange) {
		String hql = "from Go as go where go.orderId =:orderId and go.isChange=:isChange";
		Query query = getSession().createQuery(hql);
		query.setInteger("orderId", orderId);
		query.setInteger("isChange", isChange);
		return (Go) query.uniqueResult();
	}
	
}
