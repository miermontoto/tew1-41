package com.tewrrss.presentation;

import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.tewrrss.model.Role;
import com.tewrrss.model.User;

@ManagedBean(name = "managecommunitiesbean") // ManagedBean para gestión de usuarios.
public class BeanMensajes {
	
	BeanUser user; //Variable con el usuario actual.
	
	public BeanMensajes() {
		user = new BeanUser(); // Accedo al usuario
	}
	
	/*
	 * 
	 * CAMBIAR OBJECT POR LA CLASE DE MENSAJE DEL MODELO DE LA BASE DE DATOS
	 * 
	 * */
	
	
    private List<Object> mensajes;		
    

    
    public List<Object> getMensajesComunidad(int comunidadId) {
        // Usar un DAO (Data Access Object) para obtener los mensajes desde la base de datos.
         
    	
        // Implementa un método en tu DAO que obtenga mensajes de una comunidad específica.
        // Este método se comunicará con la base de datos para obtener los mensajes.
        
    	// mensajes = mensajeDAO.getMensajesByComunidad(comunidadId);

        //Ordenar la lista por fecha
        
/*        // Definir un Comparator personalizado para ordenar por fecha de publicación.
        Comparator<Mensaje> comparadorFecha = new Comparator<Mensaje>() {
            @Override
            public int compare(Mensaje mensaje1, Mensaje mensaje2) {
                return mensaje1.getFechaPublicacion().compareTo(mensaje2.getFechaPublicacion());
            }
        };

        // Ordenar la lista utilizando el Comparator.
        Collections.sort(listaMensajes, comparadorFecha);
   */
    	
    	
        return mensajes;
    }

}
