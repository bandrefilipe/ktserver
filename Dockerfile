FROM openjdk:11-jre-slim
LABEL author="André Filipe Barranco"
LABEL email="b.andrefilipe@gmail.com"
COPY build/libs/*.jar /app/
WORKDIR /app
ENTRYPOINT java -jar *.jar
EXPOSE 8080
