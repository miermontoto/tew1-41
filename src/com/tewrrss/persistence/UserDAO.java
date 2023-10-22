package com.tewrrss.persistence;

import java.util.List;
import java.util.Optional;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

public interface UserDAO {

	List<User> getUsers();
	Optional<User> findByEmail(String email);
	boolean remove(String email);
	boolean add(User user);
	boolean update(User user);
	List<User> getUsersInCommunity(Community community);

}
