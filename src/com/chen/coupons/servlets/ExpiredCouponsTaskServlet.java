package com.chen.coupons.servlets;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.coupons.tasks.DeleteExpiredTask;

/**
 * Servlet implementation class ExpiredCouponsTask
 */
@WebServlet("/ExpiredCouponsTask")
public class ExpiredCouponsTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpiredCouponsTaskServlet() {
        super();
   
     		
    }
       
    //we initialize the servlet so it will run the task
    public void init () {
    	
    	Calendar c = Calendar.getInstance(TimeZone.getDefault());
		
		System.out.println("the thread timer was initialized. " + c.getTime() );
		
		Calendar c2 = c;
		
		int nextDay = c.get(Calendar.DATE) + 1;

		c2.set(Calendar.DATE, nextDay);

		c2.set(Calendar.HOUR_OF_DAY, 00);

		c2.set(Calendar.MINUTE,00);

		c2.set(Calendar.SECOND, 00);
		
		//testing:
		System.out.println("the task will start running at: " + c2.getTime());
		
		// Creating a task
		TimerTask timerTask = new DeleteExpiredTask();
	
		// Creating a timer
		Timer timer = new Timer();

		// Tell the timer to run the task day at midnight of next midnight.
		timer.scheduleAtFixedRate(timerTask,c2.getTime(), 8_640_000);       		
    	
    }
    
    
    
    

//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
