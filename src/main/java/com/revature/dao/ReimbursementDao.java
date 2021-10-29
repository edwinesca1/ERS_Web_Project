package com.revature.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDisplay;

public interface ReimbursementDao {
	
	//------Retrieve all reimbursement requests from the last 3 days 
	List<ReimbursementDisplay> getAllReimbursement();
	
	//------Retrieve all reimbursement requests
	List<ReimbursementDisplay> getAllReimbursement2();
	
	List<ReimbursementDisplay> getAllReimbursementByAuthor(int id);
	
	List<Reimbursement> getAllPendingReimbursement();
	
	List<Reimbursement> getAllResolvedReimbursement();
	
	Reimbursement createNewReimbursement(Reimbursement r) throws SQLException;
	
	int createNewReimbursement(int author, double amount, String description, String dateSub, int status, int type) throws SQLException;
	
	int ApproveDenyReimbursement(int reimbId, Date now, int resolverId, int resolution) throws SQLException;
}
