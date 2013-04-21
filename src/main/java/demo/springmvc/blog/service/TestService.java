package demo.springmvc.blog.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import demo.springmvc.blog.domain.Test;

@Service
public class TestService {
	private JdbcTemplate jdbcTemplate;
	private final static TestRowMapper TEST_ROW_MAPPER = new TestRowMapper();
	private static final Logger logger = LoggerFactory
			.getLogger(TestService.class);

	@Autowired
	public void setDataSource(DataSource dataSource) {
		//logger.info(dataSource.getConnection().)
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void createTest(Test test) {
		this.jdbcTemplate.update("insert into test (one,two) values (?,?)",
				test.getOne(), test.getTwo());
	}

	public List<Test> getTest() {
		return this.jdbcTemplate.query("select * from test",
				TestService.TEST_ROW_MAPPER);
	}

	private static class TestRowMapper implements RowMapper<Test> {
		@Override
		public Test mapRow(ResultSet resultSet, int line) throws SQLException {
			Test test = new Test();
			test.setId(resultSet.getInt("id"));
			test.setOne(resultSet.getString("one"));
			test.setTwo(resultSet.getString("two"));
			return test;
		}
	}
}
