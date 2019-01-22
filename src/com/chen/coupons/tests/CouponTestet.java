package com.chen.coupons.tests;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.chen.coupons.beans.Company;
import com.chen.coupons.beans.Coupon;
import com.chen.coupons.enums.*;
import com.chen.coupons.dao.CouponDao;
import com.chen.coupons.exceptions.ApplicationException;
import com.chen.coupons.utils.DateUtils;
import com.chen.coupons.utils.JdbcUtils;


public class CouponTestet {
		
		public static void main(String[] args) throws Exception {
//			JdbcUtils.getConnection();
//			Thread.sleep(3000);
//			Coupon coupon = new Coupon("chen10", "couponStartDate", "couponEndDate", 300, CouponType.Leisuer, "message", 600.0, "image",2);
//			//System.out.println(coupon.toString());
//			CouponDao couponDao = new CouponDao();

			//testing the create method
			//couponDao.createNewCoupon(coupon);
			

//		testing the getCouponById method
//			System.out.println(couponDao.getCouponById(1).toString());
//			
//			
//			//testing getAllCoupons
//			List<Coupon> couponList = new ArrayList<>();
//			couponList = couponDao.getAllCoupons();
//			System.out.println(couponList.toString());
//		
			//testing the Update Title method
//			couponDao.updateCouponTitle(1, "newCouponTitle");
//
//			//testing the Update end date method
//			couponDao.updateCouponEndDate(1, "30082018");
//			
//			//testing the Update coupon amount method
//			//couponDao.updateCouponAmount(1, 99);
//			
//			//testing the Update coupon type method
//			//couponDao.updateCouponTypeById(1, "electronic");
//			
//			//testing the Update coupon's message method
//			//couponDao.updateCouponMessageById(1, "newCouponMessage");
//			
//			//testing the Update coupon's price method
//			//couponDao.updateCouponPriceById(1, 150.0);
//			
//			//testing the Update coupon's image method
//			//couponDao.updateCouponImageById(1, "newCouponImage");
//			
//			//testing the delete coupon method
			//couponDao.deleteCouponById(6);
			
//			//testing getCouponsByCompanyId
//			List<Coupon> couponList = new ArrayList<>();
//			couponList = couponDao.getCouponsByCompanyId(2);
//			System.out.println(couponList.toString());
//
//			
////			//testing getCustomerCoupons
//			List<Coupon> couponList1 = new ArrayList<>();
//			couponList1 = couponDao.getCustomerCoupons(1);
//			System.out.println(couponList.toString());
			
			
			//testing the title:
			//couponDao.isCouponExistByName("cdcd");
			
			//testing the purchase coupon method
			//couponDao.purchaseCoupon(2, 6 );
			
			//testing the isCompanyCouponExistByName method
			//Coupon coupon2 = new Coupon("chenAndAnat", "13102018", "16102018", 300, CouponType.Leisuer, "message", 600.0, "image",2);
			//System.out.println(couponDao.isCompanyCouponExistByName(coupon2));
			
			//testing the deleteExpiresCoupons
			//couponDao.deleteExpiredCoupons();
			
			//updateType
			//couponDao.updateCouponTypeById(7, CouponType.Holiday);
			
			
			//checkFormat
			//System.out.println(DateUtils.IsDateInFormat("2020-02-29"));
			
			//get
			//System.out.println(couponDao.getCouponsBeforeEndDate("2019-10-10"));
			
			//System.out.println(couponDao.getCouponsByType(CouponType.Holiday));
			
			//couponDao.purchaseCoupon(3, 15, 1);
			//couponDao.purchaseCoupon(3, 14, 2);
			//couponDao.deleteCouponById(14);
			
			//System.out.println(couponDao.getAllCustomerCoupons(4));
			
			//System.out.println();
			//System.out.println(couponDao.getAllCustomerActiveCoupons(4));
	}

}
