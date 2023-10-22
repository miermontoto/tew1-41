package impl.tewrrss.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.persistence.CommunityDAO;


public class CommunityJdbcDAO extends JdbcDAO implements CommunityDAO {

	@Override
	public boolean add(Community community) {
		boolean added = true; // FIXME: This is a stub
		String query = "INSERT INTO community VALUES (?, ?)";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, community.getName());
			ps.setString(2, community.getDescription());

			ps.executeUpdate();
		} catch (SQLException e) {getDatabase().handleException(e);}

		return added;
	}

	@Override
	public boolean remove(String name) {
		boolean removed = true; // FIXME: This is a stub
		String query = "DELETE FROM community WHERE name = ?";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, name);

			ps.executeUpdate();
		} catch (SQLException e) {getDatabase().handleException(e);}

		return removed;
	}

	@Override
	public boolean update(Community community) {
		boolean updated = true; // FIXME: This is a stub
		String query = "UPDATE community SET description = ? WHERE name = ?";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, community.getDescription());
			ps.setString(2, community.getName());

			ps.executeUpdate();
		} catch (SQLException e) {getDatabase().handleException(e);}

		return updated;
	}

	@Override
	public List<Community> getCommunities() {
		List<Community> communities = new ArrayList<>();

		String query = "SELECT * FROM community";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			if (rs == null) return communities;

			while(rs.next()) {
				communities.add(new Community(rs.getString("name"), rs.getString("description")));
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		return communities;
	}

}
