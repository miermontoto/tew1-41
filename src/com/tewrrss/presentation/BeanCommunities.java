package com.tewrrss.presentation;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.tewrrss.dto.Community;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.persistence.CommunityDAO;
import com.tewrrss.util.Role;

@ManagedBean(name = "communities") // ManagedBean para gesti�n de usuarios.
public class BeanCommunities implements Serializable {
	private static final long serialVersionUID = -1325688208166211122L;

	private BeanUser loginInfo;
	private CommunityDAO dao;

	private String nombre; // Nombre de la comunidad
	private String descripcion; // Descripci�n de la comunidad

	private List<Community> joined;
	private List<Community> all;

	public BeanCommunities() {
		dao = Factories.persistence.getCommunityDAO();
		loginInfo = new BeanUser();
	}

	// Función que devuelve todas las comunidades
	public List<Community> listAll() {
		this.joined = this.listJoined();
		this.all = dao.getCommunities();
		return all;
	}

	// Función que devuelve las comunidades a las que pertenece el usuario
	public List<Community> listJoined() {
		return dao.getJoinedCommunities(loginInfo.getSessionUser());
	}

	public boolean ableToJoin(Community comunidad) {
		// No funciona con el contains ni reescribiendo el equals de Community
		// no sé por qué :')
		return this.joined.stream().noneMatch(c -> c.getName().equals(comunidad.getName()));
	}

	// Método para declarar el borrado de comunidades. Se verifica antes que el usuario sea admin
	public String delete(Community comunidad) {
		if(loginInfo.getSessionRole() == Role.ADMIN) {
			dao.remove(comunidad.getName()); // Elimino la comunidad solicitada en la BBDD.
			return "success"; // El borrado ha tenido �xito
		}

		return "unauthorized"; // La comunidad no se ha podido borrar
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

		// Comprobación básica no exhaustiva antes de fallo por base de datos.
		if (all == null) this.listAll();
		for(Community cm : all) {
			if(cm.getName().equals(nombre)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_create_error_existingName"), null));
				return null;
			}
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("communities_create_ok"), null));
		dao.add(new Community(nombre, descripcion));

		return "success";
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

		dao.join(community, loginInfo.getSessionUser());

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("communities_list_join_ok"), null));
		return "joined";
	}

	public String leave(Community community) {
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");

		if (community == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_list_leave_error"), null));
			return null; // Nombre vac�o, no contin�o
		}

		dao.leave(community, loginInfo.getSessionUser());

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("communities_list_leave_ok"), null));
		return "left";
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
