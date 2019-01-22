package com.chen.coupons.tasks;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import com.chen.coupons.dao.CouponDao;
import com.chen.coupons.exceptions.ApplicationException;

public class DeleteExpiredTask extends TimerTask {
	
	CouponDao couponDao = new CouponDao();
	
	@Override
    public void run() {
		long now = Calendar.getInstance().getTimeInMillis();
		Date todayDate = new Date(now);
		try {
			couponDao.deleteExpiredCoupons();
			System.out.println("done!");
		} catch ( ApplicationException e) {
			e.fillInStackTrace();
		}
		
    }
	
	
	

}
