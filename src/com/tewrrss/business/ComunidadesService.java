package com.tewrrss.business;

import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

public interface ComunidadesService {
	
	List<Community> listAll();
	List<Community> listJoined(User user);
	String join(Community community, User user);
	String leave(Community community, User user);
	String crearComunidad(Community comunidad);
	String borrarComunidad(Community comunidad, User user);
	boolean ableToJoin(Community comunidad, User user);

}
