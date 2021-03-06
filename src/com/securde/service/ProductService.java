package com.securde.service;

import com.securde.bean.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.*;

public class ProductService {

	public static List<Product> getAllProducts()
	{
		List<Product> products = new ArrayList<Product>();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<Product> q = em.createQuery("FROM product", Product.class);
			products = q.getResultList();
			
			trans.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return products;
	}
	
	public static boolean addProduct(Product p)
	{
		boolean added = false;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			em.merge(p);
			
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
	
	public static boolean updateProduct(int id, Product newinfo)
	{
		boolean updated = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			Product p = em.find(Product.class, id);
			
			p.setImagePath(newinfo.getImagePath());
			p.setName(newinfo.getName());
			p.setPrice(newinfo.getPrice());
			p.setStatus(newinfo.isStatus());
			p.setTags(newinfo.getTags());
			
			em.merge(p);
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
	
	public static boolean deleteProduct(int id)
	{
		boolean deleted = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			Product p = em.find(Product.class, id);
			
			em.remove(p);
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
	
	public static Product getProduct(int id)
	{
		Product p = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			p = em.find(Product.class, id);
			
			trans.commit();
			
		} catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return p;
	}

}
