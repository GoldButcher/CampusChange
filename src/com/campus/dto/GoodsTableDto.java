package com.campus.dto;

import java.util.List;

import com.campus.entity.Good;

public class GoodsTableDto extends BaseDto<Good>{
	
	public GoodsTableDto(){}
	
	public GoodsTableDto(Integer draw, Long recordsTotal, Long recordsFiltered, List<Good> data) {
		super(draw, recordsTotal, recordsFiltered, data);
	}
	
}
