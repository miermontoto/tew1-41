package com.tewrrss.presentation;

import javax.faces.bean.ManagedBean;

import com.tewrrss.model.Role;
import com.tewrrss.model.User;

@ManagedBean(name = "managecommunitiesbean") // ManagedBean para gesti�n de usuarios.
public class BeanCommunities {
	
	BeanUser user; //Variable con el usuario actual.
	
	public BeanCommunities() {
		
		user = new BeanUser(); // Accedo al usuario
		
	}
	
	public void listarComunidades() {
		
	}
	
	// M�todo para declarar el borrado de comunidades. Se verifica antes que el usuario sea admin
	public String borrarComunidad() {
		
		if(Role.toString(user.getRole()) == "role_admin") {
			// TODO Borrado de la columunidad en la BBDD
			
			return "success"; // El borrado ha tenido �xito
		} else {
			return "unauthorized"; // La comunidad no se ha podido borrar
		}
		
	}
	
	// M�todo para creado y borrado de publicaciones.
	public String borrarPublicacion() {
		return null;
	}

}
