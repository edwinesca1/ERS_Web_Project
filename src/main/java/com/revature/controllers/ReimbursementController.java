package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoDB;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDisplay;
import com.revature.services.ReimbursementService;

public class ReimbursementController {
	
	private static ReimbursementDao rDao = new ReimbursementDaoDB();
	private static ReimbursementService rServ = new ReimbursementService(rDao);
	
	public static void getAllReimbursements(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		if(req.getMethod().equals("GET")) {
			List<ReimbursementDisplay> reimb = rServ.getAllReimbursement();
			System.out.println(reimb);
			res.getWriter().write(new ObjectMapper().writeValueAsString(reimb));
		}
	}
}
