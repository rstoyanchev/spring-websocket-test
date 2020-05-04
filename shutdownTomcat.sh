
if [ -z "$TOMCAT_HOME" ]; then
    echo -e "\n\nPlease set TOMCAT_HOME\n\n"
    exit 1
fi

$TOMCAT_HOME/bin/shutdown.sh
