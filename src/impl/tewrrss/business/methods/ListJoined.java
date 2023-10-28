package impl.tewrrss.business.methods;

import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

import impl.tewrrss.persistence.jdbc.CommunityJdbcDAO;

public class ListJoined {

	public List<Community> listJoined(User user){
		return new CommunityJdbcDAO().getJoinedCommunities(user);
	}
}
