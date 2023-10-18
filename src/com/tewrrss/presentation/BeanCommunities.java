package com.tewrrss.presentation;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.tewrrss.model.Role;
import com.tewrrss.model.User;

@ManagedBean(name = "managecommunitiesbean") // ManagedBean para gestiï¿½n de usuarios.
public class BeanCommunities {
	
	BeanUser loginInfo;
	
	// Variables para almacenar cuando nos pasen nuevas comunidades a crear, o acceder a una nueva.
	String nombreComunidad;		// Nombre de la nueva comunidad
	String descripcionComunidad;	//Descripción de la nueva comunidad
	
	
	public BeanCommunities() {
		loginInfo = new BeanUser();
	}
	
	public String listarComunidades() {
		
		if(loginInfo.getSessionRole() == Role.USER) {
			// Muestro sólo las comunidades de ese usuario, sin posibilidad de borrarlas.
		} else if (loginInfo.getSessionRole() == Role.ADMIN) {
			// Muestro TODAS las comunidades, con posibilidad de borrar.
		}
		
		//De momento, se devuelve no implementado.
		return "unimplemented";
	}
	
	public String borrarPublicacion() {
		return "unimplemented";
	}
	
	// Mï¿½todo para declarar el borrado de comunidades. Se verifica antes que el usuario sea admin
	public String borrarComunidad() {
		if(loginInfo.getSessionRole() == Role.ADMIN) {
			// TODO Borrado de la columunidad en la BBDD
			return "success"; // El borrado ha tenido ï¿½xito
		}
		
		return "unauthorized"; // La comunidad no se ha podido borrar
	}
	
	
	public String creacomunidad() {
		
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
		
		String usuario = loginInfo.getSessionUsername(); // Obtengo el usuario que esta realizando la acción, para posteriormente, incluirlo en la BBDD
		 
		if (nombreComunidad == null || nombreComunidad.trim().isEmpty()) {
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  bundle.getString("empty_community_name"), null));
	            return null; // Nombre vacío, no continúo
	     }

	     if (descripcionComunidad == null || descripcionComunidad.trim().isEmpty()) {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("empty_community_desc"), null));
	        return null; // Descripción vacía, no continúo
	     }
	     
	     
	    //Gestión de las comunidades en la BBDD.
	     
		return "success"; // Retorno un mensaje de éxito. A continuación, se redirige al usuario a ver sus comunidades.
		
	}

	public String getNombreComunidad() {
		return nombreComunidad;
	}

	public void setNombreComunidad(String nombreComunidad) {
		this.nombreComunidad = nombreComunidad;
	}

	public String getDescripcionComunidad() {
		return descripcionComunidad;
	}

	public void setDescripcionComunidad(String descripcionComunidad) {
		this.descripcionComunidad = descripcionComunidad;
	}

	
	
	
	
	
}
