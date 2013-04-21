package demo.springmvc.blog.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import demo.springmvc.blog.domain.User;

@Service
public class UserService {
	private JdbcTemplate jdbcTemplate;
	private final static UserRowMapper USER_ROW_MAPPER = new UserRowMapper();

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<User> getAllUser() {
		return this.jdbcTemplate.query("select * from user",
				UserService.USER_ROW_MAPPER);
	}

	public void createUser(User user) {
		this.jdbcTemplate.update(
				"INSERT INTO user (username,password,fullname) VALUES (?,?,?)",
				user.getUsername(), user.getPassword(), user.getFullname());
		user.setId(this.jdbcTemplate.queryForInt(
				"select id from user where username = ?", user.getUsername()));
	}

	public User checkUser(User loginUser) {
		List<User> userList = this.jdbcTemplate.query(
				"select * from user where username = ? and password = ?",
				UserService.USER_ROW_MAPPER, loginUser.getUsername(),
				loginUser.getPassword());
		if (!userList.isEmpty()) {
			return userList.get(0);
		}
		return null;
	}

	private static class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet resultSet, int line) throws SQLException {
			User user = new User();
			user.setId(resultSet.getInt("id"));
			user.setUsername(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setFullname(resultSet.getString("fullname"));
			return user;
		}
	}
}
