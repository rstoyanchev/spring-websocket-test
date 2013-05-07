set -v

mvn -DskipTests clean package

JETTY_HOME=/opt/eclipse/jetty-distribution-9.0.2.v20130417

rm -rf $JETTY_HOME/webapps/spring-websocket-test*

cp target/spring-websocket-test.war $JETTY_HOME/webapps/

JAVA_OPTIONS='-Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n' $JETTY_HOME/bin/jetty.sh restart
