Collections Framework Overview :

Framework :  collections which is one of the most interesting topics which will save developers time

A framework is a set of several classes and interfaces whic provide a ready- made architecture .

A collection is a group of objects . where these objects are called elements


In Array - we can store primitive and non primitive type data but should be of similar type of elements - Homogeneous
int x = 10;
int[] x1 = {3,4,5};

class Employee              class Person
{							{
int empNo;					int personID;
String empName;				String personName;
} 							}


Employee[] emp = new Employee[5];

emp[0] = new Employee();
emp[1] = new Employee();
emp[2] = new Person(); // cannot store a person class object 
}

In collections we are storing homogeneous as well as heterogeneous objects , unique or duplicate collections of object

class Employee              class Person
{							{
int empNo;					int personID;
String empName;				String personName;
} 							}

List l = new ArrayList();
for(int i =0; i < 5 ; i++)
{
Employee emp = new Employee();
Person p = new Person();
l.add(emp);  // we can store emp objects , holding 5 group of objects as a single entity
l.add(p);  // we can also store person objects as it is heterogeneous
}
}


then these objects we can store in a collection;

Collections framework is a class library to handle group of objects which is present in java.util package and it allows us to store ,
retrieve , update and view group of objects 

2 types of containers 
a) one for storing a collection of elements (objects) , that is simply called a collection 
Interfaces  --  Collection , List , Set ,   so for implementation of these collection we have classes  --- 
Implementation classes  ---  ArrayList ,  LinkedList , HashSet,TreeSet 
b)  one for storing key/value pairs form of elements and keys  which we called a map
Interface --- Map
Implementation classes ---  HashMap , TreeMap












