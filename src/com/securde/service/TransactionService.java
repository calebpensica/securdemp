package com.securde.service;

import com.securde.bean.Transaction;
import com.securde.util.DBUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import java.util.*;

public class TransactionService 
{
	public static List<Transaction> getAllTransactions()
	{
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<Transaction> q = em.createQuery("FROM transaction", Transaction.class);
			transactions = q.getResultList();
			
			trans.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return transactions;
	}
	
	public static boolean addTransaction(Transaction t)
	{
		boolean added = false;
		
		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			em.merge(t);
			
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
	
	public static boolean updateTransaction(int id, Transaction newinfo)
	{
		boolean updated = false;

		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			Transaction t = em.find(Transaction.class, id);
			
			t.setBuyer(newinfo.getBuyer());
			t.setCart(newinfo.getCart());
			t.setDeliveryAdd(newinfo.getDeliveryAdd());
			t.setSum(newinfo.getSum());
			t.setTimeOrder(newinfo.getTimeOrder());
			t.setTimeReceived(newinfo.getTimeReceived());
			
			em.merge(t);
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
	
	public static boolean deleteTransaction(int id)
	{
		boolean deleted = false;

		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			Transaction t = em.find(Transaction.class, id);
			
			em.remove(t);
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
	
	public static Transaction getTransaction(int id)
	{
		Transaction t = null;

		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			t = em.find(Transaction.class, id);
			
			trans.commit();
			
		} catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return t;
	}

}
