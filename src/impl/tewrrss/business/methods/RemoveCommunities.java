package impl.tewrrss.business.methods;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.util.Role;

import impl.tewrrss.persistence.jdbc.CommunityJdbcDAO;

public class RemoveCommunities {

	public String borrarComunidad(Community comunidad) {
		return new CommunityJdbcDAO().remove(comunidad.getName()) ? "success" : "error";
	}
}
