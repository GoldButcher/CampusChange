package com.campus.service;

import com.campus.dto.UserTableDto;
import com.campus.entity.User;
import com.campus.model.DataTable2User;

public interface IUserService extends IBaseService<User,Integer>{
	public boolean addUser(User user);
	public boolean isExist();
	public boolean isExist(String username);
	public long get4Sex(String sex);
	public UserTableDto getUser4DT(DataTable2User dataTables);
}
