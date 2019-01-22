package com.chen.coupons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.chen.coupons.beans.Company;
import com.chen.coupons.enums.ErrorType;
import com.chen.coupons.exceptions.ApplicationException;
import com.chen.coupons.utils.DateUtils;
import com.chen.coupons.utils.JdbcUtils;


public class CompanyDao{

	public long createCompany(Company company) throws ApplicationException{

		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;

		try {
			// Getting a connection to the DB
			connection = JdbcUtils.getConnection();
			
			
			
			// Creating a string which will contain the query
			// PAY ATTENTION - BY USING THE ? (Question marks) WE PREVENT AN SQL INJECTION ATTACK
			String sql = "insert into company (company_name, company_password, company_email) values (?,?,?)";

			preparedStatement= connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, company.getCompanyName());
			preparedStatement.setString(2, company.getCompanyPassword());
			preparedStatement.setString(3, company.getCompanyEmail());

			preparedStatement.executeUpdate();
			
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.first();
			long companyId = resultSet.getLong(1);
			return companyId;
		} 

		catch (SQLException e) {
			throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CompanyDao, creatCompany(); FAILED");
		} 

		finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	
	public Company getCompanyByComapnyId(long companyId)  throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Company company = null;
		
		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM company WHERE company_id = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, companyId);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			company = extractCompanyFromResultSet(resultSet);

			
		}

		catch (SQLException e) {
//			e.printStackTrace();
			throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CompanyDao, getCompanyByComapnyId(); FAILED");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return company;
	}
	
	//Extract company's data by parameters from the database
	private Company extractCompanyFromResultSet(ResultSet resultSet) throws SQLException {
		Company company = new Company();
		company.setCompanyId(resultSet.getLong("company_id"));
		company.setCompanyName(resultSet.getString("company_name"));
		company.setCompanyPassword(resultSet.getString("company_password"));
		company.setCompanyEmail(resultSet.getString("company_email"));

		return company;
	}

	
	
	public List<Company> getAllCompanies()  throws ApplicationException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Company company = null;
		List<Company> companyList = new ArrayList<>();
		
		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM company";
			preparedStatement = connection.prepareStatement(sql);
			//preparedStatement.setLong(1, companyId);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			
			// Extract company's data by parameters from the database into a list
			company = extractCompanyFromResultSet(resultSet);
			companyList.add(company);
			while(resultSet.next()) {
				company = extractCompanyFromResultSet(resultSet);
				companyList.add(company);

				}
	
		}

		catch (SQLException e) {
			//	e.printStackTrace();
				throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CompanyDao, getAllCompanies(); FAILED");
		}
		
		finally {
			
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		
		return companyList;
	}
	
	
	public void updateCompany (Company company) throws ApplicationException {
		
		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;
		long companyId = company.getCompanyId();
		String companyName = company.getCompanyName();
		String newCompanyPassword = company.getCompanyPassword();
		String newCompanyEmail = company.getCompanyEmail();

		try {
			// Getting a connection to the DB
			connection = JdbcUtils.getConnection();
					
			// Creating a string which will contain the query
			// PAY ATTENTION - BY USING THE ? (Question marks) WE PREVENT AN SQL INJECTION ATTACK
			 String sql = "UPDATE coupons.company SET company_password = ?, company_name=?, company_email=? WHERE company_id = ?";
			
			preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, newCompanyPassword);
			preparedStatement.setString(2, companyName);
			preparedStatement.setString(3, newCompanyEmail);
			preparedStatement.setLong(4, companyId); //company.getCompanyId()

			preparedStatement.executeUpdate();
		} 

		catch (SQLException e) {
			throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CompanyDao, updateCompanyPassword(); FAILED");
		} 

		finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}
	
	
	
	public void deleteCompanyById (long companyId) throws ApplicationException {
		
		java.sql.PreparedStatement preparedStatement = null;
		java.sql.PreparedStatement preparedStatementDelete1 = null;
		java.sql.PreparedStatement preparedStatementDelete2 = null;

		Connection connection = null;		
			
	 	try {
				// Getting a connection to the DB
				connection = JdbcUtils.getConnection();
				//Applying JDBC transaction to make sure SQL statements within a transaction block are all executed successfully,
				//if either one of the SQL statement within the transaction block is failed, abort and rollback everything within the transaction block.
				connection.setAutoCommit(false); 
			
					updatePurchaseHistory(connection, companyId, "company deleted");
					updateCouponHistory(connection, companyId, "company deleted");
				
		
				
				//deleting all of the coupon purchases from the customer_coupons table 
				//(because it is a foreign key and the coupon cannot be deleted if foreign keys of it exists in another table)
				String sql = "delete from customer_coupon where company_id=?";
				preparedStatementDelete1= connection.prepareStatement(sql);
				preparedStatementDelete1.setLong(1, companyId);
				preparedStatementDelete1.executeUpdate();
				
				
				//deleting the coupon from the coupons table
				sql = "delete from coupon where company_id=?";
				preparedStatementDelete2= connection.prepareStatement(sql);
				preparedStatementDelete2.setLong(1, companyId);
				preparedStatementDelete2.executeUpdate();	
				  
				
				sql = "DELETE from coupons.company WHERE company_id = ?";
				preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setLong(1, companyId); //company.getCompanyId()
				preparedStatement.executeUpdate();
				
				  connection.commit();
			
			
		} 
		catch (Exception e) {
			try {
				connection.rollback();			
			} catch (SQLException e1) {
				throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CompanyDao, deleteCompanyById(); FAILED TO ROLLBACK STATEMENTS");		
			}
			throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CompanyDao, deleteCompanyById(); FAILED");
			
		} 

		finally {
			JdbcUtils.closeResources(connection, preparedStatement);
			JdbcUtils.closeResources(connection,preparedStatementDelete1);
			JdbcUtils.closeResources(connection,preparedStatementDelete2);
		}

	}
	
	
	
	//check company login parameters method, and returns the company if valid
