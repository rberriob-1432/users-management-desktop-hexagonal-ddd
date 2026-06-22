-- =============================================
-- Script de creación de la base de datos (PostgreSQL)
-- Gestión de Usuarios y Clientes - Arquitectura Hexagonal
-- =============================================

-- Eliminar tablas si existen
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS users;

-- Eliminar tipos si existen
DROP TYPE IF EXISTS user_role;
DROP TYPE IF EXISTS user_status;
DROP TYPE IF EXISTS cliente_role;
DROP TYPE IF EXISTS cliente_status;

-- Crear tipos para Usuarios
CREATE TYPE user_role AS ENUM ('ADMIN', 'MEMBER', 'REVIEWER');
CREATE TYPE user_status AS ENUM ('ACTIVE', 'INACTIVE', 'PENDING', 'BLOCKED');

-- Crear tabla de Usuarios
CREATE TABLE users (
    id          VARCHAR(36)  NOT NULL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        user_role    NOT NULL,
    status      user_status  NOT NULL DEFAULT 'PENDING',
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Crear tipos para Clientes
CREATE TYPE cliente_role AS ENUM ('ADMIN', 'MEMBER', 'REVIEWER');
CREATE TYPE cliente_status AS ENUM ('ACTIVE', 'INACTIVE', 'PENDING', 'BLOCKED');

-- Crear tabla de Clientes
CREATE TABLE clientes (
    id          VARCHAR(36)  NOT NULL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        cliente_role NOT NULL,
    status      cliente_status NOT NULL DEFAULT 'PENDING',
    calle       VARCHAR(255),
    barrio      VARCHAR(255),
    ciudad      VARCHAR(255),
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Usuario administrador inicial (password: Admin1234!)
-- El hash es un ejemplo, debe ser generado por BCrypt
INSERT INTO users (id, name, email, password, role, status)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    'Administrador',
    'admin@example.com',
    '$2a$12$8.UnVuG9HHgffUDAlk8q2OuVGkqEnLPzS47uTf4p6JAnz7f/K94O.',
    'ADMIN',
    'ACTIVE'
);
