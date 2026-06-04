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
├── .dockerignore
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

2. **Acessar a aplicação:**
- Text: http://localhost:8080/
- JSON: http://localhost:8080/api/hello

## Executar com Docker

### Build da imagem:
```bash
docker build -t hello-world-docker .
```

### Executar o container:
```bash
docker run -p 8080:8080 hello-world-docker
```

### Acessar a aplicação:
- Text: http://localhost:8080/
- JSON: http://localhost:8080/api/hello

### Parar o container:
```bash
docker stop <container_id>
```

## Endpoints

| Método | Endpoint | Resposta |
|--------|----------|----------|
| GET | `/` | "Hello World! 🐳" (texto) |
| GET | `/api/hello` | JSON com message e technology |

## Dockerfile Multi-stage

O Dockerfile usa a estratégia multi-stage para:
- **Builder**: Compila a aplicação com Maven
- **Runtime**: Executa a aplicação com JRE (imagem menor)

Vantagem: Reduz o tamanho final da imagem removendo as ferramentas de build.
