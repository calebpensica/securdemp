package com.securde.service;

import com.securde.bean.Test;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class TestService {
	
	public static void addTest(Test t) {
		//connect to db
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("papema");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			// run an insert of Test t into the database
			em.persist(t);
			
			trans.commit();
		}catch(Exception e){
			if(trans != null){
				trans.rollback();
			}
			e.printStackTrace();
		}finally{
			// close all connections
			em.close();
		}
	}

}
