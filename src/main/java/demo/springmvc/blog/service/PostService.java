package demo.springmvc.blog.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import demo.springmvc.blog.domain.Post;

@Service
public class PostService {
	private JdbcTemplate jdbcTemplate;
	private final static PostRowMapper POST_ROW_MAPPER = new PostRowMapper();

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Post> getAllPost() {
		return this.jdbcTemplate
				.query("select post.id , post.title , post.content , post.date , post.user_id , user.username from post , user where post.user_id = user.id order by post.date desc;",
						PostService.POST_ROW_MAPPER);
	}

	public List<Post> getAllPost(int user_id) {
		return this.jdbcTemplate
				.query("select post.id , post.title , post.content , post.date , post.user_id , user.username from post , user where post.user_id = user.id and post.user_id = ? order by post.date desc",
						PostService.POST_ROW_MAPPER, new Integer(user_id));
	}

	public Post getPostOfId(int post_id) {
		return this.jdbcTemplate
				.queryForObject(
						"select post.id , post.title , post.content , post.date , post.user_id , user.username from post , user where post.user_id = user.id and post.id = ?",
						PostService.POST_ROW_MAPPER, new Integer(post_id));
	}

	public void createPost(Post post) {
		this.jdbcTemplate
				.update("INSERT INTO post (title,content,date,user_id) VALUES (?,?,?,?)",
						post.getTitle(), post.getContent(), post.getDate(),
						post.getUser_id());
	}

	private static class PostRowMapper implements RowMapper<Post> {
		@Override
		public Post mapRow(ResultSet resultSet, int line) throws SQLException {
			Post post = new Post();
			post.setId(resultSet.getInt("id"));
			post.setTitle(resultSet.getString("title"));
			post.setContent(resultSet.getString("content"));
			post.setDate(resultSet.getDate("date"));
			post.setUser_id(resultSet.getInt("user_id"));
			post.setUser_username(resultSet.getString("username"));
			return post;
		}
	}
}
