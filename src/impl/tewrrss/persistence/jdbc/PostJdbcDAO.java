package impl.tewrrss.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;
import com.tewrrss.persistence.PostDAO;


public class PostJdbcDAO extends JdbcDAO implements PostDAO {

	@Override
	public List<Post> getPostsInCommunity(Community community) {
		List<Post> posts = new ArrayList<>();

		String query = "SELECT p.*, u.username FROM post as p "
			+ "INNER JOIN user as u ON p.user_email = u.email "
			+ "WHERE community_name = ?";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, community.getName());

			ResultSet rs = ps.executeQuery();
			if (rs == null) return posts;

			while (rs.next()) {
				Post post = new Post();
				post.setContent(rs.getString("content"));
				post.setCreationDate(rs.getString("creation_date"));
				post.setCommunityName(rs.getString("community_name"));
				post.setUserName(rs.getString("username"));

				posts.add(post);
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		return posts;
	}

	@Override
	public List<Post> getPostsByUser(User user) {
		List<Post> posts = new ArrayList<>();

		String query = "SELECT * FROM post WHERE user_email = ?";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, user.getEmail());

			ResultSet rs = ps.executeQuery();
			if (rs == null) return posts;

			while (rs.next()) {
				Post post = new Post();
				post.setContent(rs.getString("content"));
				post.setCreationDate(rs.getString("creation_date"));
				post.setCommunityName(rs.getString("community_name"));
				post.setUserEmail(rs.getString("user_email"));

				posts.add(post);
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		return posts;
	}

	@Override
	public List<Post> getPostsByUserInCommunity(User user, Community community) {
		List<Post> posts = new ArrayList<>();

		String query = "SELECT * FROM post WHERE user_email = ?, community_name = ?";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, user.getEmail());
			ps.setString(2, community.getName());

			ResultSet rs = ps.executeQuery();
			if (rs == null) return posts;

			while (rs.next()) {
				Post post = new Post();
				post.setContent(rs.getString("content"));
				post.setCreationDate(rs.getString("creation_date"));
				post.setCommunityName(rs.getString("community_name"));
				post.setUserEmail(rs.getString("user_email"));

				posts.add(post);
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		return posts;
	}

	@Override
	public List<Post> searchPosts(String search) {
		List<Post> posts = new ArrayList<>();

		String query = "SELECT * FROM post WHERE content LIKE '%?%'";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, search);

			ResultSet rs = ps.executeQuery();
			if (rs == null) return posts;

			while (rs.next()) {
				Post post = new Post();
				post.setContent(rs.getString("content"));
				post.setCreationDate(rs.getString("creation_date"));
				post.setCommunityName(rs.getString("community_name"));
				post.setUserEmail(rs.getString("user_email"));

				posts.add(post);
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		return posts;
	}

	@Override
	public boolean remove(Post post) {
		boolean removed = false;

		String query = "DELETE FROM post WHERE creation_date = ? AND user_email = ? AND community_name = ?";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, post.getCreationDate());
			ps.setString(2, post.getUserEmail());
			ps.setString(3, post.getCommunityName());

			removed = ps.executeUpdate() == 1;
		} catch (SQLException e) {getDatabase().handleException(e);}

		return removed;
	}

	@Override
	public boolean add(Post post) {
		boolean added = false;

		String query = "INSERT INTO post VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, post.getContent());
			ps.setString(2, post.getCreationDate());
			ps.setString(3, post.getUserEmail());
			ps.setString(4, post.getCommunityName());

			added = ps.executeUpdate() == 1;
		} catch (SQLException e) {getDatabase().handleException(e);}

		return added;
	}

	@Override
	public boolean update(Post post) {
		boolean updated = false;

		String query = "UPDATE post SET content = ? WHERE creation_date = ?, user_email = ?, community_name = ?";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, post.getContent());
			ps.setString(2, post.getCreationDate());
			ps.setString(3, post.getUserEmail());
			ps.setString(4, post.getCommunityName());

			updated = ps.executeUpdate() == 1;
		} catch (SQLException e) {getDatabase().handleException(e);}

		return updated;
	}

	@Override
	public List<Post> getPostsInCommunitiesOfUser(User user) {
		List<Post> posts = new ArrayList<>();

		String query = "SELECT P.CONTENT, P.CREATION_DATE, U.USERNAME AS USER_NAME, P.COMMUNITY_NAME " +
                "FROM PUBLIC.POST P " +
                "JOIN PUBLIC.MEMBER M ON P.COMMUNITY_NAME = M.COMMUNITY_NAME " +
                "JOIN PUBLIC.USER U ON P.USER_EMAIL = U.EMAIL " +
                "WHERE M.USER_EMAIL = ? " +
                "ORDER BY P.CREATION_DATE DESC " +
                "LIMIT 5";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, user.getEmail());

			ResultSet rs = ps.executeQuery();
			if (rs == null) return posts;

			while (rs.next()) {
				Post post = new Post();
				post.setContent(rs.getString("CONTENT"));
				post.setCreationDate(rs.getString("CREATION_DATE"));
				post.setCommunityName(rs.getString("COMMUNITY_NAME"));
				post.setUserName(rs.getString("USER_NAME"));

				posts.add(post);
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		return posts;
	}

	@Override
	public boolean dropAll() {
		return getDatabase().executeUpdate("DELETE FROM post") >= 1;
	}

}
