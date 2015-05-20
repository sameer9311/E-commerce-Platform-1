package com.biskart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.biskart.db.DBConnection;


public class LoginDAO
{
	public boolean isUserValid(String username , String pass )
	{
		boolean ret_val=false;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sqlQuery="Select * from users where email=\""+username+"\" and password=\""+pass+"\"";
		
		try
		{
			conn=DBConnection.getConnection();
			//System.out.println(" ATTEMPTING CONNECTION");
			if(conn != null)
			{
				pstmt=conn.prepareStatement(sqlQuery);
				System.out.println(pstmt);
				rs=pstmt.executeQuery();
				int count=0;
				while( rs.next() && count==0 )
				{
					++count;
				}
				if( count > 0 )
				{
					ret_val=true;
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
        	try {
        	if(null!=conn){        		
        		conn.close();
        	}
        	if(null!=pstmt){  
        		pstmt.close();
        	}
        	if(null!=rs){ 		
        		rs.close();
        	}
        	} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret_val;
	}
}