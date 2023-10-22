package com.tewrrss.persistence;

import com.tewrrss.dto.Community;

public interface CommunityDAO {

	boolean add(Community community);
	boolean remove(String name);
	boolean update(Community community);

}
