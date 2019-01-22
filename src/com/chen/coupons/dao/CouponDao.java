package com.chen.coupons.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chen.coupons.beans.Company;
import com.chen.coupons.beans.Coupon;
import com.chen.coupons.enums.CouponType;
import com.chen.coupons.beans.Customer;
import com.chen.coupons.enums.ErrorType;
import com.chen.coupons.exceptions.ApplicationException;
import com.chen.coupons.utils.DateUtils;
//import com.chen.coupons.beans.Coupon.CouponType;
import com.chen.coupons.utils.JdbcUtils;


public class CouponDao {
	
	public Coupon getCouponById(long couponId) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;
		
		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM coupon WHERE coupon_id = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, couponId);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			coupon = extractCouponFromResultSet(resultSet);
			
		}

		catch (SQLException e) {
//			e.printStackTrace();
			throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CouponDao, getCouponById(); FAILED");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return coupon;
	}
	
	//Extract company's data by parameters from the database
private Coupon extractCouponFromResultSet(ResultSet resultSet) throws SQLException {
		Coupon coupon = new Coupon();
		coupon.setCouponId(resultSet.getLong("coupon_id"));
		coupon.setCouponTitle(resultSet.getString("coupon_title"));
		coupon.setCouponStartDate(resultSet.getString("start_date"));
		coupon.setCouponEndDate(resultSet.getString("end_date"));
		coupon.setAmount(resultSet.getInt("amount"));
		coupon.setCouponType(resultSet.getString("coupon_type"));
		coupon.setMessage(resultSet.getString("coupon_message"));
		coupon.setPrice(resultSet.getDouble("coupon_price"));
		coupon.setImage(resultSet.getString("coupon_image"));
		coupon.setCompany_id(resultSet.getLong("company_id"));
		coupon.setCoupon_status(resultSet.getString("coupon_status"));
		
		
		return coupon;

	}

	
public List<Coupon> getAllActiveCoupons() throws ApplicationException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;
		List<Coupon> couponList = new ArrayList<>();
		
		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM coupon";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			
			// Extract coupons's data by parameters from the database into a list
			coupon = extractCouponFromResultSet(resultSet);
			couponList.add(coupon);
			while(resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);
				couponList.add(coupon);

				}
	
		}

		catch (SQLException e) {
			//	e.printStackTrace();
				throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CouponDao, getAllActiveCoupons(); FAILED");
		}
		
		finally {
			
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		
		return couponList;
	}

//returning a list of all coupons in the system - both active and inactive (from history)
public List<Coupon> getAllCoupons() throws ApplicationException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Coupon coupon = null;
	List<Coupon> couponList = new ArrayList<>();
	
	try {
		connection = JdbcUtils.getConnection();
		String sql = "SELECT c.*, null AS dateOfRemoval FROM coupon c UNION SELECT * FROM coupons_history ";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) {
			return null;
		}
		
		// Extract coupons's data by parameters from the database into a list
		coupon = extractCouponFromResultSet(resultSet);
		couponList.add(coupon);
		while(resultSet.next()) {
			coupon = extractCouponFromResultSet(resultSet);
			couponList.add(coupon);

			}

	}

	catch (SQLException e) {
		//	e.printStackTrace();
			throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CouponDao, getAllCoupons(); FAILED");
	}
	
	finally {
		
		JdbcUtils.closeResources(connection, preparedStatement, resultSet);
	}
	
	return couponList;
}




	

public void updateCouponTitle (long couponId, String newCouponTitle) throws ApplicationException {
	
	java.sql.PreparedStatement preparedStatement = null;
	Connection connection = null;

	try {
		// Getting a connection to the DB
		connection = JdbcUtils.getConnection();
				
		// Creating a string which will contain the query
		// PAY ATTENTION - BY USING THE ? (Question marks) WE PREVENT AN SQL INJECTION ATTACK
		 String sql = "UPDATE coupons.coupon SET coupon_title = ? WHERE coupon_id = ?";
		
		preparedStatement= connection.prepareStatement(sql);
		preparedStatement.setString(1, newCouponTitle);
		preparedStatement.setLong(2, couponId); 

		preparedStatement.executeUpdate();
	} 

	catch (SQLException e) {
		throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CouponDao, updateCouponTitle(); FAILED");
	} 

	finally {
		JdbcUtils.closeResources(connection, preparedStatement);
	}

}

