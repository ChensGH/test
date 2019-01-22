package com.chen.coupons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chen.coupons.beans.Company;
import com.chen.coupons.beans.Customer;
import com.chen.coupons.enums.ErrorType;
import com.chen.coupons.exceptions.ApplicationException;
import com.chen.coupons.utils.DateUtils;
import com.chen.coupons.utils.JdbcUtils;

public class CustomerDao {
	
	public long createCustomer(Customer customer) throws ApplicationException {

		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;

		try {
			// Getting a connection to the DB
			connection = JdbcUtils.getConnection();		
			
			// Creating a string which will contain the query
			// PAY ATTENTION - BY USING THE ? (Question marks) WE PREVENT AN SQL INJECTION ATTACK
			String sql = "insert into customer (customer_name, customer_password) values (?,?)";

			preparedStatement= connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, customer.getCustomerName());
			preparedStatement.setString(2, customer.getCustomerPassword());

			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.first();
			long customerId = resultSet.getLong(1);
			return customerId;
		} 

		catch (SQLException e) {
			throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CustomerDao, createCustomer(); FAILED");
		} 

		finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
		
	
	}

	
	public Customer getCustomerByCustomerId(long customerId) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Customer customer = null;
		
		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM customer WHERE customer_id = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, customerId);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			customer = extractCustomerFromResultSet(resultSet);

			
		}

		catch (SQLException e) {
//			e.printStackTrace();
			throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CustomerDao, getCustomerByCustomerId(); FAILED");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return customer;
	}
	
	//Extract company's data by parameters from the database
	private Customer extractCustomerFromResultSet(ResultSet resultSet) throws SQLException {
		Customer customer = new Customer();
		customer.setCustomerId(resultSet.getLong("customer_id"));
		customer.setCustomerName(resultSet.getString("customer_name"));
		customer.setCustomerPassword(resultSet.getString("customer_password"));

		return customer;
	}

	
	public List<Customer> getAllCustomers() throws ApplicationException /*throws ApplicationException */{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Customer customer = null;
		List<Customer> customerList = new ArrayList<>();
		
		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM customer";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			
			// Extract company's data by parameters from the database into a list
			customer = extractCustomerFromResultSet(resultSet);
			customerList.add(customer);
			while(resultSet.next()) {
				customer = extractCustomerFromResultSet(resultSet);
				customerList.add(customer);

				}
	
		}

		catch (SQLException e) {
			//	e.printStackTrace();
				throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CustomerDao, getAllCustomers(); FAILED");
		}
		
		finally {
			
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		
		return customerList;
	}
	
	

	//check login parameters method, returns the customer if valid
public Customer customerCheckLogin(String cutsomerName, String password) throws ApplicationException {//SQLException /*throws ApplicationException */{
	
	Connection connection = null;
	java.sql.PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Customer customer = null;
	
	try {
		connection = JdbcUtils.getConnection();
		String sql = "SELECT * FROM customer WHERE customer_name = ? AND customer_password = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, cutsomerName);
		preparedStatement.setString(2, password);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			customer = extractCustomerFromResultSet(resultSet);
		}
	}

	catch (SQLException e) {
		//	e.printStackTrace();
			throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CustomerDao, checkLogin(); FAILED");
	}
	
	finally {
		
		JdbcUtils.closeResources(connection, preparedStatement, resultSet);
	}
	
	return customer;
}


//checking if the customer ID exists in the DB
public boolean doesCustomerExistById(long customerId) throws ApplicationException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	try {
		connection = JdbcUtils.getConnection();
		String sql = "SELECT * FROM customer where customer_id = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, customerId);
		resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) {
			return false;
		}
		else {
			return true;
		}
	
	}
		catch (SQLException e) {
				throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CustomerDao, isCustomerExistById(); FAILED");
		}
		
		finally {
			
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
				
}


//checking if the customer user name exists in the DB
public boolean doesCustomerExistByName(String customerName) throws ApplicationException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	try {
		connection = JdbcUtils.getConnection();
		String sql = "SELECT * FROM customer where customer_name = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, customerName);
		resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) {
			return false;
		}
		else {
			return true;
		}
	
	}
		catch (SQLException e) {
			
				throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CustomerDao, doesCustomerExistByName(); FAILED");
		}
		
		finally {
			
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
				
}



