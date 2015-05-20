package com.biskart.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

import com.biskart.bean.Product;
import com.biskart.db.DBConnection;


public class ProductDAO {
	
	//public List<Map<String, Object>> getAllProducts() {
	public ArrayList<Product> getAllProducts() {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		//List<Map<String, Object>> retObj = null;
		ArrayList<Product> list= new ArrayList<Product>();
		
		String sqlQuery="Select * from products";
		try{
			conn=DBConnection.getConnection();
			if(conn != null){
				pstmt=conn.prepareStatement(sqlQuery);
				System.out.println(pstmt);
	            
				rs=pstmt.executeQuery();
				System.out.println(rs);
	            //retObj  = getEntitiesFromResultSet(rs);
				while(rs.next()) {
					list.add(new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getBoolean(6)));
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return list;
	}
	
	protected List<Map<String, Object>> getEntitiesFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Map<String, Object>> entities = new ArrayList<>();
        while (resultSet.next()) {
            entities.add(getEntityFromResultSet(resultSet));
        }
        return entities;
    }
	
	protected Map<String, Object> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData =  resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        Map<String, Object> resultsMap = new HashMap<>();
        for (int i = 1; i <= columnCount; ++i) {
            String columnName = metaData.getColumnName(i).toLowerCase();
            Object object = resultSet.getObject(i);
            resultsMap.put(columnName, object);
        }
        return resultsMap;
    }
}