public void updateCouponEndDate (long couponId, String newCouponEndDate) throws ApplicationException {
	
	java.sql.PreparedStatement preparedStatement = null;
	Connection connection = null;

	try {
		// Getting a connection to the DB
		connection = JdbcUtils.getConnection();
				
		// Creating a string which will contain the query
		// PAY ATTENTION - BY USING THE ? (Question marks) WE PREVENT AN SQL INJECTION ATTACK
		 String sql = "UPDATE coupons.coupon SET end_date = ? WHERE coupon_id = ?";
		
		preparedStatement= connection.prepareStatement(sql);
		preparedStatement.setString(1, newCouponEndDate);
		preparedStatement.setLong(2, couponId); 

		preparedStatement.executeUpdate();
	} 

	catch (SQLException e) {
		throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CouponDao, updateCouponEndDate(); FAILED");
	} 

	finally {
		JdbcUtils.closeResources(connection, preparedStatement);
	}

}	
	
public void updateCouponAmount (long couponId, int newCouponAmount) throws ApplicationException {
	
	java.sql.PreparedStatement preparedStatement = null;
	Connection connection = null;

	try {
		// Getting a connection to the DB
		connection = JdbcUtils.getConnection();
				
		// Creating a string which will contain the query
		// PAY ATTENTION - BY USING THE ? (Question marks) WE PREVENT AN SQL INJECTION ATTACK
		 String sql = "UPDATE coupons.coupon SET amount = ? WHERE coupon_id = ?";
		
		preparedStatement= connection.prepareStatement(sql);
		preparedStatement.setInt(1, newCouponAmount);
		preparedStatement.setLong(2, couponId); 

		preparedStatement.executeUpdate();
	} 

	catch (SQLException e) {
//		throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CompanyDao, creatCompany(); FAILED");
	} 

	finally {
		JdbcUtils.closeResources(connection, preparedStatement);
	}

}	
	
public void updateCouponTypeById (long couponId, CouponType newCouponType) throws ApplicationException {
	
	java.sql.PreparedStatement preparedStatement = null;
	Connection connection = null;

	try {
		// Getting a connection to the DB
		connection = JdbcUtils.getConnection();
				
		// Creating a string which will contain the query
		// PAY ATTENTION - BY USING THE ? (Question marks) WE PREVENT AN SQL INJECTION ATTACK
		 String sql = "UPDATE coupons.coupon SET coupon_type = ? WHERE coupon_id = ?";
		
		preparedStatement= connection.prepareStatement(sql);
		preparedStatement.setString(1, newCouponType.name());
		preparedStatement.setLong(2, couponId); 

		preparedStatement.executeUpdate();
	} 

	catch (SQLException e) {
		throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CouponDao, updateCouponTypeById(); FAILED");
	} 

	finally {
		JdbcUtils.closeResources(connection, preparedStatement);
	}

}	
	
public void updateCouponMessageById (long couponId, String newCouponMessage) throws ApplicationException {
	
	java.sql.PreparedStatement preparedStatement = null;
	Connection connection = null;

	try {
		// Getting a connection to the DB
		connection = JdbcUtils.getConnection();
				
		// Creating a string which will contain the query
		// PAY ATTENTION - BY USING THE ? (Question marks) WE PREVENT AN SQL INJECTION ATTACK
		 String sql = "UPDATE coupons.coupon SET coupon_message = ? WHERE coupon_id = ?";
		
		preparedStatement= connection.prepareStatement(sql);
		preparedStatement.setString(1, newCouponMessage);
		preparedStatement.setLong(2, couponId); 

		preparedStatement.executeUpdate();
	} 

	catch (SQLException e) {
		throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CouponDao, updateCouponMessageById(); FAILED");
	} 

	finally {
		JdbcUtils.closeResources(connection, preparedStatement);
	}

}	
	

public void updateCouponPriceById (long couponId, Double newCouponPrice) throws ApplicationException {
	
	java.sql.PreparedStatement preparedStatement = null;
	Connection connection = null;

	try {
		// Getting a connection to the DB
		connection = JdbcUtils.getConnection();
				
		// Creating a string which will contain the query
		// PAY ATTENTION - BY USING THE ? (Question marks) WE PREVENT AN SQL INJECTION ATTACK
		 String sql = "UPDATE coupons.coupon SET coupon_price = ? WHERE coupon_id = ?";
		
		preparedStatement= connection.prepareStatement(sql);
		preparedStatement.setDouble(1, newCouponPrice);
		preparedStatement.setLong(2, couponId); 

		preparedStatement.executeUpdate();
	} 

	catch (SQLException e) {
		throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CouponDao, updateCouponPriceById(); FAILED");
	} 

	finally {
		JdbcUtils.closeResources(connection, preparedStatement);
	}

}	


