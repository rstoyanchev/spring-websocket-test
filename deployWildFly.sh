
if [ -z "$WILDFLY_HOME" ]; then
    echo -e "\n\nPlease set WILDFLY_HOME\n\n"
    exit 1
fi

mvn -DskipTests clean package

rm -rf $WILDFLY_HOME/standalone/deployments/spring-websocket-test*

cp target/spring-websocket-test.war $WILDFLY_HOME/standalone/deployments/

$WILDFLY_HOME/bin/standalone.sh
