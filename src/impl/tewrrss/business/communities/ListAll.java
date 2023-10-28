package impl.tewrrss.business.communities;

import java.util.List;

import com.tewrrss.dto.Community;

import impl.tewrrss.persistence.jdbc.CommunityJdbcDAO;

public class ListAll {

	public List<Community> listAll() {
		return new CommunityJdbcDAO().getCommunities();
	}

}
