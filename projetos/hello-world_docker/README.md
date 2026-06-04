# Hello World - Spring Boot + Docker

Aplicação simples em Spring Boot containerizada com Docker.

## Estrutura do Projeto

```
hello-world_docker/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/
│       │       ├── HelloWorldApplication.java
│       │       └── HelloController.java
│       └── resources/
│           └── application.properties
├── Dockerfile
├── pom.xml
└── README.md
```

## Executar Localmente

### Pré-requisitos
- Java 17+
- Maven 3.9+

### Passos

1. **Compilar e executar com Maven:**
```bash
mvn clean spring-boot:run
```
<br /> 

- Para criar o JAR use o comando: `mvn clean install` o resultado vai ser gerado na pasta: 
```
target/builds/hello-world-docker-1.0.0-SNAPSHOT.jar
```

- Para executar o JAR use o comando: 
```bash
java -jar target/builds/hello-world-docker-1.0.0-SNAPSHOT.jar
```

2. **Acessar a aplicação:**
- Text: http://localhost:8080/
- JSON: http://localhost:8080/api/hello

## Endpoints

| Método | Endpoint | Resposta |
|--------|----------|----------|
| GET | `/` | "Hello World! 🐳" (texto) |
| GET | `/api/hello` | JSON com message e technology |
