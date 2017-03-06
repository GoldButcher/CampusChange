package com.campus.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.campus.dao.IUserDao;
import com.campus.entity.User;
import com.campus.model.DataTable2User;
@Repository
public class UserDaoImpl extends BaseDaoImpl<User,Integer> implements IUserDao{

	@Override
	public boolean isExist() {
		String hql = "select count(*) from User";
		Long count = (Long) getSession().createQuery(hql).uniqueResult();
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean isExist(String username) {
		if(get("userName", username)==null)
		{
			return false;
		}
		return true;
	}

	@Override
	public long get4Sex(String sex) {
		String hql = "select count(*) from User as user where user.userSex = :sex ";
		Query query = getSession().createQuery(hql);
		query.setParameter("sex", sex);
		return (long) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUser4DT(DataTable2User dataTables) {
		String username = dataTables.getUserName();
		String sex = dataTables.getSex();
		String email = dataTables.getEmail();
		String phone = dataTables.getPhone();
		String hql = "from User where userName like :username and userSex like :sex and  email like :email and phone like :phone";
		
		Query query = getSession().createQuery(hql);
		query.setString("username", username);
		query.setString("sex", sex);
		query.setString("email", email);
		query.setString("phone", phone);
		query.setFirstResult(dataTables.getStart());
		query.setMaxResults(dataTables.getLength());
		return query.list();
	}

	@Override
	public Long getCount4DT(DataTable2User dataTables) {
		String username = dataTables.getUserName();
		String sex = dataTables.getSex();
		String email = dataTables.getEmail();
		String phone = dataTables.getPhone();
		String hql = "select count(*) from User where userName like :username and userSex like :sex and email like :email and phone like :phone";
		
		Query query = getSession().createQuery(hql);
		query.setString("username", username);
		query.setString("sex", sex);
		query.setString("email", email);
		query.setString("phone", phone);
		return (Long) query.uniqueResult();
	}


}
