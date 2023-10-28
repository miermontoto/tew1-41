package impl.tewrrss.business.methods;

import com.tewrrss.dto.Community;

public class CrearComunidades {

	public String crearComunidad(Community comunidad) {
		return new CommunityJdbcDAO().add(comunidad) ? "success" : "error";
	}

}
