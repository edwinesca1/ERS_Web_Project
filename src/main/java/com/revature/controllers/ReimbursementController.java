package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
	
	public static void getAllReimbursementsI(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		if(req.getMethod().equals("GET")) {
			int requestID = Integer.parseInt(req.getParameter("reimb"));
			List<ReimbursementDisplay> reimb = rServ.getAllReimbursementIUE(requestID, "", "");
			System.out.println(reimb);
			res.getWriter().write(new ObjectMapper().writeValueAsString(reimb));
		}
	}
	
	public static void getAllReimbursementsU(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		if(req.getMethod().equals("GET")) {
			String username = req.getParameter("name");
			List<ReimbursementDisplay> reimb = rServ.getAllReimbursementIUE(0, username, "");
			System.out.println(reimb);
			res.getWriter().write(new ObjectMapper().writeValueAsString(reimb));
		}
	}
	
	public static void getAllReimbursementsE(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		if(req.getMethod().equals("GET")) {
			String employeeName = req.getParameter("eName");
			List<ReimbursementDisplay> reimb = rServ.getAllReimbursementIUE(0, "", employeeName);
			System.out.println(reimb);
			res.getWriter().write(new ObjectMapper().writeValueAsString(reimb));
		}
	}
	
	public static void getAllReimbursementsInFull(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		if(req.getMethod().equals("GET")) {
			List<ReimbursementDisplay> reimb = rServ.getAllReimbursementInFull();
			System.out.println(reimb);
			res.getWriter().write(new ObjectMapper().writeValueAsString(reimb));
		}
	}
	
	public static void createNewReimbursement(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
			HttpSession session = req.getSession();
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode ret = mapper.createObjectNode();
			String pattern = "yyyy-MM-dd";
			DateFormat df = new SimpleDateFormat(pattern);
			Date today = Calendar.getInstance().getTime();
	        String date = df.format(today);
	      //To read in stringified JSON data from a POST request is a little more complicated than reading form data
			StringBuilder buffer = new StringBuilder();
			
			//The buffered reader will read the json data line by line
			BufferedReader reader = req.getReader();
			
			String line;
			while((line = reader.readLine()) != null) {
				buffer.append(line);
				buffer.append(System.lineSeparator());
			}
			
			String data = buffer.toString();
			System.out.println(data);
			System.out.println(date);
			
			JsonNode parsedObj = mapper.readTree(data);
			
			double amount = parsedObj.get("amount").asDouble();
			String description = parsedObj.get("description").asText();
			int type = parsedObj.get("type").asInt();
			int status = 1;
			int id = 0;
			
				id = (Integer) session.getAttribute("id");
				
				int un = rServ.createNewReimb(id, amount, description, date, status, type);
				System.out.println("Rows been printed: "+ un);
				if(un > 0) {
					System.out.println("Rows been printed in Successful: "+ un);
					res.setStatus(200);
					ret.put("message", "Reimbursemet request created!");
					ret.put("transactionStatus", "Successful");
					res.getWriter().write(new ObjectMapper().writeValueAsString(ret));
				}else {
					System.out.println("not rows affected!");
					res.setStatus(404);
					ret.put("message", "Request failed!");
					res.getWriter().write(new ObjectMapper().writeValueAsString(ret));
				}
	}
	
	public static void getAllReimbursementsById(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		HttpSession session = req.getSession();
		int id = 0;

			id = (Integer) session.getAttribute("id");
			
			if(req.getMethod().equals("GET")) {
				List<ReimbursementDisplay> reimb = rServ.getAllReimbursementById(id);
				System.out.println(reimb);
				res.getWriter().write(new ObjectMapper().writeValueAsString(reimb));
			}
	}
	
	public static void resolveReimbursement(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		HttpSession session = req.getSession();
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode ret = mapper.createObjectNode();
		
		String pattern = "yyyy-MM-dd";
		DateFormat df = new SimpleDateFormat(pattern);
		Date today = Calendar.getInstance().getTime();
        String date = df.format(today);
        
      //To read in stringified JSON data from a POST request is a little more complicated than reading form data
		StringBuilder buffer = new StringBuilder();
		
		//The buffered reader will read the json data line by line
		BufferedReader reader = req.getReader();
		
		String line;
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		
		String data = buffer.toString();
		System.out.println(data);
		System.out.println(date);
		
		JsonNode parsedObj = mapper.readTree(data);
		
		int reimbID = parsedObj.get("requestID").asInt();
		int status = parsedObj.get("finalStatus").asInt();
		int resolverID = 0;
		
			resolverID = (Integer) session.getAttribute("id");
			
			int un = rServ.resolveReimbursement(reimbID, date, resolverID, status);
			System.out.println("Rows been printed: "+ un);
			if(un > 0) {
				System.out.println("Rows been printed in Successful: "+ un);
				
				res.setStatus(200);
				ret.put("message", "Reimbursemet request created!");
				ret.put("transactionStatus", "Successful");
				res.getWriter().write(new ObjectMapper().writeValueAsString(ret));
			}else {
				System.out.println("not rows affected!");
				
				res.setStatus(404);
				ret.put("message", "Request failed!");
				res.getWriter().write(new ObjectMapper().writeValueAsString(ret));
			}
}
}
