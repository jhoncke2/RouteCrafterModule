CREATE DATABASE IF NOT EXISTS routecraftermoduledb;
USE routecraftermoduledb;

CREATE TABLE IF NOT EXISTS cities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS routes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    city_id INT NOT NULL,
    CONSTRAINT fk_routes_city FOREIGN KEY (city_id) REFERENCES cities(id) ON DELETE CASCADE
);

INSERT INTO cities (name) VALUES ('Flandes'), ('Girardot'), ('Ibagué');

INSERT INTO routes (name, description, city_id) VALUES
('Ruta 01 - Centro / Magdalena', 'Recorrido principal desde el parque central hasta el puente férreo.', 1),
('Ruta 02 - La Capilla / Aeropuerto', 'Cubre el sector residencial norte y la terminal aérea.', 1);