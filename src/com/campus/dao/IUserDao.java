package com.campus.dao;

import java.util.List;

import com.campus.entity.User;
import com.campus.model.DataTable2User;

public interface IUserDao extends IBaseDao<User,Integer>{
	public boolean isExist();
	public boolean isExist(String username);
	public long get4Sex(String sex);
	public List<User> getUser4DT(DataTable2User dataTables);
	public Long getCount4DT(DataTable2User dataTables);
}
