package com.tewrrss.presentation;

import java.sql.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.tewrrss.business.PostsService;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.util.Role;


@ManagedBean(name = "posts")
@SessionScoped
public class BeanPosts {
    private String nuevoPost;
    private BeanInfo loginInfo;
    private PostsService service;
    private Community currentCommunity;

	public BeanPosts() {
		service = Factories.services.createPostsService();
		loginInfo = new BeanInfo();
	}

	public void setCurrentCommunity(Community community) {
		this.currentCommunity = community;
	}

	public Community getCurrentCommunity() {
		return this.currentCommunity;
	}

	public String getNuevoPost() {
		return nuevoPost;
	}

	public void setNuevoPost(String nuevoPost) {
		this.nuevoPost = nuevoPost;
	}

	public String remove(Post mensaje) {
		User user = loginInfo.getSessionUser();
		if (user.getEmail().equals(mensaje.getUserEmail()) || user.getRole() == Role.ADMIN) {
			service.remove(mensaje);
			return "success";
		}

		return "unauthorized";
	}

	public String add() {
		User user = loginInfo.getSessionUser();
		if (!nuevoPost.isEmpty())  {
	        service.add(new Post(nuevoPost, new Date(0).toString(), user.getEmail(), currentCommunity.getName()));
	        nuevoPost = "";

	        return "success";
        }

		return "error";
    }

	public List<Post> getNewPosts() {
		return service.getNewPosts(loginInfo.getSessionUser());
	}

	public List<Post> getPostsByUser() {
		return service.getPostsByUser(loginInfo.getSessionUser());
	}

    public List<Post> getPostsInCommunity() {
        return service.getPostsInCommunity(currentCommunity);
    }

}
