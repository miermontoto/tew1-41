package impl.tewrrss.business;


import java.util.List;

import com.tewrrss.business.ComunidadesService;
import com.tewrrss.dto.Community;
import impl.tewrrss.business.clases.CrearComunidades;
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
		return null;
	}

	@Override
	public List<Community> listJoined() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String join(Community community) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String leave(Community community) {
		// TODO Auto-generated method stub
		return null;
	}

}
