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



### User Story 1 : 
As a Java 11 HelloServer user

I Want to get greeting message from the server as a String (route : /v1/hello)

### User Story 2 : 
As a Java 11 HelloServer user

I Want to get greeting message from the server as a Json object (route : /v2/hello)

### User Story 3 : 
As a Java 11 HelloServer user

I Want to check "content-type" and "server" header (route : /v2/hello)

### User Story 5 : 
As a Java 11 HelloServer user

I Want to get personalized greeting message from the server (route : /v3/hello/my-name)

### User Story 6 : 
As a Java 11 HelloServer user

I Want to define a 3 seconds timeoute for my queries (route : /v4/hello)

### User Story 7 : 
As a Java 11 HelloServer user

I Want to get server response as a stream of Strings (route : /v5/hello)

### User Story 8 : 
As a Java 11 HelloServer user

I Want to retreive the cookies of a customer (route : /hello/customer/cookie-store)

