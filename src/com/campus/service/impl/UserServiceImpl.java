package com.campus.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.dao.IUserDao;
import com.campus.dto.UserTableDto;
import com.campus.entity.User;
import com.campus.model.DataTable2User;
import com.campus.service.IUserService;
import com.campus.util.DateUtil;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements IUserService{
	
	@Resource
	private IUserDao userdao;
	
	
	@Resource
	public void setUserdao(IUserDao userdao) {
		super.setBaseDao(userdao);
	}

	@Override
	public boolean addUser(User user) {
		return (boolean) userdao.save(user);
	}

	@Override
	public boolean isExist() {

		return userdao.isExist();
	}
	
	@Override
	public boolean isExist(String username) {
		return userdao.isExist(username);
	}

	@Override
	public long get4Sex(String sex) {
		return userdao.get4Sex(sex);
	}

	@Override
	public UserTableDto getUser4DT(DataTable2User dataTables) {
		List<User> data =  userdao.getUser4DT(dataTables);
		Long count = userdao.getCount4DT(dataTables);
		UserTableDto dto = new UserTableDto(dataTables.getDraw(), count, count, data);
		for (User user : data) {
			if(user.getCreateDate()!=null)
			user.setDateStr(DateUtil.date2String(user.getCreateDate()));
		}
		return dto;
	}
	
}
