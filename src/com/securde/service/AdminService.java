package com.securde.service;

import com.securde.bean.Admin;
import com.securde.util.DBUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import java.util.*;

public class AdminService 
{
	private static final String COL_USERNAME = "username";
	private static final String COL_PASSWORD = "password";
	
	public static List<Admin> getAllAdmins()
	{
		List<Admin> admins = new ArrayList<Admin>();
		
		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<Admin> q = em.createQuery("FROM admin", Admin.class);
			admins = q.getResultList();
			
			trans.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return admins;
	}
	
	public static boolean addAdmin(Admin a)
	{
		boolean added = false;
		
		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			em.merge(a);
			
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
	
	public static boolean updateAdmin(int id, Admin newinfo)
	{
		boolean updated = false;

		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			Admin a = em.find(Admin.class, id);
			
			a.setEmail(newinfo.getEmail());
			a.setfName(newinfo.getfName());
			a.setlName(newinfo.getlName());
			a.setUsername(newinfo.getUsername());
			a.setPassword(newinfo.getPassword());
			a.setUserHash(newinfo.getUserHash());
			
			em.merge(a);
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
	
	public static boolean deleteAdmin(int id)
	{
		boolean deleted = false;

		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			Admin a = em.find(Admin.class, id);
			
			em.remove(a);
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
	
	public static Admin getAdmin(int id)
	{
		Admin a = null;

		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			a = em.find(Admin.class, id);
			
			trans.commit();
			
		} catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return a;
	}
	
	public static Admin findAdmin(String username, String password)
	{
		Admin a = null;
		
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			String query = "FROM admin where " + COL_USERNAME + " = :username AND " + COL_PASSWORD + " = :password";
			TypedQuery<Admin> q = em.createQuery(query, Admin.class);
			q.setParameter("username", username);
			q.setParameter("password", password);
			List <Admin> admins = q.getResultList();
			
			if(admins != null && admins.size() != 0) {
				a = admins.get(0);
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
		
		return a;
	}
}
