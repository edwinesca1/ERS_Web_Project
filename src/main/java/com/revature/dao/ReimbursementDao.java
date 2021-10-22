package com.revature.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDisplay;

public interface ReimbursementDao {
	
	List<ReimbursementDisplay> getAllReimbursement();
	
	List<Reimbursement> getAllPendingReimbursement();
	
	List<Reimbursement> getAllResolvedReimbursement();
	
	Reimbursement createNewReimbursement(Reimbursement r) throws SQLException;
	
	int ApproveDenyReimbursement(int reimbId, Date now, int resolverId, int resolution) throws SQLException;
}
