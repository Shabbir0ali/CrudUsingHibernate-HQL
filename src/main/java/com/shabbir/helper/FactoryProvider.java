package com.shabbir.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryProvider {

	public static SessionFactory factory;

	public static SessionFactory getFactory() {

		if (factory == null) {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			factory = configuration.buildSessionFactory();
		}

		return factory;
	}

	public static void closeFactory() {
		if (factory.isOpen()) {
			factory.close();
		}

	}
}
