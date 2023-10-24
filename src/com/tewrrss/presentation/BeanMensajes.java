package com.tewrrss.presentation;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;

import impl.tewrrss.persistence.jdbc.PostJdbcDAO;



@ManagedBean(name = "BeanMensajes") 
@SessionScoped
public class BeanMensajes {
	
	private PostJdbcDAO postClase;
	private Post postActual;
    private List<Post> mensajes;		
    private String nuevoPost;

	
	public BeanMensajes() {
		postClase=new PostJdbcDAO();
		postActual = new Post();
		
	}
	
    
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

			
		if (!nuevoPost.equals("") ) {
			
	        FacesContext context = FacesContext.getCurrentInstance();
	        BeanCommunities bean = context.getApplication().evaluateExpressionGet(context, "#{communities}", BeanCommunities.class);
			
			
			postActual.setContent(nuevoPost);
			postActual.setCommunityName(bean.getNombre()); 		

			
			User user = new BeanUser().getSessionUser();
			postActual.setUserEmail(user.getEmail());
			
			Date fechaUtil = new Date(System.currentTimeMillis());

	        // Convertir la fecha de java.util.Date a java.sql.Date
	        java.sql.Date fechaSQL = new java.sql.Date(fechaUtil.getTime());
	        
			postActual.setCreationDate(fechaSQL);
			
			postClase.add(postActual);
			
            nuevoPost = "";
            
        }
    }
    
	
	public List<Post> getMensajesNuevos(){
		
		
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LOGGEDIN_USER");
		
		
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
	
    public List<Post> getMensajesComunidad() {		//MODIFICAR CON LA BASE DE DATOS
    	
    	
        FacesContext context = FacesContext.getCurrentInstance();
        BeanCommunities bean = context.getApplication().evaluateExpressionGet(context, "#{communities}", BeanCommunities.class);
    	
    	PostJdbcDAO postClase=new PostJdbcDAO();
    	mensajes = postClase.getPostsInCommunity(bean.getNombre());


        //Ordenar la lista por fecha
        
/*        // Definir un Comparator personalizado para ordenar por fecha de publicaci�n.
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
