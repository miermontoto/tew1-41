package com.tewrrss.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class RegistroBean {

	private String nombre;
	private String email;
	private String contrasena;
	private String confirmarContrasena;

	// Getters y Setters para las propiedades

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getConfirmarContrasena() {
		return confirmarContrasena;
	}

	public void setConfirmarContrasena(String confirmarContrasena) {
		this.confirmarContrasena = confirmarContrasena;
	}

	/**
	 * Lógica para el registro de usuarios.
	 * Verifica que el correo no esté repetido, realiza el registro, etc.
	 */
	public String registrarUsuario() {
		if(!contrasena.equals(confirmarContrasena)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden", "Las contraseñas no coinciden."));
			return null; // Permanece en la página de registro
		}

		if (emailYaExiste(email)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El correo electrónico ya está en uso", "El correo electrónico ya está en uso."));
			return null; // Permanece en la página de registro
		}

		// Realiza el registro del usuario y redirige a una página de éxito

		// TODO: implementar registro en BBDD
		return "success";
	}

	/**
	 * Implementa la l�gica para verificar si el correo ya está en uso en el sistema.
	 * @return true si el correo ya está en uso, false en caso contrario.
	 */
	private boolean emailYaExiste(String email) {
		return false; // TODO: implementar comprobación con BBDD
	}
}
