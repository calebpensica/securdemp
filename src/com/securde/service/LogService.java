package com.securde.service;

import com.securde.bean.Log;
import com.securde.util.DBUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import java.util.List;
import java.util.ArrayList;

public class LogService 
{
	public static List<Log> getAllLogs()
	{
		List<Log> logs = new ArrayList<>();
		
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<Log> q = em.createQuery("FROM log", Log.class);
			logs = q.getResultList();
			
			trans.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return logs;
	}
	
	public static boolean addLog(Log l)
	{
		boolean added = false;
		
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			em.merge(l);
			
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
}
