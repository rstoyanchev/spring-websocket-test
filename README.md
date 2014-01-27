## Overview

Demonstrates Spring WebSocket and SockJS support in Spring Framework 4.0.

**IMPORTANT:** This branch contains examples of using the JSR-356 `Endpoint` and `@ServerEndpoint`. The `master` branch contains examples using the Spring's `WebSocketHandler` including with SockJS fallback options.

**NOTE:** Also check out the [Stock Portfolio](https://github.com/rstoyanchev/spring-websocket-portfolio) sample, which demonstrates the use of a higher-level messaging over WebSocket.

### Tomcat

You can use Tomcat 8 (currently RC10) or Tomcat 7.0.47+. Check the [Tomcat home page](http://tomcat.apache.org/) for the latest Tomcat 8 release.

For Tomcat 8, set `TOMCAT8_HOME` as an environment variable and use [deployTomcat8.sh](https://github.com/rstoyanchev/spring-websocket-test/blob/master/deployTomcat8.sh) and [shutdownTomcat8.sh](https://github.com/rstoyanchev/spring-websocket-test/blob/master/shutdownTomcat8.sh) in this directory.

For Tomcat 7, simply use `mvn tomcat7:run`.

Open a browser and go to <http://localhost:8080/spring-websocket-test/index.html>

### Jetty 9

The easiest way to run on Jetty 9.1.1:

    mvn jetty:run

Open a browser and go to <http://localhost:8080/spring-websocket-test/index.html>

**Note:** To deploy to an actual Jetty installation, add this to Jetty's `start.ini`:

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


