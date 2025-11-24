# Superhero REST API

A REST API for managing superhero data, built with Spring Boot and Clean Architecture.

## 🚀 Quick Start

```bash
# Start MySQL & Kafka
make up

# Run application
make run

# Test
make test
```

## 📋 Tech Stack

- Java 21
- Spring Boot 4.0
- MySQL 8.0 (JPA/Hibernate)
- Apache Kafka
- Docker & Docker Compose

## 🏗️ Architecture

Clean Architecture with layer separation:

- **Core**: Business logic (domain, usecases, ports)
- **Data Provider**: MySQL repository, Kafka publisher
- **API**: REST controllers, DTOs, exception handling
- **Worker**: Kafka event consumers

## 🔌 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/superheroes` | Create superhero |
| GET | `/api/v1/superheroes` | List all |
| GET | `/api/v1/superheroes/{id}` | Get by ID |
| GET | `/api/v1/superheroes?name=X` | Search by name |
| GET | `/api/v1/superheroes?publisher=X` | Search by publisher |
| GET | `/api/v1/superheroes?alignment=X` | Search by alignment |
| PUT | `/api/v1/superheroes/{id}` | Update |
| DELETE | `/api/v1/superheroes/{id}` | Delete |
| GET | `/api/v1/superheroes/statistics` | Get stats |

## 📝 Example Request

```bash
curl -X POST http://localhost:8080/api/v1/superheroes \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Spider-Man",
    "fullName": "Peter Parker",
    "intelligence": "90",
    "strength": "55",
    "alignment": "good",
    "publisher": "Marvel Comics"
  }'
```

## 🐳 Docker Setup

### Services

- **MySQL** (3306): Database
- **Kafka** (9092): Event streaming
- **Zookeeper** (2181): Kafka coordination

### Commands

```bash
# Start infrastructure
make up                 # or: docker-compose up -d

# View logs
make logs               # or: docker-compose logs -f

# Stop services
make down               # or: docker-compose down

# Clean everything
make reset              # or: docker-compose down -v
```

### MySQL Access

```bash
# Connect to MySQL
docker exec -it superhero-mysql mysql -uroot -proot superhero_db

# Show tables
docker exec -it superhero-mysql mysql -uroot -proot superhero_db -e "SHOW TABLES;"

# View data
docker exec -it superhero-mysql mysql -uroot -proot superhero_db \
  -e "SELECT id, name, alignment FROM superheroes LIMIT 10;"
```

### Kafka Access

```bash
# List topics
docker exec -it superhero-kafka kafka-topics --bootstrap-server localhost:9092 --list

# Consume events
docker exec -it superhero-kafka kafka-console-consumer \
  --bootstrap-server localhost:9092 \
  --topic superhero-events \
  --from-beginning
```

## ⚙️ Configuration

Edit `application.properties`:

```properties
# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/superhero_db
spring.datasource.username=root
spring.datasource.password=root

# Kafka
spring.kafka.bootstrap-servers=localhost:9092

# JPA
spring.jpa.hibernate.ddl-auto=update
```

## 🧪 Development

### Local Development

```bash
# Start infrastructure
make up

# Run app (with hot reload)
make run

# Run tests (uses H2 in-memory DB)
make test

# Build
make build
```

### Full Stack in Docker

```bash
# Build and run everything
docker-compose -f docker-compose.full.yml up -d --build

# View app logs
docker-compose -f docker-compose.full.yml logs -f app

# Stop
docker-compose -f docker-compose.full.yml down
```

## 📊 Makefile Commands

```bash
make help     # Show all commands
make build    # Build application
make test     # Run tests
make run      # Run application locally
make clean    # Clean build
make up       # Start MySQL & Kafka only
make full     # Start everything (app + infrastructure)
make down     # Stop services
make logs     # View logs
make reset    # Clean everything
```

## 🔥 Features

- ✅ Clean Architecture
- ✅ RESTful API design
- ✅ MySQL with JPA/Hibernate
- ✅ Event-driven with Kafka
- ✅ Docker Compose setup
- ✅ Caching support
- ✅ Validation & error handling
- ✅ H2 for testing

## 📚 Events

Kafka topic `superhero-events` publishes:
- Superhero created
- Superhero updated
- Superhero deleted

## 🛠️ Troubleshooting

### Port conflicts

```bash
# Check what's using ports
lsof -i :3306   # MySQL
lsof -i :8080   # Application
```

### Service issues

```bash
# Check service health
docker-compose ps

# View logs
docker-compose logs mysql

# Restart
docker-compose restart
```

### Clean start

```bash
make reset
make up
make run
```

## 📖 Additional Info

- **Database**: Auto-creates tables via Hibernate DDL
- **ID Type**: Auto-increment Long (mapped to String in API)
- **Testing**: H2 in-memory database (no Docker needed)
- **Caching**: Simple cache for GET operations

---

**Ready?** Run `make up && make run` 🚀
