package impl.tewrrss.business.communities;


import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

public class AbleToJoin {

	public boolean ableToJoin(Community comunidad, User user) {

		// Verificar si el usuario puede unirse o no a esta comunidad
		List<Community> comunidadesUser = new ListJoined().listJoined(user);

		for(Community cm : comunidadesUser) {
			if(comunidad.getName().equals(cm.getName()))
				return false;
		}
		return true;

	}

}
