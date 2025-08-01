Java Exception Handling

Throwable (Base Class)


Error  -- (JVM/ System Level issues) errors which we cannot handle  ( OutOfMemoryError ), errors cannot be predicted , Usually we cannot 
catch in code , serious issues that may occur suddenly  for(i=0 ; i<n;i++)
An error is a serious issue that arises in the JVM or system environment , 
ususally outside the application control . It is also a throwable object , but it 
indicates problems that are not meant to be caught or handled in normal code by the programmers or Developers

eg: JVM runs out of memory 
Infinite recursion or a loop which causes a stack overflow

These are not recoverable .

Exception  --- Application Level issues -- which is an event that disrupts the normal flow of the program during execution. It is 
an object that represents an error or unexpected situation that can be handled gracefully using 
try , catch and finally blocks  also includes throw and throws key word.

try:=  business logic  --  you think that may be the code is risky
catch := handle immediately the exception after occured in try block 
finally block will execute in both the cases 

throws 
throws

