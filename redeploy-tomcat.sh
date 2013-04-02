set -v

mvn clean package

TOMCAT=/home/rossen/dev/sources/apache/tomcat/trunk/output/build

rm -rf $TOMCAT/webapps/spring-websocket-test*
cp target/spring-websocket-test.war $TOMCAT/webapps/
$TOMCAT/bin/shutdown.sh
sleep 3
$TOMCAT/bin/startup.sh
