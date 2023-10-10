package presentation;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class registroBean {


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

	public String registrarUsuario() {
		// Aquí implementa la lógica para registrar al usuario
		// Verifica que el correo no esté repetido, realiza el registro, etc.
		if(contrasena!=confirmarContrasena) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden", "Las contraseñas no coinciden."));
			return null; // Permanece en la página de registro
		}
		
		if (emailYaExiste(email)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El correo electrónico ya está en uso", "El correo electrónico ya está en uso."));
			return null; // Permanece en la página de registro
		} 
		else {
			// Realiza el registro del usuario y redirige a una página de éxito

			//Aqui se deberia meter la informacion a la base de datos

			return "success"; 
		}
	}

	private boolean emailYaExiste(String email) {
		// Implementa la lógica para verificar si el correo ya está en uso en el sistema
		// Retorna true si el correo ya existe, false en caso contrario


		return true; // Por defecto, asumimos que el correo no existe
	}
}

