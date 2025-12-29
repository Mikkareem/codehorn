cd backend || echo 'Already in backend folder'

./gradlew clean build -x test

CONSUL_ENABLED=false
APP_VERSION=1.0.0

set -e

if [[ ! -d logs ]]; then
  mkdir logs && echo 'Created logs folder'
fi

nohup java -Dserver.port=8080 -Dspring.cloud.consul.enabled=$CONSUL_ENABLED -jar gateway-service/build/libs/gateway-service-$APP_VERSION.jar > logs/gateway.log 2>&1 &
nohup java -Dserver.port=8081 -Dspring.cloud.consul.enabled=$CONSUL_ENABLED -jar problems-service/build/libs/problems-service-$APP_VERSION.jar > logs/problems.log 2>&1 &
#nohup java -Dserver.port=8082 -Dspring.cloud.consul.enabled=$CONSUL_ENABLED -jar submissions-service/build/libs/submissions-service-$APP_VERSION.jar > logs/submissions.log 2>&1 &
nohup java -Dserver.port=8083 -Dspring.cloud.consul.enabled=$CONSUL_ENABLED -jar code-execution-service/build/libs/code-execution-service-$APP_VERSION.jar > logs/code-exec.log 2>&1 &
nohup java -Dserver.port=8084 -Dspring.cloud.consul.enabled=$CONSUL_ENABLED -jar c-execution-service/build/libs/c-execution-service-$APP_VERSION.jar > logs/c-exec.log 2>&1 &
nohup java -Dserver.port=8085 -Dspring.cloud.consul.enabled=$CONSUL_ENABLED -jar cpp-execution-service/build/libs/cpp-execution-service-$APP_VERSION.jar > logs/cpp-exec.log 2>&1 &
nohup java -Dserver.port=8086 -Dspring.cloud.consul.enabled=$CONSUL_ENABLED -jar java-execution-service/build/libs/java-execution-service-$APP_VERSION.jar > logs/java-exec.log 2>&1 &
nohup java -Dserver.port=8087 -Dspring.cloud.consul.enabled=$CONSUL_ENABLED -jar javascript-execution-service/build/libs/javascript-execution-service-$APP_VERSION.jar > logs/javascript-exec.log 2>&1 &
nohup java -Dserver.port=8088 -Dspring.cloud.consul.enabled=$CONSUL_ENABLED -jar python-execution-service/build/libs/python-execution-service-$APP_VERSION.jar > logs/python-exec.log 2>&1 &

echo 'All Applications started successfully'

sleep 5