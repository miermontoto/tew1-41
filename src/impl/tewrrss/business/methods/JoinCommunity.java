package impl.tewrrss.business.methods;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

public class JoinCommunity {

	public String join(Community community, User user) {
		return CommunityJdbcDAO().join(community, user) ? "success" : "error";
	}


}
