package impl.tewrrss.business;

import java.util.ArrayList;
import java.util.List;

import com.tewrrss.business.DatabaseService;
import com.tewrrss.dto.*;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.persistence.*;
import com.tewrrss.util.Role;

public class SimpleDatabaseService implements DatabaseService {

	@Override
	public boolean reset() {
		PostDAO postDAO = Factories.persistence.getPostDAO();
		UserDAO userDAO = Factories.persistence.getUserDAO();
		CommunityDAO communityDAO = Factories.persistence.getCommunityDAO();

		// Drop all values
		postDAO.dropAll();
		communityDAO.dropAll();
		userDAO.dropAll();
		
		
		// Fill with sample values
		List<Community> sampleCommunities = new ArrayList<>();
		List<User> sampleUsers = new ArrayList<>();
		List<Object[]> sampleMemberships = new ArrayList<>();

		sampleCommunities.add(new Community("tew.music", "the music community."));
		sampleCommunities.add(new Community("tew.food", "the food community."));
		sampleCommunities.add(new Community("tew.programming", "the programming community."));
		sampleCommunities.add(new Community("tew.cars", "the cars community."));
		sampleCommunities.add(new Community("tew.daftpunk", "the better music community."));
		sampleCommunities.add(new Community("tew.linux", "the linux community."));

		sampleUsers.add(new User("admin@email.com", "admin", "admin", Role.ADMIN));
		for(int i = 1; i <= 10; i++) {
			sampleUsers.add(new User(String.format("user%d@email.com", i),
				String.format("user%d", i), String.format("user%d", i)));
		}

		// Generate random memberships
		for (User u : sampleUsers) {
			for (Community c : sampleCommunities) {
				if (Math.random() < 0.33) {
					sampleMemberships.add(new Object[] {c, u});
				}
			}
		}

		sampleCommunities.forEach(communityDAO::add);
		sampleUsers.forEach(userDAO::add);
		for(Object[] p : sampleMemberships) {
			communityDAO.join((Community) p[0], (User) p[1]);
		}

		return true;
	}

}
