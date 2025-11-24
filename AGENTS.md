# Agent Guidelines

## Overview
This project is a Spring Boot microservice for managing superhero data. It uses Java 21, Gradle, MySQL, Kafka and follows Clean Architecture, clearly separating core, dataprovider, api and worker layers.

## Programming Language: Java

**Best Practices:**
- Always follow SOLID principles and Clean Code
- Clean Architecture: separate business rules (core) from implementation details (dataprovider, api, worker)
- Never add comments
- Use Lombok annotations (only what will be used)
- DTOs should be records
- Follow Java naming conventions (camelCase for methods, PascalCase for classes)
- Use appropriate access modifiers (private, protected, public)
- Implement proper exception handling with try-catch blocks

**Code Standards**
- Use Java 21 features
- Follow Spring Boot best practices
- Organize code by domain and responsibility (core, dataprovider, api, worker)
- Use dependency injection for service wiring
- Prefer constructor injection
- Use Lombok to reduce boilerplate
- Always follow SOLID principles and Clean Code

## Project Structure
- `src/main/java`: Application source code
    - `core`: Business logic and domain
    - `dataprovider`: Database integrations and external clients
    - `api`: API endpoints
    - `worker`: Kafka consumers
- `src/test/java`: Unit tests (JUnit, Mockito)
- `src/test/resources`: Configuration files for tests

## Testing Patterns
- Unit tests: in `src/test/java`, use JUnit 5 and Mockito
- Test names: suffix `*Test.java`, methods should describe behavior clearly and objectively
- Use `@Test` for test methods
- Use H2 embedded database for repository tests
- Use mocked Kafka for consumer tests

## Unit Test Recommendations
- Always use `@ExtendWith(MockitoExtension.class)` at the beginning of test class
- Always use `@Mock` for simulated dependencies
- Always use `@InjectMocks` for the class under test
- Do not add comments in unit tests
- Test method names in English, following pattern: `should<ExpectedBehavior>When<Condition>()`
- Test file name should be `<ClassToBeTested>Test.java`

## Logging

- Use structured logging with proper context
- Always use structured context: `kv("key", value)`
- Levels: ERROR for errors, WARN for warnings, INFO for flow, DEBUG for details
- Avoid excessive logging to reduce costs
- Have relevant logs that tell a story to facilitate debugging

## Commits & Pull Requests
- Write clear and descriptive commit messages
- Ensure all tests are passing before pushing changes
- Run code style checks (`checkstyle`)
- Add/modify tests for new features or bug fixes

## Framework: Spring Boot
- Consult Context7 for more details about libraries and frameworks to use when possible

## Code Style: Microservices Patterns

**Microservices Pattern Guidelines:**
- Follow microservices principles and best practices
- Maintain consistency with chosen architectural style
- Apply appropriate patterns and practices for this approach

## AI Code Generation Preferences

When generating code, do the following:

- Generate complete and functional code examples with proper imports
- Include inline comments for complex logic and business rules
- Follow established patterns and conventions in this project
- Suggest improvements and alternative approaches when relevant
- Consider performance, security and maintainability
- Include error handling and edge case considerations
- Generate appropriate unit tests when creating new functions
- Follow accessibility best practices for UI components
- Use semantic HTML and appropriate ARIA attributes when applicable

