package com.chen.coupons.controllers;

import java.util.List;


import org.omg.PortableServer.ThreadPolicyOperations;

import com.chen.coupons.beans.Coupon;
import com.chen.coupons.enums.CouponType;
import com.chen.coupons.dao.CompanyDao;
import com.chen.coupons.dao.CouponDao;
import com.chen.coupons.dao.CustomerDao;
import com.chen.coupons.enums.ErrorType;
import com.chen.coupons.exceptions.ApplicationException;
import com.chen.coupons.utils.DateUtils;

public class CouponController {
	
	//Assigning a local variable for each one of the 'dao' objects,
	//in order to gain access to the methods communicating with the DB. 
	private CouponDao couponDao;
	private CompanyDao companyDao;
	private CustomerDao customerDao;

	public CouponController(){
	this.couponDao=new CouponDao();
	this.companyDao=new CompanyDao();
	this.customerDao=new CustomerDao();
	}
	
	
	public Coupon getCouponById (long couponId) throws ApplicationException {
		validateGetCouponById(couponId);
		//If we didn't catch any exception, we call the 'getCouponById' method.
		return this.couponDao.getCouponById(couponId);
		
	}
	
	//getAllCoupons
	public List<Coupon> getAllCoupons () throws ApplicationException {
		validateGetAllCoupons();
		
		////If we didn't catch any exception, we call the 'getAllCoupons' method.
		return this.couponDao.getAllCoupons();
		
	}
	
	
	//get Only Active Coupons
		public List<Coupon> getAllActiveCoupons () throws ApplicationException {
			validateGetAllCoupons();
			
			////If we didn't catch any exception, we call the 'getAllActiveCoupons' method.
			return this.couponDao.getAllActiveCoupons();
			
		}
	
	
	
	public long createCoupon(Coupon coupon)throws ApplicationException{
		//We validate the creation of a new coupon,
			validateCreateCoupon(coupon);
			
			
			//If we didn't catch any exception, we call the 'createCoupon' method.
			return this.couponDao.createNewCoupon(coupon);
			
		}
	
	public void updateCoupon(Coupon coupon) throws ApplicationException{
		
		validateCouponId(coupon.getCouponId());
		
		validateUpdateCoupon(coupon);
		
		//if we didn't catch any exception we call the updateCoupon method from the dao
		this.couponDao.updateCoupon(coupon);	
		
	}
	
	
	
	public void deleteCouponById (long couponId) throws ApplicationException{
		// We check if the coupon id exists
		validateDeleteCouponId(couponId);
		
		////If we didn't catch any exception, we call the 'deleteCouponById' method.
		this.couponDao.deleteCouponById(couponId);
	}
	
	
	public void purchaseCoupon(long customerId, long couponId, int purchaseAmount) throws ApplicationException{
		
		validatePurchase(customerId, couponId, purchaseAmount);
		
		//If we didn't catch any exception, we call the 'purchaseCoupon' method.
		this.couponDao.purchaseCoupon(customerId, couponId, purchaseAmount);
	}

	
	public List<Coupon> getAllCouponsByCompanyId(long companyId) throws ApplicationException{
		
		validateGetCouponsByCompany(companyId);
		
		//If we didn't catch any exception, we call the 'getCouponsByCompanyId' method.
		return this.couponDao.getAllCouponsByCompanyId(companyId);
		
	}
	
	
public List<Coupon> getActiveCouponsByCompanyId(long companyId) throws ApplicationException{
		
		validateGetCouponsByCompany(companyId);
		
		//If we didn't catch any exception, we call the 'getCouponsByCompanyId' method.
		return this.couponDao.getActiveCouponsByCompanyId(companyId);
		
	}
	
	
	public List<Coupon> getCouponsBeforeEndDate(String endDate) throws ApplicationException {
		validateGetCouponsBeforeEndDate(endDate);
		
		//If we didn't catch any exception, we call the 'getCouponsBeforeEndDate' method.
			return this.couponDao.getCouponsBeforeEndDate(endDate);
		
	}
	
