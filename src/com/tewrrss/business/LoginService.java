package com.tewrrss.business;

import java.util.Optional;
import com.tewrrss.dto.User;

public interface LoginService {

	User verify(String username, String pass); // Devuelve el usuario asociado a ese username y contrase�a.
	boolean emailExists(String email); // Comprueba si existe un usuario con ese email.
	void register(User user); // Registra un nuevo usuario.
}
