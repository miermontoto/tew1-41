package com.tewrrss.business;

public interface ServicesFactory {
	LoginService createLoginService();
	DatabaseService createDatabaseService();
	ComunidadesService createCommunityService();
}
