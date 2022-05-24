# HTTP Server
This is an 8th Light apprenticeship project.
## Project requirements
- To build an HTTP server which includes routes, requests, and responses. 
- The routes must be customizable with a URL, a verb, and an action to take when the route is called. 
## Run project
The following steps will allow you to run the server in your terminal.
1. Clone repository `git clone git@github.com:fi-ya/http-server.git`
2. Check to see if you have the correct version of Java (18.0.1.1) installed `java --version`
   - Choose to open build/run the server socket either with an IDE or in the terminal (CLI) using the following steps:
     - IntelliJ/VS Code: open `src/main/java/org.httpserver/Main` and pressing green `play` button next to `Main()` or click `Ctrl + Shift + R`.
     - Terminal: cd into project folder and run the command `./gradlew run`
<p align="right">(<a href="#top">back to top</a>)</p>
## Run tests
You can find all tests in `src/test/java` and run the test suite using either of the following steps.
- Open a test file and either click on green play button next to the class name or run `Ctrl + Shift + R`
- Terminal: cd into project folder and run the command `./gradlew test` or for more information run `./gradlew test --info`
<p align="right">(<a href="#top">back to top</a>)</p>
## Functional Requirements
A user should be able to interact with the HTTP server as follows:

- [] When a client sends a properly formatted request to the server, the server sends an appropriate response back to the client. 
- [] A client can send different HTTP requests to the server and get the appropriate response back each time. 
- [] Different clients can send messages to server and get back their proper responses. 
- [] The server should be able to handle 200, 300, and 400-level responses. Not every response code needs to be complete, but there should be a few representative response codes implemented for each level.
<p align="right">(<a href="#top">back to top</a>)</p>
## Implementation Requirements
- [] The server should establish a socket connection with the client using a low-level socket library. The goal of this exercise is to work with sockets directly.
- [] The server should accept and return streams of data rather than raw strings.
- [] Although not strictly speaking necessary, the HTTP server is a good time to introduce statically typed languages like Java, C#, or Swift.
- [] The HTTP server should be covered by a robust suite of unit tests.
- [] The HTTP server should pass all of the tests covered in 01_getting_started in the [HTTP Server Spec]9https://github.com/8thlight/http_server_spec).
<p align="right">(<a href="#top">back to top</a>)</p>
