package impl.tewrrss.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.persistence.CommunityDAO;


public class CommunityJdbcDAO extends JdbcDAO implements CommunityDAO {

	List<Community> allCommunities = null;
	boolean dirtyAllCommunities = false;
	List<Community> joinedCommunities = null;
	boolean dirtyJoinedCommunities = false;

	@Override
	public boolean add(Community community) {
		boolean added = false;
		String query = "INSERT INTO community VALUES (?, ?)";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, community.getName());
			ps.setString(2, community.getDescription());

			added = ps.executeUpdate() == 1;
		} catch (SQLException e) {getDatabase().handleException(e);}

		dirtyAllCommunities &= added;
		return added;
	}

	@Override
	public boolean remove(String name) {
		boolean removed = true;

		String queryDeleteCommunity = "DELETE FROM community WHERE name = ?";
		String queryDeleteMembers = "DELETE FROM member WHERE community_name = ?";

		try {
			// Se ejecuta la primera consulta para bprrar los miembros de una comunidad (restricciones de integridad)
			PreparedStatement ps1 = getDatabase().getConnection().prepareStatement(queryDeleteMembers);
			// Se procede al borrado de la comunidad.
			PreparedStatement ps2 = getDatabase().getConnection().prepareStatement(queryDeleteCommunity);
			ps1.setString(1, name);
			ps2.setString(1, name);

			// Primero eliminar membresías, luego la comunidad.
			ps1.executeUpdate();
			removed = ps2.executeUpdate() == 1;
		} catch (SQLException e) {getDatabase().handleException(e);}

		dirtyAllCommunities &= removed;
		return removed;
	}

	@Override
	public boolean update(Community community) {
		boolean updated = false;
		String query = "UPDATE community SET description = ? WHERE name = ?";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, community.getDescription());
			ps.setString(2, community.getName());

			updated = ps.executeUpdate() == 1;
		} catch (SQLException e) {getDatabase().handleException(e);}

		dirtyAllCommunities &= updated;
		return updated;
	}

	@Override
	public List<Community> getCommunities() {
		if(allCommunities != null && !dirtyAllCommunities) return allCommunities;

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

	@Override
	public List<Community> getJoinedCommunities(User user) {
		if(joinedCommunities != null && !dirtyJoinedCommunities) return joinedCommunities;
		List<Community> communities = new ArrayList<>();

		String query = "select c.* from member as m " +
			"inner join community as c on m.community_name = c.name " +
			"where m.user_email = ?";

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, user.getEmail());

			ResultSet rs = ps.executeQuery();
			if (rs == null) return communities;

			while(rs.next()) {
				communities.add(new Community(rs.getString("name"), rs.getString("description")));
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		return communities;
	}

	@Override
	public boolean join(Community community, User user) {
		boolean joined = false;

		String query = "INSERT INTO member VALUES (?, ?)";
		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, user.getEmail());
			ps.setString(2, community.getName());

			joined = ps.executeUpdate() == 1;
		} catch (SQLException e) {getDatabase().handleException(e);}

		dirtyJoinedCommunities &= joined;
		return joined;
	}

	@Override
	public boolean leave(Community community, User user) {
		boolean left = false;

		String query = "DELETE FROM member WHERE user_email = ? AND community_name = ?";
		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement(query);
			ps.setString(1, user.getEmail());
			ps.setString(2, community.getName());

			left = ps.executeUpdate() == 1;
		} catch (SQLException e) {getDatabase().handleException(e);}

		dirtyJoinedCommunities &= left;
		return left;
	}
}
