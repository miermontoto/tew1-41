package impl.tewrrss.business.communities;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

import impl.tewrrss.persistence.jdbc.CommunityJdbcDAO;

public class Leave {

	public String leave(Community community, User user) {
		return new CommunityJdbcDAO().leave(community, user) ? "success" : "error";
	}
}
