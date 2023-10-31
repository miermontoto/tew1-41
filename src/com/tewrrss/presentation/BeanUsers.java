package com.tewrrss.presentation;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.*;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.util.Role;

@ManagedBean(name = "users")
@SessionScoped
public class BeanUser implements Serializable {
	private static final long serialVersionUID = 5128381238225556L;

	private UserService userService;
	private CommunityService communityService;

	private String userEmail;
	private String communityName;

	public BeanUser() {
		userService = Factories.services.createUserService();
		communityService = Factories.services.createCommunityService();
	}

	public User getUserEmail() {
		return this.user;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Community getCommunityName() {
		return this.communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public List<User> listAll() {
		return service.listAll();
	}

	public String join() {
		Optional<User> user = userService.findByEmail(userEmail);
		Optional<Community> community = communityService.findByName(communityName);
		if (!user.isPresent() || !community.isPresent()) {
			return "error";
		}

		return communtiyService.join(community.get(), user.get());
	}

	public List<String> getEmails() {
		return userService.listAll().forEach(u -> u.getEmail()).collect(Collectors.toList());
	}

	public List<String> getCommunities() {
		return communityService.listAll().forEach(c -> c.getName()).collect(Collectors.toList());
	}
}
