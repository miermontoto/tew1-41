package com.tewrrss.presentation;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.tewrrss.business.PostsService;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.util.Role;


@ManagedBean(name = "posts") 
@SessionScoped
public class BeanPosts {
    private String nuevoPost;
    private BeanInfo loginInfo;
    private PostsService service;
    private Community currentCommunity;

	public BeanPosts() {
		service = Factories.services.createPostsService();
		loginInfo = new BeanInfo();
	}
	
	public void setCurrentCommunity(Community community) {
		this.currentCommunity = community;
	}
	
	public Community getCurrentCommunity() {
		return this.currentCommunity;
	}

	public String getNuevoPost() {
		return nuevoPost;
	}

	public void setNuevoPost(String nuevoPost) {
		this.nuevoPost = nuevoPost;
	}
	
	public String remove(Post mensaje) {
		User user = loginInfo.getSessionUser();
		if (user.getEmail().equals(mensaje.getUserEmail()) || user.getRole() == Role.ADMIN) {
			service.borrarMensajes(mensaje);
			return "success";
		}	
		
		return "unauthorized";
	}
	
	
	public String add() {	
		User user = loginInfo.getSessionUser();
		if (!nuevoPost.isEmpty())  {    
	        service.agregarMensaje(nuevoPost, currentCommunity, user);          
	        nuevoPost = "";
	        
	        return "success";
        }
		
		return "error";
    }
	
	public List<Post> getMensajesNuevos() {
		return service.getMensajesNuevos(loginInfo.getSessionUser());
	}
	
	public List<Post> getMensajesUsuario() {
		return service.getMensajesUsuario(loginInfo.getSessionUser().getEmail());
	}
	
    public List<Post> getMensajesComunidad() {
        return service.getMensajesComunidad(currentCommunity.getName(), loginInfo.getSessionUsername());
    }

}
