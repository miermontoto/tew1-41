package impl.tewrrss.business;

import com.tewrrss.business.LoginService;
import com.tewrrss.business.ServicesFactory;

public class SimpleServicesFactory implements ServicesFactory {

	@Override
	public LoginService createLoginService() {
		return new SimpleLoginService();
	}

}
