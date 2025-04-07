# repo content

web application classes are in `org.example.mvc`

embedded tomcat server classes are in `org.example.tomcat`



# how to run

place yourself in directory with pom, then

```shell
mvn clean package
```
windows:
```powershell
java -classpath "target/mvc-0.0.1/WEB-INF/lib/*;target/mvc-0.0.1/WEB-INF/classes/." org.example.tomcat.Server
```
linux:
```shell
java -classpath "target/mvc-0.0.1/WEB-INF/lib/*:target/mvc-0.0.1/WEB-INF/classes/." org.example.tomcat.Server
```

stop the server with `Ctrl+C`

# state of embedded tomcat server

currently tomcat web server is configured to start on port `8080` and uses `http` protocol

access local server with:

```
http://localhost:8080
```

tomcat server implementation uses empty context string `""`, which triggers `welcome-page` servlet `GET` response on the address above 

# todo

1. remove `ru.ivt.mvc` example
2. fix `request.getRequestDispatcher("/add.jsp")` returning `null`
3. add `welcome.jsp` page, on which database stats should be displayed (number of students and courses, modification count, requests count etc.)
4. **continuously update todo and roadmap**

    Check the links for imagination 
    
    + https://github.com/s4kibs4mi/java-developer-roadmap
    + https://github.com/akullpp/awesome-java
    + https://github.com/sindresorhus/awesome