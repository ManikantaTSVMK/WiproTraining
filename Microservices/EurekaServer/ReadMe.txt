Microservices : --  Micro + services

There are two types of architecture:

Monolithic  :-- All modules exist in one application (In an application you have different modules like  : - inventory , order , payment , user  , admin ) 
Bank A/c :-- type of a/c , loan a/c  , financial transactions , login as per the different customers or account

20 modules , 30 modules 

Drawbacks of keeping all modules in one application:

1) lot of dependency ( if any update is required in one module we have to keep hold the entire application)
2) Difficulty in testing or debugging -- because of coupling with each other
3) Limited scalability -- resources (increasing the server load) -- may be on festive season the client request may get increased which will increase the load of server (might be we need to scale up the resources (servers) -- it is costly  or scale down)
4) Deployment challenges 
5) No modularity -- Complexity increases
6) Manpower 


Microservice Based Architecture(Advanced Architecture) -- collection of small , loosely coupled services . Each service is 
self contained and implements a single business capability . 
These services communicate with each other through well-defined APIs over HTTP.

User --- App ( Services -- Apis)--- single business capability 
Admin  -- App (Services -- APis)
Inventory -- App
Order --- App 
Payment

Microservice provides different features and has a different mechanism which we can handle using
1) services will run on different port no's  hence there will be less dependency , loosely coupled
2) Circuit Breaker / Fall back  -- if internally one service is calling another service then there may be a fall back(backup) // You can easily check which service is down (metric report(actuator)) -- Prometheus and Grafana are two widely used open-source tools that are frequently deployed together for comprehensive system monitoring and visualization.
3) Load Balancer -- Scaling up the resources /  Scale down the resources
4) Supported Multiple languages

Disadvantage --- resources are costly..

Eureka Server -- Eureka Server is a central component in a microservice architecture, acting as a service registry for dynamic service discovery. It is part of the Spring Cloud Netflix project, which provides tools for building cloud-native applications.
Service Registration:
Service Discovery:
Health Checking:
Load Balancing (in conjunction with clients):
Decentralized Communication: