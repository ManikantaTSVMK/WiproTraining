import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class RowMapperImpl implements RowMapper<User> {

	
	
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User todo = new User();
		todo.setEmail(rs.getString(1));
		todo.setName(rs.getString(2));
		todo.setPassword(rs.getString(3));
		return todo;
	}

}

