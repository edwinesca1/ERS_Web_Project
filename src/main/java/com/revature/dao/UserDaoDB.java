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
	
	
	public int getEmailUsername(String username, String email){
		 int goodBad = 0;
		try {
			//make the actual connection to the DB
			Connection con = conUtil.getConnection();
			
			//Creating a simple statement
			String sql = "select ers_users.ers_username, ers_users.user_email from ers_users";
			
			//We need to create a statement with the sql string
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			//We have to loop through the resultset an create objects based off the return
			while(rs.next()) {
				System.out.println("printing usernames: " + rs.getString(1));
				System.out.println("printing emails: "+rs.getString(2));
				if(username.toLowerCase().equals(rs.getString(1).toLowerCase())) {
					return 2;
				}else if(email.toLowerCase().equals(rs.getString(2).toLowerCase())) {
					return 3;
				}
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return goodBad;
	}
	
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
	public List<User> getUserByFullName(String username) {
		List<User> userList = new ArrayList<User>();
		
		try {
			//make the actual connection to the DB
			Connection con = conUtil.getConnection();
			
			//Creating a simple statement
			String sql = "SELECT * FROM ers_users where lower(concat(user_first_name, ' ', user_last_name)) like '%"+ username.toLowerCase() +"%'";
			
			//We need to create a statement with the sql string
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			//We have to loop through the resultset an create objects based off the return
			while(rs.next()) {
				userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
			//System.out.println(userList);
			return userList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public User getUserById(int userId) {
		User user = new User();
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "SELECT * FROM ers_users WHERE ers_users.ers_user_id = " + userId;
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				user.setUserId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setfName(rs.getString(4));
				user.setlName(rs.getString(5));
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
				user.setfName(rs.getString(4));
				user.setlName(rs.getString(5));
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
	
	@Override
	public int createAccount(String first, String last, String email, String username, String password, int roleID)
			throws SQLException {
		
		Connection con = conUtil.getConnection();
		
		int verify = getEmailUsername(username, email);
		
		System.out.println("printing veify: "+ verify);
		if(verify > 0)
		{
			return verify;
		}else {
			System.out.println("printing before insert into users");
			//Another way to crate the statement for the query
			String sql = "INSERT INTO ers_users(ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) VALUES"
					+"(?,?,?,?,?,?) returning ers_user_id";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, first);
			ps.setString(4, last);
			ps.setString(5, email);
			ps.setInt(6, roleID);
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			rs.next();
			int idCol =  rs.getInt(1);
			System.out.println(rs.getInt(1));
			if(idCol > 0) {return 1;}
			else return 0;
		}
	}

	@Override
	public int updateUser(int id, String f, String l, String e, String u, String nPass, String cPass) throws SQLException {
		
		Connection con = conUtil.getConnection();
		
		String sql = "";
		
		if(nPass == null || nPass == "") {
			sql = "UPDATE ers_users "
					+ "SET user_first_name='"+ f +"',"
					+ "	user_last_name='"+ l +"',"
					+ "	user_email='"+ e +"' "
					+ "WHERE ers_user_id = "+ id ;
		}else {
			sql = "UPDATE ers_users "
					+ "SET user_first_name='"+ f +"',"
					+ "	user_last_name='"+ l +"',"
					+ "	user_email='"+ e +"',"
					+ "	ers_password='"+ nPass +"' "
					+ "WHERE ers_user_id = "+ id ;
		}
		Statement s = con.createStatement();
		int rowsAffected = s.executeUpdate(sql);
		
		return rowsAffected;
	}
}
