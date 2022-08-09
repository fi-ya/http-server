[![fi-ya](https://circleci.com/gh/fi-ya/http-server.svg?style=shield)](https://circleci.com/docs/)
[![codecov](https://codecov.io/gh/fi-ya/http-server/branch/main/graph/badge.svg?token=QS61AKNVG9)](https://codecov.io/gh/fi-ya/http-server)
# HTTP Server
This is an 8th Light apprenticeship project.
## Project requirements
- [x] To build an HTTP server which includes routes, requests, and responses. 
- [x] The routes must be customizable with a URL, a verb, and an action to take when the route is called. 
- [x] The server will be able to return structured data in formats such as HTML, JSON, and XML, each with the appropriate MIME type.
- [x] The server is to serve content from a directory of files.
## Prerequisites
1. Run `java --version` and ignore next step if java 17.0.4 already installed. 
2. Install [Java (17.0.4)](https://www.oracle.com/java/technologies/downloads/#java17)
## Run project
The following steps will allow you to run the server in your terminal.
1. Clone repository and htt_server_spec submodule by running `git clone --recurse-submodules git@github.com:fi-ya/http-server.git`
2. `cd` into the project folder `http-server` and run shell script with `bash setup.sh`
3. Build the application and run unit test using this command `./gradlew clean build`
4. Start http server with this command `gradlew run`
5. Send client request to request URL listed in [project features](#project-features)
   - using netcat by running `nc localhost 5000` followed by the client request, ie. `GET /simple_get HTTP/1.1` and wait for server response.
   - using [postman](https://www.postman.com/) 
   - using `cURL` in the terminal
6. Stop server with this command `CTRL + c`
<p align="right">(<a href="#top">back to top</a>)</p>

## Project features
| **Request**     |                       |           | **Response**               |                                            |             |
|:-----------:|:---------------------:|:---------:|:----------------------:|:------------------------------------------:|:-----------:|
| **Http Method** |      **Target**       | **Body**      | **Status Code/Text**       |                **Headers**                 |  **Body**   |
| GET         |      /simple_get      |           | 200 OK                 |                                            |             |
| HEAD        |      /simple_get      |           | 200 OK                 |                                            |             |
| HEAD        |     /head_request     |           | 200 OK                 |                                            |             |
| GET         |     /head_request     |           | 405 Method Not Allowed |            Allow: HEAD, OPTIONS            |             |
| GET         |    /page_not_exist    |           | 404 Not Found          |                                            |             |
| GET         | /simple_get_with_body |           | 200 OK                 |                                            | Hello world |
| OPTIONS     |    /method_options    |           | 200 OK                 |         Allow: GET, HEAD, OPTIONS          |             |
| OPTIONS     |   /method_options2    |           | 200 OK                 |    Allow: GET, HEAD, OPTIONS, PUT, POST    |             |
| POST        |      /echo_body       | some body | 200 OK                 |                                            |  some body  |
| GET         |       /redirect       |           | 301 Moved Permanently  | Location: http://127.0.0.1:5000/simple_get |             |
| GET         |    /text_response     |           | 200 OK  |          Content-Type: text/plain          |  text response   |
| GET         |    /html_response     |           | 200 OK  |          Content-Type: text/html           |  `<html><body><p>HTML Response</p></body></html>`   |
| GET         |    /json_response     |           | 200 OK  |       Content-Type: application/json;charset=utf-8        |  `{ key1: 'value1', key2: 'value2' }`   |
| GET         |     /xml_response     |           | 200 OK  |        Content-Type: application/xml;charset=utf-8        |  `<note><body>XML Response</body></note>`   |
## Run tests
You can find all tests in `src/test/java` and run the test suite using either of the following steps.
- Open a test file and either click on green play button next to the class name or run `Ctrl + Shift + R`
- Terminal: cd into project folder and run the command `./gradlew test` or for more information run `./gradlew test --info`

### Running acceptance tests
- Start the HTTP server on port 5000, by following the `Run project` steps above.
- Once your server is running, `cd` into http_server_spec directory `src/test/http_server_spec`.
- Following the steps in the [README](https://github.com/8thlight/http_server_spec#installation) run `bundle install` to install the required dependencies.
- Use one of the following commands to run acceptance test suite in the terminal:
  - Run full test suite via `rake test`
  - Run the tests from a specific section of the features: 
    - `rake test:f1 # Run all of the tests in 01_getting_started`
    - `rake test:f2 # Run all of the tests in 02_structured_data`
    - `rake test:f3 # Run all of the tests in 03_file_server`
    - `rake test:f4 # Run all of the tests in 04_todo_list`
 For further information check [acceptance README](https://github.com/8thlight/http_server_spec)
<p align="right">(<a href="#top">back to top</a>)</p>

## Functional Requirements
A user should be able to interact with the HTTP server as follows:
- [x] When a client sends a properly formatted request to the server, the server sends an appropriate response back to the client. 
- [x] A client can send different HTTP requests to the server and get the appropriate response back each time. 
- [x] Different clients can send messages to server and get back their proper responses. 
- [x] The server should be able to handle 200, 300, and 400-level responses. Not every response code needs to be complete, but there should be a few representative response codes implemented for each level.

The HTTP server should have the following behavior:
- [x] It should be able to return structured data in a variety of formats including HTML, JSON, and XML.
- [x] The HTTP response should have the appropriate content type headers to specify what type of data is being returned.
- [x] A user should be able to retrieve a variety of files from an index directory via their HTTP server. The HTTP responses should be properly formatted with the correct MIME types for each file.
<p align="right">(<a href="#top">back to top</a>)</p>

## Implementation Requirements
- [x] The server should establish a socket connection with the client using a low-level socket library. The goal of this exercise is to work with sockets directly.
- [x] The server should accept and return streams of data rather than raw strings.
- [x] Although not strictly speaking necessary, the HTTP server is a good time to introduce statically typed languages like Java, C#, or Swift.
- [x] The HTTP server should be covered by a robust suite of unit tests.
- [x] The HTTP server should pass all the tests covered in [01_getting_started HTTP Server Spec](https://github.com/8thlight/http_server_spec/tree/master/features/01_getting_started).
- [x] The HTTP server should pass all the tests covered in [02_structured_data HTTP Server Spec](https://github.com/8thlight/http_server_spec/blob/master/features/02_structured_data/structured_data.feature).
- [x] The HTTP server should pass all of the tests covered in [03_file_server HTTP Server Spec](https://github.com/8thlight/http_server_spec/tree/master/features/03_file_server).
<p align="right">(<a href="#top">back to top</a>)</p>
