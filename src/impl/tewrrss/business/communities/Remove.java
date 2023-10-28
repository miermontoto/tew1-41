package impl.tewrrss.business.communities;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.util.Role;

import impl.tewrrss.persistence.jdbc.CommunityJdbcDAO;

public class Remove {

	public String borrarComunidad(Community community) {
		return new CommunityJdbcDAO().remove(community) ? "success" : "error";
	}
}
