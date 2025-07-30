final keyword :

Final variables :  can be assigned only once , If it's a reference(object or array),
the reference can't change , but the object's state may change.

Final Methods : Cannot be overridden by subclasses - used when you want to preserve
the behaviour(actions), Final methods can be overloaded ( because it is defined in same class)

Final Classes :  Cannot be subclassed  , useful for immutablility/security (eg. java.lang.String is final)

blank finals :  must be assigned exactly once , usually within the constructors or 
static initializers  -- useful , for ensuring constants  

this Keyword:
1) It refers to the current object instance
2) used inside instance methods/constructors to 
distinguish the ambiguity between instance variables and local variables if they have same name.
3) also used to call another constructor in the same class

Rule : Cannot be used in static block or context
class Hello{

int id ;
String name;

Hello(int id , String name)
{

this.id = id;
System.out.println(id);
System.out.println(this.id);

}


}


Transient Keyword:
Java Serialization : (Serialize and Deserialize) --> Converting the object to bytes  and deserialize it back from bytes to object
(Encrypting and Decrypting)
So while serialize and deserizable if you want certains attributes like passwords , security tokens should not be saved or should be regenerated or a part of it there we use transient keyword


instanceof  -  generally we use or checks at the time of type casting from object class to a particular class (Employee or Student) 
runtime type safety 

ArrayList<Student> ar = new ArrayList<>();
ArrayList<Employee> ar = new ArrayList<>();

It returns true if object is an instance of the class otherwise it gives false
It is safe before type casting


Inner Class : A class defined within the another class

Class Hello
{

class Message
{


}


}

Non-static member inner class  :  inner class can access all outer members (including private )  The inner class object needs to be tied with outer class object
Static Nested class: can access only static member of outer class , No need to tied with outer class object ( based on Builder pattern)
Local inner class : defined inside  a method , can access local variables that are final 
Anonymous Inner Class : Functional Interface : unnamed , inline class that implements an interface or extends a class 


OOPS : -Object Oriented Programming -- Real world

Class and Object  -- state  -- ( attributes)  --- behaviour --- ( functions)
RV

Employee e  = new Employee();

OOPS features  - Main Pillars
a) Abstraction   ---   It is implemented using Abstract Class or an Interface 
b) Polymorphism  ---   It is implemented using Run time polymorphism(Overriding) or Compile Time Polymorphism(Overloading)
c) Encapsulation ---    It is implemented using the private variables where direct accessibility is not given but we provide getter or setter methods  as per the requirement
d) Inheritance  --     It is implemented using extends keyword when child class inherit the base class


Encapsulation  -- when we focus on the data security,  

private int age = 30;  anyone can change or modify data without any rule ( no data restriction , no validation)

// Create an Encapsulated Employee class having fields as id, name and salary , 
//set the salary where the logic is that salary should be below 50000 while setting
//Apply trim on name input and display employee info 

Uses:-

Data Security  -- Protecting data or hiding internal state  and requiring all interaction due to public getter/setter methods
               -- Also controlled accessibility either getter( for reading only) or setter(for writing only ) or both getter/setter(Reading & Writing)

Polymorphism : Means many forms   
It allows one interface or method name to behave differently based on the object it's acting upon

decision of calling the method depends upon the object which will be decided either at compile time or run time
1) Method Overloading -- Static Binding -- (Compile time that which method will be called)  --- Same method in same class but different parameters
2) Method Overriding  -- Dynamic Binding -- (At run time it will be called either the parent one or the child one depends upon the creation of object)   --- Same method in base class and child class where child class  may redefine the parent's class method
 

polymorphism uses :

1. Code reusability  
2. Extensibility  -- Easy to add new behaviour or features without rewriting
3. Loose Coupling -- Works with base types of classes
4. Run time flexibility : At run time decision








