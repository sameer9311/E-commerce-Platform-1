package com.biskart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biskart.bean.Attributes;
import com.biskart.db.DBConnection;
//import com.sun.jersey.json.impl.reader.JsonXmlEvent.Attributes;

public class AddProductDAO {
	
	public int addProduct(int sid,String prodname, String cat, String subcat,String [] attrlist)
	{
		int retval = -1,pid,index,temp;
			
		
		Connection conn=null;
		PreparedStatement pstmt1=null,pstmt2=null,pstmt3=null,pstmt4=null,pstmt5=null;
		ResultSet rs1=null,rs2=null,rs3=null;
		
		String sqlQuery1="select * from products where pname = ?";
		
		String sqlQuery2="insert into products(pname,category,subcategory) values (?,?,?);";
		
		String sqlQuery3="insert into product_seller_mapping(pid,sid) values (?,?)";
		
		String sqlQuery4="insert into prod_attr_mapping(pid,aid,attr_value) values (?,?,?)";
		
		String sqlQuery5="select last_insert_id() as last_pid from products";
		
		
		
		try
		{
			conn=DBConnection.getConnection();
			
			if(conn != null)
			{
				/** check first if product is present in database **/
				
				pstmt1=conn.prepareStatement(sqlQuery1);
				pstmt1.setString(1, prodname);
				rs1=pstmt1.executeQuery();
				
				if(rs1.next()) /*** product present already ***/
				{
					pid = rs1.getInt(1);
					pstmt3=conn.prepareStatement(sqlQuery3);
					pstmt3.setInt(1, pid);
					pstmt3.setInt(2,sid);
					retval=pstmt3.executeUpdate();
					System.out.println("product already in database");

				}
				else	/*** new product to be added  ***/
				{
					pstmt2=conn.prepareStatement(sqlQuery2);
					pstmt2.setString(1, prodname);
					pstmt2.setString(2, cat);
					pstmt2.setString(3, subcat);
					retval=pstmt2.executeUpdate();
					System.out.println("new product added and retval is "+retval);
					
					
					/** get product id of last inserted product **/ 
					
					pstmt5 = conn.prepareStatement(sqlQuery5);
					rs3 = pstmt5.executeQuery();
					rs3.next();
					pid = rs3.getInt("last_pid");
					
					System.out.println("the value of inserted product id is "+pid);
					
					pstmt4=conn.prepareStatement(sqlQuery4);
					pstmt4.setInt(1, pid);
					
					/*** add the attributes of the product ***/
					
					for(index=0;index<5;index++)
					{						
						temp = index + 1;
						pstmt4.setInt(2,temp);						
						pstmt4.setString(3, attrlist[index]);
						retval=pstmt4.executeUpdate();
					}
					
					
					
				}
				
				
				
				
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
        	try {
        		
        		if(null!=rs1)
        		{
        			rs1.close();
        		}
        		if(null!=rs2)
        		{
        			rs2.close();
        		}
        		if(null!=rs3)
        		{
        			rs3.close();
        		}
        		
        		if(null!=pstmt1){  
	        		pstmt1.close();
	        	}
        		if(null!=pstmt2){  
	        		pstmt2.close();
	        	}
        		if(null!=pstmt3){  
	        		pstmt3.close();
	        	}
        		if(null!=pstmt4){  
	        		pstmt4.close();
	        	}
        		if(null!=pstmt5){  
	        		pstmt5.close();
	        	}
	        	if(null!=conn){        		
	        		conn.close();
	        	}
	        	
	        	
        	}
        	 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		
		return retval;
		
	}
	
	/** to return the list of attributes dynamically to the seller while adding products **/
	
	public ArrayList<Attributes> getAttributes()
	{
		
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			
			ArrayList<Attributes> list= new ArrayList<Attributes>();
			
			String sqlQuery="Select * from attr_mapping";
			try{
				conn=DBConnection.getConnection();
				if(conn != null){
					pstmt=conn.prepareStatement(sqlQuery);
					
		            
					rs=pstmt.executeQuery();
					
		           
					while(rs.next()) {
						list.add(new Attributes(rs.getInt(1),rs.getString(2)));
						System.out.println(list.get(list.size()-1));
						System.out.println(" ");
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
	        	try {
	        		if(null!=rs){ 		
		        		rs.close();
		        	}
	        		if(null!=pstmt){  
		        		pstmt.close();
		        	}
		        	if(null!=conn){        		
		        		conn.close();
		        	}
		        	
		        	
	        	}
	        	 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
			return list;
	}

}