public void updateCustomer (Customer customer)  throws ApplicationException  {
	
	java.sql.PreparedStatement preparedStatement = null;
	Connection connection = null;
	long customerId = customer.getCustomerId();
	String newCustomerPassword = customer.getCustomerPassword();
	String newCustomerName = customer.getCustomerName();

	try {
		// Getting a connection to the DB
		connection = JdbcUtils.getConnection();
				
		// Creating a string which will contain the query
		// PAY ATTENTION - BY USING THE ? (Question marks) WE PREVENT AN SQL INJECTION ATTACK
		 String sql = "UPDATE coupons.customer SET customer_password = ? , customer_name = ? WHERE customer_id = ?";
		
		preparedStatement= connection.prepareStatement(sql);
		preparedStatement.setString(1, newCustomerPassword);
		preparedStatement.setString(2, newCustomerName);
		preparedStatement.setLong(3, customerId); 

		preparedStatement.executeUpdate();
	} 

	catch (SQLException e) {
		throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CustomerDao, updateCustomer(); FAILED");
	} 

	finally {
		JdbcUtils.closeResources(connection, preparedStatement);
	}

}

//deleting a customer from the table
public void deleteCustomer (long customerId) throws ApplicationException {
	
	java.sql.PreparedStatement preparedStatementInsert = null;
	java.sql.PreparedStatement preparedStatementDelete1 = null;
	java.sql.PreparedStatement preparedStatementDelete2 = null;
	java.sql.PreparedStatement preparedStatementUpdate = null;


	Connection connection = null;
	
	try {
		connection = JdbcUtils.getConnection();
		
		//applying JDBC transaction make sure all of the statements are executed successfully, 
		//if either one of the statements within the block fails, abort and rollback everything in the transaction block.
		connection.setAutoCommit(false);
		
		// copying the data of purchases of the soon-to-be deleted customers to the purchase history table. 
		//(since the customer_coupon table only contains data of purchases of active customers and active coupons.)
		String sql = "insert into purchase_history (purchase_id, customerId, coupon_id, company_id, purchased_amount,purchased_date) select * from customer_coupon cc where cc.customer_id=?;";
		preparedStatementInsert= connection.prepareStatement(sql);
		preparedStatementInsert.setLong(1, customerId);
		preparedStatementInsert.executeUpdate();
		
		//deleting all of the coupon purchases from the customer_coupons table 
		//(because it is a foreign key and the coupon cannot be deleted if foreign keys of it exists in another table)
		sql = "delete from customer_coupon where customer_id=?";
		preparedStatementDelete1= connection.prepareStatement(sql);
		preparedStatementDelete1.setLong(1, customerId);
		preparedStatementDelete1.executeUpdate();
		
		//deleting the customer from the customers table
		sql = "delete from customer where customer_id=?";
		preparedStatementDelete2= connection.prepareStatement(sql);
		preparedStatementDelete2.setLong(1, customerId);
		preparedStatementDelete2.executeUpdate();
		
		//updating the customerSstatus field in the purchase_history table
		sql = "update purchase_history set customer_status = ? where customerId=?";
		preparedStatementUpdate= connection.prepareStatement(sql);
		preparedStatementUpdate.setString(1, "deleted");
		preparedStatementUpdate.setLong(2, customerId);
		preparedStatementUpdate.executeUpdate();
			
	
		//Committing the statements (executes only if all statements are successful).
		connection.commit();

	}	catch (Exception e) {
		try {
			connection.rollback();
		} catch (SQLException e1) {
			throw new ApplicationException(e1, ErrorType.SYSTEM_ERROR, "error in CustomerDao -delete customer: failed to rollback previous statements "+DateUtils.getCurrentDateAndTime());
		}
		throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "error in CustomerDao: failed to delete a customer " +DateUtils.getCurrentDateAndTime());
	}
	finally {
		JdbcUtils.closeResources(connection, preparedStatementInsert);
		JdbcUtils.closeResources(null, preparedStatementDelete1);
		JdbcUtils.closeResources(null, preparedStatementDelete2);
		JdbcUtils.closeResources(null, preparedStatementUpdate);
	}
}













	
	
	
	

}
