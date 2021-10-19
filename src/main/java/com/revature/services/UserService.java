package com.revature.services;

import java.util.List;

import com.revature.dao.UserDao;
import com.revature.exceptions.InvalidCredentialsException;
import com.revature.exceptions.UserDoesNotExistException;
import com.revature.logging.Logging;
import com.revature.models.User;

public class UserService {
	
	private UserDao uDao;
	
	//Retrieving all users as admin
	public List<User> getAllUsers(){
		return uDao.getAllUsers();
	}
	
	public User SignIn(String username, String password) {
		
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
}