public void updateCouponImageById (long couponId, String newCouponImage) throws ApplicationException {
	
	java.sql.PreparedStatement preparedStatement = null;
	Connection connection = null;

	try {
		// Getting a connection to the DB
		connection = JdbcUtils.getConnection();
				
		// Creating a string which will contain the query
		// PAY ATTENTION - BY USING THE ? (Question marks) WE PREVENT AN SQL INJECTION ATTACK
		 String sql = "UPDATE coupons.coupon SET coupon_image = ? WHERE coupon_id = ?";
		
		preparedStatement= connection.prepareStatement(sql);
		preparedStatement.setString(1, newCouponImage);
		preparedStatement.setLong(2, couponId); 

		preparedStatement.executeUpdate();
	} 

	catch (SQLException e) {
		throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CouponDao, updateCouponImageById(); FAILED");
	} 

	finally {
		JdbcUtils.closeResources(connection, preparedStatement);
	}

}	


public void deleteCouponById (long couponId) throws ApplicationException {
	
	
	java.sql.PreparedStatement preparedStatementDelete1 = null;
	java.sql.PreparedStatement preparedStatementDelete2 = null;

	Connection connection = null;
	String endDate = this.getCouponById(couponId).getCouponEndDate();

	try {
		// Getting a connection to the DB
		connection = JdbcUtils.getConnection();
		//Applying JDBC transaction to make sure SQL statements within a transaction block are all executed successfully,
		//if either one of the SQL statement within the transaction block is failed, abort and rollback everything within the transaction block.
		connection.setAutoCommit(false); 
	
		if (DateUtils.isCurrentDateAfterEndDate(endDate)) {
			updatePurchaseHistory(connection, couponId, "Expired");
			updateCouponHistory(connection, couponId, "Expired");
		}
		else {
			updateCouponHistory(connection, couponId, "Deleted");
			updatePurchaseHistory(connection, couponId, "Deleted");
		}
		
		
		//deleting all of the coupon purchases from the customer_coupons table 
		//(because it is a foreign key and the coupon cannot be deleted if foreign keys of it exists in another table)
		String sql = "delete from customer_coupon where coupon_id=?";
		preparedStatementDelete1= connection.prepareStatement(sql);
		preparedStatementDelete1.setLong(1, couponId);
		preparedStatementDelete1.executeUpdate();
		
		
		//deleting the coupon from the coupons table
		sql = "delete from coupon where coupon_id=?";
		preparedStatementDelete2= connection.prepareStatement(sql);
		preparedStatementDelete2.setLong(1, couponId);
		preparedStatementDelete2.executeUpdate();	
		  
		  connection.commit();
		  
	} 

	catch (Exception e) {
		try {
			connection.rollback();			
		} catch (SQLException e1) {
			throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CouponDao, deleteCouponById(); FAILED TO ROLLBACK STATEMENTS");		
		}
		throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CouponDao, deleteCouponById(); FAILED");
		
	} 

	finally {
		JdbcUtils.closeResources(connection, preparedStatementDelete1);
		JdbcUtils.closeResources(connection, preparedStatementDelete2);
		
	}

}


private void updateCouponHistory(Connection connection, long couponId, String reason) throws ApplicationException{
	
	java.sql.PreparedStatement preparedStatementInsert = null;
	java.sql.PreparedStatement preparedStatementUpdate = null;
	String date = DateUtils.getCurrentDate();
	
	try {
		
		 //Inserting coupon info to the coupons_history table	
		String sql = "insert into coupons_history (coupon_id, coupon_title,start_date, end_date, amount, coupon_type, coupon_message, coupon_price, coupon_image, company_id, coupon_status) select * from coupon c where c.coupon_id=?;";		
		 preparedStatementInsert= connection.prepareStatement(sql);
		 preparedStatementInsert.setLong(1, couponId); 
		 preparedStatementInsert.executeUpdate();	
		 
		 
		  //updating the cuopun_status field in the coupon_history table
		  sql = "update coupons_history set coupon_status = ?, dateOfRemoval=? where coupon_id=?";
		  preparedStatementUpdate= connection.prepareStatement(sql);
		  preparedStatementUpdate.setString(1, reason);
		  preparedStatementUpdate.setString(2, date);
		  preparedStatementUpdate.setLong(3, couponId);
		  preparedStatementUpdate.executeUpdate();		 	  		  
		
	}
	
	catch (Exception e) {
		try {
			connection.rollback();			
		} catch (SQLException e1) {
			throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CouponDao, deleteCouponById(); FAILED TO ROLLBACK STATEMENTS");		
		}
		throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CouponDao, deleteCouponById(); FAILED");
		
	} 

	finally {
		JdbcUtils.closeResources(null, preparedStatementInsert);
		JdbcUtils.closeResources(null, preparedStatementUpdate);
		
	}
	
}

