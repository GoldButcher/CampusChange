package com.campus.service;

import java.util.List;

import com.campus.entity.User;

public interface IUserInfoService extends IBaseService<User, Integer>{

	/**
	 * @Description: 获取用户个人资料
	 * @author jiang-weirong
	 * @date 2016年11月27日 下午8:08:12 
	 * @param userId
	 * @return  
	 * List<User> 
	*/
	public User getUserInfo(int userId) throws Exception;

	public int updataUserInfo(User user) throws Exception;
}