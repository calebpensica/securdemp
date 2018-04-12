package com.securde.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.securde.bean.CartItem;
import com.securde.bean.Product;
import com.securde.util.DBUtil;

public class CartItemService 
{
	
	private static final String COL_CART = "cartid";
	
	public static List<CartItem> getAllCartItems()
	{
		List<CartItem> cartitems = new ArrayList<CartItem>();
		
		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<CartItem> q = em.createQuery("FROM cartitem", CartItem.class);
			cartitems = q.getResultList();
			
			trans.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return cartitems;
	}
	
	public static boolean addCartItem(CartItem ci)
	{
		boolean added = false;
		
		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			em.merge(ci);
			
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
	
	public static boolean updateCartItem(int id, CartItem newinfo)
	{
		boolean updated = false;

		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			CartItem ci = em.find(CartItem.class, id);
			
			ci.setCart(newinfo.getCart());
			ci.setProduct(newinfo.getProduct());
			ci.setQuantity(newinfo.getQuantity());
			
			em.merge(ci);
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
	
	public static boolean deleteCartItem(int id)
	{
		boolean deleted = false;

		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			CartItem ci = em.find(CartItem.class, id);
			
			em.remove(ci);
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
	
	public static CartItem getCartItem(int id)
	{
		CartItem ci = null;

		EntityManager em = DBUtil.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			ci = em.find(CartItem.class, id);
			
			trans.commit();
			
		} catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return ci;
	}
	
	public static boolean deleteCartItems(int cartid)
	{
		boolean deleted = false;
		List<CartItem> items = new ArrayList<>();
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			String query = "FROM cartitem where " + COL_CART + " = :cart"; 
			TypedQuery<CartItem> q = em.createQuery(query, CartItem.class);
			q.setParameter("cart", cartid);
			items = q.getResultList();
			trans.commit();
			deleted = true;
			
			for(CartItem ci: items)
			{
				if(deleteCartItem(ci.getCitemid()))
					deleted = true;
				else {
					deleted = false;
					break;
				}
			}
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
}