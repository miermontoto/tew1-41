package impl.tewrrss.business;


import java.util.List;

import com.tewrrss.business.ComunidadesService;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

import impl.tewrrss.business.clases.CrearComunidades;
import impl.tewrrss.business.clases.Join;
import impl.tewrrss.business.clases.Leave;
import impl.tewrrss.business.clases.ListAll;
import impl.tewrrss.business.clases.ListJoined;
import impl.tewrrss.business.clases.AbleToJoin;
import impl.tewrrss.business.clases.BorrarComunidades;

public class ComunidadesServiceImpl implements ComunidadesService{

	@Override
	public String crearComunidad(Community comunidad) {
		return new CrearComunidades().crearComunidad(comunidad);
	}

	@Override
	public String borrarComunidad(Community comunidad, User user) {
		return new BorrarComunidades().borrarComunidad(comunidad, user);
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
