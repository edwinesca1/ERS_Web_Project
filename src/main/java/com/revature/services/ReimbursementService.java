package com.revature.services;

import java.util.List;

import com.revature.dao.ReimbursementDao;
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
}
