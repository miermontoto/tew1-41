package com.tewrrss.presentation;

import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.tewrrss.model.Role;
import com.tewrrss.model.User;

@ManagedBean(name = "user")
@SessionScoped
public class BeanUser implements Serializable {
	private static final long serialVersionUID = 55556L;

	public String getSessionUsername() {
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LOGGEDIN_USER");
		return user.getName();
	}

	public int getSessionRole() {
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LOGGEDIN_USER");
		return user.getRole();
	}

	public String getSessionRoleName() {
		return Role.toString(this.getSessionRole());
	}
}
