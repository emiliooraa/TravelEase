-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 21-11-2025 a las 02:33:21
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `travelease`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `email` varchar(255) NOT NULL,
  `rol` varchar(255) NOT NULL DEFAULT 'Usuario',
  `password` varchar(255) NOT NULL,
  `fotoPerfil` blob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `dni`, `email`, `rol`, `password`, `fotoPerfil`) VALUES
(1, 'Admin', '12345678', 'admin@travelease.com', 'ADMIN', '$2a$12$hBvi1MWRNDgHI5Os7eqaXelK8rLUsc8LpSSFK1Kq4PDa2o3ajp82i', NULL),       --Admin1234@
(2, 'Pepe', '11223344', 'pepe@gmail.com', 'CLIENTE', '$2a$10$zrgSZTk4EPAJuX8YATiR7ulHA8C5ATq6R43KV90e9JzXzTXIXYd7a', NULL),            --Pepe1234@
(21, 'Emilio', '87654321', 'emilio@gmail.com', 'MANAGER', '$2a$10$28XwMPGcE2Wstn.syAbz0uGqFNSDZNZYzwufgCROh4/WzDcGuA0Iy', NULL),       --Emilio1234@
(22, 'Gianella', '44332211', 'gianella@gmail.com', 'OPERARIO', '$2a$10$XN2LIQ09t4IZUOlvzRZLw.DjWwW5GOw4ZhPPoxFxSFkYmQrbIGwrq', NULL);  --Giane1234@

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
