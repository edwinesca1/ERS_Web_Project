package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.models.User;
import com.revature.services.UserService;

public class EmployeesController {
	
	private static UserDao uDao = new UserDaoDB();
	private static UserService uServ = new UserService(uDao);
	
	public static void getAllEmployees(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{

			if(req.getMethod().equals("GET")) {
				List<User> users = uServ.getAllUsers();
				System.out.println(users);
				res.getWriter().write(new ObjectMapper().writeValueAsString(users));
			}

	}
	
	public static void getEmployeeByFullName(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		if(req.getMethod().equals("GET")) {
			String username = req.getParameter("username");
			List<User> users = uServ.getUserByFullName(username);
			
			System.out.println(users);
			System.out.println("Before mapping the response");
			res.getWriter().write(new ObjectMapper().writeValueAsString(users));
		}
	}
	
	public static void getEmployeeBySession(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		if(req.getMethod().equals("GET")) {
			HttpSession session = req.getSession(true);
			int userId = (Integer) session.getAttribute("id");
			User uEmployee = uServ.getUserById(userId);
			
			System.out.println(uEmployee);
			System.out.println("Before mapping the response for employee by Id");
			res.getWriter().write(new ObjectMapper().writeValueAsString(uEmployee));
		}else{
			System.out.println("Before updating user account info");
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
			
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode ret = mapper.createObjectNode();
			JsonNode parsedObj = mapper.readTree(data);
			
			String fName = parsedObj.get("fName").asText();
			String lName = parsedObj.get("lName").asText();
			String email = parsedObj.get("email").asText();
			String username = parsedObj.get("username").asText();
			String newPassword = parsedObj.get("newPassword").asText();
			String cPassword = parsedObj.get("cPassword").asText();
			System.out.println("password is: "+ cPassword);
			
			//logic for user update account
			HttpSession session = req.getSession(true);
			//System.out.println(session.getAttribute("id"));
				System.out.println("After session ID");
				int u = uServ.updateAccount(fName, lName, email, username, newPassword, cPassword);
				if(u == 1) {
					res.setStatus(200);
					ret.put("message", "Information updated!");
					ret.put("transactionStatus", "Successful");
					res.getWriter().write(new ObjectMapper().writeValueAsString(ret));
				}else {
					System.out.println("not rows affected!");
					res.setStatus(404);
					ret.put("message", "Confirmation password incorrect!");
					res.getWriter().write(new ObjectMapper().writeValueAsString(ret));
				}

		}
	}
	
}
