package impl.tewrrss.business.posts;

import java.util.List;

import com.tewrrss.dto.Post;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.persistence.PersistenceFactory;

public class GetMensajesComunidad {

	public List<Post> getMensajesComunidad(String nombreComunidad, String useremail){
		PersistenceFactory factoria= Factories.persistence;
		List<Post> mensajes= factoria.getPostDAO().getPostsInCommunity(nombreComunidad);
		
	   	for(Post p:mensajes) {	//De esta forma cambiamos el nombre de la comunidad por el del usuario que creo el post
    		p.setCommunityName(useremail);
    	}

		return mensajes;
	}
}
