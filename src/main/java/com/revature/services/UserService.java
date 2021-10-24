package com.revature.services;

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
}
