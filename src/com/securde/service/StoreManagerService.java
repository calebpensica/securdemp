package com.securde.service;

import com.securde.bean.StoreManager;
import com.securde.util.DBUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import java.util.*;

public class StoreManagerService 
{
	public static List<StoreManager> getAllManagers()
	{
		List<StoreManager> managers = new ArrayList<StoreManager>();
		
		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<StoreManager> q = em.createQuery("FROM storemanager", StoreManager.class);
			managers = q.getResultList();
			
			trans.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return managers;
	}
	
	public static boolean addManager(StoreManager s)
	{
		boolean added = false;
		
		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			em.merge(s);
			
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
	
	public static boolean updateManager(int id, StoreManager newinfo)
	{
		boolean updated = false;

		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			StoreManager s = em.find(StoreManager.class, id);
			
			s.setEmail(newinfo.getEmail());
			s.setfName(newinfo.getfName());
			s.setlName(newinfo.getlName());
			s.setUsername(newinfo.getUsername());
			s.setPassword(newinfo.getPassword());
			s.setUserHash(newinfo.getUserHash());
			
			em.merge(s);
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
	
	public static boolean deleteManager(int id)
	{
		boolean deleted = false;

		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			StoreManager s = em.find(StoreManager.class, id);
			
			em.remove(s);
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
	
	public static StoreManager getManager(int id)
	{
		StoreManager s = null;

		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			s = em.find(StoreManager.class, id);
			
			trans.commit();
			
		} catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return s;
	}
	
	private static final String COL_USERNAME = "username";
	private static final String COL_PASSWORD = "password";
	
	public static StoreManager findManager(String username, String password)
	{
		StoreManager s = null;
		
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			String query = "FROM storemanager where " + COL_USERNAME + " = :username AND " + COL_PASSWORD + " = :password";
			TypedQuery<StoreManager> q = em.createQuery(query, StoreManager.class);
			q.setParameter("username", username);
			q.setParameter("password", password);
			List <StoreManager> managers = q.getResultList();
			
			if(managers != null && managers.size() != 0) {
				s = managers.get(0);
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
		
		return s;
	}
}
