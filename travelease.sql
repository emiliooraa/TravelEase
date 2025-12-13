-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 13-12-2025 a las 22:41:44
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
-- Estructura de tabla para la tabla `destino`
--

CREATE TABLE `destino` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `pais` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotel`
--

CREATE TABLE `hotel` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `ciudad` varchar(100) NOT NULL,
  `pais` varchar(100) NOT NULL,
  `estrellas` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `hotel`
--

INSERT INTO `hotel` (`id`, `nombre`, `ciudad`, `pais`, `estrellas`) VALUES
(1, 'Hotel Centro', 'Madrid', 'España', 3),
(2, 'Gran Palace', 'Lima', 'Perú', 4),
(3, 'Resort Playa', 'Cancún', 'México', 5),
(4, 'City Business Hotel', 'Buenos Aires', 'Argentina', 4),
(5, 'Roma Antica', 'Roma', 'Italia', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paquetes`
--

CREATE TABLE `paquetes` (
  `id_paquete` int(11) NOT NULL,
  `id_vuelo` int(11) NOT NULL,
  `id_hotel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `email` varchar(255) NOT NULL,
  `rol` varchar(255) NOT NULL DEFAULT 'CLIENTE',
  `password` varchar(255) NOT NULL,
  `fotoPerfil` blob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `dni`, `email`, `rol`, `password`, `fotoPerfil`) VALUES
(1, 'Admin', '12345678', 'admin@travelease.com', 'ADMIN', '$2a$12$hBvi1MWRNDgHI5Os7eqaXelK8rLUsc8LpSSFK1Kq4PDa2o3ajp82i', NULL),
(2, 'Pepe', '11223344', 'pepe@gmail.com', 'CLIENTE', '$2a$10$zrgSZTk4EPAJuX8YATiR7ulHA8C5ATq6R43KV90e9JzXzTXIXYd7a', NULL),
(21, 'Emilio', '87654321', 'emilio@gmail.com', 'MANAGER', '$2a$10$28XwMPGcE2Wstn.syAbz0uGqFNSDZNZYzwufgCROh4/WzDcGuA0Iy', NULL),
(22, 'Gianella', '11111111', 'gianella@gmail.com', 'OPERARIO', '$2a$10$XN2LIQ09t4IZUOlvzRZLw.DjWwW5GOw4ZhPPoxFxSFkYmQrbIGwrq', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta_hotel`
--

CREATE TABLE `venta_hotel` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_hotel` int(11) NOT NULL,
  `fecha_entrada` date NOT NULL,
  `fecha_salida` date NOT NULL,
  `noches` int(11) NOT NULL,
  `fecha_venta` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta_vuelo`
--

CREATE TABLE `venta_vuelo` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_vuelo` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL DEFAULT 1,
  `fecha_venta` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vuelo`
--

CREATE TABLE `vuelo` (
  `id_vuelo` int(11) NOT NULL,
  `codigo` varchar(20) NOT NULL,
  `origen` varchar(100) NOT NULL,
  `destino` varchar(100) NOT NULL,
  `fecha_salida` datetime NOT NULL,
  `fecha_llegada` datetime NOT NULL,
  `aerolinea` varchar(100) DEFAULT NULL,
  `asientos_disponibles` int(11) NOT NULL DEFAULT 0,
  `capacidad_total` int(11) NOT NULL DEFAULT 180
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `vuelo`
--

INSERT INTO `vuelo` (`id_vuelo`, `codigo`, `origen`, `destino`, `fecha_salida`, `fecha_llegada`, `aerolinea`, `asientos_disponibles`, `capacidad_total`) VALUES
(1, 'AR123', 'Buenos Aires', 'Madrid', '2025-12-15 10:00:00', '2025-12-15 23:30:00', 'Aerolíneas Argentinas', 0, 180),
(2, 'LA456', 'Santiago', 'Lima', '2025-12-20 08:30:00', '2025-12-20 11:00:00', 'LATAM', 0, 180),
(3, 'IB789', 'Madrid', 'Roma', '2025-12-22 14:00:00', '2025-12-22 16:30:00', 'Iberia', 0, 180),
(4, 'AA010', 'Buenos Aires', 'Miami', '2025-12-25 22:00:00', '2025-12-26 06:00:00', 'American Airlines', 0, 180),
(5, 'EK777', 'Buenos Aires', 'Dubái', '2026-01-05 21:00:00', '2026-01-06 22:00:00', 'Emirates', 0, 180);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `destino`
--
ALTER TABLE `destino`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `paquetes`
--
ALTER TABLE `paquetes`
  ADD PRIMARY KEY (`id_paquete`),
  ADD KEY `idx_paquetes_vuelo` (`id_vuelo`),
  ADD KEY `idx_paquetes_hotel` (`id_hotel`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `venta_hotel`
--
ALTER TABLE `venta_hotel`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_hotel` (`id_hotel`);

--
-- Indices de la tabla `venta_vuelo`
--
ALTER TABLE `venta_vuelo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_vuelo` (`id_vuelo`);

--
-- Indices de la tabla `vuelo`
--
ALTER TABLE `vuelo`
  ADD PRIMARY KEY (`id_vuelo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `destino`
--
ALTER TABLE `destino`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `paquetes`
--
ALTER TABLE `paquetes`
  MODIFY `id_paquete` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `venta_hotel`
--
ALTER TABLE `venta_hotel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `venta_vuelo`
--
ALTER TABLE `venta_vuelo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vuelo`
--
ALTER TABLE `vuelo`
  MODIFY `id_vuelo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `paquetes`
--
ALTER TABLE `paquetes`
  ADD CONSTRAINT `fk_paquete_hotel` FOREIGN KEY (`id_hotel`) REFERENCES `hotel` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_paquete_vuelo` FOREIGN KEY (`id_vuelo`) REFERENCES `vuelo` (`id_vuelo`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `venta_hotel`
--
ALTER TABLE `venta_hotel`
  ADD CONSTRAINT `venta_hotel_hotel_fk` FOREIGN KEY (`id_hotel`) REFERENCES `hotel` (`id`),
  ADD CONSTRAINT `venta_hotel_usuario_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `venta_vuelo`
--
ALTER TABLE `venta_vuelo`
  ADD CONSTRAINT `venta_vuelo_usuario_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `venta_vuelo_vuelo_fk` FOREIGN KEY (`id_vuelo`) REFERENCES `vuelo` (`id_vuelo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
