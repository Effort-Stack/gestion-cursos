-- Crear la base de datos si a�n no existe
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'baseproyecto')
BEGIN
    CREATE DATABASE baseproyecto;
END
GO

USE [baseproyecto]
GO

-- Tabla Profesor
CREATE TABLE Profesor (
    id INT PRIMARY KEY IDENTITY(1,1),
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    carnet VARCHAR(50) UNIQUE NOT NULL
);
GO

-- Tabla Estudiante
CREATE TABLE Estudiante (
    id INT PRIMARY KEY IDENTITY(1,1),
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    carnet VARCHAR(50) UNIQUE NOT NULL
);
GO

-- Tabla Curso
CREATE TABLE Curso (
    id INT PRIMARY KEY IDENTITY(1,1),
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    duracion FLOAT NOT NULL  -- Duraci�n en horas y fracciones de hora
);
GO

-- Tabla Nota
CREATE TABLE Nota (
    id INT PRIMARY KEY IDENTITY(1,1),
    notaExamen FLOAT NOT NULL CHECK (notaExamen BETWEEN 0 AND 10),
    notaTarea FLOAT NOT NULL CHECK (notaTarea BETWEEN 0 AND 10),
    notaLaboratorio FLOAT NOT NULL CHECK (notaLaboratorio BETWEEN 0 AND 10),
    notaFinal FLOAT NOT NULL CHECK (notaFinal BETWEEN 0 AND 10),
    curso_id INT NOT NULL,
    estudiante_id INT NOT NULL,
    FOREIGN KEY (curso_id) REFERENCES Curso(id),
    FOREIGN KEY (estudiante_id) REFERENCES Estudiante(id)
);
GO

-- Inserciones para la tabla Profesor
INSERT INTO Profesor (nombre, apellido, correo, carnet)
VALUES 
('Juan', 'P�rez', 'juan.perez@example.com', 'JP-001'),
('Mar�a', 'Rodr�guez', 'maria.rodriguez@example.com', 'MR-002'),
('Carlos', 'G�mez', 'carlos.gomez@example.com', 'CG-003'),
('Ana', 'Fern�ndez', 'ana.fernandez@example.com', 'AF-004'),
('David', 'Mart�nez', 'david.martinez@example.com', 'DM-005');
GO

-- Inserciones para la tabla Estudiante
INSERT INTO Estudiante (nombre, apellido, correo, carnet)
VALUES 
('Sof�a', 'L�pez', 'sofia.lopez@example.com', 'SL-101'),
('Lucas', 'Garc�a', 'lucas.garcia@example.com', 'LG-102'),
('Valeria', 'Hern�ndez', 'valeria.hernandez@example.com', 'VH-103'),
('Mateo', 'Morales', 'mateo.morales@example.com', 'MM-104'),
('Camila', 'Torres', 'camila.torres@example.com', 'CT-105');
GO

-- Inserciones para la tabla Curso
INSERT INTO Curso (nombre, descripcion, duracion)
VALUES 
('Matem�ticas B�sicas', 'Curso introductorio a matem�ticas.', 2.5),
('Historia Mundial', 'Estudio de la historia mundial desde la antig�edad hasta la actualidad.', 3),
('Biolog�a General', 'Introducci�n a los conceptos b�sicos de biolog�a.', 2),
('Programaci�n I', 'Introducci�n a la programaci�n.', 3.5),
('F�sica I', 'Principios b�sicos de la f�sica.', 2.5);
GO

-- Inserciones para la tabla Nota
INSERT INTO Nota (notaExamen, notaTarea, notaLaboratorio, notaFinal, curso_id, estudiante_id)
VALUES 
(8.5, 9.0, 8.8, 8.79, 1, 1),
(7.8, 9.2, 8.5, 8.41, 2, 1),
(9.0, 8.9, 9.1, 8.97, 3, 2),
(8.2, 8.4, 8.6, 8.42, 4, 2),
(8.8, 8.6, 8.5, 8.67, 5, 3);
GO
