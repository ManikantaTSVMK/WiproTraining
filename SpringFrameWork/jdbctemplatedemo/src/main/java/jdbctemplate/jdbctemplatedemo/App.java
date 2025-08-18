package jdbctemplate.jdbctemplatedemo;

import java.util.HashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import jdbctemplate.resources.SpringConfig;
import jdbctemplate.resources.SpringConfigFile;


public class App {
    public static void main(String[] args) {
        
    	int id_val=89;
    	String name_val="Neha",email_val="neha24@gmail.com";
    	
    	ApplicationContext context= new AnnotationConfigApplicationContext(SpringConfig.class);
    	NamedParameterJdbcTemplate  jdbcTemplate= context.getBean(NamedParameterJdbcTemplate.class);
           
    	  HashMap<String,Object> map=new HashMap<>();
    	  map.put("id",id_val );
    	  map.put("email",email_val);
    	  map.put("name",name_val);
    	  
    	
    	//1. insert (named parameter)
    	   String query="insert into student values(:id,:name,:email)";
    	     int count= jdbcTemplate.update(query,map);
    	
    	     if(count>0)
    	       System.out.println("data is inserted");
    	       else
    	       	System.out.println("data is not inserted");
    	       }
        
//    	int id=23;
//    	String name="Priyanshi",email="priyanshi19@gmail.com";
//    	
//    	ApplicationContext context= new AnnotationConfigApplicationContext(SpringConfigFile.class);
//    	JdbcTemplate  jdbcTemplate= context.getBean(JdbcTemplate.class);
//           
    	
    	
    	//1. insert
//    	String insertquery="insert into student values(?,?,?)";
//    	int count =jdbcTemplate.update(insertquery,id,name,email);
//    if(count>0)
//    System.out.println("data is inserted");
//    else
//    	System.out.println("data is not inserted");
//    }
//    
//
    	
    	//2. Fetch all users
//    	String selectquery="select * from student";
//   List<Student> list1= 	jdbcTemplate.query(selectquery,new StudentRowMapper() );
//        for(Student obj:list1)
//        System.out.println(obj);
    
    //3. fetch single user
//       String singleSty="select * from student where id=?";
//       Student obj=jdbcTemplate.queryForObject(singleSty,new StudentRowMapper(),id );
//           System.out.println(obj);
    
    	
    	//4. delete
//    	String deletequery="delete from student where id=?";
//    	int count =jdbcTemplate.update(deletequery,id);
//    if(count>0)
//    System.out.println("data is deleted");
//    else
//    	System.out.println("data is not deleted");
//    }
    	
    	
    	//5.Update
//    	String query="update Student set name=? where id=?";
//    	int count =jdbcTemplate.update(query,name,id);
//    if(count>0)
//    System.out.println("data is udpated");
//    else
//    	System.out.println("data is not updated");
//    }
}