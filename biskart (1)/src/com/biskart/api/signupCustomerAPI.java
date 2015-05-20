package com.biskart.api;

//import java.util.ArrayList;
//import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.biskart.dao.SignupCustomerDao;


@Path("signup/customer")
public class signupCustomerAPI {

	@GET
	@Path("/{name}/{email}/{password}/{address}/{contact}")
	@Produces( MediaType.APPLICATION_JSON )
	//@Produces( MediaType.TEXT_PLAIN )
	public boolean addProduct( @PathParam("name") String name ,@PathParam("email") String email ,@PathParam("password") String password ,
			@PathParam("address") String address,@PathParam("contact") String contact) 
	{
		SignupCustomerDao signs = new SignupCustomerDao();
		return signs.db_query(name,email,password,address,contact);
	}
}