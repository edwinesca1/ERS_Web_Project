package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDaoDB implements UserDao{

	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	
	@Override
	public List<User> getAllUsers() {
		
		List<User> userList = new ArrayList<User>();
		
		try {
			//make the actual connection to the DB
			Connection con = conUtil.getConnection();
			
			//Creating a simple statement
			String sql = "SELECT * FROM ers_users";
			
			//We need to create a statement with the sql string
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			//We have to loop through the resultset an create objects based off the return
			while(rs.next()) {
				userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
			return userList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUserByUsername(String userUserName) {

		User user = new User();
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "SELECT * FROM ers_users WHERE ers_users.ers_username = '" + userUserName + "'";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				user.setUserId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setlName(rs.getString(4));
				user.setfName(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setUserRole(rs.getInt(7));
			}
			return user;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User createUser(User u) throws SQLException {

		Connection con = conUtil.getConnection();
		
		//Another way to crate the statement for the query
		String sql = "INSERT INTO ers_users(ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) VALUES"
				+"(?,?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, u.getUsername());
		ps.setString(2, u.getPassword());
		ps.setString(3, u.getfName());
		ps.setString(4, u.getlName());
		ps.setString(5, u.getEmail());
		ps.setInt(6, u.getUserRole());
		
		ps.execute();
		
		User u1 = getUserByUsername(u.getUsername()); 
		return u1;
	}

}
