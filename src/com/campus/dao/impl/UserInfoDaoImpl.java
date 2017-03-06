package com.campus.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.campus.dao.IUserInfoDao;
import com.campus.entity.User;

@Repository
public class UserInfoDaoImpl extends BaseDaoImpl<User, Integer> implements IUserInfoDao {
	@Override
	public User getUserInfo(int userId) throws Exception {
		String hql = "from User where userId = :userId ";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger("userId", userId);
		return (User) query.uniqueResult();
	}

	public int updataUserInfo(User user) throws Exception {
		String userName, phone, userSex, email;
		int userId;
		userId=user.getUserId();
		userName = user.getUserName();
		phone = user.getPhone();
		userSex = user.getUserSex();
		email = user.getEmail();
		String hql = "updata User set userName = :userName , phone = :phone ,userSex = :userSex,email = :email where userId = :userId ";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger("userId", userId);
		query.setString(userName, userName);
		query.setString(phone, phone);
		query.setString(userSex, userSex);
		query.setString(email, email);
		return 0;
	}
}