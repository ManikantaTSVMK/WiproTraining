Spring Security is again one of the powerful module integrates with Spring Framework

Spring Security is a separate project within the Spring ecosystem and is not explicitly shown as a distinct module within the "Spring Framework Runtime" diagram provided in the image. While the image illustrates core modules like "Core Container," "Data Access/Integration," and "Web," Spring Security is a powerful and customizable authentication and access control framework that integrates with the Spring Framework but exists as its own project. 
Explanation:
Spring Framework Modules: The image depicts the architectural modules of the Spring Framework, such as the Core Container (Beans, Core, Context, SpEL), Data Access/Integration (JDBC, ORM, OXM, JMS, Transactions), Web (WebSocket, Servlet, Web, Portlet), AOP, Aspects, Instrumentation, Messaging, and Test. 
Spring Security: Spring Security provides comprehensive security services for Java EE-based enterprise applications. It offers features like authentication, authorization, and protection against common attacks. 
Integration: Spring Security seamlessly integrates with the various modules of the Spring Framework to secure web applications, REST APIs, and other Spring-based applications. 


1. First thing is we need to add a dependency in Pom.xml to get the default login and logout options / interface -- Spring Security 
2. create a productcontroller 
@RestController
public class ProductController {

	@GetMapping("/")
	public String WelcomeMessage()
	{
		return "Welcome to Spring Security Session";
	}
}

3. So to access the above url(http://localhost:8080/login) in the login screen enter the default username as user and the password provided on console for eg:
Using generated security password: e4c8bdb9-5b49-4ad8-8f14-cabe7d7aa394

after this it will redirect to the welcomeMessage to pring .


Authentication and Authorization
a)Authentication -- You user name and password to authenticate a valid user for any organization or to access any of the application(either you can define it in a file or jdbc authentication , jwt authentication , oAuth authentication)
b) Authorization : -- Once authentication is done then based on the usertype( admin , guestuser , user) the roles(the apis to access) will be assigned to the sepecific user type 

