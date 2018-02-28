package com.securde.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
	
	private static EntityManagerFactory factory;
	
	public static EntityManager getEntityManager(){
		factory = Persistence.createEntityManagerFactory("papema");
		return factory.createEntityManager();
	}
}
