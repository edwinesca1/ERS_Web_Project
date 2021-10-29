package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SessionController {

	public static void getSession(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		System.out.println("before setting sesInfo");
		HttpSession session = req.getSession();
		
		ObjectMapper mapper = new ObjectMapper();
		
		ObjectNode sesInfo = mapper.createObjectNode();
		
		if(session.getAttribute("id") == null) {
			System.out.println("No session ID!");
			res.setStatus(401);
			sesInfo.put("message", "User is not logged In!");
			res.getWriter().write(new ObjectMapper().writeValueAsString(sesInfo));
		}else {
		sesInfo.put("userId", session.getAttribute("id").toString());
		System.out.println("after setting sesInfo");
		res.setStatus(200);
		res.getWriter().write((new ObjectMapper().writeValueAsString(sesInfo)));
		System.out.println(res.getStatus());
		}
	}
	
	public static void dropSession(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		System.out.println("before deleting session");
		HttpSession session = req.getSession();
		ObjectMapper mapper = new ObjectMapper();
		
		session.invalidate();
		
		ObjectNode sesInfo = mapper.createObjectNode();
		sesInfo.put("message", "User logged out!");
		res.setStatus(200);
		res.getWriter().write((new ObjectMapper().writeValueAsString(sesInfo)));
	}
	
}
