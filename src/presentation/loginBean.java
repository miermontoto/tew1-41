package presentation;

public class loginBean {
	
	String username;
	String password;

	public loginBean() {
		
		//Inicializo las variables del Bean
		username = new String();
		password = new String(); 
	}
	
	public String manageLogin() {
		
		//Lógica que haga una consulta a BBDD. Implementar en otr Bean
		
		boolean authStatus = true; //Simula que todo ha ido bien
		
		if(authStatus) {
			return "success";
		} else {
			return "error";
		}
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
