package com.tewrrss.business;

import java.util.List;
import com.tewrrss.model.User;

public interface LoginService {

	User verify(String username, String pass);
	boolean validLogin(String username, String pass);

}
