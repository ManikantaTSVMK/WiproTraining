

( In core java 
a. we were adding a jar file  -- added to a class path
b. We created a properties file --(developer was creating it)
c) we created a model / Bean class -- where class properties needs to be mapped with the table(RDBMS) in backend -- (Developer was mapping it manually)
d) we created a database / table (fields) -- Developer was also interacting with database and somehow he should also know the queries of different databases may be  Mysql , oracle, Mongodb)
e) PreparedStatement , Statement , CallableStatment for queries  -- Here he will write those queries to perform DDL/DML operations)
)

That's why Hibernate came into the picture  :  Hibernate is an ORM Framework that maps java objects to relational database tables

Core Components:
a) Dependency -- instead of jar file (which spring will do automatically (DI)set to a class path )
a) Configuration -- use to configure Hibernate(XML or java based annotation type)

Steps : First the container will read the 
1) configuration(reads (hibernate.cfg.xml / hibernateconfig.java / annotation))
2) build once the SessionFactory ( Which is thread safe and immutable)
2) for per user it will open the Session ( 1st level cache, manages object state)
3) begin/ commits through a Transaction class (ACID ,  commit/rollback)
4) perform CRUD& Query API (Hibernate has its own or uses HQL / JPQL ( Hibernate Query Language) --- Native SQL --- criteria API)
5) JDBC/Dialects
6) Database