private void updatePurchaseHistory(Connection connection, long couponId, String reason) throws ApplicationException{
	
	java.sql.PreparedStatement preparedStatementInsert = null;
	java.sql.PreparedStatement preparedStatementUpdate = null;
	String date = DateUtils.getCurrentDate();
	
	try {
		
		 //Inserting coupon info to the purchase_history table	
		String sql = "insert into purchase_history (purchase_id, customerId, coupon_id, company_id, purchased_amount,purchased_date) select * from customer_coupon cc where cc.coupon_id=?;";		
		 preparedStatementInsert= connection.prepareStatement(sql);
		 preparedStatementInsert.setLong(1, couponId); 
		 preparedStatementInsert.executeUpdate();	
		 
		 
		  //updating the cuopun_status field in the purchase_history table
		  sql = "update purchase_history set coupon_status = ? where coupon_id=?";
		  preparedStatementUpdate= connection.prepareStatement(sql);
		  preparedStatementUpdate.setString(1, reason);
		  preparedStatementUpdate.setLong(2, couponId);
		  preparedStatementUpdate.executeUpdate();		 	  		  
		
	}
	
	catch (Exception e) {
		try {
			connection.rollback();			
		} catch (SQLException e1) {
			throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CouponDao, deleteCouponById(); FAILED TO ROLLBACK STATEMENTS");		
		}
		throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CouponDao, deleteCouponById(); FAILED");
		
	} 

	finally {
		JdbcUtils.closeResources(null, preparedStatementInsert);
		JdbcUtils.closeResources(null, preparedStatementUpdate);
		
	}
	
}







public long createNewCoupon(Coupon coupon) throws ApplicationException {

	java.sql.PreparedStatement preparedStatement = null;
	Connection connection = null;

	try {
		// Getting a connection to the DB
		connection = JdbcUtils.getConnection();		
		
		// Creating a string which will contain the query
		String sql = "insert into coupon (coupon_title, start_date, end_date, amount, coupon_type, coupon_message, coupon_price, coupon_image,company_id) values (?,?,?,?,?,?,?,?,?)";

		preparedStatement=connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, coupon.getCouponTitle());
		preparedStatement.setString(2, coupon.getCouponStartDate());
		preparedStatement.setString(3, coupon.getCouponEndDate());
		preparedStatement.setInt(4, coupon.getAmount());
		preparedStatement.setString(5, coupon.getCouponType());
		preparedStatement.setString(6, coupon.getMessage()); 
		preparedStatement.setDouble(7, coupon.getPrice());
		preparedStatement.setString(8, coupon.getImage());
		preparedStatement.setLong(9, coupon.getCompany_Id());
		
		preparedStatement.executeUpdate();
		
		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		resultSet.first();
		long couponId = resultSet.getLong(1);
		return couponId;
	} 

	catch (SQLException e) {
		throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CouponDao, createNewCoupon(); FAILED");
	} 

	finally {
		JdbcUtils.closeResources(connection, preparedStatement);
	}

}


//getting all the active coupons of a company - get the coupons by company_id
public List<Coupon> getActiveCouponsByCompanyId(long company_id) throws ApplicationException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Coupon coupon = null;
	List<Coupon> couponList = new ArrayList<>();
	
	try {
		connection = JdbcUtils.getConnection();
		String sql = "SELECT * FROM coupon where company_id = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, company_id);
		resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) {
			return null;
		}
		
		// Extract coupons's data by parameters from the database into a list
		coupon = extractCouponFromResultSet(resultSet);
		couponList.add(coupon);
		while(resultSet.next()) {
			coupon = extractCouponFromResultSet(resultSet);
			couponList.add(coupon);

			}

	}

	catch (SQLException e) {
		//	e.printStackTrace();
			throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CouponDao, getActiveCouponsByCompanyId(long)(); FAILED");
	}
	
	finally {
		
		JdbcUtils.closeResources(connection, preparedStatement, resultSet);
	}
	
	return couponList;
}


