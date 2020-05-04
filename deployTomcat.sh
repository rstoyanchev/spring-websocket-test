
if [ -z "$TOMCAT_HOME" ]; then
    echo -e "\n\nPlease set TOMCAT_HOME\n\n"
    exit 1
fi

mvn -DskipTests clean package

rm -rf $TOMCAT_HOME/webapps/spring-websocket-test*

cp target/spring-websocket-test.war $TOMCAT_HOME/webapps/

$TOMCAT_HOME/bin/startup.sh
