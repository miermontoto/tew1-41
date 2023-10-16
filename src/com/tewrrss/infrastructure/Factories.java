package com.tewrrss.infrastructure;

import impl.tewrrss.business.SimpleServicesFactory;
import com.tewrrss.business.ServicesFactory;

public class Factories {

	public static final ServicesFactory services = new SimpleServicesFactory();

	private Factories() { }

}
