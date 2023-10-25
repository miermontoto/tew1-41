package impl.tewrrss.business;

import com.tewrrss.business.ComunidadesService;
import com.tewrrss.business.LoginService;
import com.tewrrss.business.ServicesFactory;

public class SimpleServicesFactory implements ServicesFactory {

	@Override
	public LoginService createLoginService() {
		return new SimpleLoginService();
	}

	@Override
	public ComunidadesService createCommunityService() {
		return new ComunidadesServiceImpl();
	}

}
