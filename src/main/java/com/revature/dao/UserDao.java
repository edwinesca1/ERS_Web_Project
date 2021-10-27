package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.models.User;

public interface UserDao {
	
	List<User> getAllUsers();
	
	List<User> getUserByFullName(String username);
	
	User getUserByUsername(String userUserName);
	
	User getUserById(int userId);
	
	User createUser(User u) throws SQLException;
	
	int updateUser(int id, String f, String l, String e, String u, String nPass, String cPass) throws SQLException;
}
