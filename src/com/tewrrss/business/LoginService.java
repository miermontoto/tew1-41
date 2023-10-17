package com.tewrrss.business;

import java.util.List;
import com.tewrrss.model.User;

public interface LoginService {

	User verify(String username, String pass); // Devuelve el usuario asociado a ese username y contrase�a.
	boolean validLogin(String username, String pass); // M�todo en la interfaz para verificar si un user/pass es correcto.

}
