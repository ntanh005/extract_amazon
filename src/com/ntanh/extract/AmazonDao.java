package com.ntanh.extract;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class AmazonDao implements IAmazonDao {

	@Override
	public void insertAsinList(String asin, List<AmazonAsin> lst)  throws Exception {
		
		
		Connection con = MyConnection.getInstance().getConection();
		PreparedStatement dltstm  = null;
		PreparedStatement insertStm = null;
		try{
			con.setAutoCommit(false);
			// delete exist data
			String dltSql = "delete from dt_amazon_asin where asin_code = ?";
			dltstm = con.prepareStatement(dltSql);
			dltstm.setString(1, asin);
			dltstm.executeUpdate();
			
			// insert new record
			String insertSql = "insert into  dt_amazon_asin(asin_code, dt_asin_code, type, created_date) values(?, ?, ?, now()) ";
			insertStm = con.prepareStatement(insertSql);
			for(int idx = 0; idx < 20 && idx < lst.size(); idx++){
				AmazonAsin obj = lst.get(idx);
				insertStm.setString(1, obj.asin_code);
				insertStm.setString(2, obj.dt_asin_code);
				insertStm.setInt(3, obj.type);
				insertStm.addBatch();
			}
			insertStm.executeBatch();			
			con.commit();			
			
		}catch(Exception ex){
			con.rollback();
			throw ex;
		}finally {
			insertStm.close();
			dltstm.close();
		}		

	}

}
