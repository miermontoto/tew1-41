package impl.tewrrss.business;

import com.tewrrss.model.Role;
import com.tewrrss.model.User;

// TODO: implementar un servicio que no sea de juguete (con conexión a BD y comprobaciones)
public class SimpleLoginService implements com.tewrrss.business.LoginService {

	@Override
	public User verify(String login, String password) {
		if (!validLogin(login, password)) return null;
		return new User(login, "admin", Role.ADMIN);
	}

	@Override
	public boolean validLogin(String login, String password) {
		// TODO: login en BBDD
		return login.equals("admin") && password.equals("tew");
	}
}