//We check if the coupon's name is already exist in the DB
public boolean isCouponExistByName(String coupon_title) throws ApplicationException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	try {
		connection = JdbcUtils.getConnection();
		String sql = "SELECT * FROM coupon where coupon_title = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, coupon_title);
		resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) {
			System.out.println("false");
			return false;
		}
		else {
			System.out.println("true");
			return true;
		}
	
	}
		catch (SQLException e) {
			//	e.printStackTrace();
				throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CouponDao, isCouponExistByName(); FAILED");
		}
		
		finally {
			
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
				
}

// getting a list of all the coupons a customer had purchased, by customer_id.
public List<Coupon> getCustomerCoupon (long customerId) throws ApplicationException{
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Coupon coupon = null;
	List<Coupon> list = new ArrayList<Coupon> ();
	
	try {
		connection = JdbcUtils.getConnection();
		
		String sql = "SELECT * from coupon c inner join customer_coupon cc on c.coupon_id=cc.coupon_id where cc.customerId = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, customerId);
		resultSet = preparedStatement.executeQuery();
		
		//inserting the 1st coupon to the list
		coupon = extractCouponFromResultSet(resultSet);
		list.add(coupon);
		
		//inserting all coupons, starting from the second.
		while (resultSet.next()) {
			coupon = extractCouponFromResultSet(resultSet);
			list.add(coupon);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CouponDao, getCustomerCoupon(); FAILED");
	} finally {
		JdbcUtils.closeResources(connection, preparedStatement);
	}
	
	return list;
}



//purchase coupon
	//updating the customer_coupon table when a customer purchased a new coupon.	
	public void purchaseCoupon (long customerId, long couponId, int purchaseAmount) throws ApplicationException	{
		
		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;
		Coupon coupon= this.getCouponById(couponId);
		long companyId = coupon.getCompany_Id();
		String purchaseDate = DateUtils.getCurrentDate();
		int coupunAmountBefore = coupon.getAmount();
			
		try {
			
			//we are subtracting the purchaseAmount from the remaining coupon's amount
			updateCouponAmount(couponId, (coupon.getAmount() - purchaseAmount));
			connection = JdbcUtils.getConnection(); 
			
			String sql = "insert into coupons.customer_coupon (customer_id, coupon_id, purchase_amount, purchase_date,company_id) values (?,?,?,?,?)";
			
			preparedStatement= connection.prepareStatement(sql); 
			preparedStatement.setLong(1, customerId);
			preparedStatement.setLong(2, couponId);
			preparedStatement.setInt(3, purchaseAmount);
			preparedStatement.setString(4, purchaseDate);
			preparedStatement.setLong(5, companyId);
			preparedStatement.executeUpdate();
			
			
		}
		catch (Exception e){
			updateCouponAmount(couponId,coupunAmountBefore);//in case we got an exception we update the coupon's amount to the previous amount
			throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CouponDao, purchaseCoupon(); FAILED");
		}
		
		finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}


	//We check if the coupon's ID is already exist in the DB
	public boolean isCouponExistById(long couponId) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM coupon where coupon_id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, couponId);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return false;
			}
			else {
				return true;
			}
		
		}
			catch (SQLException e) {
					throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CouponDao, isCouponExistById(); FAILED");
			}
			
			finally {
				
				JdbcUtils.closeResources(connection, preparedStatement, resultSet);
			}
					
	}
	
	
	public List<Coupon> getCouponsBeforeEndDate(String EndDate) throws ApplicationException{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;
		List<Coupon> couponList = new ArrayList<>();
		
		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM coupon where end_date < ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, EndDate);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			
			// Extract coupons's data by parameters from the database into a list
			coupon = extractCouponFromResultSet(resultSet);
			couponList.add(coupon);
			while(resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);
				couponList.add(coupon);

				}

		}

		catch (SQLException e) {
			//	e.printStackTrace();
				throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CouponDao, getCouponsBeforeEndDate(); FAILED");
		}
		
		finally {
			
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		
		return couponList;
	}
		
		
		
	//We check if the coupon's Title already exist in the DB for the given company
	public boolean isCompanyCouponExistByName(String couponTitle, long copmanyId) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM coupon where coupon_title = ? AND company_id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, couponTitle);
			preparedStatement.setLong(2, copmanyId);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return false;
			}
			else {
				return true;
			}
		
		}
			catch (SQLException e) {
				//	e.printStackTrace();
					throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CouponDao, isCompanyCouponExistByName(); FAILED");
			}
			
			finally {
				
				JdbcUtils.closeResources(connection, preparedStatement, resultSet);
			}					
	}
	

	
