./gradlew clean build

CONSUL_ENABLED=false
APP_VERSION=1.0.0

set -e

if [[ ! -d logs ]]; then
  mkdir logs && echo 'Created logs folder'
fi

nohup java -Dserver.port=8080 -Dspring.cloud.consul.enabled=$CONSUL_ENABLED -jar gateway-service/build/libs/gateway-service-$APP_VERSION.jar > logs/app1.log 2>&1 &
nohup java -Dserver.port=8081 -Dspring.cloud.consul.enabled=$CONSUL_ENABLED -jar problems-service/build/libs/problems-service-$APP_VERSION.jar > logs/app2.log 2>&1 &

echo 'All Applications started successfully'

sleep 5