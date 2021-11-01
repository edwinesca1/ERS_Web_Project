package com.revature.test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.models.ReimbursementDisplay;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class ReimbursementServiceTest {
	
	@InjectMocks
	public UserService uServ;
	
	@InjectMocks
	public ReimbursementService rServ;
	
	@Mock
	public UserDao uDao;
	
	@Mock
	public ReimbursementDao rDao;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testEmptyReimbursementList() {
		List<ReimbursementDisplay> reimbList = rServ.getAllReimbursement();
		assertEquals("The list should have no users", 0, reimbList.size());
	}
	
	@Test
	public void testGetReimbursementsInFull() {
		ReimbursementDisplay reimb = new ReimbursementDisplay(1, 2000, "dateSub", "dateRes", "description", 1, 2, 1, 4, "afname", "alname", "rfname", "rlname", "status", "type");
		ReimbursementDisplay reimb2 = new ReimbursementDisplay(2, 3000, "dateSub", "dateRes", "description", 1, 2, 1, 4, "afname", "alname", "rfname", "rlname", "status", "type");
		List<ReimbursementDisplay> reimbList = new ArrayList<ReimbursementDisplay>();
		reimbList.add(reimb);
		reimbList.add(reimb2);
		
		when(rDao.getAllReimbursement2()).thenReturn(reimbList);
		
		List<ReimbursementDisplay> newReimbList = rServ.getAllReimbursementInFull();
		
		assertEquals(2, newReimbList.size());
	}
	

	@Test
	public void testGetReimbursementsByAuthor() {
		ReimbursementDisplay reimb = new ReimbursementDisplay(1, 2000, "dateSub", "dateRes", "description", 1, 2, 1, 4, "afname", "alname", "rfname", "rlname", "status", "type");
		ReimbursementDisplay reimb2 = new ReimbursementDisplay(2, 3000, "dateSub", "dateRes", "description", 1, 2, 1, 4, "afname", "alname", "rfname", "rlname", "status", "type");
		List<ReimbursementDisplay> reimbList = new ArrayList<ReimbursementDisplay>();
		//reimbList.add(reimb);
		reimbList.add(reimb2);
		
		when(rDao.getAllReimbursementByAuthor(2)).thenReturn(reimbList);
		
		List<ReimbursementDisplay> newReimbList = rServ.getAllReimbursementById(2);
		
		assertEquals(3000, newReimbList.get(0).getAmount(), 0);
	}
	
	@Test
	public void testGetReimbursementsFilteredIUE() {
		ReimbursementDisplay reimb = new ReimbursementDisplay(1, 2000, "dateSub", "dateRes", "description", 1, 2, 1, 4, "jack", "alname", "rfname", "rlname", "status", "Lodging");
		ReimbursementDisplay reimb2 = new ReimbursementDisplay(2, 3000, "dateSub", "dateRes", "description", 1, 2, 1, 4, "Rick", "alname", "rfname", "rlname", "status", "Food");
		List<ReimbursementDisplay> reimbList = new ArrayList<ReimbursementDisplay>();
		reimbList.add(reimb);
		reimbList.add(reimb2);
		
		when(rDao.getAllReimbursementIUE(anyInt(), anyString(), anyString())).thenReturn(reimbList);
		
		List<ReimbursementDisplay> newReimbList = rServ.getAllReimbursementIUE(0, "ck", "");
		
		assertEquals("Lodging", newReimbList.get(0).getReimbType());
		assertEquals("Food", newReimbList.get(1).getReimbType());
	}
	
	@Test
	public void testCreateRequestReimbFail() throws SQLException {
		ReimbursementDisplay reimb = new ReimbursementDisplay(1, 2000, "dateSub", "dateRes", "description", 1, 2, 1, 4, "jack", "alname", "rfname", "rlname", "status", "Lodging");
		ReimbursementDisplay reimb2 = new ReimbursementDisplay(2, 3000, "dateSub", "dateRes", "description", 1, 2, 1, 4, "Rick", "alname", "rfname", "rlname", "status", "Food");
		List<ReimbursementDisplay> reimbList = new ArrayList<ReimbursementDisplay>();
		reimbList.add(reimb);
		reimbList.add(reimb2);
		
		when(rDao.createNewReimbursement(anyInt(), anyDouble(), anyString(), anyString(), anyInt(), anyInt())).thenThrow(new SQLException());
		
		int val = rServ.createNewReimb(0, 0, "", "", 0, 0);
		
		//assertEquals(1, val);
	}
	
	@Test
	public void testCreateRequestReimb() throws SQLException {
		ReimbursementDisplay reimb = new ReimbursementDisplay(1, 2000, "dateSub", "dateRes", "description", 1, 2, 1, 4, "jack", "alname", "rfname", "rlname", "status", "Lodging");
		ReimbursementDisplay reimb2 = new ReimbursementDisplay(2, 3000, "dateSub", "dateRes", "description", 1, 2, 1, 4, "Rick", "alname", "rfname", "rlname", "status", "Food");
		List<ReimbursementDisplay> reimbList = new ArrayList<ReimbursementDisplay>();
		reimbList.add(reimb);
		reimbList.add(reimb2);
		
		when(rDao.createNewReimbursement(anyInt(), anyDouble(), anyString(), anyString(), anyInt(), anyInt())).thenReturn(1);
		
		int val = rServ.createNewReimb(4, 2000, "text", "moreText", 1, 3);
		
		assertEquals(1, val);
	}
	
	@Test
	public void testResolveReimbFail() throws SQLException {
		ReimbursementDisplay reimb = new ReimbursementDisplay(1, 2000, "dateSub", "dateRes", "description", 1, 2, 1, 4, "jack", "alname", "rfname", "rlname", "status", "Lodging");
		ReimbursementDisplay reimb2 = new ReimbursementDisplay(2, 3000, "dateSub", "dateRes", "description", 1, 2, 1, 4, "Rick", "alname", "rfname", "rlname", "status", "Food");
		List<ReimbursementDisplay> reimbList = new ArrayList<ReimbursementDisplay>();
		reimbList.add(reimb);
		reimbList.add(reimb2);
		
		when(rDao.ApproveDenyReimbursement(anyInt(), anyString(), anyInt(), anyInt())).thenThrow(new SQLException());
		
		int val = rServ.resolveReimbursement(2, "information", 2, 4);
		
		//assertEquals(1, val);
	}
	
	@Test
	public void testResolveReimb() throws SQLException {
		ReimbursementDisplay reimb = new ReimbursementDisplay(1, 2000, "dateSub", "dateRes", "description", 1, 2, 1, 4, "jack", "alname", "rfname", "rlname", "status", "Lodging");
		ReimbursementDisplay reimb2 = new ReimbursementDisplay(2, 3000, "dateSub", "dateRes", "description", 1, 2, 1, 4, "Rick", "alname", "rfname", "rlname", "status", "Food");
		List<ReimbursementDisplay> reimbList = new ArrayList<ReimbursementDisplay>();
		reimbList.add(reimb);
		reimbList.add(reimb2);
		
		when(rDao.ApproveDenyReimbursement(anyInt(), anyString(), anyInt(), anyInt())).thenReturn(1);
		
		int val = rServ.resolveReimbursement(2, "information", 2, 4);
		
		assertEquals(1, val);
	}
	
	
}
