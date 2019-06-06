# Laboratory Work Nr. 3

### Objectives 
 - Study the HTTP protocol;
 - Understand the basic concepts used in HTTP communication;
 - Learn the HTTP methods.
 
### Task
 * Implement an HTTP client that would correctly use the main HTTP methods. Use the www.httpbin.org as a server.
 
### Theory
Hypertext Transfer Protocol (HTTP) is an application-layer protocol for transmitting hypermedia documents, such as HTML. It was designed
for communication between web browsers and web servers, but it can also be used for other purposes. HTTP follows a classical client-server
model, with a client opening a connection to make a request, then waiting until it receives a response. HTTP is a stateless protocol,
meaning that the server does not keep any data (state) between two requests. Though often based on a TCP/IP layer, it can be used on any
reliable transport layer; that is, a protocol that doesn't lose messages silently, such as UDP.

HTTP defines a set of request methods to indicate the desired action to be performed for a given resource. Although they can also be nouns,
these request methods are sometimes referred as HTTP verbs. Each of them implements a different semantic, but some common features are
shared by a group of them: e.g. a request method can be safe, idempotent, or cacheable.

HTTP main methods which I have use in this laboratory work:
 - GET - equests a representation of the specified resource. Requests using GET should only retrieve data.
 - POST - is used to submit an entity to the specified resource, often causing a change in state or side effects on the server.
 - PUT - replaces all current representations of the target resource with the request payload.
 - DELETE - deletes the specified resource.
 - PATCH - is used to apply partial modifications to a resource.
 
 ### Implementation
In this laboratory work I performed different HTTP requests in Java using the built-in Java class _HttpUrlConnection_. The _HttpUrlConnection_
allows us to perform basic HTTP requests without the use of any additional libraries. All the classes that are needed are contained in the
_java.net_ package.
 
For each HTTP request I implemented I had created a separate method with a corresponding name. The workflow for in each method is kind of the
same, the only thing differs is the request type and the request parameters.
 
In order to create a request I created a _HttpUrlConnection_ instance by using the _openConnection()_ method of the URL class. This method
only creates a connection, but does not establish the connection yet. Next I set the request type by passing the corresponding value as an
argument to the _setRequestMethod_ method.
 
If there are parameters to add to a request, the _doOutput_ property should be set to true. The parameters should be written as a string
of the following form: _param1=value&param2=value_. This string should be written to the _OutputStream_ of the _HttpUrlConnection_ instance.
In order to facilitate the above string formation I have used the _ParameterStringBuilder_ class. It contains a method _getParamsString_
that transforms a _Map_ to a string of the required form.
 
Adding headers to a request can be achieved by using the _setRequestProperty()_ method.
 
When the request is ready to be executed, one of the several methods can be called: _getResponseCode()_, _connect()_, _getInputStream_ or
_getOutputStream_. I used the first mentioned one. As its name suggests, it returns the response code which describes the status of the
sent request.
 
Reading the response of the request can be done by parsing the _InputStream_ of _HttpUrlConnection_ instance. If the reqest fails, trying
to read the _InputStream_ won't work. Instead, the _ErrorStream_ can be consumed. Comparing the HTTP status code can suggest us what stream
to use. I placed the response in the _content_ string.
 
Using the _disconnect()_ method can help us in closing the connection.

An important thing I want to mention is that the approach I used to create and execute a request does not allow me to perform all of the
above mentioned for a _PACTH_ request. Because of that I have used another approach which implies use of _httpcomponents_ dependencies.
They can be added manually to the project or by using an automation tool such as Maven. Generally speaking both workflows pass in the same way, no matter what approach is used: first the connection is created, then additional parameters are added, after that the request is executed and finally the response is read.

Because of the possibility of getting an image from that server I have decided to create one more GET request. But this one differs from previous one, here I used the _read()_ method of the _ImageIO_ class. I passed an URL to read from as the function's argument. In order to view the image I used a JFrame object.

Below are presented responses I have got to the GET and POST requests and the returned image from the server.

![Variant](https://github.com/denisdumitras/NetworkProgramming/tree/master/Lab.3/Screens/GET.PNG)

![Variant](https://github.com/denisdumitras/NetworkProgramming/tree/master/Lab.3/Screens/POST.PNG)

![Variant](https://github.com/denisdumitras/NetworkProgramming/tree/master/Lab.3/Screens/Image.PNG)

### Conclusion
In this laboratory work I have learned what the HTTP protocol is and how it can be used while trying to communicate through the internet. Also, I have developed a HttpClient application which deals with the main HTTP methods. There are several possibilites of creating and executing requests in Java. No matter what approach will be used, the workflow remains the same.
