package com.biskart.api;

import javax.ws.rs.GET;
//import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.biskart.dao.SignupSellerDao;

@Path("signup/seller")
public class signupSellerAPI {

	@GET
	@Path("/{name}/{email}/{password}/{address}/{mobile}/{city}/{state}/{pincode}/{rating}/{alt_contact}")
	@Produces( MediaType.APPLICATION_JSON )
	public boolean signup_seller(@PathParam("name") String name ,@PathParam("email") String email ,@PathParam("password") String password ,
			@PathParam("address") String address,@PathParam("mobile") int mobile,@PathParam("city") String city, @PathParam("state") String state,
			@PathParam("pincode") int pincode, @PathParam("rating") double rating,@PathParam("alt_contact") String alt_contact )
	{
		
		
		SignupSellerDao signs = new SignupSellerDao();
		
		return signs.db_query(name,email,password,address,mobile,city,state,pincode,rating,alt_contact);
	}
}