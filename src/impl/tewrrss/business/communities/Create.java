package impl.tewrrss.business.communities;

import com.tewrrss.dto.Community;

import impl.tewrrss.persistence.jdbc.CommunityJdbcDAO;

public class Create {

	public String crearComunidad(Community comunidad) {
		return new CommunityJdbcDAO().add(comunidad) ? "success" : "error";
	}

}
