package com.securde.service;

import com.securde.bean.Admin;
import com.securde.bean.Product;
import com.securde.bean.Tag;
import com.securde.util.DBUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import java.util.*;

public class ProductService {
	
	private static final String COL_PRICE = "price";

	public static List<Product> getAllProducts()
	{
		List<Product> products = new ArrayList<Product>();
		
		EntityManager em = DBUtil.getEntityManager();
		
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
		
		EntityManager em = DBUtil.getEntityManager();
		
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
		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			Product p = em.find(Product.class, id);
			
			p.setImagePath(newinfo.getImagePath());
			p.setName(newinfo.getName());
			p.setPrice(newinfo.getPrice());
			p.setStatus(newinfo.isStatus());
	//		p.setTags((HashSet<Tag>)newinfo.getTags());
			
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
		EntityManager em = DBUtil.getEntityManager();
		
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
		EntityManager em = DBUtil.getEntityManager();
		
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
	
	public static List<Product> getAllProductsByPriceRange(float min, float max)
	{
		List<Product> products = null;
		
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			String query = "FROM product where " + COL_PRICE + " >= :min AND " + COL_PRICE + " <= :max";
			TypedQuery<Product> q = em.createQuery(query, Product.class);
			q.setParameter("min", min);
			q.setParameter("max", max);
			products = q.getResultList();
			
			trans.commit();
			
		} catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return products;
	}

}
