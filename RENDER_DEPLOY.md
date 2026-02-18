# Deploy en Render (Docker)

## 1. Crear servicios con Blueprint
1. En Render, elige `New -> Blueprint`.
2. Selecciona el repo `renzowt123-ux/Jimenez`.
3. Render creara:
   - Database: `pedido-db`
   - Web service: `pedido-api`

## 2. Variables necesarias
El `render.yaml` ya configura:
- `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD` (desde la DB de Render)
- `SPRING_PROFILES_ACTIVE=prod`
- `JWT_SECRET` (auto generado)
- `JWT_EXPIRATION_MS=86400000`

No configures `PORT` manualmente. Render lo inyecta automaticamente.

## 3. Verificar build y arranque
En logs del servicio web debes ver:
- `>>> Using DB_HOST/DB_PORT/DB_NAME variables`
- `Started PedidoApplication`

Si falla la conexion a base de datos, revisa que el web service y la DB esten en el mismo Blueprint/proyecto.

## 4. Error comun con DATABASE_URL
Ahora la app soporta estos formatos:
- `postgres://...`
- `postgresql://...`
- `jdbc:postgresql://...`

No necesitas prefijo manual `jdbc:` en Render.
