package com.tewrrss.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

@ManagedBean(name = "berror")
@SessionScoped
public class BeanError implements Serializable {
	// Los detalles de cada error a considerar deber�n ser:
	// 	1. La vista desde la que se redireccion� el error.
	// 	2. El m�todo donde se produjo el error.
	// 	3. La clase correspondiente al m�todo.
	// 	4. El mensaje de error.

	private static final long serialVersionUID = 1L;
	private String view;
	private String method;
	private String clase;
	private String message;

	public BeanError() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");

		this.view = bundle.getString("error_unknown");
		this.method = bundle.getString("error_unknown");
		this.clase = bundle.getString("error_unknown");
		this.message = bundle.getString("error_unknown");
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
