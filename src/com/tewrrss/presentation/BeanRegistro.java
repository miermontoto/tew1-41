package com.tewrrss.presentation;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tewrrss.business.LoginService;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.util.Role;

@ManagedBean(name = "registro")
@SessionScoped
public class BeanRegistro implements Serializable {
	private static final long serialVersionUID = 6076632229413784370L;

	// Datos del cliente.
	private String nombre;
	private String email;
	private String contrasena;
	private String confirmarContrasena;
	private boolean rgpd;

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

	public boolean isRgpd() {
		return rgpd;
	}

	public void setRgpd(boolean rgpd) {
		this.rgpd = rgpd;
	}

	/**
	 * Lógica para el registro de usuarios.
	 * Verifica que el correo no esté repetido, realiza el registro, etc.
	 */
	public String registrarUsuario() {
		LoginService service;

		if (!rgpd) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe aceptar la política de privacidad", "Debe aceptar la política de privacidad."));
			return ""; // Permanece en la página de registro
		}

		if (!contrasena.equals(confirmarContrasena)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden", "Las contraseñas no coinciden."));
			return ""; // Permanece en la página de registro
		}
    
		service = Factories.services.createLoginService();

		if (service.emailExists(email)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El correo electrónico ya está en uso", "El correo electrónico ya está en uso."));
			return ""; // Permanece en la página de registro
		}

		User newUser = new User(email, nombre, contrasena);

		service.register(newUser);
		BeanLogin.putUserInSession(newUser);
		return "success";
	}
}
