package com.campus.dto;

import java.util.List;

import com.campus.entity.Order;

public class OrderTableDto extends BaseDto<OrderDto>{

	public OrderTableDto(){}
	
	public OrderTableDto(Integer draw, Long recordsTotal, Long recordsFiltered, List<OrderDto> data) {
		super(draw, recordsTotal, recordsFiltered, data);
		// TODO Auto-generated constructor stub
	}
	
}
