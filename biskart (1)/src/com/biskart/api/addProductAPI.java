package com.biskart.api;




import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


//import com.biskart.bean.Attributes;
import com.biskart.dao.AddProductDAO;




@Path("addproduct")
public class addProductAPI {	
	
	
	@POST
	@Path("/")
	@Consumes( MediaType.APPLICATION_JSON )
	@Produces(MediaType.APPLICATION_JSON)
	public int getarray(String jsonreq)
	{
		int retval = -1;
		String attributeList[] = new String[5];
		
		try 
		{
			
			
			JSONObject jobj = new JSONObject (jsonreq);
			int sid = jobj.getInt("sid");
			String prodname = jobj.getString("pname");
			String category = jobj.getString("cat");
			String subcategory = jobj.getString("subcat");
			
			attributeList[0] = jobj.getString("Brand");			
			attributeList[1] = jobj.getString("OS");			
			attributeList[2] = jobj.getString("Color");			
			attributeList[3] = jobj.getString("ScreenSize");		
			attributeList[4] = jobj.getString("RAM");
			
			
			
			System.out.println(sid);
			System.out.println(prodname);
			System.out.println(category);
			System.out.println(subcategory);
			
			
			AddProductDAO daobj = new AddProductDAO();
			retval = daobj.addProduct(sid, prodname, category,subcategory,attributeList);
			
			
		} 
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
		return retval;
	}
	
	
		
	
}











