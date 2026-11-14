package com.routecrafter_jdbc.dao;

import com.routecrafter_jdbc.config.DatabaseConnection;
import com.routecrafter_jdbc.model.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO {

    /**
     * GET /cities/{cityId}/routes
     * Consulta todas las rutas asociadas a una ciudad específica.
     */
    public List<Route> getRoutesByCityId(int cityId) {
        List<Route> routeList = new ArrayList<>();
        String sql = "SELECT id, name, description, city_id FROM routes WHERE city_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cityId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Route route = new Route(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("city_id"));
                    routeList.add(route);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar rutas por ciudad: " + e.getMessage());
        }
        return routeList;
    }

    /**
     * POST /cities/{cityId}/routes
     * Inserta una nueva ruta vinculada al cityId recibido.
     */
    public boolean createRoute(int cityId, Route route) {
        String sql = "INSERT INTO routes (name, description, city_id) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, route.getName());
            stmt.setString(2, route.getDescription());
            stmt.setInt(3, cityId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error al crear la ruta (verifique que el cityId exista): " + e.getMessage());
            return false;
        }
    }

    /**
     * PUT /routes/{routeId}
     * Actualiza el nombre y descripción de una ruta existente por su ID.
     */
    public boolean updateRoute(int routeId, Route route) {
        String sql = "UPDATE routes SET name = ?, description = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, route.getName());
            stmt.setString(2, route.getDescription());
            stmt.setInt(3, routeId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar la ruta: " + e.getMessage());
            return false;
        }
    }

    /**
     * DELETE /routes/{routeId}
     * Elimina una ruta de la base de datos a partir de su ID.
     */
    public boolean deleteRoute(int routeId) {
        String sql = "DELETE FROM routes WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, routeId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar la ruta: " + e.getMessage());
            return false;
        }
    }
}