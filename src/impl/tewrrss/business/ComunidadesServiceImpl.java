package impl.tewrrss.business;


import com.tewrrss.business.ComunidadesService;
import com.tewrrss.dto.Community;
import impl.tewrrss.business.clases.BorrarComunidades;

public class ComunidadesServiceImpl implements ComunidadesService{

	@Override
	public boolean crearComunidad(Community comunidad) {
		// TODO Auto-generated method stub
		return new BorrarComunidades().borrarComunidad(comunidad);

	}

	@Override
	public boolean borrarComunidad(Community comunidad) {
		// TODO Auto-generated method stub
		return new CrearComunidades().g
	}

}
