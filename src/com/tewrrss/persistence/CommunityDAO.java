package com.tewrrss.persistence;

import java.util.List;

import com.tewrrss.dto.Community;

public interface CommunityDAO {

	boolean add(Community community);
	boolean remove(String name);
	boolean update(Community community);
	List<Community> getCommunities();

}
