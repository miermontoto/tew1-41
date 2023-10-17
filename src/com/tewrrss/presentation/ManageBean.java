package com.tewrrss.presentation;

import com.tewrrss.model.Role;
import com.tewrrss.model.User;

public class ManageBean {
	
	BeanUser user; //Variable con el usuario actual.
	
	public ManageBean() {
		
		user = new BeanUser(); // Accedo al usuario
		
	}
	
	// Método para declarar el borrado de comunidades. Se verifica antes que el usuario sea admin
	public String borrarComunidad() {
		
		if(Role.toString(user.getRole()) == "role_admin") {
			// TODO Borrado de la columunidad en la BBDD
			return "success";
		} else {
			return "unauthorized";
		}
		
	}
	
	// Método para creado y borrado de publicaciones.
	public String borrarPublicacion() {
		return null;
	}

}
