-- Crea la base de datos 
CREATE DATABASE gestion_tareas_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE gestion_tareas_db;

-- Tabla estado 
CREATE TABLE estado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

-- Tabla proyecto 
CREATE TABLE proyecto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    fecha_inicio DATE
);


-- Tabla tarea
CREATE TABLE tarea (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    descripcion TEXT,
    proyecto_id INT NOT NULL,
    estado_id INT NOT NULL DEFAULT 1,
    
        CONSTRAINT fk_tarea_proyecto FOREIGN KEY (proyecto_id) 
        REFERENCES proyecto(id) ON DELETE CASCADE,
        
    CONSTRAINT fk_tarea_estado FOREIGN KEY (estado_id) 
        REFERENCES estado(id)
);

-- Datos iniciales

-- Estados fijos
INSERT INTO estado (nombre) VALUES ('Pendiente');
INSERT INTO estado (nombre) VALUES ('En Progreso');
INSERT INTO estado (nombre) VALUES ('Finalizado');

-- Proyectos de ejemplo
INSERT INTO proyecto (nombre, descripcion, fecha_inicio) VALUES 
('Sistema de Ventas', 'Desarrollo de módulo de facturación', CURDATE());
INSERT INTO proyecto (nombre, descripcion, fecha_inicio) VALUES 
('Pagina Web Corporativa', 'Landing page para cliente', CURDATE());

-- Tareas de ejemplo
INSERT INTO tarea (titulo, descripcion, proyecto_id, estado_id) VALUES 
('Diseñar base de datos', 'Modelo E-R completo', 1, 3); -- Finalizado
INSERT INTO tarea (titulo, descripcion, proyecto_id, estado_id) VALUES 
('Crear API Java', 'Servlets y DAOs', 1, 2); -- En Progreso
INSERT INTO tarea (titulo, descripcion, proyecto_id, estado_id) VALUES 
('Maquetar Home', 'HTML y CSS básico', 2, 1); -- Pendiente