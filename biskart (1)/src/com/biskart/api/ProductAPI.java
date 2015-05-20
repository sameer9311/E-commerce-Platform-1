package com.biskart.api;

//import java.util.ArrayList;
//import java.util.HashMap;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.biskart.bean.Product;
//import com.biskart.bean.Product;
import com.biskart.bean.Products;
import com.biskart.dao.ProductDAO;

@Path("products")
public class ProductAPI {
	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	//@Produces( MediaType.TEXT_PLAIN )
	//public Products getAllproducts() 
	public ArrayList<Product> getAllproducts()
	{
		//why create a class Products with ArrayList<Product>? 
		ArrayList<Product> list=new ArrayList<Product>();
		ProductDAO pdObj=null;
		pdObj = new ProductDAO();
		
		list=pdObj.getAllProducts();
		System.out.println(pdObj.getAllProducts());
		
		Products products = new Products();
		products.setProductList(pdObj.getAllProducts());
		
		return list;
		
	}
}