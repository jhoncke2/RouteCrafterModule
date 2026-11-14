# Route Crafter - Módulo de Rutas (Java + JDBC + MySQL)

Módulo de gestión de rutas del proyecto formativo **Route Crafter**, implementado en Java utilizando **JDBC** y patrón **DAO** para operaciones CRUD sobre MySQL.

## Endpoints / Operaciones implementadas
- `GET /cities/{cityId}/routes` — Consultar rutas asociadas a una ciudad (`getRoutesByCityId`).
- `POST /cities/{cityId}/routes` — Crear una ruta ligada a una ciudad (`createRoute`).
- `PUT /routes/{routeId}` — Actualizar nombre y descripción de una ruta por ID (`updateRoute`).
- `DELETE /routes/{routeId}` — Eliminar una ruta por ID (`deleteRoute`).

## Estructura del proyecto
- `src/com/routecrafterjdbc/config/DatabaseConnection.java` — Conexión JDBC a MySQL.
- `src/com/routecrafterjdbc/model/Route.java` — Modelo entidad `Route`.
- `src/com/routecrafterjdbc/dao/RouteDAO.java` — Acceso a datos con `PreparedStatement`.
- `src/com/routecrafterjdbc/Main.java` — Menú interactivo por consola.
- `lib/mysql-connector-j-*.jar` — Driver JDBC de MySQL.
- `sql/routecrafter_setup.sql` — Script de creación de base de datos, tablas y datos semilla.

## Cómo ejecutar el proyecto
1. **Base de datos:** Inicia MySQL (puerto `3306`) y ejecuta el script `sql/routecrafter_setup.sql` en MySQL Workbench o consola MySQL para crear `routecraftermoduledb`.
2. **Credenciales JDBC:** Revisa `src/com/routecrafterjdbc/config/DatabaseConnection.java`:
   - `URL`: `jdbc:mysql://127.0.0.1:3306/routecraftermoduledb?useSSL=false&serverTimezone=UTC`
   - `USER`: `root`
   - `PASSWORD`: `""` (vacía por defecto en DBngin; ajusta si tu servidor tiene clave).
3. **Dependencias:** Verifica que el `.jar` de `lib/` esté en *Referenced Libraries* en VS Code.
4. **Ejecución:** Abre `Main.java` en Visual Studio Code y ejecuta el método `main`.****