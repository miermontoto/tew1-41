package impl.tewrrss.business.posts;

import java.util.List;

import com.tewrrss.dto.Post;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.persistence.PersistenceFactory;

import impl.tewrrss.persistence.jdbc.PostJdbcDAO;

public class GetMensajesUsuario {
	public List<Post> getMensajesUsuario(String useremail){

		PersistenceFactory factoria= Factories.persistence;
		return factoria.getPostDAO().getPostsFromUser(useremail);

	}
}
