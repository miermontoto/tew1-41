package com.tewrrss.presentation;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.bean.*;

import com.tewrrss.business.CommunityService;
import com.tewrrss.business.UserService;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

@ManagedBean(name = "users")
@SessionScoped
public class BeanUsers implements Serializable {
	private static final long serialVersionUID = 5128381238225556L;

	private UserService userService;
	private CommunityService communityService;

	private String userEmail;
	private String communityName;

	public BeanUsers() {
		userService = Factories.services.createUserService();
		communityService = Factories.services.createCommunityService();
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getCommunityName() {
		return this.communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String join() {
		Optional<User> user = userService.findByEmail(userEmail);
		Optional<Community> community = communityService.findByName(communityName);
		if (!user.isPresent() || !community.isPresent()) {
			return "error";
		}

		return communityService.join(community.get(), user.get());
	}

	public List<String> listEmails() {
		return userService.listAll().stream().map(u -> u.getEmail()).collect(Collectors.toList());
	}

	public List<String> listCommunities() {
		return communityService.listAll().stream().map(c -> c.getName()).collect(Collectors.toList());
	}
}
