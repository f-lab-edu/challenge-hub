FROM openjdk:17
COPY . /app
WORKDIR /app
RUN ./gradlew build -x test  # 빌드
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/build/libs/challenge-hub-0.0.1-SNAPSHOT.jar"]
