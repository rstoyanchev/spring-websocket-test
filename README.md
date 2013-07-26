
## Overview

Demonstrates Spring WebSocket and SockJS support in Spring Framework 4.0, currently in development.

**IMPORTANT:** This branch contains examples of using JSR-356 `Endpoint` and `@ServerEndpoint`. The `master` branch contains examples using Spring's `WebSocketHandler` including with SockJS fallback options. 

**NOTE:** You'll most likely also want to check out the [Stock Portfolio](https://github.com/rstoyanchev/spring-websocket-portfolio) sample, which demonstrates the use of a higher-level messsaging protocol over WebSocket.

### Tomcat

At present Tomcat 8 is available as snapshots only (alpha release is forthcoming). Spring Framework 4.0 M2 release has been tested with [this snapshot](https://repository.apache.org/content/repositories/snapshots/org/apache/tomcat/tomcat/8.0-SNAPSHOT/tomcat-8.0-20130430.222112-4.tar.gz). When using a more recent snapshot, you may also have to switch to Spring Framework 4.0.0.BUILD-SNAPSHOT.

After unzipping Tomcat 8, set `TOMCAT8_HOME` as an environment variable and use [deployTomcat8.sh](https://github.com/rstoyanchev/spring-websocket-test/blob/master/deployTomcat8.sh) and [shutdownTomcat8.sh](https://github.com/rstoyanchev/spring-websocket-test/blob/master/shutdownTomcat8.sh) in this directory.

Open a browser and go to <http://localhost:8080/spring-websocket-test/index.html>


### Jetty 9

The easiest way to run on Jetty 9.0.4:

    mvn jetty:run

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


