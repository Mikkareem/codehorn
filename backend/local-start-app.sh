cd backend || echo 'Already in backend folder'

./gradlew clean build -x test

CONSUL_ENABLED=true
APP_VERSION=1.0.0

set -e

if [[ ! -d logs ]]; then
  mkdir logs && echo 'Created logs folder'
fi

nohup java -Dserver.port=8080 -Dspring.cloud.consul.enabled=$CONSUL_ENABLED -jar gateway-service/build/libs/gateway-service-$APP_VERSION.jar > logs/app1.log 2>&1 &
nohup java -Dserver.port=8081 -Dspring.cloud.consul.enabled=$CONSUL_ENABLED -jar problems-service/build/libs/problems-service-$APP_VERSION.jar > logs/app2.log 2>&1 &
#nohup java -Dserver.port=8082 -Dspring.cloud.consul.enabled=$CONSUL_ENABLED -jar submissions-service/build/libs/submissions-service-$APP_VERSION.jar > logs/app3.log 2>&1 &
nohup java -Dserver.port=8083 -Dspring.cloud.consul.enabled=$CONSUL_ENABLED -jar code-execution-service/build/libs/code-execution-service-$APP_VERSION.jar > logs/app4.log 2>&1 &
nohup java -Dserver.port=8084 -Dspring.cloud.consul.enabled=$CONSUL_ENABLED -jar java-execution-service/build/libs/java-execution-service-$APP_VERSION.jar > logs/app5.log 2>&1 &

echo 'All Applications started successfully'

sleep 5