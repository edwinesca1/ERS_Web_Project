package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDisplay;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDaoDB implements ReimbursementDao {
	
	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	
	SimpleDateFormat formatter = new SimpleDateFormat("MMMM/dd/yyyy");

	@Override
	public List<ReimbursementDisplay> getAllReimbursement() {
		
		List<ReimbursementDisplay> reimbList = new ArrayList<ReimbursementDisplay>();
		String strDate1;
		String strDate2;
		
		try {
			//make the actual connection to the DB
			Connection con = conUtil.getConnection();
			
			//Creating a simple statement
			String sql = "select re.*, u.user_first_name aName, u.user_last_name aLastname, ua.user_first_name rName, ua.user_last_name rLastname, s.reimb_status, t.reimb_type "
					+ "from ers_reimbursement re "
					+ "inner join ers_users u on u.ers_user_id = re.reimb_author "
					+ "inner join ers_users ua on ua.ers_user_id = re.reimb_resolver "
					+ "inner join ers_reimbursement_status s on s.reimb_status_id = re.reimb_status_id "
					+ "inner join ers_reimbursement_type t on t.reimb_type_id = re.reimb_type_id";
			
			//We need to create a statement with the sql string
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			//We have to loop through the resultset an create objects based off the return
			while(rs.next()) {
				reimbList.add(new ReimbursementDisplay(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15)));
			}
			System.out.println(reimbList);
			return reimbList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Reimbursement> getAllPendingReimbursement() {
		
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		
		try {
			//make the actual connection to the DB
			Connection con = conUtil.getConnection();
			
			//Creating a simple statement
			String sql = "select * from ers_reimbursement where reimb_status_id = 1";
			
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
	public List<Reimbursement> getAllResolvedReimbursement() {
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		
		try {
			//make the actual connection to the DB
			Connection con = conUtil.getConnection();
			
			//Creating a simple statement
			String sql = "select * from ers_reimbursement where reimb_status_id != 1";
			
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
