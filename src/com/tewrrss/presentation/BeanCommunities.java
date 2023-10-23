package com.tewrrss.presentation;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.persistence.CommunityDAO;
import com.tewrrss.persistence.PersistenceFactory;
import com.tewrrss.util.Role;

@ManagedBean(name = "communities") // ManagedBean para gestiï¿½n de usuarios.
public class BeanCommunities {


	private BeanUser loginInfo;
	private PersistenceFactory BBDD;

	private String nombre;
	private String descripcion;

	public BeanCommunities() {
		loginInfo = new BeanUser();
		BBDD = Factories.persistence;
	}

	public List<Community> listarComunidades() {


		if(loginInfo.getSessionRole() == Role.USER) {
			// Muestro sólo las comunidades de ese usuario, sin posibilidad de borrarlas. Útil para ver las comunidades a las qe está unido.
			
			
		} else if (loginInfo.getSessionRole() == Role.ADMIN) {
			// Muestro TODAS las comunidades, con posibilidad de borrar.
			
			//CommunityDAO comunidades = BBDD.getCommunityDAO();
			//return comunidades.getCommunities(); //Obtengo todas las comunidades

			/*if (loginInfo.getSessionRole() == Role.USER) {
				// TODO: mostrar sï¿½lo las comunidades de ese usuario, sin posibilidad de borrarlas.
			} else {
				// TODO: mostrar TODAS las comunidades, con posibilidad de borrar.
			}*/
			//De momento, se devuelve no implementado.


		}
		
		CommunityDAO comunidades = BBDD.getCommunityDAO();
		return comunidades.getCommunities();
		
		//return "unimplemented";

	}


	// Me devuelve las comunidades disponibles para este usuario, ya que ya podría estar unido a alguna. NO BORRAR
	/*public Comunidad listarComunidadesDisponibles() {

		String username = loginInfo.getSessionUsername(); // Obtengo el usuario antes de hacer nada, útil para obtener
														  // obtener las comunidades en las que NO está.

		// BBDD, Obtener las comunidades de la BBDD

		return comunidades;

	}*/


	public String borrarPublicacion() {
		return "unimplemented";
	}

	// MÃ©todo para declarar el borrado de comunidades. Se verifica antes que el usuario sea admin
	public String borrarComunidad() {
		if(loginInfo.getSessionRole() == Role.ADMIN) {
			// TODO: Borrado de la columunidad en la BBDD
			return "success"; // El borrado ha tenido ï¿½xito
		}

		return "unauthorized"; // La comunidad no se ha podido borrar
	}

	public String crearComunidad() {
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");

		if (nombre == null || nombre.trim().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_create_error_emptyName"), null));
			return null; // Nombre vacï¿½o, no continï¿½o
		}

		if (descripcion == null || descripcion.trim().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_create_error_emptyDesc"), null));
			return null; // Descripciï¿½n vacï¿½a, no continï¿½o
		}

		// TODO: Gestiï¿½n de las comunidades en la BBDD.
		return "success"; // Retorno un mensaje de ï¿½xito. A continuaciï¿½n, se redirige al usuario a ver sus comunidades.
	}


	// Método que permite guardar una comunidad en el objeto de este Bean, de forma que sea accesible por las acciones que se desencadenen a continuación.
	// (Unirse a comunidades, Ver mensajes de la comunidad, etc)
	public String guardarComunidad(/*Comunidad comunidad*/) {
		//this.comunidad = comunidad;
		return "success";
	}






}
