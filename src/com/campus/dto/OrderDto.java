package com.campus.dto;

import java.util.Date;

public class OrderDto {
    private Integer gOId;
    private Integer goodId;
    private Integer orderId;
    private Integer isChange;
    private Integer sellerUserId;
    private String sellerName;
    private Integer buyllerUserId;
    private String buyllerName;
    private String goodIntroduction;
    private String sellgoodName;
    private double sellprice;
    private String changegoodName;
    private double changeprice;
    private Date createDate;
    private Date modifyDate;
    private Integer orderStatus;
    private Integer isOrdered;

    public OrderDto() {
        super();
    }
    
    

	public OrderDto(Integer gOId, Integer goodId, Integer orderId, Integer isChange, Integer sellerUserId,
			String sellerName, Integer buyllerUserId, String buyllerName, String goodIntroduction, String sellgoodName,
			double sellprice, String changegoodName, double changeprice, Date createDate, Date modifyDate,
			Integer orderStatus, Integer isOrdered) {
		super();
		this.gOId = gOId;
		this.goodId = goodId;
		this.orderId = orderId;
		this.isChange = isChange;
		this.sellerUserId = sellerUserId;
		this.sellerName = sellerName;
		this.buyllerUserId = buyllerUserId;
		this.buyllerName = buyllerName;
		this.goodIntroduction = goodIntroduction;
		this.sellgoodName = sellgoodName;
		this.sellprice = sellprice;
		this.changegoodName = changegoodName;
		this.changeprice = changeprice;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.orderStatus = orderStatus;
		this.isOrdered = isOrdered;
	}



	public Integer getgOId() {
		return gOId;
	}

	public void setgOId(Integer gOId) {
		this.gOId = gOId;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getIsChange() {
		return isChange;
	}

	public void setIsChange(Integer isChange) {
		this.isChange = isChange;
	}

	public Integer getSellerUserId() {
		return sellerUserId;
	}

	public void setSellerUserId(Integer sellerUserId) {
		this.sellerUserId = sellerUserId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Integer getBuyllerUserId() {
		return buyllerUserId;
	}

	public void setBuyllerUserId(Integer buyllerUserId) {
		this.buyllerUserId = buyllerUserId;
	}

	public String getBuyllerName() {
		return buyllerName;
	}

	public void setBuyllerName(String buyllerName) {
		this.buyllerName = buyllerName;
	}

	public String getGoodIntroduction() {
		return goodIntroduction;
	}

	public void setGoodIntroduction(String goodIntroduction) {
		this.goodIntroduction = goodIntroduction;
	}

	public String getSellgoodName() {
		return sellgoodName;
	}

	public void setSellgoodName(String sellgoodName) {
		this.sellgoodName = sellgoodName;
	}

	public double getSellprice() {
		return sellprice;
	}

	public void setSellprice(double sellprice) {
		this.sellprice = sellprice;
	}

	public String getChangegoodName() {
		return changegoodName;
	}

	public void setChangegoodName(String changegoodName) {
		this.changegoodName = changegoodName;
	}

	public double getChangeprice() {
		return changeprice;
	}

	public void setChangeprice(double changeprice) {
		this.changeprice = changeprice;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getIsOrdered() {
		return isOrdered;
	}

	public void setIsOrdered(Integer isOrdered) {
		this.isOrdered = isOrdered;
	}
    
   
}
