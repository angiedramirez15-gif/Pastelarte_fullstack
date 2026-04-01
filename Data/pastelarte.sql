-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-04-2026 a las 05:15:43
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
-- Base de datos: `pastelarte`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `correo` varchar(30) NOT NULL,
  `contraseña` varchar(20) NOT NULL,
  `direccion` varchar(20) DEFAULT NULL,
  `idrol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `nombre`, `correo`, `contraseña`, `direccion`, `idrol`) VALUES
(1, 'Daniela ramirez', 'dani@gmail.com', '1234', 'Cra 7H', 5),
(2, 'mayra calvo', 'mayra@@gmail.com', '1789', 'Cra 8', 6),
(3, 'Angie Dazaa', 'daza@gmail.com', '789654', 'pasaje 7 f', 6),
(4, 'alejandra chocue', 'alejach@gmail.com', 'aleja123', 'av 6 #10', 6),
(5, 'marcela lopez', 'lopez12@gmail.com', '12lope', 'cra 10', 6),
(6, 'claudia quintero', 'claudis12@gmail.com', 'solecito', 'calle 58', 6),
(9, 'michael ortega', 'ortemai@gmail.com', 'maicol12', 'cra 35 ', 6),
(10, 'steban cadena', 'cadena@gmail.com', '24112025', 'Diagonal 30 #20-19', 6),
(11, 'juliana muñoz', 'muñoz78@gmail.com', 'wich2025', 'transversal 80', 6),
(12, 'Jaider Marimon', 'jaiderm@gmail.com', 'mari1234', 'avenida circunvalar', 5),
(13, 'milvia daza', 'milvia22@gmail.com', 'jesus1234', 'cra 1d bis', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_pedido`
--

