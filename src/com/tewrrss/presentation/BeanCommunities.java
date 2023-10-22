package com.tewrrss.presentation;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.tewrrss.dto.User;
import com.tewrrss.util.Role;

@ManagedBean(name = "communities") // ManagedBean para gesti�n de usuarios.
public class BeanCommunities {

	private BeanUser loginInfo;

	// Variables para almacenar cuando nos pasen nuevas comunidades a crear, o acceder a una nueva.
	private String nombre;
	private String descripcion;

	public BeanCommunities() {
		loginInfo = new BeanUser();
	}

	public String listarComunidades() {
		if (loginInfo.getSessionRole() == Role.USER) {
			// TODO: mostrar s�lo las comunidades de ese usuario, sin posibilidad de borrarlas.
		} else {
			// TODO: mostrar TODAS las comunidades, con posibilidad de borrar.
		}

		return "unimplemented";
	}

	public String borrarPublicacion() {
		return "unimplemented";
	}

	// Método para declarar el borrado de comunidades. Se verifica antes que el usuario sea admin
	public String borrarComunidad() {
		if(loginInfo.getSessionRole() == Role.ADMIN) {
			// TODO: Borrado de la columunidad en la BBDD
			return "success"; // El borrado ha tenido �xito
		}

		return "unauthorized"; // La comunidad no se ha podido borrar
	}

	public String crearComunidad() {
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");

		if (nombre == null || nombre.trim().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_create_error_emptyName"), null));
			return null; // Nombre vac�o, no contin�o
	    }

	    if (descripcion == null || descripcion.trim().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_create_error_emptyDesc"), null));
			return null; // Descripci�n vac�a, no contin�o
	    }

	    // TODO: Gesti�n de las comunidades en la BBDD.
		return "success"; // Retorno un mensaje de �xito. A continuaci�n, se redirige al usuario a ver sus comunidades.
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String str) {
		this.descripcion = str;
	}
}
