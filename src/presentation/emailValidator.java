package presentation;

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
public class emailValidator implements Validator{

	private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private Pattern pattern;

    public emailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return; // Deja la validación para el atributo 'required'
        }

        String email = value.toString();
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            FacesMessage message = new FacesMessage("Pon un email válido");
            throw new ValidatorException(message);
        }
    }



}