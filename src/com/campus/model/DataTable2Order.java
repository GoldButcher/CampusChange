package com.campus.model;

public class DataTable2Order {
	private Integer draw;//需要回写的参数
	private Integer start;
	private Integer length;
	private String search;//搜索框参数
	private int isChange;
	private int orderStatus;
	private String sellername;
	private String buyllername;
	
	public DataTable2Order(Integer draw, Integer start, Integer length, String search, int isChange, int orderStatus,
			String sellername, String buyllername) {
		super();
		this.draw = draw;
		this.start = start;
		this.length = length;
		this.search = search;
		this.isChange = isChange;
		this.orderStatus = orderStatus;
		this.sellername = sellername;
		this.buyllername = buyllername;
	}
	public DataTable2Order() {
		super();
	}
	
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getIsChange() {
		return isChange;
	}
	public void setIsChange(int isChange) {
		this.isChange = isChange;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	public String getBuyllername() {
		return buyllername;
	}
	public void setBuyllername(String buyllername) {
		this.buyllername = buyllername;
	}

	
}