public void deleteExpiredCoupons() throws ApplicationException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;
		String currentDate = DateUtils.getCurrentDate().toString().replaceAll("/", "-");
		
		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM coupon where end_date < ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, currentDate);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return;
			}
			
			// deleting the expired coupons that returned from the query above
			coupon = extractCouponFromResultSet(resultSet);
			this.deleteCouponById(coupon.getCouponId());
			while(resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);
				this.deleteCouponById(coupon.getCouponId());

				}
		}

		catch (SQLException e) {
				throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CouponDao, getAllCoupons(); FAILED");
		}
		
		finally {
			
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		
	}
	

//getting the list of coupons within the given price range
public List<Coupon> getCouponsByPrices (double minPrice, double maxPrice) throws ApplicationException {
	
	PreparedStatement preparedStatement = null;
	Connection connection = null;
	ResultSet resultSet = null;
	List<Coupon> couponsList = new ArrayList<Coupon>();
	Coupon coupon= null;

	try {
		connection = JdbcUtils.getConnection();
		
		String sql = "select * from coupon where coupon_price >= ? and coupon_price<=?";
		preparedStatement= connection.prepareStatement(sql);
		preparedStatement.setDouble(1, minPrice);
		preparedStatement.setDouble(2, maxPrice);
		resultSet=preparedStatement.executeQuery();
		
		if (!resultSet.next()) {
			return null;
		}
		//inserting the 1st
		coupon = extractCouponFromResultSet(resultSet);
		couponsList.add(coupon);
		//inserting starting from the 2nd
		while (resultSet.next()) {
			coupon = extractCouponFromResultSet(resultSet);
			couponsList.add(coupon);
		}
	} catch (Exception e) {
		throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "couponsDao:getCouponsByPrices failed. "+DateUtils.getCurrentDateAndTime());
	}
	finally {
		JdbcUtils.closeResources(connection, preparedStatement);
	}
	
	return couponsList;
}



//getting company coupons - get coupons by type
public List<Coupon> getCouponsByType(CouponType couponType) throws ApplicationException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	String type = couponType.name();
	Coupon coupon = null;
	List<Coupon> couponList = new ArrayList<>();
	
	try {
		connection = JdbcUtils.getConnection();
		String sql = "SELECT * FROM coupon where coupon_type = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, type);
		resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) {
			return null;
		}
		
		// Extract coupons's data by parameters from the database into a list
		coupon = extractCouponFromResultSet(resultSet);
		couponList.add(coupon);
		while(resultSet.next()) {
			coupon = extractCouponFromResultSet(resultSet);
			couponList.add(coupon);

			}

	}

	catch (SQLException e) {
			throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CouponDao, getCouponsByType(); FAILED");
	}
	
	finally {
		
		JdbcUtils.closeResources(connection, preparedStatement, resultSet);
	}
	
	return couponList;
}


//getting customer active coupons - getting all the the active coupons the customer purchased	
public List<Coupon> getAllCustomerActiveCoupons (long customerId) throws ApplicationException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Coupon coupon = null;
	List<Coupon> couponsList = new ArrayList<Coupon> ();
	
	try {
		connection = JdbcUtils.getConnection();
		
		String sql = "SELECT * from coupon c inner join customer_coupon cc on c.coupon_id=cc.coupon_id where cc.customer_id = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, customerId);
		resultSet = preparedStatement.executeQuery();
		
		if (!resultSet.next()) {
			return null;
		}
		
		//inserting the 1st coupon to the list
		coupon = extractCouponFromResultSet(resultSet);
		couponsList.add(coupon);
//		
		//inserting all coupons
		while (resultSet.next()) {
			coupon = extractCouponFromResultSet(resultSet);
			couponsList.add(coupon);
		}
		
	}catch (Exception e) {
		throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "failed to create a list of all coupons of a customer "+DateUtils.getCurrentDateAndTime());
	}
	finally {
		JdbcUtils.closeResources(connection, preparedStatement);
	}
	
	return couponsList;
}
	
