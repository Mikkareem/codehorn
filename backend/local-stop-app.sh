APP_VERSION=1.0.0

set -e

pkill -f gateway-service/build/libs/gateway-service-$APP_VERSION.jar
pkill -f problems-service/build/libs/problems-service-$APP_VERSION.jar

echo 'All Applications stopped successfully'