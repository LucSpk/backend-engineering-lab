# Estágio 1: Build - Baixa a imagem do Maven com JDK 17 para a fase de build
FROM maven:3.8.5-openjdk-17 AS build    

# Copia o código-fonte para o diretório /app/src
COPY src /app/src
# Copia o arquivo pom.xml para o diretório /app
COPY pom.xml /app

# Define o diretório de trabalho para /app (Muda para o diretório /app)
WORKDIR /app
# Executa o comando Maven para limpar e rodar a aplicação Spring Boot
RUN mvn clean install


# Estágio 2: Runtime - Baixa a imagem do OpenJDK 17 JRE para a fase de runtime (imagem menor)
FROM eclipse-temurin:17-jre-alpine

# Copia o JAR gerado para a fase de runtime (runtime é a fase final da imagem, onde a aplicação será executada)
COPY --from=build /app/target/builds/hello-world-docker-1.0.0-SNAPSHOT.jar /app/hello-world-docker.jar

# Define o diretório de trabalho para /app
WORKDIR /app

# Expõe a porta 8080 para acesso externo
EXPOSE 8080

# Comando para rodar a aplicação Spring Boot
CMD ["java", "-jar", "hello-world-docker.jar"]
