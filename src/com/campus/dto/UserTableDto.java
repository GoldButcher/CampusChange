package com.campus.dto;

import java.util.List;

import com.campus.entity.User;
import com.campus.entity.User;

public class UserTableDto extends BaseDto<User>{
	
	public UserTableDto(){}
	
	public UserTableDto(Integer draw, Long recordsTotal, Long recordsFiltered, List<User> data) {
		super(draw, recordsTotal, recordsFiltered, data);
	}
	
}
