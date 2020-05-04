## Overview

Demonstrates Spring WebSocket and SockJS support in the Spring Framework.

**IMPORTANT:** This branch contains examples of using the JSR-356 `Endpoint` and `@ServerEndpoint`. The `master` branch contains examples using the Spring's `WebSocketHandler` including with SockJS fallback options.

**NOTE:** Also check out the [Stock Portfolio](https://github.com/rstoyanchev/spring-websocket-portfolio) sample, which demonstrates the use of a higher-level messaging over WebSocket.

### Tomcat

Check the [Tomcat home page](http://tomcat.apache.org/) for the latest Tomcat release.

For Tomcat, set `TOMCAT_HOME` as an environment variable and use [deployTomcat.sh](https://github.com/rstoyanchev/spring-websocket-test/blob/master/deployTomcat.sh) and [shutdownTomcat.sh](https://github.com/rstoyanchev/spring-websocket-test/blob/master/shutdownTomcat.sh) in this directory.

Open a browser and go to <http://localhost:8080/spring-websocket-test/index.html>

### Jetty

The easiest way to run on Jetty:

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


