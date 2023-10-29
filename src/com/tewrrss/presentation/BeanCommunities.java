package com.tewrrss.presentation;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.tewrrss.business.CommunityService;
import com.tewrrss.dto.Community;
import com.tewrrss.infrastructure.Factories;

@ManagedBean(name = "communities")
public class BeanCommunities implements Serializable {
	private static final long serialVersionUID = -1325688208166211122L;

  private BeanInfo loginInfo;

	private String nombre;
	private String descripcion;

	private CommunityService CS;

	public BeanCommunities() {
		CS = Factories.services.createCommunityService();
		loginInfo = new BeanInfo();
	}

	public List<Community> listAll() {
		return CS.listAll();
	}

	public List<Community> listJoined() {
		return CS.listJoined(loginInfo.getSessionUser());
	}

	public boolean ableToJoin(Community comunidad) {
		return CS.ableToJoin(comunidad, loginInfo.getSessionUser());
	}

	public String delete(Community comunidad) {
		return CS.remove(comunidad);
	}

	public String create() {
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");

		if (nombre == null || nombre.trim().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_create_error_emptyName"), null));
			return null;
		}

		if (descripcion == null || descripcion.trim().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_create_error_emptyDesc"), null));
			return null;
		}

		for(Community cm : CS.listAll()) {
			if(cm.getName().equals(nombre)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_create_error_existingName"), null));
				return null;
			}
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("communities_create_ok"), null));
		return CS.create(new Community(this.nombre, this.descripcion));
	}

	public String join(Community community) {
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");

		if (community == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_list_join_error"), null));
			return null;
		}

		if (!ableToJoin(community)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_list_join_error"), null));
			return null;
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("communities_list_join_ok"), null));
		return CS.join(community, loginInfo.getSessionUser());
	}

	public String leave(Community community) {
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");

		if (community == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("communities_list_leave_error"), null));
			return null;
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("communities_list_leave_ok"), null));
		return CS.leave(community, loginInfo.getSessionUser());
	}

	public Community findByName(String name) {
		Optional<Community> community = CS.findByName(name);
		if (community.isPresent()) {
			return community.get();
		}
		return null;
	}

	public BeanInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(BeanInfo loginInfo) {
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
