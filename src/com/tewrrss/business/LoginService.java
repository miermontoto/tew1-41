package com.tewrrss.business;

import com.tewrrss.dto.User;

public interface LoginService {

	User verify(String username, String pass); // Devuelve el usuario asociado a ese username y contraseña.
	boolean emailExists(String email); // Comprueba si existe un usuario con ese email.
	void register(User user); // Registra un nuevo usuario.

}
