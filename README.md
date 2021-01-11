# Java 11 HTTP Client KATA



### Description :
We will discover the new Java 11 HTTP Client.
Java 11 provides the HTTP Client API to facilitate client-side use of the HTTP protocol.
This API should replace the historic HttpURLConnection class introduced in JDK 1.1. This class has many drawbacks including:
- old and difficult to maintain
- not easy to use
- works only in synchronous mode

### Technical requirements : 
- java 11 or higher


##  PART I : 

### User Story 1 : 
As a Java 11 HelloServer user

I Want to get greeting message from the server as a String (route : /v1/hello)

### User Story 2 : 
As a Java 11 HelloServer user

I Want to get greeting message from the server as a Json object (route : /v2/hello)

### User Story 3 : 
As a Java 11 HelloServer user

I Want to get personalized greeting message from the server (route : /v3/hello/my-name)

### User Story 4 : 
As a Java 11 HelloServer user

I Want to define a 3 seconds timeoute for my queries (route : /v4/hello)

### User Story 5 : 
As a Java 11 HelloServer user

I Want to get server response as a stream of Strings (route : /v5/hello)

### User Story 6 : 
As a Java 11 HelloServer user

I Want to retreive the cookies of a customer (route : /hello/customer/cookie-store)

##  PART II : 
In this part, we will focus on :
- redirection
- BodyHandlers
- Async request

### User Story 7 : 
As a Java 11 HelloServer user

I Want to follow redirect 
AND avoid 302 response (GET - route : /hello-redir) 

### User Story 8 : 
As a Java 11 HelloServer user

I Want to post the order from Json file (POST - route : /file/orders) 

### User Story 9 : 
As a Java 11 HelloServer user

I Want to post the order from Input Stream (POST - route : /stream/orders) 

### User Story 10 : 
As a Java 11 HelloServer user

I Want to implement a blockingSearch (Sync) call of a keyword in a given URI

### User Story 11 : 
As a Java 11 HelloServer user

I Want to implement a non blockingSearch (Async) call of a keyword in a given URI

### User Story 12 : 
As a Java 11 HelloServer user

I Want to implement a non blockingSearch (Async) and parallel call of a keyword in a list of URI




