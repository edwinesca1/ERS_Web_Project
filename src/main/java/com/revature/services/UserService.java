package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.dao.UserDao;
import com.revature.exceptions.InvalidCredentialsException;
import com.revature.exceptions.UserDoesNotExistException;
import com.revature.logging.Logging;
import com.revature.models.User;

public class UserService {
	
	private UserDao uDao;
	
	public UserService(UserDao uDao) {
		this.uDao = uDao;
	}
	
	//Retrieving all users as admin
	public List<User> getAllUsers(){
		return uDao.getAllUsers();
	}
	
	public List<User> getUserByFullName(String username){
		return uDao.getUserByFullName(username);
	}
	
	public User getUserById(int userId) {
			
			User u = uDao.getUserById(userId);
			
			if(u.getUserId() == 0) {
				Logging.logger.warn("User is not logged in");
				throw new UserDoesNotExistException();
			}else {
				Logging.logger.info("User info retrieve succesfully");
				return u;
			}
	}
	
	public User signIn(String username, String password) {
		
		User u = uDao.getUserByUsername(username);
		
		if(u.getUserId() == 0) {
			Logging.logger.warn("User does not exist in the data base");
			throw new UserDoesNotExistException();
		}else if(!u.getPassword().equals(password)) {
			Logging.logger.warn("User tried to logIn with invalid credentials");
			throw new InvalidCredentialsException();
		}else {
			Logging.logger.info("User logged in successfully");
			return u;
		}
	}
	
	public int SignUp(String first, String last, String email, String username, String password, int roleID) {
		int rows = 0;
		
		try {
		rows = uDao.createAccount(first, last, email, username, password, roleID);
		}catch(SQLException e) {
			Logging.logger.warn("Create new account failed");
		}
		
		return rows;
	}
	
	public int updateAccount(String f, String l, String e, String u, String nPass, String cPass) {
		
		User us = uDao.getUserByUsername(u);
		System.out.println(us);
		int rowsUpdated = 0;
		
		if(us.getUserId() == 0) {
			Logging.logger.warn("User does not exist in the data base");
			Logging.logger.warn(new InvalidCredentialsException());
		}else if(!us.getPassword().equals(cPass)) {
			Logging.logger.warn("Confirmation password does not match");
			Logging.logger.warn(new InvalidCredentialsException());
		}else {
			try {
				rowsUpdated = uDao.updateUser(us.getUserId(), f, l, e, u, nPass, cPass);
			}catch(SQLException ex) {
				Logging.logger.warn("Update account failed");
			}
		}
		return rowsUpdated;
	}
}
