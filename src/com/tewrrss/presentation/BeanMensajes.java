package com.tewrrss.presentation;

import java.sql.Date;
import java.util.Calendar;
import java.util.Comparator;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;

import impl.tewrrss.persistence.jdbc.PostJdbcDAO;
import impl.tewrrss.persistence.jdbc.UserJdbcDAO;



@ManagedBean(name = "BeanMensajes") 
@SessionScoped
public class BeanMensajes {
	
	private PostJdbcDAO postClase;
	private Post postActual;
    private List<Post> mensajes;		
    private String nuevoPost;

    
    public BeanCommunities getComunidad() {
		return comunidad;
	}


	public void setComunidad(BeanCommunities comunidad) {
		this.comunidad = comunidad;
	}

	@ManagedProperty(value = "#{communities}")
    private BeanCommunities comunidad;
	
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

	
	public void borrarMensaje(Post mensaje) {	

		// acceder a la base de datos y eliminar este mensaje solo en caso de que sea el dueño del mensaje		
		User user = new BeanUser().getSessionUser();
		if(user.getEmail().equals(mensaje.getUserEmail())) {
			postClase.remove(mensaje);
			System.out.println("\n\nMensaje eliminado\n");
			// Obtener el FacesContext

		}		
	}
	
	
	public void agregarMensaje(BeanCommunities bc) {		

			
		if (!nuevoPost.equals("") ) {    
	        
			postActual.setContent(nuevoPost);
			postActual.setCommunityName(bc.comunidad.getName()); 		
			
			User user = new BeanUser().getSessionUser();
			postActual.setUserEmail(user.getEmail());

			int year = Calendar.getInstance().get(Calendar.YEAR);
			int month = Calendar.getInstance().get(Calendar.MONTH);
			int day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			
	        @SuppressWarnings("deprecation")
			Date fecha= new Date(year, month, day);
			postActual.setCreationDate(fecha);
			
			postClase.add(postActual);
			
            nuevoPost = "";
            
        }
    }
    
	
	public List<Post> getMensajesNuevos(){		//POR COMPLETAR
		
		
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LOGGEDIN_USER");
		
		
		//Sacar de la base de datos los mensajes delas comunidades a las que pertenezca el usuario
		//Hay que filtrar que solo se  muestren los ultimos 5
		
		
		return mensajes;
	}
	
	public List<Post> getMensajesUsuario(){
		//Sacamos el usuario del contexto
		User user = new BeanUser().getSessionUser();		//Sacamos los mensajes de ese usuario 
		PostJdbcDAO postClase=new PostJdbcDAO();
		mensajes =postClase.getPostsFromUser(user.getEmail());

		return mensajes;
	}
	
    public List<Post> getMensajesComunidad() {		
    	
    	
        FacesContext context = FacesContext.getCurrentInstance();
        BeanCommunities bean = context.getApplication().evaluateExpressionGet(context, "#{communities}", BeanCommunities.class);
    	
    	PostJdbcDAO postClase=new PostJdbcDAO();
    	mensajes = postClase.getPostsInCommunity(bean.comunidad.getName());


    	UserJdbcDAO usuarioClase = new UserJdbcDAO();
    	
    	for(Post p:mensajes) {	//De esta forma cambiamos el nombre de la comunidad por el del usuario que creo el post
    		p.setCommunityName(usuarioClase.findByEmail(p.getUserEmail()).get().getUsername());
    	}
    	
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
