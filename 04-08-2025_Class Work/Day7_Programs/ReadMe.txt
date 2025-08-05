Multithreading :

Multitasking ,Multithreading and Multiprocessing

Multitasking : The ability of an OS which handles or manages the different tasks , to execute multiple independent progras
or tasks concurrently , where tasks are switching so instantly between each other (time-sharing the CPU in OS) . Each task
has its own seperate memory space. 

for eg: Running  web browser , word application , music player on a single - core processor or computer . The OS will allocate
 small time slices to each application , creating the virtual environment or illusion that all are running parallely 
 
Multithreading : A technique where a single program or process is dividied into multiple independent units of execution called 
Thread

for eg :  A web server handling multiple client request  concurrently , Instead of creating a new process for each request
(would be  proper resource allocation ) , the server creates a new thread for each client request , sharing the server's resources 
(asynchronous manner) 

Synchronous --- When one thread will execute completely then only the another thread or a process will execute , no matter how 
how much time the first thread is taking to complete. for eg: Read / Write , Withdraw  / Deposit 

Asynchronous --- When multiple thread or process can execute concurrently or parallely to utilize the waiting time for a thread which 
is time consuming process : --  for eg: Order/(Notification/Alerts) parallely they are receiving  ,,
File copying ( while copying // some percentage  it shows that how much time is left or done) 

class Program
{

void input()
{}

void display()
{}

static void main(String[] args) -- thread
{
    input()  --- thread
    display() --- thread
}

}

Thread is light in process ( weight)
Better Peformance , Optimization 
Thread communication

Multiprocessing :   the use of multiple independent processing units( Multiple processors / CPU cores)  within a single computer
system to execute multiple processes or programs simultaneously in parallel where each process runs on a seperate processor

Gaming software , video editing software ( audio / video / animation) the process takes place and has its own dedicated memory space .

Different parts of the video related tasks can be distributed among the available cores , which leads to faster completion




__________________________________________

Multitasking --- When OS runs Multiple processes , each with seperate memory
Multithreading -- When threads within one process , sharing memory and resources . Threads are lighter, switch faster and communicate easily


Java uses preemptive scheduling which means the OS or JVM scheduler gives each thread time slices  -- each thread has its own stack frame but shares the heap


There are two ways to create or implement Thread
1. You should extends a Thread class
2. Implement Runnable interface and pass it to new Thread() and call start() -- run() 

Each thread instance has individual stack , program counter , state and priority but a common heap 

Life Cycle of a thread (Multiple states of a Thread)

1) initialize a thread   -- > new Thread() -- > thread object is created but not started 
2) Make a thread in a runnable state ( ready to run) --- > t.start() --- you are starting a thread ( but not guaranteed that it will run immediately because it will be scheduled by JVM)
3) Running ( Executing ) ---> will call run() -->  actual execution of your thread by CPU
4) Blocked state --->  thread is waiting to enter a synchronized block/ Methods that's already locked
3) Waiting () -->  by calling wait()    - To resume from waiting state defined by wait() will be resumed using notify() or notifyAll()
4) Pausing state --- > , sleep() ( where you defing certain duration ) ,  wait(ms) , join(ms) 
5) Dead state --- >  stop()



Design pattern  : -- Creational  , Behavioural  , Structural

GOF --- Design pattern\

Design Patterns: Creational - Factory, Abstract Factory, Builder, Prototype, Singleton.

Design Patterns: Structural - Adapter, Bridge, Composite, Decorator, Facade, Flyweight, Proxy

Design Patterns: Behavioral  - Interpreter, Template method, Chain responsibility, Comment Iterator, Mediator, Momento, Observer, State, Strategy, Visitor.
