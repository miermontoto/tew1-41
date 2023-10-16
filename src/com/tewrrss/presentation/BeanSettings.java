package com.tewrrss.presentation;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "settings")
@SessionScoped
public class BeanSettings implements Serializable {
	private static final long serialVersionUID = 2L;
	private static final Locale ENGLISH = new Locale("en");
	private static final Locale SPANISH = new Locale("es");
	private Locale locale = new Locale("es");

	@ManagedProperty(value="#{berror}")
	private BeanError error;

	public void setError(BeanError error) {
		this.error = error;
	}

	public BeanError getError(){
		return this.error;
	}

	@PostConstruct
	public void init() {
		error = (BeanError) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("error");
		if (error == null) {
			error = new BeanError();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("error", error);
		}
	}

	public Locale getLocale() {
		return(locale);
	}

	public void setSpanish(ActionEvent event) {
		locale = SPANISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		} catch (Exception ex) {
			getError().setView("");
			getError().setMethod("setSpanish");
			getError().setClase(this.getClass().getName());
			getError().setMessage(ex.getMessage());
		}
	}

	public void setEnglish(ActionEvent event) {
		locale = ENGLISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		} catch (Exception ex){
			getError().setView("");
			getError().setMethod("setEnglish");
			getError().setClase(this.getClass().getName());
			getError().setMessage(ex.getMessage());
		}
	}
}
