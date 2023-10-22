package com.tewrrss.business;

import java.util.Optional;
import com.tewrrss.dto.User;

public interface LoginService {

	User verify(String username, String pass); // Devuelve el usuario asociado a ese username y contrase�a.

}
