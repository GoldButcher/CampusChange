package com.campus.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.dao.IUserInfoDao;
import com.campus.entity.User;
import com.campus.service.IUserInfoService;
import com.campus.util.DateUtil;
@Transactional
@Service
public class UserInfoServiceImpl extends BaseServiceImpl<User, Integer>  implements IUserInfoService{
	@Resource
	private IUserInfoDao userInfoDao;
	
	@Override
	public User getUserInfo(int userId) throws Exception{
		User user = userInfoDao.getUserInfo(userId);
		user.setDateStr(DateUtil.date2String(user.getCreateDate())); 
		return userInfoDao.getUserInfo(userId);
	} 
	
	@Override
	public int updataUserInfo(User user) throws Exception{
		return userInfoDao.updataUserInfo(user);
	}
}
