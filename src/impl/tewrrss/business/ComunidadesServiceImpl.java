package impl.tewrrss.business;


import java.util.List;

import com.tewrrss.business.ComunidadesService;
import com.tewrrss.dto.Community;
import impl.tewrrss.business.clases.CrearComunidades;
import impl.tewrrss.business.clases.Join;
import impl.tewrrss.business.clases.Leave;
import impl.tewrrss.business.clases.ListAll;
import impl.tewrrss.business.clases.ListJoined;
import impl.tewrrss.business.clases.BorrarComunidades;

public class ComunidadesServiceImpl implements ComunidadesService{

	@Override
	public boolean crearComunidad(Community comunidad) {
		return new BorrarComunidades().borrarComunidad(comunidad);

	}

	@Override
	public boolean borrarComunidad(Community comunidad) {
		return new CrearComunidades().crearComunidad(comunidad);
	}

	@Override
	public List<Community> listAll() {
		return new ListAll().listAll();
	}

	@Override
	public List<Community> listJoined() {
		return new ListJoined().listJoined();
	}

	@Override
	public String join(Community community) {
		return new Join().join(community);
	}

	@Override
	public String leave(Community community) {
		return new Leave().leave(community);
	}

}
