.PHONY: help build test run up down full logs clean reset

help:
	@echo "🦸 Superhero REST API"
	@echo ""
	@echo "Development:"
	@echo "  make build    - Build application"
	@echo "  make test     - Run tests"
	@echo "  make run      - Run application"
	@echo "  make clean    - Clean build"
	@echo ""
	@echo "Docker:"
	@echo "  make up       - Start MySQL & Kafka"
	@echo "  make full     - Start everything (app + infra)"
	@echo "  make down     - Stop services"
	@echo "  make logs     - View logs"
	@echo "  make reset    - Clean everything"

build:
	@./gradlew build -x test

test:
	@./gradlew test

run:
	@./gradlew bootRun

clean:
	@./gradlew clean

up:
	@docker-compose up -d
	@sleep 3
	@docker-compose ps

down:
	@docker-compose down

full:
	@docker-compose --profile full up -d --build
	@sleep 5
	@docker-compose ps

logs:
	@docker-compose logs -f

reset:
	@docker-compose down -v
	@./gradlew clean

