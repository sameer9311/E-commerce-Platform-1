package com.biskart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.biskart.db.DBConnection;

public class SignupSellerDao {
	
	public boolean db_query(String name,String email,String password,String address,int mobile,String city,String state,int pincode,double rating,String alt_contact)
	{
		String query1,query2;
		int userid=0;
		query1 = "insert into users (name,email,password,is_verified,is_customer) values (?,?,?,?,?)";
		System.out.println(query1);
		Connection conn=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
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
				pstmt1.setInt(5,0);
				
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
			    //here we want user_id from database
			    
				if(null!=pstmt2)
				{
					pstmt2.close();
				}
			    String query3 = "insert into sellers (seller_id,address,mobile,city,state,pincode,rating,alt_contact) values (?,?,?,?,?,?,?,?)";
				pstmt3=conn.prepareStatement(query3);
				pstmt3.setInt(1,userid);
				pstmt3.setString(2,address);
				pstmt3.setInt(3,mobile);
				pstmt3.setString(4,city);
				pstmt3.setString(5,state);
				pstmt3.setInt(6,pincode);
				pstmt3.setDouble(7,rating);
				pstmt3.setString(8,alt_contact);
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
	            //retObj  = getEntitiesFromResultSet(rs);*/
			}
		}
		catch(Exception ex)
		{
			System.out.println("stuck somewhere");
			ex.printStackTrace();
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
		return true;
	}
}