# Instrucciones para desplegar en Render

## 🚀 IMPORTANTE: Usar Blueprint

### Pasos:
1. Ve a **https://dashboard.render.com**
2. Haz clic en **New** → **Blueprint**
3. Selecciona tu repositorio: `renzowt123-ux/Jimenez`
4. Haz clic en **Create from Blueprint**

Render creará automáticamente:
- PostgreSQL Database (`pedido-db`)
- Web Service (`pedido-api`)

---

## ⚠️ PASO CRÍTICO: Configurar DATABASE_URL

Después que Render cree los servicios:

### 1. Copia la URL de la Base de Datos
- Ve al servicio **pedido-db** (Database)
- En **Connections**, busca **Internal Database URL**
- Cópiala completa (debe incluir `postgresql://user:password@host:port/dbname`)

**Ejemplo:**
```
postgresql://pedido_db_user:PASSWORD123@dpg-abc123:5432/pedido_db
```

### 2. Configura en el Web Service
- Ve al servicio **pedido-api** (Web Service)
- Ve a **Environment**
- Agrega/actualiza la variable:
  - **Key:** `DATABASE_URL`
  - **Value:** Pega la URL completa que copiaste anteriormente

### 3. Redeploy
- En **pedido-api**, haz clic en **Manual Deploy** o **Redeploy**
- Espera a que compile y deploy

---

## ✅ Verificar que funciona

Cuando veas en los logs:
```
Root WebApplicationContext: initialization completed in XXXX ms
```

Sin errores de conexión a BD = **¡Éxito!** 🎉

---

## 🐛 Troubleshooting

Si sale error `"Driver org.postgresql.Driver claims to not accept jdbcUrl"`:
1. Verifica que DATABASE_URL incluya el **port** (5432)
2. Verifica que la URL sea de tipo **Internal** (no external)
3. Redeploy

---

## 📝 URLs Importantes
- Dashboard: https://dashboard.render.com
- Docs: https://render.com/docs/databases

