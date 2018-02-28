package com.securde.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.securde.bean.InventoryStaff;
import com.securde.util.DBUtil;

public class InventoryStaffService 
{
	public static List<InventoryStaff> getAllInventoryStaffs()
	{
		List<InventoryStaff> staffs = new ArrayList<InventoryStaff>();
		
		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<InventoryStaff> q = em.createQuery("FROM inventorystaff", InventoryStaff.class);
			staffs = q.getResultList();
			
			trans.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return staffs;
	}
	
	public static boolean addStaff(InventoryStaff i)
	{
		boolean added = false;
		
		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			em.merge(i);
			
			trans.commit();
			added = true;
		} catch (Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return added;
	}
	
	public static boolean updateStaff(int id, InventoryStaff newinfo)
	{
		boolean updated = false;

		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			InventoryStaff i = em.find(InventoryStaff.class, id);
			
			i.setEmail(newinfo.getEmail());
			i.setfName(newinfo.getfName());
			i.setlName(newinfo.getlName());
			i.setUsername(newinfo.getUsername());
			i.setPassword(newinfo.getPassword());
			
			em.merge(i);
			trans.commit();
			updated = true;
		} catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return updated;
	}
	
	public static boolean deleteStaff(int id)
	{
		boolean deleted = false;

		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			InventoryStaff i = em.find(InventoryStaff.class, id);
			
			em.remove(i);
			trans.commit();
			deleted = true;
		} catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return deleted;
	}
	
	public static InventoryStaff getStaff(int id)
	{
		InventoryStaff i = null;

		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			i = em.find(InventoryStaff.class, id);
			
			trans.commit();
			
		} catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return i;
	}
	
	private static final String COL_USERNAME = "username";
	private static final String COL_PASSWORD = "password";
	
	public static InventoryStaff findStaff(String username, String password)
	{
		InventoryStaff i = null;
		
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			String query = "FROM inventorystaff where " + COL_USERNAME + " = :username AND " + COL_PASSWORD + " = :password";
			TypedQuery<InventoryStaff> q = em.createQuery(query, InventoryStaff.class);
			q.setParameter("username", username);
			q.setParameter("password", password);
			List <InventoryStaff> staffs = q.getResultList();
			
			if(staffs != null && staffs.size() != 0) {
				i = staffs.get(0);
			}
			trans.commit();
			
		} catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}	
		
		return i;
	}
}
