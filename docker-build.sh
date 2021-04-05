./gradlew bootJar
docker build -t bandrefilipe/$(basename "$PWD") .
