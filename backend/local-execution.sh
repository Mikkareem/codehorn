#cd java-execution-service && ../gradlew clean build && cd ..



#nohup java -Dserver.port=9090 -jar java-execution-service/build/libs/java-execution-service-1.0.0.jar > logs/app.log 2>&1 &