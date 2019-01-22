package com.chen.coupons.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.chen.coupons.beans.Coupon;
import com.chen.coupons.beans.Customer;
import com.chen.coupons.beans.Purchase;
import com.chen.coupons.controllers.CouponController;
import com.chen.coupons.enums.CouponType;
import com.chen.coupons.exceptions.ApplicationException;


@Path("/Coupons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CouponsApi {

	private CouponController couponConroller;
	//constructor
	public CouponsApi () {
		 this.couponConroller = new CouponController();
	}
	
	
	@POST
	//http://localhost:8080/CouponsProjectAPI/rest/Coupons/
	public void creatCoupon(Coupon coupon) throws ApplicationException{
		System.out.println(this.couponConroller.createCoupon(coupon));
	}
	
	@GET
	@Path("/{couponId}/byId")
	//http://localhost:8080/CouponsProjectAPI/rest/Coupons/1/byId
	public Coupon getCoupnById(@PathParam("couponId") long couponId)throws ApplicationException{
		 return this.couponConroller.getCouponById(couponId);			
	}
	
	@DELETE
	@Path("/{couponId}")
	//http://localhost:8080/CouponsProjectAPI/rest/Coupons/23
	public void deleteCoupon(@PathParam("couponId") long couponId)throws ApplicationException{
		this.couponConroller.deleteCouponById(couponId);
	}
	
	@PUT
	//http://localhost:8080/CouponsProjectAPI/rest/Coupons/
	public void updateCoupon(Coupon coupon) throws ApplicationException{
		System.out.println(coupon);
		this.couponConroller.updateCoupon(coupon);
	}
	
	
	@GET
	//http://localhost:8080/CouponsProjectAPI/rest/Coupons/
	public List<Coupon> getAllCoupons()throws ApplicationException{
		 return this.couponConroller.getAllCoupons();			
	}
		
	
	@GET
	@Path("/active")
	//http://localhost:8080/CouponsProjectAPI/rest/Coupons/active
	public List<Coupon> getAllActiveCoupons()throws ApplicationException{
		 return this.couponConroller.getAllActiveCoupons();			
	}
	
	
	@GET
	@Path("/byType")
	//http://localhost:8080/CouponsProjectAPI/rest/Coupons/byType?type=Food
	public List<Coupon> getCoupnsByType(@QueryParam("type") String type)throws ApplicationException{
		CouponType couponType = CouponType.valueOf(type);
		 return this.couponConroller.getCouponsByType(couponType);		
	}
	
	@GET
	@Path("/byEndDate")
	//http://localhost:8080/CouponsProjectAPI/rest/Coupons/byEndDate?endDate=2019-10-30
	public List<Coupon> getCoupnsBeforeEndDate(@QueryParam("endDate") String endDate)throws ApplicationException{
		 return this.couponConroller.getCouponsBeforeEndDate(endDate);	
	}
	
	@GET
	@Path("/byPrices")
	//http://localhost:8080/CouponsProjectAPI/rest/Coupons/byPrices?min=100&max=300
	public List<Coupon> getCoupnsByPrices(@QueryParam("min") double minPrice, @QueryParam("max") double maxPrice)throws ApplicationException{
		 return this.couponConroller.getCouponsByPrices(minPrice, maxPrice);	
	}
	
	@GET
	@Path("/{companyId}/CompanyActiveCoupons")
	//http://localhost:8080/CouponsProjectAPI/rest/Coupons/2/CompanyActiveCoupons
	public List<Coupon> getCoupnsByPrices(@PathParam("companyId") long companyId)throws ApplicationException{
		 return this.couponConroller.getActiveCouponsByCompanyId(companyId);
	}
	
	@GET
	@Path("/{companyId}/CompanyCoupons")
	//http://localhost:8080/CouponsProjectAPI/rest/Coupons/1/CompanyCoupons
	public List<Coupon> getAllCompanyCoupons(@PathParam("companyId") long companyId)throws ApplicationException{
		 return this.couponConroller.getAllCouponsByCompanyId(companyId);
	}
	
	
	@GET
	@Path("/{customerId}/CustomerActiveCoupons")
	//http://localhost:8080/CouponsProjectAPI/rest/Coupons/3/CustomerActiveCoupons
	public List<Coupon> getCustomerActiveCoupons(@PathParam("customerId") long customerId)throws ApplicationException{
		 return this.couponConroller.getAllCustomerActiveCoupons(customerId);
	}
	
	@GET
	@Path("/{customerId}/AllCustomerCoupons")
	//http://localhost:8080/CouponsProjectAPI/rest/Coupons/3/AllCustomerCoupons
	public List<Coupon> getAllCustomerCoupons(@PathParam("customerId") long customerId)throws ApplicationException{
		 return this.couponConroller.getAllCustomerCoupons(customerId);
	}
	
	 @POST
	 @Path ("/purchaseCoupon")
	 public void purchaseCoupon (Purchase purchase) throws ApplicationException{
		 long couponId = purchase.getCouponId();
		 long customerId=purchase.getCustomerId();
		 int amount = purchase.getAmount();
		 this.couponConroller.purchaseCoupon(customerId, couponId, amount);
	 }
	
	
	
	
	
	
	
	
	
	
	
}
