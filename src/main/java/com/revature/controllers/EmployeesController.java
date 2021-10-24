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
		}
	}
	
}
