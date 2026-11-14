package com.routecrafter_jdbc;

import com.routecrafter_jdbc.dao.RouteDAO;
import com.routecrafter_jdbc.model.Route;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RouteDAO routeDAO = new RouteDAO();
        int option = 0;

        while (option != 5) {
            System.out.println("\n=============================================");
            System.out.println("   ROUTECRAFTER - MÓDULO DE RUTAS (JDBC)     ");
            System.out.println("=============================================");
            System.out.println("1. Consultar rutas por ciudad  [GET    /cities/{cityId}/routes]");
            System.out.println("2. Crear nueva ruta en ciudad  [POST   /cities/{cityId}/routes]");
            System.out.println("3. Actualizar ruta existente   [PUT    /routes/{routeId}]");
            System.out.println("4. Eliminar ruta               [DELETE /routes/{routeId}]");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Por favor ingrese un número válido.");
                scanner.next();
                continue;
            }
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (option) {
                case 1:
                    System.out.print("Ingrese el ID de la ciudad (cityId) [Ej: 1=Flandes, 2=Girardot]: ");
                    int getCityId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("\n-> Ejecutando GET /cities/" + getCityId + "/routes");
                    List<Route> routes = routeDAO.getRoutesByCityId(getCityId);

                    if (routes.isEmpty()) {
                        System.out.println(
                                "No se encontraron rutas registradas para la ciudad con ID " + getCityId + ".");
                    } else {
                        for (Route r : routes) {
                            System.out.println("   " + r);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el ID de la ciudad destino (cityId): ");
                    int postCityId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Ingrese el nombre de la ruta: ");
                    String newName = scanner.nextLine();

                    System.out.print("Ingrese la descripción de la ruta: ");
                    String newDescription = scanner.nextLine();

                    System.out.println("\n-> Ejecutando POST /cities/" + postCityId + "/routes");
                    Route newRoute = new Route(newName, newDescription, postCityId);
                    boolean created = routeDAO.createRoute(postCityId, newRoute);

                    if (created) {
                        System.out.println("Ruta creada exitosamente en la base de datos.");
                    } else {
                        System.out.println("No se pudo crear la ruta.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el ID de la ruta a modificar (routeId): ");
                    int updateRouteId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Ingrese el nuevo nombre de la ruta: ");
                    String updatedName = scanner.nextLine();

                    System.out.print("Ingrese la nueva descripción de la ruta: ");
                    String updatedDescription = scanner.nextLine();

                    System.out.println("\n-> Ejecutando PUT /routes/" + updateRouteId);
                    Route updatedRoute = new Route();
                    updatedRoute.setName(updatedName);
                    updatedRoute.setDescription(updatedDescription);

                    boolean updated = routeDAO.updateRoute(updateRouteId, updatedRoute);
                    if (updated) {
                        System.out.println("Ruta actualizada correctamente.");
                    } else {
                        System.out.println("No se encontró ninguna ruta con el ID " + updateRouteId + ".");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el ID de la ruta a eliminar (routeId): ");
                    int deleteRouteId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("\n-> Ejecutando DELETE /routes/" + deleteRouteId);
                    boolean deleted = routeDAO.deleteRoute(deleteRouteId);

                    if (deleted) {
                        System.out.println("Ruta eliminada exitosamente.");
                    } else {
                        System.out.println("No se encontró ninguna ruta con el ID " + deleteRouteId + ".");
                    }
                    break;

                case 5:
                    System.out.println("Cerrando módulo de rutas...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
        scanner.close();
    }
}