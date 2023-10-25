package impl.tewrrss.business.classesPublicaciones;

import java.util.List;

import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.persistence.PersistenceFactory;

public class GetMensajesNuevos {

	public List<Post> getMensajesNuevos(User user){
		PersistenceFactory factoria = Factories.persistence;
		return factoria.getPostDAO().getPostsFromComunitysUser(user.getEmail());
	}
}
