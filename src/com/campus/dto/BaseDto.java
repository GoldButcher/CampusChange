package com.campus.dto;

import java.util.List;

public class BaseDto<T> {
	private Integer draw;
	private Long recordsTotal;
	private Long recordsFiltered;
	private List<T> data;
	
	
	public BaseDto() {
		super();
	}
	public BaseDto(Integer draw, Long recordsTotal, Long recordsFiltered, List<T> data) {
		super();
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.data = data;
	}
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public Long getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	} 
	
	
}
