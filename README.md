# Student Management System

A simple comprehensive web application built with Spring Boot MVC to demonstrate CRUD operations and best practices in modern Java web development.

## 📋 Table of Contents

- [About](#-about)
- [Features](#-features)
- [Technologies Used](#-technologies-used)
- [Prerequisites](#-prerequisites)
- [Getting Started](#-getting-started)
  - [Clone the Repository](#clone-the-repository)
  - [Environment Setup](#environment-setup)
  - [Running with Docker](#running-with-docker)
  - [Running Locally](#running-locally)
- [Contributing](#-contributing)
- [Author](#-author)
- [License](#-license)

## 🎯 About

This project is a practical demonstration of Spring Boot MVC (Model-View-Controller) architecture. It implements a student management system where users can perform full CRUD operations (Create, Read, Update, Delete) on student records.

The application comes pre-seeded with 3 dummy students for testing purposes and showcases modern Java development practices including containerization with Docker.

## ✨ Features

- **CRUD Operations**: Complete Create, Read, Update, and Delete functionality for student records
- **MVC Architecture**: Clean separation of concerns following the MVC design pattern
- **Responsive UI**: User-friendly web interface for managing students
- **Database Persistence**: PostgreSQL database for reliable data storage
- **Dockerized**: Fully containerized application for easy deployment
- **Health Checks**: Built-in health monitoring endpoints
- **Pre-seeded Data**: Sample students included for immediate testing

## 🛠 Technologies Used

### Backend

- **Java 21** - Latest LTS version
- **Spring Boot 3.x** - Application framework
  - Spring MVC - Web layer
  - Spring Data JPA - Data persistence
  - Spring Boot Actuator - Health monitoring
  - Thymeleaf - Template engine
- **PostgreSQL 15** - Database
- **Hibernate** - ORM framework
- **Maven** - Build tool

### DevOps

- **Docker** - Containerization
- **Docker Compose** - Multi-container orchestration

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

- **Docker** (version 20.10+)
- **Docker Compose** (version 2.0+)

OR for local development:

- **Java JDK 21**
- **Maven 3.9+**
- **PostgreSQL 15**

## 🚀 Getting Started

### Clone the Repository

```bash
git clone https://github.com/Black21396/student-management-system.git
cd student-management-system
```

### Environment Setup

1. Create a `.env` file in the project root:

```bash
cp .env.example .env
```

2. Edit `.env` with your configuration:

**Important**: Never commit `.env` to version control!

### Running with Docker

This is the easiest and recommended way to run the application:

1. **Start the application:**

```bash
docker-compose up -d
```

This will:

- Build the Spring Boot application
- Start PostgreSQL database
- Start the application on port 8090

2. **Access the application:**

Open your browser and navigate to:

```
http://localhost:8090/students
```

3. **View logs:**

```bash
# All services
docker-compose logs -f

# Spring application only
docker-compose logs -f spring-app

# Database only
docker-compose logs -f postgres-db
```

4. **Stop the application:**

```bash
docker-compose down
```

5. **Stop and remove volumes (clean restart):**

```bash
docker-compose down -v
```

### Running Locally

If you prefer to run without Docker:

1. **Start PostgreSQL:**

```bash
# Using Docker
docker run -d \
  --name postgres-db \
  -e POSTGRES_DB=student_management_system \
  -e POSTGRES_USER=fadi \
  -e POSTGRES_PASSWORD=fadi \
  -p 5432:5432 \
  postgres:15-alpine

# OR use your local PostgreSQL installation
```

2. **Configure application:**

Edit `src/main/resources/application.properties`:

3. **Build and run:**

```bash
# Build
mvn clean package

# Run
java -jar target/student-system.jar

# OR run directly with Maven
mvn spring-boot:run
```

4. **Access the application:**

```
http://localhost:8090/students
```

### Health Check

- `GET /actuator/health` - Application health status

## 🐳 Docker Configuration

### Dockerfile Features

- **Multi-stage build** - Optimized build process
- **Layer caching** - Fast rebuilds during development
- **Non-root user** - Enhanced security
- **JVM optimization** - Container-aware memory settings
- **Health checks** - Automatic health monitoring
- **Alpine-based** - Minimal image size (~200MB)

### Docker Compose Features

- **Health checks** - Ensures database is ready before starting app
- **Resource limits** - Prevents resource exhaustion
- **Named volumes** - Persistent database storage
- **Custom network** - Isolated network for services
- **Environment variables** - Flexible configuration

### Useful Docker Commands

```bash
# Build without cache
docker-compose build --no-cache

# View running containers
docker-compose ps

# Check health status
docker-compose ps
curl http://localhost:8090/actuator/health

# Access database
docker exec -it postgres-db psql -U fadi -d student_management_system

# View database data
docker exec -it postgres-db psql -U fadi -d student_management_system -c "SELECT * FROM students;"

# Rebuild and restart
docker-compose down
docker-compose up -d --build
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 👨‍💻 Author

**Fadi Salameh**

- Email: fadi.salameh@web.de
- GitHub: [@Black21396](https://github.com/Black21396)

## 📝 License

This project is created for educational purposes to demonstrate Spring Boot MVC concepts and best practices.

---

### Application won't start

1. **Check if ports are available:**

```bash
lsof -i :8090
lsof -i :5432
```

2. **Check Docker logs:**

```bash
docker-compose logs
```

3. **Database connection issues:**

- Verify PostgreSQL is running: `docker-compose ps`
- Check database credentials in `.env`
- Ensure database health check passes

### Database reset

To reset the database:

```bash
docker-compose down -v
docker-compose up -d
```

This will delete all data and recreate with seed data.

---

**⭐ If you found this project helpful, please give it a star!**
