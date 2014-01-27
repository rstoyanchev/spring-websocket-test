## Overview

Demonstrates Spring WebSocket and SockJS support in Spring Framework 4.0.

**IMPORTANT:** The `master` branch contains examples using Spring's `WebSocketHandler` including with SockJS fallback options. The `endpoint` branch contains examples of using JSR-356 `Endpoint` and `@ServerEndpoint`.

**NOTE:** Also check out the [Stock Portfolio](https://github.com/rstoyanchev/spring-websocket-portfolio) sample that demonstrates the use of a higher-level messaging over WebSocket.

### Tomcat

You can use Tomcat 8 (currently RC10) or Tomcat 7.0.47+. Check the [Tomcat download page](http://tomcat.apache.org/download-80.cgi) for the latest Tomcat 8 release.

For Tomcat 8, set `TOMCAT8_HOME` as an environment variable and use [deployTomcat8.sh](https://github.com/rstoyanchev/spring-websocket-test/blob/master/deployTomcat8.sh) and [shutdownTomcat8.sh](https://github.com/rstoyanchev/spring-websocket-test/blob/master/shutdownTomcat8.sh) in this directory.

For Tomcat 7, simply use `mvn tomcat7:run`.

Open a browser and go to <http://localhost:8080/spring-websocket-test/index.html>

### Jetty 9

The easiest way to run on Jetty 9.1.1 is `mvn jetty:run`.

Open a browser and go to <http://localhost:8080/spring-websocket-test/index.html>

**Note:** To deploy to a Jetty installation, add this to Jetty's `start.ini`:

    OPTIONS=plus
    etc/jetty-plus.xml
    OPTIONS=annotations
    etc/jetty-annotations.xml

### Glassfish

Glassfish 4 provides JSR-356 support.

Download Glassfish 4 and unzip the downloaded distribution.

Start the server:

    cd <unzip_dir>/glassfish4
    bin/asadmin start-domain

Deploy the WAR file using the script in this directory.

Open a browser and go to <http://localhost:8080/spring-websocket-test/index.html>

Watch the logs:

    cd <unzip_dir>/glassfish4
    less `glassfish/domains/domain1/logs/server.log`