//getting all the the coupons the customer purchased - including both active and deleted coupons
public List<Coupon> getAllCustomerCoupons (long customerId) throws ApplicationException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Coupon coupon = null;
	List<Coupon> couponsList = new ArrayList<Coupon> ();
	
	try {
		connection = JdbcUtils.getConnection();
		
		String sql = "(SELECT c.*,null as dateOfRemoval FROM coupon c JOIN customer_coupon cc ON c.coupon_id = cc.coupon_id " + 
				" WHERE cc.customer_id = ?) " + 
				"UNION" +
				" (SELECT ch.* FROM coupons_history ch JOIN purchase_history ph ON ch.coupon_id = ph.coupon_id" + 
				" WHERE ph.customerId = ?)"; 
				
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, customerId);
		preparedStatement.setLong(2, customerId);
		resultSet = preparedStatement.executeQuery();
		
		if (!resultSet.next()) {
			return null;
		}
		
		//inserting the 1st coupon to the list
		coupon = extractCouponFromResultSet(resultSet);
		couponsList.add(coupon);
//		
		//inserting all coupons
		while (resultSet.next()) {
			coupon = extractCouponFromResultSet(resultSet);
			couponsList.add(coupon);
		}
		
	}catch (Exception e) {
		throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "failed to create a list of all coupons of a customer "+DateUtils.getCurrentDateAndTime());
	}
	finally {
		JdbcUtils.closeResources(connection, preparedStatement);
	}
	
	return couponsList;
}
	

public void updateCoupon (Coupon coupon) throws ApplicationException {
	
	java.sql.PreparedStatement preparedStatement = null;
	Connection connection = null;
	

	try {
		// Getting a connection to the DB
		connection = JdbcUtils.getConnection();
				
		// Creating a string which will contain the query
		// PAY ATTENTION - BY USING THE ? (Question marks) WE PREVENT AN SQL INJECTION ATTACK
		 String sql = "UPDATE coupons.coupon SET coupon_title = ?, end_date =?, amount =?, coupon_type=?, coupon_message=?, coupon_price=?, coupon_image=?, company_id=? WHERE coupon_id = ?";
		
		preparedStatement= connection.prepareStatement(sql);
		preparedStatement.setString(1, coupon.getCouponTitle());
		preparedStatement.setString(2, coupon.getCouponEndDate());
		preparedStatement.setInt(3, coupon.getAmount());
		preparedStatement.setString(4, coupon.getCouponType());
		preparedStatement.setString(5, coupon.getMessage());
		preparedStatement.setDouble(6, coupon.getPrice());
		preparedStatement.setString(7, coupon.getImage());
		preparedStatement.setLong(8, coupon.getCompany_Id());
		preparedStatement.setLong(9, coupon.getCouponId());
		 
		preparedStatement.executeUpdate();
	} 

	catch (SQLException e) {
		throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CouponDao, updateCouponImageById(); FAILED");
	} 

	finally {
		JdbcUtils.closeResources(connection, preparedStatement);
	}

}	
	
	
//returning a list of all coupons of a certain company - both active and inactive(from history)
public List<Coupon> getAllCouponsByCompanyId(long company_id) throws ApplicationException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Coupon coupon = null;
	List<Coupon> couponList = new ArrayList<>();
	
	try {
		connection = JdbcUtils.getConnection();
		
		String sql = " (SELECT c.*,null AS dateOfRemoval FROM coupon c WHERE company_id=?"
				   + " UNION"
				   + " SELECT ch.* FROM coupons_history ch WHERE company_id=? )";
		
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, company_id);
		preparedStatement.setLong(2, company_id);
		resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) {
			return null;
		}
		
		// Extract coupons's data by parameters from the database into a list
		coupon = extractCouponFromResultSet(resultSet);
		couponList.add(coupon);
		while(resultSet.next()) {
			coupon = extractCouponFromResultSet(resultSet);
			couponList.add(coupon);

			}

	}

	catch (SQLException e) {
		//	e.printStackTrace();
			throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CouponDao, getAllCouponsByCompanyId(); FAILED");
	}
	
	finally {
		
		JdbcUtils.closeResources(connection, preparedStatement, resultSet);
	}
	
	return couponList;
}








	





 

}



