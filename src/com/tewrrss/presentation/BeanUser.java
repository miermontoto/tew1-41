package com.tewrrss.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tewrrss.model.Role;
import com.tewrrss.model.User;

@ManagedBean(name = "user")
@SessionScoped
public class BeanUser extends User implements Serializable {
	private static final long serialVersionUID = 55556L;
	
	private String sessionUsername;
	private String sessionRole;

	public BeanUser() {super("null", "null");}

	public String getSessionUsername() {
		BeanUser user = (BeanUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		return user.getName();
	}

	public String getSessionRole() {
		BeanUser user = (BeanUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		return Role.toString(user.getRole());
	}

	public void setSessionUsername() { }

	public void setSessionRole() { }
}
