package com.tewrrss.presentation;

import javax.faces.bean.ManagedBean;

import com.tewrrss.model.Role;
import com.tewrrss.model.User;

@ManagedBean(name = "managecommunitiesbean") // ManagedBean para gesti�n de usuarios.
public class BeanCommunities {
	
	BeanUser loginInfo;
	
	public BeanCommunities() {
		loginInfo = new BeanUser();
	}
	
	public String listarComunidades() {
		return "unimplemented";
	}
	
	public String borrarPublicacion() {
		return "unimplemented";
	}
	
	// M�todo para declarar el borrado de comunidades. Se verifica antes que el usuario sea admin
	public String borrarComunidad() {
		if(loginInfo.getSessionRole() == Role.ADMIN) {
			// TODO Borrado de la columunidad en la BBDD
			return "success"; // El borrado ha tenido �xito
		}
		
		return "unauthorized"; // La comunidad no se ha podido borrar
	}
}
