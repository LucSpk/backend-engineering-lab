# Kafka - Configuração do Ambiente

## Pré-requisitos

Antes de iniciar, certifique-se de possuir:

* Docker instalado
* Docker Compose instalado

Verifique as versões:

```bash
docker --version
docker compose version
```

---

# Estrutura do projeto

O projeto deve possuir um arquivo `docker-compose.yml` com a seguinte configuração:

```yaml
services:
  kafka:
    image: apache/kafka:4.1.1
    container_name: kafka
    restart: unless-stopped
    ports:
      - "9092:9092"
    volumes:
      - kafka-data:/var/lib/kafka/data
    environment:
      CLUSTER_ID: "5L6g3nShT-eMCtK--X86sw"
      KAFKA_NODE_ID: 1
      KAFKA_PROCESS_ROLES: broker,controller
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_LISTENERS: PLAINTEXT://:9092,CONTROLLER://:9093
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,CONTROLLER:PLAINTEXT
      KAFKA_CONTROLLER_QUORUM_VOTERS: 1@kafka:9093
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1

  kafka-ui:
    image: provectuslabs/kafka-ui
    container_name: kafka-ui
    depends_on:
      - kafka
    restart: always
    ports:
      - "8001:8080"
    environment:
      - KAFKA_CLUSTERS_0_NAME=local
      - KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS=kafka:9092

volumes:
  kafka-data:
```

---

# Iniciando o ambiente

Na raiz do projeto execute:

```bash
docker compose up -d
```

---

# Verificando os containers

Confirme que os containers foram iniciados corretamente:

```bash
docker ps
```

Resultado esperado:

```text
CONTAINER ID   IMAGE                    NAMES
xxxxxxxxxxxx   apache/kafka:4.1.1       kafka
xxxxxxxxxxxx   provectuslabs/kafka-ui   kafka-ui
```

---

# Acessando o Kafka UI

Abra o navegador:

```
http://localhost:8001
```

O cluster `local` deverá aparecer automaticamente.

---

# Criando um tópico

Criar um tópico chamado `mensagens`:

```bash
docker exec kafka /opt/kafka/bin/kafka-topics.sh \
  --create \
  --topic mensagens \
  --bootstrap-server localhost:9092 \
  --partitions 3 \
  --replication-factor 1
```

Resultado esperado:

```text
Created topic mensagens.
```

---

# Listando os tópicos

```bash
docker exec kafka /opt/kafka/bin/kafka-topics.sh \
  --list \
  --bootstrap-server localhost:9092
```

Resultado esperado:

```text
mensagens
```

---

# Reiniciando o ambiente

Para parar os containers:

```bash
docker compose down
```

Para iniciá-los novamente:

```bash
docker compose up -d
```

Como o volume `kafka-data` é persistido, os tópicos e dados permanecem disponíveis.

---

# Removendo completamente o ambiente

Caso seja necessário apagar também os dados persistidos:

```bash
docker compose down -v
```

Na próxima inicialização será necessário criar novamente os tópicos desejados.

---

# Resumo dos principais comandos

## Subir o ambiente

```bash
docker compose up -d
```

## Verificar containers

```bash
docker ps
```

## Parar o ambiente

```bash
docker compose down
```

## Remover ambiente e volumes

```bash
docker compose down -v
```

## Criar tópico

```bash
docker exec kafka /opt/kafka/bin/kafka-topics.sh \
  --create \
  --topic mensagens \
  --bootstrap-server localhost:9092 \
  --partitions 3 \
  --replication-factor 1
```

## Listar tópicos

```bash
docker exec kafka /opt/kafka/bin/kafka-topics.sh \
  --list \
  --bootstrap-server localhost:9092
```
