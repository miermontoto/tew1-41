package com.tewrrss.persistence;

import java.util.List;

import com.tewrrss.dto.Post;

public interface PostDAO {

	List<Post> getPostsInCommunity(String communityName);
	List<Post> getPostsFromUser(String userEmail);
	List<Post> getPostsFromComunitysUser(String userEmail);
	List<Post> getPostsFromUserInCommunity(String userEmail, String communityName);
	List<Post> searchPosts(String search);
	boolean remove(Post post);
	boolean add(Post post);
	boolean update(Post post);

}
