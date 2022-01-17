FROM openjdk:8u191-jre-alpine3.8

Run apk --update add --no-cache
RUN apk add curl jq

# Workspace

WORKDIR /usr/share/auto

# Add all .jar under target from host in this image
Add target/selenium-docker.jar         selenium-docker.jar
Add target/selenium-docker-tests.jar   selenium-docker-tests.jar
Add target/libs                        libs

# in case any other dependency like csv/xls/json add taht as well
#Add target/config.properties           src/test/resources/Properties/config.properties

# Add suite files
Add smoke.xml                          smoke.xml
Add regression.xml                     regression.xml
Add config.properties                  src/test/resources/Properties/config.properties

# Add health check script
Add healthcheck.sh                     healthcheck.sh

ENTRYPOINT sh healthcheck.sh

#BROWSER
#RUNTYPE
#HUB_HOST
#SUITE
#ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DBROWSER=$BROWSER -DRUNTYPE=$RUNTYPE -Dhub_host=$HUB_HOST org.testng.TestNG $SUITE
