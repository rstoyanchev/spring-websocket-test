
## Overview

Demonstrates Spring WebSocket and SockJS support in Spring Framework 4.0, currently in development.

**NOTE:** You'll most likely also want to check out the [Stock Portfolio](https://github.com/rstoyanchev/spring-websocket-portfolio) sample, which demonstrates the use of a higher-level messsaging protocol over WebSocket.

### Tomcat

At present Tomcat 8 is [available as snapshots](https://repository.apache.org/content/repositories/snapshots/org/apache/tomcat/tomcat/8.0-SNAPSHOT/) (alpha release is forthcoming).

### Jetty 9

The easiest way to run on Jetty 9.0.4:

    mvn jetty:run

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

Watch the logs:

    cd <unzip_dir>/glassfish4
    less `glassfish/domains/domain1/logs/server.log`


