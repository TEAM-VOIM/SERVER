FROM openjdk:21-jdk-slim
WORKDIR /app
RUN ln -sf /usr/share/zoneinfo/Asia/Seoul /etc/localtime && \
    echo "Asia/Seoul" > /etc/timezone
COPY build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]