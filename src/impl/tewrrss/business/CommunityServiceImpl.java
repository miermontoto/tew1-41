package impl.tewrrss.business;


import java.util.List;

import com.tewrrss.business.CommunityService;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

import impl.tewrrss.business.communities.AbleToJoin;
import impl.tewrrss.business.communities.Create;
import impl.tewrrss.business.communities.Join;
import impl.tewrrss.business.communities.Leave;
import impl.tewrrss.business.communities.ListAll;
import impl.tewrrss.business.communities.ListJoined;
import impl.tewrrss.business.communities.Remove;

public class CommunityServiceImpl implements CommunityService {

	@Override
	public String create(Community comunidad) {
		return new Create().crearComunidad(comunidad);
	}

	@Override
	public String remove(Community comunidad) {
		return new Remove().borrarComunidad(comunidad);
	}

	@Override
	public List<Community> listAll() {
		return new ListAll().listAll();
	}

	@Override
	public List<Community> listJoined(User user) {
		return new ListJoined().listJoined(user);
	}

	@Override
	public String join(Community community, User user) {
		return new Join().join(community, user);
	}

	@Override
	public String leave(Community community, User user) {
		return new Leave().leave(community, user);
	}

	@Override
	public boolean ableToJoin(Community comunidad, User user) {
		return new AbleToJoin().ableToJoin(comunidad, user);
	}

}
