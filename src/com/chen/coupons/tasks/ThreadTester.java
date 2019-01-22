package com.chen.coupons.tasks;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class ThreadTester {

	public static void main(String[] args) {
	

		
		// Creating a task 
		TimerTask timerTask = new DeleteExpiredTask();
		
		// Creating a timer
		Timer timer = new Timer();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 22);
		calendar.set(Calendar.MINUTE,27);
		calendar.set(Calendar.SECOND,00);
		
		// Tell the timer to run the task every 10 seconds, starting of now
		timer.scheduleAtFixedRate(timerTask,calendar.getTime(), 8_640_000);
		
		//timer.schedule(timerTask, 10000);
		
		System.out.println("TimerTask started");

//		try {
//			// 10 seconds delay before canceling the task
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		// Removing the task
//		timer.cancel();
//		System.out.println("TimerTask cancelled");
//	}
		
		
		
	}
		
		

	}


