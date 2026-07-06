# 🎮 Biblioteca de Videojuegos

Una aplicación web para gestionar tu colección personal de videojuegos, plataformas y géneros. Desarrollada con **Spring Boot** y **Thymeleaf**, con almacenamiento en **PostgreSQL**.

## 📋 Descripción

**Biblioteca de Videojuegos** es una plataforma integral para organizar y administrar una colección de juegos. Permite gestionar múltiples plataformas (consolas, PC, etc.), catalogar videojuegos con información detallada y clasificarlos por géneros.

### Características principales

- 🎮 **Gestión de Videojuegos**: Crear, editar, listar y eliminar videojuegos con detalles como título, calificación, favoritos e imagen
- 🖥️ **Gestión de Plataformas**: Administrar consolas y plataformas (nombre, fabricante, año de lanzamiento)
- 🏷️ **Gestión de Géneros**: Organizar juegos por géneros con descripción
- 📊 **Relaciones entre entidades**: Los juegos están vinculados a plataformas y géneros
- 🎨 **Interfaz moderna**: Diseño responsive con Thymeleaf
- 📄 **Exportación PDF**: Capacidad de generar reportes en PDF con OpenPDF

## 🛠️ Tecnologías

- **Framework**: Spring Boot 4.1.0
- **Lenguaje**: Java 25
- **BD**: PostgreSQL
- **ORM**: JPA/Hibernate
- **Plantillas**: Thymeleaf
- **Motor de PDF**: OpenPDF 1.3.30
- **Utilidades**: Lombok (reducción de boilerplate)
- **Build Tool**: Maven

## 📦 Requisitos previos

- **Java 25** o superior
- **PostgreSQL 12+**
- **Maven 3.6+**

## 🚀 Instalación y configuración

### 1. Clonar el repositorio

```bash
git clone <tu-repositorio>
cd biblioteca
```

### 2. Configurar PostgreSQL

Crear la base de datos:

```sql
CREATE DATABASE "Videojuegos_DB";
```

### 3. Configurar credenciales

Editar `src/main/resources/application.properties` con tus credenciales:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/Videojuegos_DB
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
```

### 4. Construir y ejecutar

```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
mvn spring-boot:run
```

La aplicación estará disponible en: **http://localhost:8080/videojuegos**

## 📁 Estructura del proyecto

```
src/
├── main/
│   ├── java/com/kristian/curso/biblioteca/
│   │   ├── controller/         # Controladores REST/MVC
│   │   │   ├── AppController.java
│   │   │   ├── GeneroController.java
│   │   │   ├── PlataformaController.java
│   │   │   ├── VideoJuegoController.java
│   │   │   └── HelloWorldController.java
│   │   ├── service/            # Lógica de negocio
│   │   │   ├── IGeneroService.java
│   │   │   ├── GeneroServiceImpl.java
│   │   │   ├── IPlataformaService.java
│   │   │   ├── PlataformaServiceImpl.java
│   │   │   ├── IVideoJuegoService.java
│   │   │   └── VideoJuegoServiceImpl.java
│   │   ├── repository/         # Acceso a datos (JPA)
│   │   │   ├── IGeneroRepo.java
│   │   │   ├── IPlataformaRepo.java
│   │   │   └── IVideoJuegoRepo.java
│   │   ├── model/              # Entidades JPA
│   │   │   ├── Genero.java
│   │   │   ├── Plataforma.java
│   │   │   └── VideoJuego.java
│   │   ├── dto/                # Data Transfer Objects
│   │   │   ├── FilaDto.java
│   │   │   └── FormDto.java
│   │   └── BibliotecaApplication.java  # Main
│   └── resources/
│       ├── application.properties      # Configuración
│       └── templates/                  # Vistas Thymeleaf
│           ├── Index.html
│           ├── ListarDatos.html
│           ├── FormularioCrear.html
│           └── VideoJuegosForm.html
└── test/                       # Tests
```

## 🗄️ Modelo de datos

### Entidades

#### VideoJuego
```
- id (PK)
- titulo
- calificacion
- favorito
- urlImagen
- plataforma_id (FK)
- genero_id (FK)
```

#### Genero
```
- id (PK)
- nombre (UNIQUE, NOT NULL)
- descripcion
```

#### Plataforma
```
- id (PK)
- fabricante (empresa)
- nombre (NOT NULL)
- anyo
```

### Relaciones

- **VideoJuego** → **Plataforma** (ManyToOne)
- **VideoJuego** → **Genero** (ManyToOne)

## 🌐 Rutas principales

### Plataformas
- `GET /videojuegos/plataforma/lista` - Listar plataformas
- `GET /videojuegos/plataforma/nueva` - Formulario nueva plataforma
- `POST /videojuegos/plataforma/crear` - Crear plataforma

### Videojuegos
- `GET /videojuegos/juegos/lista` - Listar juegos
- `GET /videojuegos/juegos/nuevo` - Formulario nuevo juego
- `POST /videojuegos/juegos/crear` - Crear juego

### Géneros
- `GET /videojuegos/genero/lista` - Listar géneros
- `GET /videojuegos/genero/nuevo` - Formulario nuevo género
- `POST /videojuegos/genero/crear` - Crear género

## 🔧 Configuración avanzada

### Propiedades Hibernate

```properties
spring.jpa.hibernate.ddl-auto=update  # Auto-genera tablas
spring.jpa.show-sql=true               # Muestra SQL en consola
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### Pool de conexiones HikariCP

```properties
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
```

## 📊 Dependencias principales

| Dependencia | Versión | Propósito |
|-------------|---------|-----------|
| spring-boot-starter-data-jpa | - | Acceso a datos con JPA |
| spring-boot-starter-thymeleaf | - | Templating HTML |
| spring-boot-starter-webmvc | - | Web MVC |
| postgresql | - | Driver PostgreSQL |
| openpdf | 1.3.30 | Generación de PDF |
| lombok | - | Reducción de código boilerplate |

## 🧪 Testing

```bash
# Ejecutar tests
mvn test

# Ejecutar con cobertura
mvn test jacoco:report
```

## 📝 Notas de desarrollo

- La aplicación usa el context path `/videojuegos` en todas las rutas
- Hibernate actualiza automáticamente el esquema de la BD (`ddl-auto=update`)
- Se usa Lombok para generar getters, setters y constructores automáticamente
- Las relaciones entre entidades son LAZY para optimizar rendimiento

## 🐛 Troubleshooting

### Error de conexión a PostgreSQL
- Verificar que PostgreSQL está ejecutándose
- Confirmar que la BD `Videojuegos_DB` existe
- Revisar credenciales en `application.properties`

### Error al generar PDF
- Verificar que OpenPDF está correctamente importado
- Revisar permisos de escritura en el directorio de salida

## 📄 Licencia

Este proyecto es parte del curso de formación en desarrollo Java con Spring Boot.

## 👤 Autor

Cristian - Proyecto académico

---

**Última actualización**: 2026
