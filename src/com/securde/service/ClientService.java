package com.securde.service;

import com.securde.bean.Client;
import com.securde.util.DBUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import java.util.*;

public class ClientService 
{
	private static final String COL_USERNAME = "username";
	private static final String COL_PASSWORD = "password";

	public static List<Client> getAllClients()
	{
		List<Client> clients = new ArrayList<Client>();
		
		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<Client> q = em.createQuery("FROM client", Client.class);
			clients = q.getResultList();
			
			trans.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return clients;
	}
	
	public static boolean addClient(Client c)
	{
		boolean added = false;
		
		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			em.merge(c);
			
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
	
	public static boolean updateClient(int id, Client newinfo)
	{
		boolean updated = false;

		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			Client c = em.find(Client.class, id);
			
			c.setEmail(newinfo.getEmail());
			c.setfName(newinfo.getfName());
			c.setlName(newinfo.getlName());
			c.setUsername(newinfo.getUsername());
			c.setPassword(newinfo.getPassword());
			c.setContactNo(newinfo.getContactNo());
			c.setHomeAdd(newinfo.getHomeAdd());
			c.setUserHash(newinfo.getUserHash());
			
			em.merge(c);
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
	
	public static boolean deleteClient(int id)
	{
		boolean deleted = false;

		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			Client c = em.find(Client.class, id);
			
			em.remove(c);
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
	
	public static Client getClient(int id)
	{
		Client c = null;

		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			c = em.find(Client.class, id);
			
			trans.commit();
			
		} catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return c;
	}
	
	public static Client findClient(String username, String password)
	{
		Client c = null;
		
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			String query = "FROM client where " + COL_USERNAME + " = :username AND " + COL_PASSWORD + " = :password";
			TypedQuery<Client> q = em.createQuery(query, Client.class);
			q.setParameter("username", username);
			q.setParameter("password", password);
			List <Client> clients = q.getResultList();
			
			if(clients != null && clients.size() != 0) {
				c = clients.get(0);
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
		
		return c;
	}
}
