package com.tewrrss.presentation;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import com.tewrrss.business.ComunidadesService;
import com.tewrrss.dto.Community;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.persistence.CommunityDAO;
import com.tewrrss.util.Role;

@ManagedBean(name = "communities") // ManagedBean para gesti�n de usuarios.
public class BeanCommunities implements Serializable {
	private static final long serialVersionUID = -1325688208166211122L;

	@ManagedProperty(value="#{user}") 
	public BeanUser loginInfo; // Informaci�n de la sesi�n del usuario (par�metros de inicio de sesi�n)
	
	private CommunityDAO dao; // DAO para gestinar las comunidades.

	private String nombre;
	private String descripcion;

	private List<Community> joined;
	private List<Community> all;
	
	private ComunidadesService CS;

	public BeanCommunities() {
		CS = Factories.services.createCommunityService(); // Creo el servicio de comunidades.
		
		//dao = Factories.persistence.getCommunityDAO();
		
		//loginInfo = new BeanUser();
	}

	// Funci�n que devuelve todas las comunidades
	
	public List<Community> listAll() {
		return CS.listAll(); // Listo todas las comunidades
	}

	// Funci�n que devuelve las comunidades a las que pertenece el usuario
	public List<Community> listJoined() {
		return CS.listJoined(loginInfo.getSessionUser());
		
	}

	public boolean ableToJoin(Community comunidad) {
		//return this.joined.stream().noneMatch(c -> c.getName().equals(comunidad.getName()));
		return CS.ableToJoin(comunidad, loginInfo.getSessionUser());
	}

	// M�todo para declarar el borrado de comunidades. Se verifica antes que el usuario sea admin
	public String delete(Community comunidad) {
		
		return CS.borrarComunidad(comunidad, loginInfo.getSessionUser());
		
	}

	public String create() {
		
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

		for(Community cm : CS.listAll()) {
			if(cm.getName().equals(nombre)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_create_error_existingName"), null));
				return null;
			}
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("communities_create_ok"), null));
	
		// Todo ha ido bien, llamo a crear comunidad
		return CS.crearComunidad(new Community(this.nombre, this.descripcion)); // LLamo a la lista de comunidades

		
	}

	public String join(Community community) {
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");

		if (community == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_list_join_error"), null));
			return null; // Nombre vac�o, no contin�o
		}

		if (!ableToJoin(community)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_list_join_error"), null));
			return null; // Nombre vac�o, no contin�o
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("communities_list_join_ok"), null));
		
		// Llamo a unirme a la comunidad.
		return CS.join(community, loginInfo.getSessionUser());
		
	}

	public String leave(Community community) {
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");

		if (community == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_list_leave_error"), null));
			return null; // Nombre vac�o, no contin�o
		}


		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("communities_list_leave_ok"), null));
		
		// Todo ha ido bien, procedo a llamar a leave de negocio.
		return CS.leave(community, loginInfo.getSessionUser());
	}

	public BeanUser getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(BeanUser loginInfo) {
		this.loginInfo = loginInfo;
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

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
