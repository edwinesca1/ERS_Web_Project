package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.models.User;
import com.revature.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class LoginController {
	
	private static UserDao uDao = new UserDaoDB();
	private static UserService uServ = new UserService(uDao);
	
	public static void login(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
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
		JsonNode parsedObj = mapper.readTree(data);
		
		String username = parsedObj.get("username").asText();
		String password = parsedObj.get("password").asText();
		
		try {
			System.out.println("In the Sign in method");
			User u = uServ.signIn(username, password);
			System.out.println(u);

			//We will keep track of if a user is signed in by storing their id in the session
			HttpSession mySession = req.getSession();
			mySession.setAttribute("id", u.getUserId());
			res.setStatus(200);
			res.getWriter().write(new ObjectMapper().writeValueAsString(u));
		} catch(Exception e) {
			res.setStatus(403);
			res.getWriter().println("Username or password incorrect");
		}
		
	}
	
	public static void signup(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
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
				String password = parsedObj.get("password").asText();
				int roleID = 2;
				
				try {
					System.out.println("In the sign up method");
					int accCreated = uServ.SignUp(fName, lName, email, username, password, roleID);
					System.out.println(accCreated);
					if(accCreated == 0) {
						res.setStatus(401);
						ret.put("transactionStatus", "Failed!");
						ret.put("message", "something went wrong!");
						res.getWriter().write(new ObjectMapper().writeValueAsString(ret));
					}else if(accCreated == 2) {
						res.setStatus(403);
						ret.put("transactionStatus", "Failed!");
						ret.put("message", "the username is not available!");
						res.getWriter().write(new ObjectMapper().writeValueAsString(ret));
					}else if(accCreated == 3) {
						res.setStatus(403);
						ret.put("transactionStatus", "Failed!");
						ret.put("message", "An account with this email already exists!");
						res.getWriter().write(new ObjectMapper().writeValueAsString(ret));
					}else {
						res.setStatus(200);
						ret.put("message", "Account created!");
						ret.put("transactionStatus", "Successful");
						res.getWriter().write(new ObjectMapper().writeValueAsString(ret));
					}
				} catch(Exception e) {
					res.setStatus(400);
					res.getWriter().println("Something went wrong");
				}
	}
	
}
