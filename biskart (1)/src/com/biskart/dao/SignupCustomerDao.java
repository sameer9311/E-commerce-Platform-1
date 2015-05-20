package com.biskart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.biskart.db.DBConnection;

public class SignupCustomerDao {
	public boolean db_query(String name,String email,String password ,String address,String contact)
	{
		String query1, query2,query3,query4;
		int userid=0;
		//boolean ret_val=false;
		query1 = "insert into users (name,email,password,is_verified,is_customer) values (?,?,?,?,?)";
		System.out.println(query1);
		Connection conn=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
		PreparedStatement pstmt4=null;
		ResultSet rs=null;
		try
		{
			conn=DBConnection.getConnection();
			if(conn != null)
			{
				pstmt1=conn.prepareStatement(query1);
				pstmt1.setString(1,name);
				pstmt1.setString(2,email);
				pstmt1.setString(3,password);
				pstmt1.setInt(4,1);
				pstmt1.setInt(5,1);
				
			    int rows =pstmt1.executeUpdate();
			    System.out.println("rows:- "+rows);
				if(null!=pstmt1)
				{
					pstmt1.close();
				}
				query2 = "select user_id from users where email= ? and name= ?";
			    pstmt2=conn.prepareStatement(query2);
			    pstmt2.setString(1,email);
			    pstmt2.setString(2,name);
			    rs=pstmt2.executeQuery();
			    if(rs.next())
			    {
			    	 userid=rs.getInt("user_id");
			    	 System.out.println("userid :- " +userid);
			    }
			    else
			    {
			    	System.out.println("There is some problem. Try again,error code 100\n");
			    }
			    if(null!=pstmt2)
				{
					pstmt2.close();
				}
			    query3="insert into customer_address (customer_id,address) values (?,?)";
			    pstmt3=conn.prepareStatement(query3);
				pstmt3.setInt(1,userid);
				pstmt3.setString(2,address);
				rows =pstmt3.executeUpdate();
			    if(rows>0)
			    {
			    	System.out.println("rows:-"+rows);
			    }
			    else
			    {
			    	System.out.println("There is some problem. Try again.error code 101\n");
			    }
				if(null!=pstmt3)
		        {  
		       		pstmt3.close();
		       	}
				query4="insert into customer_contact (customer_id,contact) values (?,?)";
			    pstmt4=conn.prepareStatement(query4);
				pstmt4.setInt(1,userid);
				pstmt4.setString(2,contact);
				rows =pstmt4.executeUpdate();
			    if(rows>0)
			    {
			    	System.out.println("rows:-"+rows);
			    }
			    else
			    {
			    	System.out.println("There is some problem. Try again.error code 101\n");
			    }
				if(null!=pstmt4)
		        {  
		       		pstmt4.close();
		       	}
			}
			return true;
			
		}
		catch(Exception e)
		{
			System.out.println("stuck somewhere");
			e.printStackTrace();
			return false;
		}
		finally
		{
			try 
        	{
	        	if(null!=conn)
	        	{        		
	        		conn.close();
	        	}
	        	
	        	if(null!=rs)
	        	{ 		
	        		rs.close();
	        	}
	        	
        	} 
	        catch (SQLException e) 
	        {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}