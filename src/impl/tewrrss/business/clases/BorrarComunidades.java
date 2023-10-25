package impl.tewrrss.business.clases;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.util.Role;

public class BorrarComunidades {

	public String borrarComunidad(Community comunidad, User user) {
		
		
		if(user.getRole() == Role.ADMIN) {
			// Pido a la capa de negocio borrar la comunidad. TODO
			
			return "success"; // El borrado ha tenido éxito
		}

		return "unauthorized"; // La comunidad no se ha podido borrar
		
		//return false;
	}
}
