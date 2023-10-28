package impl.tewrrss.business;

import java.util.List;

import com.tewrrss.business.PostsService;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;

import impl.tewrrss.business.posts.AgregarMensaje;
import impl.tewrrss.business.posts.BorrarMensaje;
import impl.tewrrss.business.posts.GetMensajesComunidad;
import impl.tewrrss.business.posts.GetMensajesNuevos;
import impl.tewrrss.business.posts.GetMensajesUsuario;
import impl.tewrrss.persistence.jdbc.PostJdbcDAO;
import impl.tewrrss.persistence.jdbc.UserJdbcDAO;

public class PostsServiceImpl implements PostsService{

	@Override
	/*
	 * Se crea el post con el contenido dado en la comunidad dada pos el usuario dado 
	 * y en la fecha actual del sistema
	 * **/
	public void agregarMensaje(String contenido, Community com, User user) {
		//Este metodo debe agregar a la base de dato el Post dado, ya esta configurado
		new AgregarMensaje().agregarMensaje(contenido, com, user);;
	}
	
	@Override
	/*
	 * Borra el Post dado
	 * */
	public void borrarMensajes(Post post) {
		// Borrar de la base de datos el Post post
		new BorrarMensaje().borrarMensaje(post);
	}

	
	@Override
	/*
	 * Este metodo devuelve todos los Posts del usuario dado
	 * **/
	public List<Post> getMensajesUsuario(String useremail){
		//Devolver una lista con todos los post del usuario
//asegurarse de que los mensajes estes ordenados de mas reciente a mas antiguo
	
		return new GetMensajesUsuario().getMensajesUsuario(useremail);
	}
	
	
	@Override
	/*
	 * Este metodo devuelve todos los Posts de la comunidad dada
	 * **/
	public List<Post> getMensajesComunidad(String nombreComunidad, String useremail){
		
		//asegurarse de que los Post estes ordenados de mas reciente a mas antiguo
		return new GetMensajesComunidad().getMensajesComunidad(nombreComunidad, useremail);
	}
	
	@Override
/**Este metodo devuelve los ultimos 5 Posts de las comunidades 
 * a las que pertenece el usuario*/
	public List<Post> getMensajesNuevos(User user){		//FALTA POR IMPLEMENTAR
		
		return new GetMensajesNuevos().getMensajesNuevos(user);
	}
}
