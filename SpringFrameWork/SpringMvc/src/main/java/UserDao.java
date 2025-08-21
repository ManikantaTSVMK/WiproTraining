import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UserDao {

	

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int saveUser(User todo) {
	String insertQuery= "insert into User (email,name,password) values(?,?,?)";//prepared statemnts
	int updatedRows=jdbcTemplate.update(insertQuery,todo.getEmail(),todo.getName(),todo.getPassword());
		return updatedRows;
	}
	
	
	
	public List<User> getUsers() {
		String selectQuery="select * from User";
		List<User> todo=jdbcTemplate.query(selectQuery,new RowMapperImpl());
		return todo;
	}


	

	
	
}