package com.coupon.model;
import java.sql.Date;

public class CouponVO implements java.io.Serializable{
	private Integer couponId;
	private String couponCode;
	private Integer discountValue;
	private Integer minSpend;
	private Date startDate;
	private Date endDate;
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public Integer getDiscountValue() {
		return discountValue;
	}
	public void setDiscountValue(Integer duscountValue) {
		this.discountValue = duscountValue;
	}
	public Integer getMinSpend() {
		return minSpend;
	}
	public void setMinSpend(Integer minSpend) {
		this.minSpend = minSpend;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	
}
