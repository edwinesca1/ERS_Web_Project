package com.revature.test;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.dao.UserDao;
import com.revature.services.UserService;

public class ReimbursementServiceTest {
	@InjectMocks
	public UserService uServ;
	
	@Mock
	public UserDao uDao;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
}
