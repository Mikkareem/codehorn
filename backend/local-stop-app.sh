APP_VERSION=1.0.0

set -e

pkill -f gateway-service/build/libs/gateway-service-$APP_VERSION.jar
pkill -f problems-service/build/libs/problems-service-$APP_VERSION.jar
#pkill -f submissions-service/build/libs/submissions-service-$APP_VERSION.jar
pkill -f code-execution-service/build/libs/code-execution-service-$APP_VERSION.jar
pkill -f java-execution-service/build/libs/java-execution-service-$APP_VERSION.jar

echo 'All Applications stopped successfully'