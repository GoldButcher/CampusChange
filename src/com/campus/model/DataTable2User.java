package com.campus.model;

public class DataTable2User{
	private Integer draw;//需要回写的参数
	private Integer start;
	private Integer length;
	private String search;//搜索框参数
	private String userName;
	private String sex;
	private String email;
	private String phone;
	
	public DataTable2User() {
		super();
	}
	
	public DataTable2User(Integer draw, Integer start, Integer length, String search, String userName, String sex,
			String email, String phone) {
		super();
		this.draw = draw;
		this.start = start;
		this.length = length;
		this.search = search;
		this.userName = userName;
		this.sex = sex;
		this.email = email;
		this.phone = phone;
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
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
	
}
