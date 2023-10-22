package com.tewrrss.presentation;

import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;

import impl.tewrrss.persistence.jdbc.PostJdbcDAO;



@ManagedBean(name = "managecommunitiesbean") // ManagedBean para gestión de usuarios.
public class BeanMensajes {
	
	PostJdbcDAO postClase;
	
	public BeanMensajes() {
		postClase=new PostJdbcDAO();
	}
	
	/*
	 * 
	 * CAMBIAR OBJECT POR LA CLASE DE MENSAJE DEL MODELO DE LA BASE DE DATOS
	 * 
	 * */
	
	
    private List<Post> mensajes;		
    private String nuevoPost;
    
    
    public List<Post> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<Post> mensajes) {
		this.mensajes = mensajes;
	}

	public String getNuevoPost() {
		return nuevoPost;
	}

	public void setNuevoPost(String nuevoPost) {
		this.nuevoPost = nuevoPost;
	}

	
	public void borrarMensaje(Post mensaje) {	//BASE DE DATOS CAMBIAR OBJECT POR OBJETO MENSAJE
		// acceder a la base de datos y eliminar este mensaje
		
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LOGGEDIN_USER");
		postClase.remove(mensaje);
		
	}
	
	
	public void agregarMensaje() {		// MODIFICAR CON LA BASE DE DATOS
		if (nuevoPost != null && !nuevoPost.isEmpty()) {
            // Crear un nuevo mensaje y agregarlo a la lista
			
/*			
            Mensaje mensaje = new Mensaje();
            mensaje.setContenido(nuevoPost);
            mensaje.setUsuario("Nombre del usuario actual"); // Sacar del contexto o bean el usuario actual
			mensaje.setSetFecha();		//Sacar la fecha actual del sistema
	
            mensajes.add(mensaje);
*/
            // Limpiar el cuadro de texto
            nuevoPost = "";
            
            
            // Aqui habria que ademas añadir ese post a la base de datos
        }
    }
    
	
	public List<Post> getMensajesNuevos(){
		
		//Sacar de la base de datos los mensajes delas comunidades a las que pertenezca el usuario
		//Hay que filtrar que solo se  muestren los ultimos 5
		
		
		return mensajes;
	}
	
	public List<Post> getMensajesUsuario(){
		//Sacamos el usuario del contexto
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LOGGEDIN_USER");
		//Sacamos los mensajes de ese usuario 
		PostJdbcDAO postClase=new PostJdbcDAO();
		mensajes =postClase.getPostsFromUser(user.getEmail());

		return mensajes;
	}
	
    public List<Post> getMensajesComunidad(int comunidadId) {		//MODIFICAR CON LA BASE DE DATOS
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
