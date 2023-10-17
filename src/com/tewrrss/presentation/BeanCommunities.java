package com.tewrrss.presentation;

import javax.faces.bean.ManagedBean;

import com.tewrrss.model.Role;
import com.tewrrss.model.User;

@ManagedBean(name = "managecommunitiesbean") // ManagedBean para gesti�n de usuarios.
public class BeanCommunities {
	
	BeanUser loginInfo;
	String nombrenuevacomunidad;
	String mensaje; // Mensaje que se mostrar� en caso de errores con la creaci�n de la comunidad.
	
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
	
	public String creacomunidad() {
		System.out.print("PRUEBA");
		this.mensaje = "Comunidad creada con �xito";
		return "success"; // Retorno un mensaje de �xito.
		
	}

	
	public String getNombrenuevacomunidad() {
		return nombrenuevacomunidad;
	}

	public void setNombrenuevacomunidad(String nombrenuevacomunidad) {
		this.nombrenuevacomunidad = nombrenuevacomunidad;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
