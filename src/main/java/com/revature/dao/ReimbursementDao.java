package com.revature.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDao {
	
	List<Reimbursement> getAllReimbursement(int t);
	
	Reimbursement createNewReimbursement(Reimbursement r) throws SQLException;
	
	int ApproveDenyReimbursement(int reimbId, Date now, int resolverId, int resolution) throws SQLException;
}
