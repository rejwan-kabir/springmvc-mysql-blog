package demo.springmvc.blog.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import demo.springmvc.blog.domain.Comment;

@Service
public class CommentService {
	private JdbcTemplate jdbcTemplate;
	private final static CommentRowMapper COMMENT_ROW_MAPPER = new CommentRowMapper();

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Comment> getAllcommentOfPost(int post_id) {
		return this.jdbcTemplate
				.query("select comment.id , comment.content , comment.date , comment.user_id , comment.post_id , user.username from comment , user where comment.post_id = ? and comment.user_id = user.id order by comment.date asc",
						CommentService.COMMENT_ROW_MAPPER, new Integer(post_id));
	}

	public void createcomment(Comment comment) {
		this.jdbcTemplate
				.update("INSERT INTO comment (content,date,user_id,post_id) VALUES (?,?,?,?)",
						comment.getContent(), comment.getDate(),
						comment.getUser_id(), comment.getPost_id());
	}

	private static class CommentRowMapper implements RowMapper<Comment> {
		@Override
		public Comment mapRow(ResultSet resultSet, int line)
				throws SQLException {
			Comment comment = new Comment();
			comment.setId(resultSet.getInt("id"));
			comment.setContent(resultSet.getString("content"));
			comment.setDate(resultSet.getDate("date"));
			comment.setUser_id(resultSet.getInt("user_id"));
			comment.setPost_id(resultSet.getInt("post_id"));
			comment.setUser_username(resultSet.getString("username"));
			return comment;
		}
	}
}
