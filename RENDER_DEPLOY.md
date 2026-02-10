# Instrucciones para desplegar en Render

## Requisitos previos:
1. Cuenta en Render (render.com)
2. Repositorio Git (GitHub)
3. PostgreSQL database en Render

## Pasos:

### 1. Crear base de datos PostgreSQL en Render
- Go to https://dashboard.render.com
- Crear nuevo PostgreSQL database
- Copiar la URL de conexión interna (Internal Database URL)

### 2. Variables de entorno en Render
En el servicio web, agregar variables:
```
DB_URL=postgresql://[user]:[password]@[host]:[port]/[database]
DB_USER=[username]
DB_PASSWORD=[password]
SPRING_PROFILES_ACTIVE=prod
```

### 3. Crear Web Service en Render
- Repository: Seleccionar tu repo GitHub
- Build Command: `mvn clean package -DskipTests`
- Start Command: `java -jar target/pedido-0.0.1-SNAPSHOT.jar`
- Environment: Java
- Region: Seleccionar la más cercana
- Instances: Free tier

### 4. Conectar con GitHub
- Push a Github
- Render deployará automáticamente en cada push

## URLs importantes:
- Render Dashboard: https://dashboard.render.com
- PostgreSQL Docs: https://docs.render.com/databases

## Para testing local:
```bash
# Instalar PostgreSQL localmente
# Actualizar application.properties con datos locales
mvn spring-boot:run
```

## Docker local:
```bash
mvn clean package
docker build -t pedido-app .
docker run -e DB_URL=jdbc:postgresql://host.docker.internal:5432/dbpedidos \
           -e DB_USER=postgres \
           -e DB_PASSWORD=postgres \
           -p 8080:8080 pedido-app
```
