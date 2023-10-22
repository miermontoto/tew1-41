package impl.tewrrss.business;

import java.util.Optional;

import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

public class SimpleLoginService implements com.tewrrss.business.LoginService {

	@Override
	public User verify(String email, String password) {
		Optional<User> user = Factories.persistence.getUserDAO().findByEmail(email);

		if (user.isPresent()) {
			if (user.get().getPassword().equals(password)) {
				return user.get();
			}
		}

		return null;
	}
}
