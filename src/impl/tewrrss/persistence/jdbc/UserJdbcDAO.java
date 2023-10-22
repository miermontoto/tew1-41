package impl.tewrrss.persistence.jdbc;

import com.tewrrss.util.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tewrrss.dto.User;
import com.tewrrss.persistence.UserDAO;


public class UserJdbcDAO extends JdbcDAO implements UserDAO {

	public static List<User> allUsers = null;
	public static boolean dirtyAllUsers = true;

	public List<User> getUsers() {
		if (allUsers != null && allUsers.size() > 0 && !dirtyAllUsers) {
			return allUsers;
		}

		List<User> users = new ArrayList<>();

		ResultSet rs = getDatabase().executeQuery("SELECT * FROM user");
		if (rs == null) return users;

		try {
			while(rs.next()) {
				User user = new User();
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("role"));

				users.add(user);
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		allUsers = users;
		return users;
	}

	public Optional<User> findByEmail(String email) {
		Optional<User> user = Optional.empty();

		ResultSet rs = getDatabase().executeQuery("SELECT * FROM user WHERE email = '" + email + "'");
		if (rs == null) return user;

		try {
			if (rs.next()) {
				user = Optional.of(new User());
				user.get().setEmail(rs.getString("email"));
				user.get().setPassword(rs.getString("password"));
				user.get().setRole(rs.getInt("role"));
			}
		} catch (SQLException e) {getDatabase().handleException(e);}

		return user;
	}

	public boolean remove(String email) {
		boolean removed = true; // FIXME: comprobar que se ha borrado

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement("DELETE FROM user WHERE email = ?");
			ps.setString(1, email);

			ps.executeQuery();
		} catch (SQLException e) {getDatabase().handleException(e);}

		return removed;
	}

	public boolean add(User user) {
		boolean added = true; // FIXME: comprobar que se ha añadido

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement("INSERT INTO user VALUES (?, ?, ?)");
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getRole());

			ps.executeQuery();
		} catch (SQLException e) {getDatabase().handleException(e);}

		return added;
	}

	public boolean update(User user) {
		boolean updated = true; // FIXME: comprobar que se ha actualizado

		try {
			PreparedStatement ps = getDatabase().getConnection().prepareStatement("UPDATE user SET password = ?, role = ? WHERE email = ?");
			ps.setString(1, user.getPassword());
			ps.setInt(2, user.getRole());
			ps.setString(3, user.getEmail());

			ps.executeQuery();
		} catch (SQLException e) {getDatabase().handleException(e);}

		return updated;
	}
}