	public List<Coupon> getCouponsByPrices(double minPrice, double maxPrice) throws ApplicationException{
		
		validateGetCouponsByPrices(minPrice, maxPrice);
		
		//If we didn't catch any exception, we call the 'getCouponsByPrices' method.
			return this.couponDao.getCouponsByPrices(minPrice, maxPrice);
		
	}
	
	
	public List<Coupon> getCouponsByType(CouponType couponType) throws ApplicationException{
		
		validateGetCouponsByType(couponType);
		
		//If we didn't catch any exception, we call the 'getCouponsByType' method.
		return this.couponDao.getCouponsByType(couponType);
		
	}
	
	
	public List<Coupon> getAllCustomerActiveCoupons (long customerId) throws ApplicationException{
		validateGetAllCustomerActiveCoupons(customerId);
		return this.couponDao.getAllCustomerActiveCoupons(customerId);
	}
	
	
	 
	public List<Coupon>  getAllCustomerCoupons (long customerId) throws ApplicationException{
		validateGetAllCustomerCoupons(customerId);
		return this.couponDao. getAllCustomerCoupons(customerId);
	}
	
	
	
	
	
	
	
	//Validation method		
	
	private void validateGetAllCoupons() throws ApplicationException {
		if(couponDao.getAllCoupons()==null) {		
			throw new ApplicationException(ErrorType.Empty_List, "The list of coupons is empty " + DateUtils.getCurrentDateAndTime());
		}
	
	}
	
	
	private void validateGetAllActiveCoupons() throws ApplicationException {
		if(couponDao.getAllActiveCoupons()==null) {		
			throw new ApplicationException(ErrorType.Empty_List, "The list of coupons is empty " + DateUtils.getCurrentDateAndTime());
		}
	
	}
	
	
	
	private void validateCouponId(long couponId) throws ApplicationException{
		if(!this.couponDao.isCouponExistById(couponId)) {
			throw new ApplicationException(ErrorType.ID_Does_Not_Exist, "The given coupon id does not exist " 
		               + DateUtils.getCurrentDateAndTime());
		}
	}
	
