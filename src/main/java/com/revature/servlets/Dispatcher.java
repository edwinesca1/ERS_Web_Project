package com.revature.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.controllers.LoginController;

public class Dispatcher{
	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		System.out.println("We are in the servlet dispatcher with URI: " + req.getRequestURI());
		switch(req.getRequestURI()) {
		case "ExpenseReimbursementSystem/api/login":
			LoginController.login(req, res);
			break;
		}
	}
	
}
