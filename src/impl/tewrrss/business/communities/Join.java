package impl.tewrrss.business.communities;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

import impl.tewrrss.persistence.jdbc.CommunityJdbcDAO;

public class Join {

	public String join(Community community, User user) {
		return new CommunityJdbcDAO().join(community, user) ? "success" : "error";
	}


}
