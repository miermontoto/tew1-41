package impl.tewrrss.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.tewrrss.dto.Post;
import com.tewrrss.persistence.PostDAO;


public class PostJdbcDAO extends JdbcDAO implements PostDAO {

	@Override
	public List<Post> getPostsInCommunity(String communityName) {
		List<Post> posts = new ArrayList<>();

		String query = "SELECT * FROM post WHERE community_name = ?";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, communityName);

			ResultSet rs = ps.executeQuery();
			if (rs == null) return posts;

			while (rs.next()) {
				Post post = new Post();
				post.setContent(rs.getString("content"));
				post.setCreationDate(rs.getDate("creation_date"));
				post.setCommunityName(rs.getString("community_name"));
				post.setUserEmail(rs.getString("user_email"));

				posts.add(post);
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		return posts;
	}

	@Override
	public List<Post> getPostsFromUser(String userEmail) {
		List<Post> posts = new ArrayList<>();

		String query = "SELECT * FROM post WHERE user_email = ?";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, userEmail);

			ResultSet rs = ps.executeQuery();
			if (rs == null) return posts;

			while (rs.next()) {
				Post post = new Post();
				post.setContent(rs.getString("content"));
				post.setCreationDate(rs.getDate("creation_date"));
				post.setCommunityName(rs.getString("community_name"));
				post.setUserEmail(rs.getString("user_email"));

				posts.add(post);
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		return posts;
	}

	@Override
	public List<Post> getPostsFromUserInCommunity(String userEmail, String communityName) {
		List<Post> posts = new ArrayList<>();

		String query = "SELECT * FROM post WHERE user_email = ?, community_name = ?";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, userEmail);
			ps.setString(2, communityName);

			ResultSet rs = ps.executeQuery();
			if (rs == null) return posts;

			while (rs.next()) {
				Post post = new Post();
				post.setContent(rs.getString("content"));
				post.setCreationDate(rs.getDate("creation_date"));
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
				post.setCreationDate(rs.getDate("creation_date"));
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

		String query = "DELETE FROM post WHERE creation_date = ?, user_email = ?, community_name = ?";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setDate(1, post.getCreationDate());
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
			ps.setDate(2, post.getCreationDate());
			ps.setString(3, post.getCommunityName());
			ps.setString(4, post.getUserEmail());

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
			ps.setDate(2, post.getCreationDate());
			ps.setString(3, post.getUserEmail());
			ps.setString(4, post.getCommunityName());

			updated = ps.executeUpdate() == 1;
		} catch (SQLException e) {getDatabase().handleException(e);}

		return updated;
	}

	@Override
	public boolean dropAll() {
		return getDatabase().executeUpdate("DELETE FROM POST") >= 0;
	}
}
