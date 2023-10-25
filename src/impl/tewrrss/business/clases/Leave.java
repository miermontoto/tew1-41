package impl.tewrrss.business.clases;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

public class Leave {

	public String leave(Community community, User user) {
		
		// Dejo la comunidad en la BBDD.
		//dao.leave(community, user.getUsername());
		return "left";
		//return "unimplementhed";
	}
}