CREATE TABLE `detalle_pedido` (
  `id_detalle` int(11) NOT NULL,
  `id_pedido` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `id_personalizacion` int(11) DEFAULT NULL,
  `cantidad` int(11) NOT NULL,
  `subtotal` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_pedido`
--

INSERT INTO `detalle_pedido` (`id_detalle`, `id_pedido`, `id_producto`, `id_personalizacion`, `cantidad`, `subtotal`) VALUES
(4, 3, 1, NULL, 2, 96000),
(5, 4, 6, 6, 1, 48000),
(6, 5, 12, 6, 2, 28000),
(7, 7, 9, NULL, 3, 25000),
(8, 6, 14, 7, 3, 120000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodo_pago`
--

CREATE TABLE `metodo_pago` (
  `idpago` int(11) NOT NULL,
  `tipo` enum('efectivo','nequi') DEFAULT NULL,
  `detalle` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `metodo_pago`
--

INSERT INTO `metodo_pago` (`idpago`, `tipo`, `detalle`) VALUES
(1, 'nequi', 'Transferencia digital'),
(2, 'efectivo', 'Pago contra entrega');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `idpedido` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `estado` enum('pendiente','pagado','cancelado') DEFAULT NULL,
  `total` float DEFAULT NULL,
  `idpago` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`idpedido`, `id_cliente`, `fecha`, `estado`, `total`, `idpago`) VALUES
(3, 2, '2025-11-01', 'pendiente', 96000, 2),
(4, 11, '2025-11-30', 'pagado', 40000, 1),
(5, 13, '2025-11-25', 'cancelado', 28000, 2),
(6, 10, '2025-11-15', 'pagado', 48000, 1),
(7, 11, '2025-11-05', 'pagado', 25000, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personalizacion`
--

CREATE TABLE `personalizacion` (
  `id_personalizacion` int(11) NOT NULL,
  `tamaño` varchar(30) DEFAULT NULL,
  `sabor` varchar(30) DEFAULT NULL,
  `decoraciones` enum('fondant','chispas','frutas','flores comestibles') DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL COMMENT 'aqui se escribe si quiere agregar algun texto en el pastel o algo que quiera personalizar.',
  `costoextra` float DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personalizacion`
--

INSERT INTO `personalizacion` (`id_personalizacion`, `tamaño`, `sabor`, `decoraciones`, `descripcion`, `costoextra`) VALUES
(1, 'pequeño', 'chocolate', '', 'escribir feliz cumpleaños daniela', 20000),
(2, 'grande', 'vainilla', '', 'escribir feliz aniversario', 30000),
(5, 'pequeño', 'chocolate', 'fondant', 'escribir feliz cumpleaños daniela', 20000),
(6, 'grande', 'fresa', 'flores comestibles', 'escribir feliz aniversario', 30000),
(7, 'pequeño', 'chocolate', '', 'escribir feliz  grado  daniela', 20000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `descripcion` tinytext DEFAULT NULL,
  `precio` float NOT NULL,
  `categoria` enum('pastel','ceroazucar','adicionales','postres') DEFAULT 'pastel'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `nombre`, `descripcion`, `precio`, `categoria`) VALUES
(1, 'Vainilla Dama', 'suave bizcocho con aroma dulce y textura esponjosa', 45000, 'pastel'),
(2, 'Torta de fresa', 'bizcochuelo suave con crema de fresa natural', 40000, 'pastel'),
(3, 'Combinado', 'mitad vainilla,mitad chocolate sabor:sabor perfecto y equilibrado', 55000, 'pastel'),
(4, 'Chocomani', 'chocolante intenso con toque crocante del mani ', 40000, 'pastel'),
(5, 'Chocolate', 'bizcochuelo humedo y rico en cacao puro', 35000, 'pastel'),
(6, 'Vainilla con arequipe', 'Esponjose pastel de arequipe con relleno cremoso de arequipe', 48000, 'pastel'),
(7, 'Vainilla chia', 'Pastel ligero de vainilla con chía que aporta textura sutil', 45000, 'ceroazucar'),
(8, 'Vainilla con Chocolate', 'Bizcocho suave de vainilla con capa de cacao sin azúcar', 40000, 'ceroazucar'),
(9, 'Vainilla Coco', 'Esponjoso bizcocho de vainilla con toque tropical de coco.', 25000, 'ceroazucar'),
(10, 'Galletas Rizadas', 'Crujientes y ligeras, con dulzor natural sin azúcar', 25000, 'ceroazucar'),
(11, 'Cocadas', 'Dulce coco rallado con textura tierna y aroma natural.', 25000, 'ceroazucar'),
(12, 'Panderos/Cubanos', 'Galletas suaves, aireadas y de sabor tradicional.', 25000, 'ceroazucar'),
(13, 'Piono', 'Delicado pionono relleno con crema y sabor equilibrado.', 45000, 'postres'),
(14, 'Leches Genovesa', 'Genovesa clásica, esponjosa, con capas de crema.', 40000, 'postres'),
(15, 'Manjar Español', 'Manjar preparado con receta tradicional y cobertura suave', 25000, 'postres'),
(16, 'Napoleon', 'Napoleón crocante con crema pastelera fina.', 25000, 'postres'),
(17, 'Selva Negra', 'Selva Negra con trozos de cereza y chocolate rallado.', 25000, 'postres'),
(18, 'Trufa de Chocolate', 'Trufa intensa con ganache y detalles dorados.', 25000, 'postres'),
(19, 'Tres Leches Arequipe', 'Bizcochuelo húmedo con mezcla de leches y suave toque de arequipe', 45000, 'adicionales'),
(20, 'Tres Leches Chocolate', 'Pastel esponjoso bañado en leches con cacao intenso.', 40000, 'adicionales'),
(21, 'Tres Leches Tradicional', 'Clásica textura húmeda con sabor equilibrado y cremoso.', 25000, 'adicionales'),
(22, 'Tropical de frutas', 'Bizcocho suave con mezcla fresca de frutas naturales.', 25000, 'adicionales'),
(23, 'Napoleon', 'Hojaldre crujiente con crema suave y dulzor delicado.', 30000, 'adicionales');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL,
  `titulo` varchar(12) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` enum('activo','inactivo') DEFAULT 'activo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id_rol`, `titulo`, `descripcion`, `estado`) VALUES
(5, 'admin', 'Administrador del sistema', 'activo'),
(6, 'cliente', 'Cliente frecuente de la pastelería', 'activo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD UNIQUE KEY `correo` (`correo`),
  ADD UNIQUE KEY `unique_correo` (`correo`),
  ADD KEY `idrol` (`idrol`);

--
-- Indices de la tabla `detalle_pedido`
--
ALTER TABLE `detalle_pedido`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `idpedido` (`id_pedido`),
  ADD KEY `idproducto` (`id_producto`),
  ADD KEY `idpersonalizacion` (`id_personalizacion`);

--
-- Indices de la tabla `metodo_pago`
--
ALTER TABLE `metodo_pago`
  ADD PRIMARY KEY (`idpago`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`idpedido`),
  ADD KEY `idcliente` (`id_cliente`),
  ADD KEY `fk_pedido_pago` (`idpago`);

--
-- Indices de la tabla `personalizacion`
--
ALTER TABLE `personalizacion`
  ADD PRIMARY KEY (`id_personalizacion`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `detalle_pedido`
--
ALTER TABLE `detalle_pedido`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `metodo_pago`
--
ALTER TABLE `metodo_pago`
  MODIFY `idpago` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `idpedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `personalizacion`
--
ALTER TABLE `personalizacion`
  MODIFY `id_personalizacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`idrol`) REFERENCES `rol` (`id_rol`);

--
-- Filtros para la tabla `detalle_pedido`
--
ALTER TABLE `detalle_pedido`
  ADD CONSTRAINT `detalle_pedido_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`idpedido`),
  ADD CONSTRAINT `detalle_pedido_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  ADD CONSTRAINT `detalle_pedido_ibfk_3` FOREIGN KEY (`id_personalizacion`) REFERENCES `personalizacion` (`id_personalizacion`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `fk_pedido_pago` FOREIGN KEY (`idpago`) REFERENCES `metodo_pago` (`idpago`),
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
