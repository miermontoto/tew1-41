package impl.tewrrss.business;

import java.util.List;

import com.tewrrss.business.PostsService;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;

import impl.tewrrss.business.posts.*;

public class PostsServiceImpl implements PostsService{

	@Override
	public String add(Post post) {
		return new Add().add(post);
	}

	@Override
	public String remove(Post post) {
		return new Remove().remove(post);
	}


	@Override
	public List<Post> getPostsByUser(User user){
		return new GetPostsByUser().getPostsByUser(user);
	}


	@Override
	public List<Post> getPostsInCommunity(Community community) {
		return new GetPostsInCommunity().getPostsFromCommunity(community);
	}

	@Override
	public List<Post> getNewPosts(User user) {
		return new GetNewPosts().getNewPosts(user);
	}

	@Override
	public List<Post> getPostsByUserInCommunity(User user, Community community) {
		return new GetPostsByUserInCommunity().getPostsByUserInCommunity(user, community);
	}
}
