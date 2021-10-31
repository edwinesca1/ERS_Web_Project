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
	
	//------Retrieve reimbursements by filtered values
	List<ReimbursementDisplay> getAllReimbursementIUE(int id, String username, String employeeName);
	
	List<Reimbursement> getAllPendingReimbursement();
	
	List<Reimbursement> getAllResolvedReimbursement();
	
	Reimbursement createNewReimbursement(Reimbursement r) throws SQLException;
	
	int createNewReimbursement(int author, double amount, String description, String dateSub, int status, int type) throws SQLException;
	
	int ApproveDenyReimbursement(int reimbId, String dateResolved, int resolverID, int resolution) throws SQLException;
}
