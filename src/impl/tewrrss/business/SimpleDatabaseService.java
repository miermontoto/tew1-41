package impl.tewrrss.business;

import com.tewrrss.business.DatabaseService;
import com.tewrrss.util.Database;

public class SimpleDatabaseService implements DatabaseService {

	@Override
	public boolean reset() {
		return new Database().reset();
	}

}
