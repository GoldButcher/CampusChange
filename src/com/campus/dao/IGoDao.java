package com.campus.dao;

import com.campus.entity.Go;

public interface IGoDao extends IBaseDao<Go, Integer>{
	public Go getchangego4orderId(Integer orderId,Integer isChange);
}
