set -v

mvn clean package

GLASSFISH4=/opt/javanet/glassfish4-b82/

$GLASSFISH4/bin/asadmin deploy --force=true target/spring-websocket-test.war
