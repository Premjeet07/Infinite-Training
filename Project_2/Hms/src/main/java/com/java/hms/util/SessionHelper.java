package com.java.hms.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class SessionHelper {

	public static SessionFactory getConnection() {
		return new AnnotationConfiguration().configure().buildSessionFactory();
	}
}
