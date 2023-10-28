package impl.tewrrss.business.posts;

import com.tewrrss.dto.Post;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.persistence.PersistenceFactory;

public class BorrarMensaje {
	/*
	 * Borra el Post dado de la base de datos
	 * **/
	public void borrarMensaje(Post mensaje) {
		PersistenceFactory factoria = Factories.persistence;
		factoria.getPostDAO().remove(mensaje);
	}
}
