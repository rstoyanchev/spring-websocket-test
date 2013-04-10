set -v

mvn -DskipTests clean package

GLASSFISH4=/opt/javanet/glassfish4-b84/

$GLASSFISH4/bin/asadmin deploy --force=true target/spring-websocket-test.war
