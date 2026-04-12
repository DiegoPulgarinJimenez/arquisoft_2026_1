[![CI/CD Pipeline](https://github.com/DiegoPulgarinJimenez/arquisoft_2026_1/actions/workflows/build.yml/badge.svg)](https://github.com/DiegoPulgarinJimenez/arquisoft_2026_1/actions/workflows/build.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=DiegoPulgarinJimenez_arquisoft_2026_1&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=DiegoPulgarinJimenez_arquisoft_2026_1)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=DiegoPulgarinJimenez_arquisoft_2026_1&metric=bugs)](https://sonarcloud.io/summary/new_code?id=DiegoPulgarinJimenez_arquisoft_2026_1)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=DiegoPulgarinJimenez_arquisoft_2026_1&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=DiegoPulgarinJimenez_arquisoft_2026_1)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=DiegoPulgarinJimenez_arquisoft_2026_1&metric=coverage)](https://sonarcloud.io/summary/new_code?id=DiegoPulgarinJimenez_arquisoft_2026_1)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=DiegoPulgarinJimenez_arquisoft_2026_1&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=DiegoPulgarinJimenez_arquisoft_2026_1)

---
## Laboratorio CI/CD con Spring Boot, GitHub Actions y SonarCloud

### Descripción

Este laboratorio tiene como objetivo implementar un entorno colaborativo de integración continua (CI/CD) utilizando tecnologías modernas como Spring Boot, GitHub Actions y SonarCloud. Se busca automatizar el proceso de construcción, análisis de calidad de código y validación continua de un proyecto backend, siguiendo buenas prácticas de desarrollo colaborativo. 

Se implementa una App sencilla con las siguientes operaciones: 

- Get random nations
- Get random currencies
- Get random Aircraft
- Get application version
- health check

---
### Objetivos 

- Implementar un pipeline de integración continua
- Automatizar la construcción del proyecto
- Integrar análisis estático de código
- Gestionar el control de versiones de forma colaborativa
- Familiarizarse con herramientas de CI/CD

---
### Tecnologías Utilizadas

- Spring Boot
- Maven (gestión de dependencias)
- GitHub
- GitHub Actions
- SonarCloud
- GitKraken (cliente Git)
- IntelliJ IDEA

---
### Estructura del Proyecto 
```
.
├── .github
│   └── workflows
│       └── build.yml
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── src
├── main
│   ├── java
│   │   └── com
│   │       └── udea
│   │           └── lab22026
│   │               ├── DataController.java
│   │               └── Lab22026Application.java
│   └── resources
│       ├── application.properties
│       ├── static
│       └── templates
└── test
└── java
└── com
└── udea
└── lab22026
└── Lab22026ApplicationTests.java
```

---
### Configuración del pipeline CI

El pipeline está definido en: 

`.github/workflows/build.yml` 

### Flujo del pipeline 

Cada vez que se realiza un push o pull request:

1. Se clona el repositorio
2. Se configura el entorno Java
3. Se construye el proyecto con Maven
4. Se ejecutan pruebas (si existen)
5. Se analiza el código con SonarCloud

---
### Instalación y ejecución

- Para ejecutar el proyecto, utiliza el wrapper de Maven incluido:

Linux o Mac: 

`./mvnw spring-boot:run`

Windows: 

`$ mvnw spring-boot:run`

Esto descargará automáticamente todas las dependencias necesarias y levantará la aplicación.

Para compilar el proyecto y ejecutar las pruebas unitarias:

Linux o MacOS:

`./mvnw clean install`

Windows:

`$ mvnw clean install`

Generación de cobertura de pruebas

Para construir el proyecto (sin ejecutar pruebas) y preparar los reportes necesarios:

`./mvnw -B package -DskipTests --file pom.xml`

Notas importantes: 

En sistemas Linux y macOS se usa `./mvnw`

En Windows se usaría `mvnw.cmd`

El wrapper (mvnw) permite ejecutar Maven sin necesidad de tenerlo instalado globalmente

---
### Análisis con SonarCloud 

El proyecto está integrado con SonarCloud para:

- Evaluar calidad del código
- Detectar bugs y vulnerabilidades
- Analizar cobertura de pruebas 

---
### Problemas Encontrados

Durante el laboratorio se presentó la pérdida de archivos del proyecto debido a:

Manejo incorrecto de cambios en GitKraken
Archivos no versionados en Git

### Solución Aplicada 

Se recuperaron los archivos mediante::

- Historial local de IntelliJ IDEA (Local History)

---

### Lección Aprendida

- Realizar commits frecuentes
- Revisar cambios antes de confirmar
- No depender únicamente de herramientas gráficas 

---