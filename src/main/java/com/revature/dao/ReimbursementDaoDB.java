package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDaoDB implements ReimbursementDao {
	
	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public List<Reimbursement> getAllReimbursement(int t) {
		
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		
		try {
			//make the actual connection to the DB
			Connection con = conUtil.getConnection();
			
			//Creating a simple statement
			String sql = "select * from ers_reimbursement where reimb_status_id = "+ t;
			
			//We need to create a statement with the sql string
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			//We have to loop through the resultset an create objects based off the return
			while(rs.next()) {
				reimbList.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(7), rs.getInt(7)));
			}
			return reimbList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Reimbursement createNewReimbursement(Reimbursement r) throws SQLException {
		
		Connection con = conUtil.getConnection();
		
		//Another way to crate the statement for the query
		String sql = "insert into ers_reimbursement(reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_status_id, reimb_type_id) VALUES"
				+"(?,?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setDouble(1, r.getAmount());
		ps.setDate(2, (java.sql.Date) r.getReimbSubmitted());
		ps.setString(3, r.getDescription());
		ps.setInt(4, r.getReimbAuthor());
		ps.setInt(5, r.getReimbStatus());
		ps.setInt(6, r.getReimbType());
		
		ps.execute();
		return null;
	}

	@Override
	public int ApproveDenyReimbursement(int reimbId, Date now, int resolverId, int resolution) throws SQLException {
		
		Connection con = conUtil.getConnection();
		
		String sql = "update ers_reimbursement "
				+ "set reimb_resolved = '"+ now +"',"
				+ "	reimb_resolver = "+ resolverId +","
				+ "	reimb_status_id = "+ resolution +" "
				+ "where reimb_id = "+ reimbId +"";
		
		Statement s = con.createStatement();
		int rowsAffected = s.executeUpdate(sql);
		
		return rowsAffected;
	}

}
