    #!usr/bin/env bash
    # Environment Variables
    # HUB_HOST
    # BROWSER
    # MODULE



      echo "Checking if hub is ready - $hub_host"
      while [[ "$( curl -s http://$hub_host:4444/wd/hub/status | jq -r .value.ready )" != "true" ]];  do
      sleep 1
      done


     # start the java command
     java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* \
          -Dbrowser=$browser \
          -DrunType=$runType \
          -Dhub_host=$hub_host \
          org.testng.TestNG $suite