public Company companyCheckLogin(String companyName, String password) throws ApplicationException {
	
	Connection connection = null;
	java.sql.PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Company company = null;
	
	try {
		connection = JdbcUtils.getConnection();
		String sql = "SELECT * FROM company WHERE company_name = ? AND company_password = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, companyName);
		preparedStatement.setString(2, password);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			company = extractCompanyFromResultSet(resultSet);
		}
			
	}

	catch (SQLException e) {
		
			throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CompanyDao, companyCheckLogin(); FAILED");
	}
	
	finally {
		
		JdbcUtils.closeResources(connection, preparedStatement, resultSet);
	}
	
	return company;
}

	

//checking if the company ID exists in the DB
public boolean doesCompanyExistById(long companyId) throws ApplicationException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	try {
		connection = JdbcUtils.getConnection();
		String sql = "SELECT * FROM company where company_id = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, companyId);
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
				throw new ApplicationException(e,ErrorType.SYSTEM_ERROR,"Error in CompanyDao, isCompanyExistById(); FAILED " + DateUtils.getCurrentDateAndTime());
		}
		
		finally {
			
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
				
}

	
public boolean doesCompanyExistByName(String companyName) throws ApplicationException{
	java.sql.PreparedStatement preparedStatement = null;
	Connection connection = null;
	ResultSet resultSet = null;
	
	try {
		connection = JdbcUtils.getConnection();
		
		String sql = "select * from company where company_name=? ";
		preparedStatement= connection.prepareStatement(sql); 
		preparedStatement.setString(1, companyName);
		resultSet=preparedStatement.executeQuery();
	
		if (!resultSet.next()) {
			return false;
		}
		else {
			return true;
		}   
	}
	catch (Exception e) {
		throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "error in CompanyDao:doesCompanyExistByName Failed. " +DateUtils.getCurrentDateAndTime());
	}
	finally {
		JdbcUtils.closeResources(connection, preparedStatement);
	}
	}
	
	
	
private void updateCouponHistory(Connection connection, long companyId, String reason) throws ApplicationException{
	
	java.sql.PreparedStatement preparedStatementInsert = null;
	java.sql.PreparedStatement preparedStatementUpdate = null;
	String date = DateUtils.getCurrentDate();
	
	try {
		
		 //Inserting coupon info to the coupons_history table	
		String sql = "insert into coupons_history (coupon_id, coupon_title,start_date, end_date, amount, coupon_type, coupon_message, coupon_price, coupon_image, company_id, coupon_status) select * from coupon c where c.company_id=?;";		
		 preparedStatementInsert= connection.prepareStatement(sql);
		 preparedStatementInsert.setLong(1, companyId); 
		 preparedStatementInsert.executeUpdate();	
		 
		 
		  //updating the cuopun_status field in the coupon_history table
		  sql = "update coupons_history set coupon_status = ?, dateOfRemoval=? where company_id=?";
		  preparedStatementUpdate= connection.prepareStatement(sql);
		  preparedStatementUpdate.setString(1, reason);
		  preparedStatementUpdate.setString(2, date);
		  preparedStatementUpdate.setLong(3, companyId);
		  preparedStatementUpdate.executeUpdate();		 	  		  
		
	}
	
	catch (Exception e) {
		try {
			connection.rollback();			
		} catch (SQLException e1) {
			throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CouponDao, updateCouponHistory(); FAILED TO ROLLBACK STATEMENTS");		
		}
		throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CouponDao, updateCouponHistory(); FAILED");
		
	} 

	finally {
		JdbcUtils.closeResources(null, preparedStatementInsert);
		JdbcUtils.closeResources(null, preparedStatementUpdate);
		
	}
	
}



private void updatePurchaseHistory(Connection connection, long company_id, String reason) throws ApplicationException{
	
	java.sql.PreparedStatement preparedStatementInsert = null;
	java.sql.PreparedStatement preparedStatementUpdate = null;
	String date = DateUtils.getCurrentDate();
	
	try {
		
		 //Inserting coupon info to the purchase_history table	
		String sql = "insert into purchase_history (purchase_id, customerId, coupon_id, company_id, purchased_amount,purchased_date) select * from customer_coupon cc where cc.company_id=?;";		
		 preparedStatementInsert= connection.prepareStatement(sql);
		 preparedStatementInsert.setLong(1, company_id); 
		 preparedStatementInsert.executeUpdate();	
		 
		 
		  //updating the cuopun_status field in the purchase_history table
		  sql = "update purchase_history set coupon_status = ?,company_status=? where company_id=?";
		  preparedStatementUpdate= connection.prepareStatement(sql);
		  preparedStatementUpdate.setString(1, reason);
		  preparedStatementUpdate.setString(2, "deleted");
		  preparedStatementUpdate.setLong(3, company_id);
		  preparedStatementUpdate.executeUpdate();		 	  		  
		
	}
	
	catch (Exception e) {
		try {
			connection.rollback();			
		} catch (SQLException e1) {
			throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CouponDao, updatePurchaseHistory(); FAILED TO ROLLBACK STATEMENTS");		
		}
		throw new ApplicationException( e, ErrorType.SYSTEM_ERROR,"Error in CouponDao, updatePurchaseHistory(); FAILED");
		
	} 

	finally {
		JdbcUtils.closeResources(null, preparedStatementInsert);
		JdbcUtils.closeResources(null, preparedStatementUpdate);
		
	}
	
}


	
	
	
		
	}
	
	
	
	
	
	
	
	
	
	
	

	
