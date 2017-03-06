package com.campus.dao;

import java.util.List;

import com.campus.entity.User;


public interface IUserInfoDao extends IBaseDao<User, Integer>{
	/**
	 * @Description: 获取用户个人资料
	 * @author jiang-weirong
	 * @date 2016年11月27日 下午8:08:12 
	 * @param userId
	 * @return  
	 * List<User> 
	*/
	public User getUserInfo(int userId) throws Exception;

	/**
	 * @Description: 更新用户资料
	 * @author ding
	 * @date 2016年11月28日 下午7:20:56 
	 * @param user
	 * @return  
	 * JSONObject 
	*/
	public int updataUserInfo(User user) throws Exception;
}