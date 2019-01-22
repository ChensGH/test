package com.chen.coupons.tests;

import com.chen.coupons.beans.Coupon;
import com.chen.coupons.enums.*;
import com.chen.coupons.controllers.CouponController;
import com.chen.coupons.dao.CouponDao;
import com.chen.coupons.exceptions.ApplicationException;

public class CouponControllerTester {

	public static void main(String[] args) throws ApplicationException {
		
		CouponController couponController = new CouponController();
		CouponDao couponDao = new CouponDao();
		
		//create coupon
		//Coupon coupon = new Coupon("chen12", "2010-12-30", "2019-12-30", 300, CouponType.Electronic, "message", 600.0, "image",2);
		//System.out.println(couponController.createCoupon(coupon));
		
		
		// testing the createCoupon - title validation - too long
		//Coupon coupon = new Coupon("dffhssdfsffjsdjfsjdfsjdfsfsjfsdfsd4543ffdffsfschen12", "2010-12-30", "2019-12-30", 300, CouponType.Electronic, "message", 600.0, "image",2);
		//couponController.createCoupon(coupon);
		
		
		// testing the createCoupon - title validation - already exist for company
		//Coupon coupon = new Coupon("chen12", "2010-12-30", "2019-12-30", 300, CouponType.Electronic, "message", 600.0, "image",2);
		//couponController.createCoupon(coupon);
		
		
		// testing the createCoupon - title validation - too short
		//Coupon coupon = new Coupon("x", "2010-12-30", "2019-12-30", 300, CouponType.Electronic, "message", 600.0, "image",2);
		//couponController.createCoupon(coupon);
		
		
		// testing the createCoupon - dates validation - format
		//Coupon coupon = new Coupon("x", "2010/12/30", "2019-12-30", 300, CouponType.Electronic, "message", 600.0, "image",2);
		//couponController.createCoupon(coupon);
		
		// testing the createCoupon - dates validation - end date has passed
		//Coupon coupon = new Coupon("x", "2010-12-30", "2018-10-30", 300, CouponType.Electronic, "message", 600.0, "image",2);
		//couponController.createCoupon(coupon);	
		
		
		//testing the createCoupon - dates validation - end date before startDate
		//Coupon coupon = new Coupon("x", "2010-12-30", "2017-10-30", 300, CouponType.Electronic, "message", 600.0, "image",2);
		//couponController.createCoupon(coupon);
		
		// testing the createCoupon - dates validation - date doesnt exist (example: november 31)
		//Coupon coupon = new Coupon("x", "2010-12-30", "2018-11-31", 300, CouponType.Electronic, "message", 600.0, "image",2);
		//couponController.createCoupon(coupon);
		
		// testing the createCoupon - amount validation
		//Coupon coupon = new Coupon("x", "2010-12-30", "2018-11-30", -1, CouponType.Electronic, "message", 600.0, "image",2);
		//couponController.createCoupon(coupon);
		
		// testing the createCoupon - message validation
		//Coupon coupon = new Coupon("newCoupon", "2010-12-30", "2018-11-30", 100, CouponType.Electronic, "abc", 600.0, "image",2);
		//couponController.createCoupon(coupon);
		
		
		// testing the createCoupon - price validation
		//Coupon coupon = new Coupon("newCoupon1", "2010-12-30", "2018-11-30", 100, CouponType.Electronic, "message", -20, "image",2);
		//couponController.createCoupon(coupon);
		
		// testing the createCoupon - companyId validation
		//Coupon coupon = new Coupon("newCoupon1", "2010-12-30", "2018-11-30", 100, CouponType.Electronic, "message", 200, "image",20);
		//couponController.createCoupon(coupon);
		
		
		// testing purchase coupon: customerId doesnt exist
		//couponController.purchaseCoupon(6, 11, 2);
		//couponController.purchaseCoupon(customerId, couponId, purchaseAmount);

		
		// testing update method : changing the amount
		//Coupon coupon = couponController.getCouponById(11);
		//System.out.println("Before update: " + couponController.getCouponById(11).getAmount() );
		//coupon.setAmount(10);
		//couponController.updateCoupon(coupon);
		//System.out.println(couponController.getCouponById(11).getAmount());

		//testing purchase coupon: there are not enough coupons for purchase
		//couponController.purchaseCoupon(5, 11, 11);

		
		//testing purchase coupon: amount not valid
		//couponController.purchaseCoupon(5, 11, -1);
		
		
		//testing purchase coupon: subtracting amount
		//System.out.println("Before update: " + couponController.getCouponById(11).getAmount() );
		//couponController.purchaseCoupon(5, 11, 1);
		//System.out.println("after purchase: " + couponController.getCouponById(11).getAmount() );

		
		//getAllCoupons
		//System.out.println(couponController.getAllCoupons());
		
		//getCouponById
		//System.out.println(couponController.getCouponById(1));
		
		//deletecouponbyid
		//couponController.deleteCouponById(6);
		
		//purchase
		//couponController.purchaseCoupon(4, 15, 2);
		
		//updateCouponAmount
		//couponController.updateCouponAmount(1, 10);
		
		//updateCouponEndDate
		//couponController.updateCouponEndDate(1, "2019-12-31");
		
		//System.out.println(couponController.getCouponsBeforeEndDate("2010-12-30"));
		
		//Coupon coupon = new Coupon("chen12", "2010-12-30", "2019-12-30", 300, CouponType.Electronic, "message", 600.0, "image",2);
		//System.out.println(couponController.createCoupon(coupon));
		
		
		
		
		
		
		
		
	}

}
