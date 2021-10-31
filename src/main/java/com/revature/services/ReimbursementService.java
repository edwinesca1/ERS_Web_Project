package com.revature.services;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.logging.Logging;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDisplay;

public class ReimbursementService {
	
	private ReimbursementDao rDao;
	
	public ReimbursementService(ReimbursementDao rDao) {
		this.rDao = rDao;
	}
	
	public List<ReimbursementDisplay> getAllReimbursement(){
		return rDao.getAllReimbursement();
	}
	
	public List<ReimbursementDisplay> getAllReimbursementInFull(){
		return rDao.getAllReimbursement2();
	}
	
	public List<ReimbursementDisplay> getAllReimbursementById(int id){
		return rDao.getAllReimbursementByAuthor(id);
	}
	
	public List<ReimbursementDisplay> getAllReimbursementIUE(int id, String username, String employeeName){
		return rDao.getAllReimbursementIUE(id, username, employeeName);
	}
	
	public int createNewReimb(int author, double amount, String description, String dateSub, int status, int type) {
		
		int rowsAffected = 0;
		try {
		rowsAffected = rDao.createNewReimbursement(author, amount, description, dateSub, status, type);
		System.out.println("Rows forwarded: "+ rowsAffected);
		}catch(SQLException e) {
			Logging.logger.warn("New Reimbursement request failed!");
		}
		return rowsAffected;
	}
	
	public int resolveReimbursement(int reimbID, String dateResolved, int resolverID, int resolution) {
		int rowsAffected = 0;
		
		try {
			rowsAffected = rDao.ApproveDenyReimbursement(reimbID, dateResolved, resolverID, resolution);
			System.out.println("Rows forwarded: "+ rowsAffected);
		}catch(SQLException e) {
			Logging.logger.warn("New Reimbursement request failed!");
		}
		
		return rowsAffected;
	}
}
