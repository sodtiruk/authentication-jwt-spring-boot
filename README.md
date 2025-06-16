# Authentication JWT Spring Boot Project Base

This project is a reusable base for building authentication systems using JWT (JSON Web Token) with Spring Boot. It is designed to help you quickly start new applications that require secure authentication and user management.

## Features
- JWT-based authentication
- Spring Boot configuration
- Modular structure for easy extension
- Ready for Docker deployment

## Getting Started

### Prerequisites
- [Docker](https://www.docker.com/get-started) installed on your machine

### Running the Application with Docker

1. **Start the application:**
   ```sh
   docker compose up -d --build
   ```
   This command will build and start the application in detached mode.

2. **Stop the application:**
   ```sh
   docker compose down
   ```
   This command will stop and remove the containers.

### Customization
- Use this project as a base for your own authentication-enabled Spring Boot applications.
- Extend or modify modules as needed for your use case.

## Project Structure
- `src/main/java` - Main source code
- `src/main/resources` - Configuration and resources
- `docker-compose.yaml` - Docker Compose configuration
- `Dockerfile` - Docker build instructions

## License
This project is intended for reuse as a base for authentication systems. Customize and extend as needed for your own projects.
