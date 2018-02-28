package com.securde.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
	
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysqldb");
	
	public static EntityManager getEntityManager(){
		return factory.createEntityManager();
	}
}
