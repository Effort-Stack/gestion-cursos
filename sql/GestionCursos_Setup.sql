-- Crear la base de datos si aún no existe
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'baseproyecto')
BEGIN
    CREATE DATABASE baseproyecto;
END
GO

USE [baseproyecto]
GO

-- Tabla Usuario
CREATE TABLE Usuario (
    id INT PRIMARY KEY IDENTITY(1,1),
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(100) NOT NULL
);
GO

-- Tabla Curso
CREATE TABLE Curso (
    id INT PRIMARY KEY IDENTITY(1,1),
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    duracion INT NOT NULL, -- Duración en horas
    estructuraEvaluacion VARCHAR(255) -- Se podría usar un tipo más avanzado como JSON en SQL Server, pero para mantenerlo simple usaremos VARCHAR
);
GO

-- Tabla Nota
CREATE TABLE Nota (
    id INT PRIMARY KEY IDENTITY(1,1),
    tipo VARCHAR(50) NOT NULL CHECK (tipo IN ('TAREA', 'EXAMEN', 'LABORATORIO')), -- Reemplazo de ENUM
    valor FLOAT NOT NULL,
    curso_id INT NOT NULL,
    FOREIGN KEY (curso_id) REFERENCES Curso(id)
);
GO

-- Tabla Profesor (sin el campo especialidad)
CREATE TABLE Profesor (
    usuario_id INT PRIMARY KEY,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);
GO

-- Tabla Estudiante
CREATE TABLE Estudiante (
    usuario_id INT PRIMARY KEY,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);
GO

-- Tabla Profesor_Curso
CREATE TABLE Profesor_Curso (
    profesor_id INT,
    curso_id INT,
    PRIMARY KEY (profesor_id, curso_id),
    FOREIGN KEY (profesor_id) REFERENCES Profesor(usuario_id),
    FOREIGN KEY (curso_id) REFERENCES Curso(id)
);
GO

-- Tabla Estudiante_Curso
CREATE TABLE Estudiante_Curso (
    estudiante_id INT,
    curso_id INT,
    PRIMARY KEY (estudiante_id, curso_id),
    FOREIGN KEY (estudiante_id) REFERENCES Estudiante(usuario_id),
    FOREIGN KEY (curso_id) REFERENCES Curso(id)
);
GO

-- Tabla Estudiante_Curso_Nota
CREATE TABLE Estudiante_Curso_Nota (
    estudiante_curso_estudiante_id INT,
    estudiante_curso_curso_id INT,
    nota_id INT,
    PRIMARY KEY (estudiante_curso_estudiante_id, estudiante_curso_curso_id, nota_id),
    FOREIGN KEY (estudiante_curso_estudiante_id, estudiante_curso_curso_id) REFERENCES Estudiante_Curso(estudiante_id, curso_id),
    FOREIGN KEY (nota_id) REFERENCES Nota(id)
);

GO

-- Insertando datos en la tabla Usuario
INSERT INTO Usuario (nombre, apellido, correo, contrasena)
VALUES ('Juan', 'Pérez', 'juan.perez@example.com', 'contraseña123'),
       ('Maria', 'Gomez', 'maria.gomez@example.com', 'contraseña456');
GO

-- Insertando datos en la tabla Curso
INSERT INTO Curso (nombre, descripcion, duracion, estructuraEvaluacion)
VALUES ('Matemáticas Básicas', 'Curso introductorio a matemáticas.', 50, '{"examen": "60%", "tareas": "30%", "laboratorio": "10%"}'),
       ('Historia Mundial', 'Estudio de la historia mundial desde la antigüedad hasta la actualidad.', 75, '{"examen": "70%", "tareas": "20%", "laboratorio": "10%"}');
GO

-- Insertando datos en la tabla Profesor (Suponiendo que Juan es el profesor)
INSERT INTO Profesor (usuario_id)
VALUES (1);
GO

-- Insertando datos en la tabla Estudiante (Suponiendo que Maria es la estudiante)
INSERT INTO Estudiante (usuario_id)
VALUES (2);
GO

-- Insertando datos en la tabla Profesor_Curso (Suponiendo que Juan enseña ambos cursos)
INSERT INTO Profesor_Curso (profesor_id, curso_id)
VALUES (1, 1),
       (1, 2);
GO

-- Insertando datos en la tabla Estudiante_Curso (Suponiendo que Maria toma ambos cursos)
INSERT INTO Estudiante_Curso (estudiante_id, curso_id)
VALUES (2, 1),
       (2, 2);
GO

-- Insertando datos en la tabla Nota (Suponiendo notas para Maria en ambos cursos)
INSERT INTO Nota (tipo, valor, curso_id)
VALUES ('EXAMEN', 85.5, 1),
       ('TAREA', 92, 1),
       ('LABORATORIO', 88, 1),
       ('EXAMEN', 80, 2),
       ('TAREA', 78, 2);
GO

-- Insertando datos en la tabla Estudiante_Curso_Nota (Relacionando notas con Maria y sus cursos)
INSERT INTO Estudiante_Curso_Nota (estudiante_curso_estudiante_id, estudiante_curso_curso_id, nota_id)
VALUES (2, 1, 1),
       (2, 1, 2),
       (2, 1, 3),
       (2, 2, 4),
       (2, 2, 5);
GO