package impl.tewrrss.business.methods;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

import impl.tewrrss.persistence.jdbc.CommunityJdbcDAO;

public class LeaveCommunity {

	public String leave(Community community, User user) {
		return new CommunityJdbcDAO().leave(community, user) ? "success" : "error";
	}
}
