package com.tewrrss.business;

import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;

public interface PostsService {

	void agregarMensaje(String contenido, Community com, User user);

	void borrarMensajes(Post post);

	List<Post> getMensajesUsuario(String useremail);

	List<Post> getMensajesComunidad(String nombreComunidad, String useremail);

	List<Post> getMensajesNuevos(User user);


	
}