	private void validateCouponTitle(long company_id,String coupon_Title) throws ApplicationException{
		//We check if the title length is valid
		if (coupon_Title.length()>45 || coupon_Title.length()<2) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "Invalid copuon title " + DateUtils.getCurrentDateAndTime());
		}
		
		// we check if the coupon title does already exist for the coupon company 
		if (this.couponDao.isCompanyCouponExistByName(coupon_Title,company_id)) {
			throw new ApplicationException(ErrorType.Title_ALREADY_EXISTS, "coupon's title already exists for company " + 
					DateUtils.getCurrentDateAndTime());		
		}
	}

	
	
	private void validateCreateCoupon (Coupon coupon) throws ApplicationException{

		if(coupon.getAmount() < 0) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "inavalid coupon amount " + 
						DateUtils.getCurrentDateAndTime());		
		}
		
		if(coupon.getPrice() < 0) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "inavalid coupon price " + 
						DateUtils.getCurrentDateAndTime());		
		}
		
		//we check if the company id exists
		if(!this.companyDao.doesCompanyExistById(coupon.getCompany_Id())) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "inavalid company id - does not exist! " + 
					DateUtils.getCurrentDateAndTime());	
		}
		
		// we check if the coupon title does already exist for the coupon company 
		if (this.couponDao.isCompanyCouponExistByName(coupon.getCouponTitle(),coupon.getCompany_Id())) {
			throw new ApplicationException(ErrorType.Title_ALREADY_EXISTS, "coupon's title already exists for company " + 
					DateUtils.getCurrentDateAndTime());		
		}
		
		//we check if the given start date fits the format YYYY-MM-DD
		if(! DateUtils.IsDateInFormat(coupon.getCouponStartDate())){
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "inavalid coupon start date - not in format " + 
					DateUtils.getCurrentDateAndTime());		
		}
		
		//we check if the given end date fits the format YYYY-MM-DD
				if(! DateUtils.IsDateInFormat(coupon.getCouponEndDate())){
					throw new ApplicationException(ErrorType.INVALID_PARAMETER, "inavalid coupon end date - not in format " + 
							DateUtils.getCurrentDateAndTime());		
				}
				
		
		//we check if today is later than end date
		Integer convetrtedNowDate = Integer.parseInt(DateUtils.getCurrentDate().replaceAll("-", ""));
		Integer convetrtedNewDate = Integer.parseInt(coupon.getCouponEndDate().replaceAll("-", ""));
		if(convetrtedNewDate < convetrtedNowDate) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "Invalid End Date " + 
					DateUtils.getCurrentDateAndTime());
		}
		
		//we check if start date is bigger than end date
		if (DateUtils.isDate1AfterDate2(coupon.getCouponStartDate(), coupon.getCouponEndDate())) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "Invalid Start Date, later than end date " + 
					DateUtils.getCurrentDateAndTime());
		}
		
		
		validateCouponTitle(coupon.getCompany_Id(), coupon.getCouponTitle());
		
		validateUpdateCouponMessageById(coupon.getMessage());
		
		
	}
	
	
	private void validateDeleteCouponId(long couponId) throws ApplicationException{
		// We check if the coupon id exists
		validateCouponId(couponId);
	}
	
	
	private void validateUpdateCoupon (Coupon coupon) throws ApplicationException{

		if(coupon.getAmount() < 0) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "inavalid coupon amount " + 
						DateUtils.getCurrentDateAndTime());		
		}
		
		if(coupon.getPrice() < 0) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "inavalid coupon price " + 
						DateUtils.getCurrentDateAndTime());		
		}
		
		//we check if the company id exists
		if(!this.companyDao.doesCompanyExistById(coupon.getCompany_Id())) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "inavalid comany id - does not exist! " + 
					DateUtils.getCurrentDateAndTime());	
		}
		
		//we check if the given start date fits the format YYYY-MM-DD
		if(! DateUtils.IsDateInFormat(coupon.getCouponStartDate())){
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "inavalid coupon start date - not in format " + 
					DateUtils.getCurrentDateAndTime());		
		}
		
		//we check if the given end date fits the format YYYY-MM-DD
				if(! DateUtils.IsDateInFormat(coupon.getCouponEndDate())){
					throw new ApplicationException(ErrorType.INVALID_PARAMETER, "inavalid coupon end date - not in format " + 
							DateUtils.getCurrentDateAndTime());		
				}
				
		
		//we check if today is later than end date
		Integer convetrtedNowDate = Integer.parseInt(DateUtils.getCurrentDate().replaceAll("-", ""));
		Integer convetrtedNewDate = Integer.parseInt(coupon.getCouponEndDate().replaceAll("-", ""));
		if(convetrtedNewDate < convetrtedNowDate) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "Invalid End Date " + 
					DateUtils.getCurrentDateAndTime());
		}
		
		//we check if start date is bigger than end date
		if (DateUtils.isDate1AfterDate2(coupon.getCouponStartDate(), coupon.getCouponEndDate())) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "Invalid Start Date, later than end date " + 
					DateUtils.getCurrentDateAndTime());
		}
		
		
		Coupon oldCoupon = this.getCouponById(coupon.getCouponId()); //getting the coupon before changes
		String oldTitle = oldCoupon.getCouponTitle();
		String newTitle = coupon.getCouponTitle();
		
		//checking if the titles are equal before and after update
		if(! oldTitle.equals(newTitle)) {
			//if yes, we validate the new title
			validateCouponTitle(coupon.getCompany_Id(), coupon.getCouponTitle());
		}
		
		//checking if the start date was updated
		String oldStratDate = oldCoupon.getCouponStartDate();
		if(! oldStratDate.equals(coupon.getCouponStartDate()) ) {
				throw new ApplicationException(ErrorType.SYSTEM_ERROR, "forbidden action, can not update start date of coupon " + 
						DateUtils.getCurrentDateAndTime());	
				}
		//checking if the end date was extended when there are no coupons left in stock
		String oldEndDate = oldCoupon.getCouponEndDate();
		if(DateUtils.isDate1AfterDate2(coupon.getCouponEndDate(), oldEndDate) && coupon.getAmount()==0 ) {
				throw new ApplicationException(ErrorType.SYSTEM_ERROR, "forbidden action: you are not allowed to extend coupon end date when there are no coupons in stock " + 
							DateUtils.getCurrentDateAndTime());	
				}	
		
		validateUpdateCouponMessageById(coupon.getMessage());
		
		
	}
	
	
	
	
	private void validatePurchase (long customerId, long couponId, int purchaseAmount) throws ApplicationException{
		//We check if the coupon id exists
		validateCouponId(couponId);
		
		//We check if the customer id exists
		if (!customerDao.doesCustomerExistById(customerId)) {
			throw new ApplicationException(ErrorType.ID_Does_Not_Exist, "The given customer id does not exist " 
		               + DateUtils.getCurrentDateAndTime());		
		}
		
		//We check if the purchaseAmount id valid
		if(purchaseAmount <= 0) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "The given purchase amount is not valid " 
		               + DateUtils.getCurrentDateAndTime());
		}
		
		//we check if there are enough coupons for purchase
		Coupon coupon = this.getCouponById(couponId);
		if(coupon.getAmount() - purchaseAmount < 0) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "There are not enough coupons for purchase " 
		               + DateUtils.getCurrentDateAndTime());
		}	
	}
	
	//we check the new end date parameter
	private void validateUpdateCouponEndDate(long couponId, String endDate) throws ApplicationException{
		
		//we check if the given end date fits the format YYYY-MM-DD
		if(! DateUtils.IsDateInFormat(endDate)) {
				throw new ApplicationException(ErrorType.INVALID_PARAMETER, "inavalid coupon end date - not in format " + 
						DateUtils.getCurrentDateAndTime());		
				}
		
		//we check if the coupon id exists
		validateCouponId(couponId);
		
		//we check if there are coupons in stock
		Coupon coupon = this.getCouponById(couponId);
		if (coupon.getAmount() <= 0) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "There are no coupons in stock. Please update coupon amount before updating the coupon's end date" + 
					DateUtils.getCurrentDateAndTime());
		}
		
		
		//we check if today is later than end date
		Integer convetrtedNowDate = Integer.parseInt(DateUtils.getCurrentDate().replaceAll("-", ""));
		Integer convetrtedNewDate = Integer.parseInt(endDate.replaceAll("-", ""));
		if(convetrtedNewDate < convetrtedNowDate) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "Invalid End Date " + 
					DateUtils.getCurrentDateAndTime());
		}
		
		//we check if start date is bigger than end date
		if (DateUtils.isDate1AfterDate2(coupon.getCouponStartDate(), endDate)) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "Invalid Start Date, later than end date " + 
					DateUtils.getCurrentDateAndTime());
		}			
	}
	
	
	private void validateUpdateCouponAmount(long couponId, int newCouponAmount) throws ApplicationException{
		
		//We check if the coupon id exists
		validateCouponId(couponId);
		
		//we check if the parameter is valid
		if (newCouponAmount < 0) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "Invalid amount " + 
					DateUtils.getCurrentDateAndTime());
		}
	}
	
	
	private void validateUpdateCouponMessageById(String newCouponMessage)throws ApplicationException{
		
		//We check if the title length is valid
			if (newCouponMessage.length()>200 || newCouponMessage.length()<2) {
				throw new ApplicationException(ErrorType.INVALID_PARAMETER, "Invalid copuon Message " + DateUtils.getCurrentDateAndTime());
			}		
	}
	
	
	private void validateUpdateCouponPrice(long couponId, double newCouponPrice)throws ApplicationException{
		//We check if the coupon id exists
		validateCouponId(couponId);
		
		//we check if the new price is valid
		if (newCouponPrice < 0) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "Invalid copuon price " 
					+ DateUtils.getCurrentDateAndTime());
		}
	}
	
	
	private void validateGetCouponsByCompany(long copmanyId) throws ApplicationException {
		
		//we check if the given company id exists in the DB
		if(!companyDao.doesCompanyExistById(copmanyId)) {
			throw new ApplicationException(ErrorType.ID_Does_Not_Exist, "The copmany Id does not exist " 
					+ DateUtils.getCurrentDateAndTime());
		}
	
		if(couponDao.getAllCouponsByCompanyId(copmanyId)==null) {		
			throw new ApplicationException(ErrorType.Empty_List, "The list of coupons for the given company is empty " + DateUtils.getCurrentDateAndTime());
		}
	}
	
	
	private void validateGetCouponsBeforeEndDate(String endDate) throws ApplicationException {
		
		//we check if the given end date fits the format YYYY-MM-DD
			if(! DateUtils.IsDateInFormat(endDate)) {
				throw new ApplicationException(ErrorType.INVALID_PARAMETER, "inavalid coupon end date - not in format " + 
						DateUtils.getCurrentDateAndTime());		
					}
				
		//we check if the list is empty
			if(couponDao.getCouponsBeforeEndDate(endDate)==null) {		
				throw new ApplicationException(ErrorType.Empty_List, "The list of coupons is empty - GetCouponsBeforeEndDate" + DateUtils.getCurrentDateAndTime());
			}	
		
	}
	
	
	private void validateGetCouponsByPrices (double minPrice, double maxPrice) throws ApplicationException {
		
		//we check if the max price is bigger than the min price
		if (minPrice >= maxPrice) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "The min price is bigger or equal to the max price" 
					+ DateUtils.getCurrentDateAndTime());
		}
		
		if (minPrice < 0 || maxPrice < 0) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "prices cannot be negetive" 
					+ DateUtils.getCurrentDateAndTime());
		}
		
	}
	
	
	private void validateGetCouponsByType(CouponType couponType) throws ApplicationException{
		
		//we check if the list is empty
		if(couponDao.getCouponsByType(couponType)==null) {		
			throw new ApplicationException(ErrorType.Empty_List, "The list of coupons is empty - getCouponsByType" + DateUtils.getCurrentDateAndTime());
		}	
		
	}
	
	
	//validate the getAllCustomerActiveCoupons
	private void validateGetAllCustomerActiveCoupons(long customerId) throws ApplicationException {
			if (!this.customerDao.doesCustomerExistById(customerId)) {
					throw new ApplicationException(ErrorType.INVALID_PARAMETER, "customerId doesnt exist.");
				}		
			}
			
	//validate the getAllCustomerCoupons
	private void validateGetAllCustomerCoupons(long customerId) throws ApplicationException {

				if (!this.customerDao.doesCustomerExistById(customerId)) {
					throw new ApplicationException(ErrorType.INVALID_PARAMETER, "customerId doesnt exist.");
				}		
			}
	
	
	//validate the getCouponByID
	private void validateGetCouponById(long couponId) throws ApplicationException {
		//validate that the coupon id exists in the DB
		validateCouponId(couponId);
		
//		//validate if the object is null
//		if(couponDao.getCouponById(couponId)==null) {		
//			throw new ApplicationException(ErrorType.NULL_OBJECT, "The object that returned is null " + DateUtils.getCurrentDateAndTime());
//		}
		
		
	}
	
	
	
	
	
	
	
	
	
}


