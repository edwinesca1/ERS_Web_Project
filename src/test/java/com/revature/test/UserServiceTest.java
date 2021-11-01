package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.hamcrest.CoreMatchers.*;

import com.revature.dao.UserDao;
import com.revature.exceptions.InvalidCredentialsException;
import com.revature.exceptions.UserDoesNotExistException;
import com.revature.models.User;
import com.revature.services.UserService;

public class UserServiceTest {
	@InjectMocks
	public UserService uServ;
	
	@Mock
	public UserDao uDao;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testValidLogin() {
		User u1 = new User(1, "testuser", "testpass","test", "user", "test@mail.com", 2);
		
		when(uDao.getUserByUsername(anyString())).thenReturn(u1);
		
		User loggedIn = uServ.signIn("testuser", "testpass");
		
		assertEquals(u1.getUserId(), loggedIn.getUserId());
	}
	
	@Test(expected = UserDoesNotExistException.class)
	public void testInvalidUsername() {
		User not = new User(0, "test", "user", "test@mail.com", "testuser", "testpass", 0);
		
		when(uDao.getUserByUsername(anyString())).thenReturn(not);
		
		User loggedIn = uServ.signIn("test", "testpass");
	}
	
	@Test(expected = InvalidCredentialsException.class)
	public void testInvalidPassword() {
		User not = new User(1, "test", "user", "test@mail.com", "testuser", "wrongpass",0);
		
		when(uDao.getUserByUsername(anyString())).thenReturn(not);
		
		uServ.signIn("testuser", "testpass");
	}  
	
	@Test(expected = UserDoesNotExistException.class)
	public void testInvalidUserByID() {
		User not = new User(0, "", "", "", "", "", 0);
		
		when(uDao.getUserById(1)).thenReturn(not);
		
		User customer = uServ.getUserById(1);
	}
	
	@Test
	public void testEmptyUserList() {
		List<User> userList = uServ.getAllUsers();
		assertEquals("The list should have no users", 0, userList.size());
	}
	
	@Test
	public void testGetUsersById() {
		User not = new User(1, "test", "user", "test@mail.com", "testuser", "wrongpass",0);
		
		when(uDao.getUserById(1)).thenReturn(not);
		
		User customer = uServ.getUserById(1);
		
		assertEquals(1, customer.getUserId());
	} 
	
	
	@Test
	public void testGetUsersByFullName() {
		User jack = new User(1, "test", "user", "test@mail.com", "testuser", "wrongpass",0);
		List<User> userList = new ArrayList<User>();
		userList.add(jack);
		
		when(uDao.getUserByFullName(anyString())).thenReturn(userList);
		
		uServ.getUserByFullName("test");
		//assertEquals(jack.getfName(), userList.get(1).getfName());
	}
	
	@Test
	public void testCreatNewUser() throws SQLException {
		//uServ.SignUp("test", "user", "test@email.com", "testuser", "password", 1);
		when(uServ.SignUp("test", "user", "test@email.com", "testuser", "password", 1)).thenReturn(1);
		List<User> userList = uServ.getAllUsers();
		
		when(uDao.createAccount(anyString(), anyString(), anyString(), anyString(), anyString(), anyInt())).thenThrow(new SQLException());
		
		int val = uServ.SignUp("", "", "", "", "", 0);
		//assertEquals(1, userList.size());
	}
	
	@Test(expected = InvalidCredentialsException.class)
	public void testUpdateAccountInvalidCredentials() throws SQLException {
		User u1 = new User(1, "testuser", "testpass","test", "user", "test@mail.com", 2);
		User u2 = new User(2, "testuser1", "testpass","test1", "user1", "test1@mail.com", 2);
		User not = new User(0, "", "", "", "", "", 0);
		when(uDao.getUserByUsername(anyString())).thenReturn(u1);
		
		when(uDao.updateUser(anyInt(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenThrow(new InvalidCredentialsException());
		
		int customer = uServ.updateAccount("", "", "", "", "", "testwrong");	
	} 
	
	@Test(expected = InvalidCredentialsException.class)
	public void testUpdateAccountInvalidCredentials2() throws SQLException {
		User u1 = new User(1, "testuser", "testpass","test", "user", "test@mail.com", 2);
		User u2 = new User(2, "testuser1", "testpass","test1", "user1", "test1@mail.com", 2);
		User not = new User(0, "", "", "", "", "", 0);
		when(uDao.getUserByUsername(anyString())).thenReturn(not);
		
		when(uDao.updateUser(anyInt(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenThrow(new InvalidCredentialsException());
		
		int customer = uServ.updateAccount("", "", "", "", "", "testwrong");	
	} 
	
	@Test
	public void testUpdateAccountFail() throws SQLException {
		User u1 = new User(1, "testuser", "testpass","test", "user", "test@mail.com", 2);
		User u2 = new User(2, "testuser1", "testpass","test1", "user1", "test1@mail.com", 2);
		when(uDao.getUserByUsername(anyString())).thenReturn(u1);
		
		when(uDao.updateUser(anyInt(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenThrow(new SQLException());
		
		int customer = uServ.updateAccount("", "", "", "", "", "testpass");
	} 
	
	@Test
	public void testUpdateAccount() {
		User u1 = new User(1, "testuser", "testpass","test", "user", "test@mail.com", 2);
		User u2 = new User(2, "testuser1", "testpass2","test1", "user1", "test1@mail.com", 2);
		
		when(uDao.getUserByUsername(anyString())).thenReturn(u2);
		//when(uDao.updateUser(2, anyString(), anyString(), anyString(), anyString(), anyString(), "testpass2")).thenReturn(1);
		
		int customer = uServ.updateAccount("", "", "", "", "", "testpass2");
	} 
	
}
