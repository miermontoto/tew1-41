package impl.tewrrss.business;


import java.util.List;

import com.tewrrss.business.CommunityService;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

import impl.tewrrss.business.methods.AbleToJoin;
import impl.tewrrss.business.methods.RemoveCommunities;
import impl.tewrrss.business.methods.CrearComunidades;
import impl.tewrrss.business.methods.JoinCommunity;
import impl.tewrrss.business.methods.LeaveCommunity;
import impl.tewrrss.business.methods.ListAll;
import impl.tewrrss.business.methods.ListJoined;

public class CommunityServiceImpl implements CommunityService {

	@Override
	public String create(Community comunidad) {
		return new CrearComunidades().crearComunidad(comunidad);
	}

	@Override
	public String remove(Community comunidad) {
		return new RemoveCommunities().borrarComunidad(comunidad);
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
		return new JoinCommunity().join(community, user);
	}

	@Override
	public String leave(Community community, User user) {
		return new LeaveCommunity().leave(community, user);
	}

	@Override
	public boolean ableToJoin(Community comunidad, User user) {
		return new AbleToJoin().ableToJoin(comunidad, user);
	}

}
