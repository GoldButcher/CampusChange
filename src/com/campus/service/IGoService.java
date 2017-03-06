package com.campus.service;

import com.campus.entity.Go;

public interface IGoService extends IBaseService<Go, Integer>{
	public Go getchangego4orderId(Integer goodId,Integer isChange);
}
