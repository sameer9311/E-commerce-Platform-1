package com.biskart.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.biskart.dao.LoginDAO;


@Path("login")
public class LoginAPI {

	@GET
	@Path("/{username}/{password}")
	@Produces( MediaType.APPLICATION_JSON )
	//@Produces( MediaType.TEXT_PLAIN )
	public boolean verifyLogin(@PathParam("username") String user , @PathParam("password") String pass )
	{
		LoginDAO logDAO = new LoginDAO();
		return logDAO.isUserValid(user, pass);
	}
}
