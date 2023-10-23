package com.tewrrss.infrastructure;

import impl.tewrrss.business.SimpleServicesFactory;
import impl.tewrrss.persistence.SimplePersistenceFactory;

import com.tewrrss.business.ServicesFactory;
import com.tewrrss.persistence.PersistenceFactory;

public class Factories {

	public static final ServicesFactory services = new SimpleServicesFactory();
	public static final PersistenceFactory persistence = new SimplePersistenceFactory();

	private Factories() { }

}
