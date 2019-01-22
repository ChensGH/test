package com.chen.coupons.beans;

import javax.xml.bind.annotation.XmlRootElement;

import com.chen.coupons.enums.CouponType;


@XmlRootElement
public class Coupon {
	
	private long couponId;
	private String couponTitle;
	private String couponStartDate;
	private String couponEndDate;
	private CouponType couponType;;
	private int amount;
	private String message;
	private double price;
	private String image;
	private long company_id;
	private String coupon_status;
	


	public Coupon () {
		
	}

	public Coupon(String couponTitle, String couponStartDate, String couponEndDate, int amount, CouponType couponType,
			String message, double price, String image, long company_id) {
		super();
		this.couponTitle = couponTitle;
		this.couponStartDate = couponStartDate;
		this.couponEndDate = couponEndDate;
		this.amount = amount;
		this.couponType = couponType;
		this.message = message;
		this.price = price;
		this.image = image;
		this.company_id = company_id;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public String getCouponTitle() {
		return couponTitle;
	}

	public void setCouponTitle(String couponTitle) {
		this.couponTitle = couponTitle;
	}

	public String getCouponStartDate() {
		return couponStartDate;
	}

	public void setCouponStartDate(String couponStartDate) {
		this.couponStartDate = couponStartDate;
	}

	public String getCouponEndDate() {
		return couponEndDate;
	}

	public void setCouponEndDate(String couponEndDate) {
		this.couponEndDate = couponEndDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getCouponType() {
		return this.couponType.name();
		
	}
	
	public void setCouponType(String type) {
		this.couponType = CouponType.valueOf(type);
	}
	
	public long getCompany_Id() {
		return company_id;
	}
	
	public String getCoupon_status() {
		return coupon_status;
	}

	public long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}

	public void setCoupon_status(String coupon_status) {
		this.coupon_status = coupon_status;
	}

	@Override
	public String toString() {
		return "\n Coupon [couponId=" + couponId + ", couponTitle=" + couponTitle + ", couponStartDate=" + couponStartDate
				+ ", couponEndDate=" + couponEndDate + ", couponType=" + couponType + ", amount=" + amount
				+ ", message=" + message + ", price=" + price + ", image=" + image + ", company_id=" + company_id
				+ ", coupon_status=" + coupon_status + "]";
	}
	


	
	
	
	
	
	
	
	
	
	
	

}
