package com.tewrrss.presentation;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tewrrss.business.InterfacePublicacionesService;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;

import impl.tewrrss.business.PublicacionesService;
import impl.tewrrss.business.classesPublicaciones.AgregarMensaje;
import impl.tewrrss.persistence.jdbc.PostJdbcDAO;
import impl.tewrrss.persistence.jdbc.UserJdbcDAO;



@ManagedBean(name = "BeanMensajes") 
@SessionScoped
public class BeanMensajes {
		
    private String nuevoPost;

    @ManagedProperty("#{communities}")
    private BeanCommunities bc;
   
    @ManagedProperty("#{user}")
    private BeanUser user;
       
    private PublicacionesService publicacionService;
	
	public BeanUser getUser() {
		return user;
	}

	public void setUser(BeanUser user) {
		this.user = user;
	}

	public PublicacionesService getPublicacionService() {
		return publicacionService;
	}

	public void setPublicacionService(PublicacionesService publicacionService) {
		this.publicacionService = publicacionService;
	}

	public BeanMensajes() {
		publicacionService = new PublicacionesService();
	}
    
    public BeanCommunities getBc() {
		return bc;
	}

	public void setBc(BeanCommunities bc) {
		this.bc = bc;
	}

	public String getNuevoPost() {
		return nuevoPost;
	}

	public void setNuevoPost(String nuevoPost) {
		this.nuevoPost = nuevoPost;
	}

	
	public void borrarMensaje(Post mensaje) {	

// acceder a la base de datos y eliminar este mensaje solo en caso de que sea el dueño del mensaje		
		if(user.getSessionUser().getEmail().equals(mensaje.getUserEmail())) {
			publicacionService.borrarMensajes(mensaje);

		}		
	}
	
	
	public void agregarMensaje() {		

		if (!nuevoPost.isEmpty() )  {    
	        publicacionService.agregarMensaje(nuevoPost,bc.comunidad,user.getSessionUser());          
	        nuevoPost = "";
        }

    }
    
	
	public List<Post> getMensajesNuevos(){	//PASADO
		
		return publicacionService.getMensajesNuevos(user.getSessionUser());
	}
	
	public List<Post> getMensajesUsuario(){	//PASADO
		//Sacamos el usuario del contexto

		return publicacionService.getMensajesUsuario(user.getSessionUser().getEmail());
	}
	
    public List<Post> getMensajesComunidad() {	//PASADO	
    	

        return publicacionService.getMensajesComunidad(bc.getComunidad().getName(),user.getSessionUser().getUsername());
    }

}
