package com.tewrrss.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

    private static final Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public EmailValidator() { /* Se necesita un constructor, aunque estÃ© vacÃ­o */ }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return; // Deja la validaciï¿½n para el atributo 'required'
        }

        String email = value.toString();
        Matcher matcher = pattern.matcher(email);

        // Si el email no esta bien formado, devolvemos una excepción de validación, lo que causa un mensaje en el Front
        if (!matcher.matches()) {
            FacesMessage message = new FacesMessage("El email no es válido");
            throw new ValidatorException(message);
        }
    }
}
