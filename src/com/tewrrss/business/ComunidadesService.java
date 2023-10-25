package com.tewrrss.business;

import java.util.List;

import com.tewrrss.dto.Community;

public interface ComunidadesService {
	
	List<Community> listAll();
	List<Community> listJoined();
	String join(Community community);
	String leave(Community community);
	boolean crearComunidad(Community comunidad);
	boolean borrarComunidad(Community comunidad);

}
