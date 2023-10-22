package impl.tewrrss.persistence;

import com.tewrrss.persistence.UserDAO;
import com.tewrrss.persistence.PersistenceFactory;
import impl.tewrrss.persistence.jdbc.UserJdbcDAO;

public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public UserDAO getUserDAO() {
		return new UserJdbcDAO();
	}

}
