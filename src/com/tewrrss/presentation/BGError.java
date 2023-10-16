package com.tewrrss.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

@ManagedBean(name = "berror")
@SessionScoped
public class BGError implements Serializable {
	// Los detalles de cada error a considerar deberán ser:
	// 	1. La vista desde la que se redireccionó el error.
	// 	2. El método donde se produjo el error.
	// 	3. La clase correspondiente al método.
	// 	4. El mensaje de error.

	private static final long serialVersionUID = 1L;
	private String view;
	private String method;
	private String clase;
	private String message;

	public BGError() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");

		this.view = bundle.getString("unknownError");
		this.method = bundle.getString("unknownError");
		this.clase = bundle.getString("unknownError");
		this.message = bundle.getString("unknownError");
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
