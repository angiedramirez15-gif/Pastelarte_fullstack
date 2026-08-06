CREATE DATABASE  IF NOT EXISTS `pastelarte` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `pastelarte`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pastelarte
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `id_rol` int(11) NOT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `correo` (`correo`),
  UNIQUE KEY `unique_correo` (`correo`),
  KEY `idrol` (`id_rol`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Daniela ramirez','dani@gmail.com','Cra 7H',5,'$2b$12$XMYYGB6USrQ5Nrn/sl3eJexytNqoxMvj8nMKFxRAmmMGS8aF9IY1e'),(2,'mayra calvo','mayra@@gmail.com','Cra 8',6,'$2b$12$WDAK/8j9trj7BqzFUx0SKuZ4b711UATgJzLSPmFTgzbQTnZ2ZWu0K'),(3,'Angie Dazaa','daza@gmail.com','pasaje 7 f',6,'$2b$12$WDAK/8j9trj7BqzFUx0SKuZ4b711UATgJzLSPmFTgzbQTnZ2ZWu0K'),(4,'alejandra chocue','alejach@gmail.com','av 6 #10',6,'$2b$12$WDAK/8j9trj7BqzFUx0SKuZ4b711UATgJzLSPmFTgzbQTnZ2ZWu0K'),(5,'marcela lopez','lopez12@gmail.com','cra 10',6,'$2b$12$WDAK/8j9trj7BqzFUx0SKuZ4b711UATgJzLSPmFTgzbQTnZ2ZWu0K'),(6,'claudia quintero','claudis12@gmail.com','calle 58',6,'$2b$12$WDAK/8j9trj7BqzFUx0SKuZ4b711UATgJzLSPmFTgzbQTnZ2ZWu0K'),(9,'michael ortega','ortemai@gmail.com','cra 35 ',6,'$2b$12$WDAK/8j9trj7BqzFUx0SKuZ4b711UATgJzLSPmFTgzbQTnZ2ZWu0K'),(10,'steban cadena','cadena@gmail.com','Diagonal 30 #20-19',6,'$2b$12$WDAK/8j9trj7BqzFUx0SKuZ4b711UATgJzLSPmFTgzbQTnZ2ZWu0K'),(11,'juliana muñoz','muñoz78@gmail.com','transversal 80',6,'$2b$12$WDAK/8j9trj7BqzFUx0SKuZ4b711UATgJzLSPmFTgzbQTnZ2ZWu0K'),(12,'Jaider Marimon','jaiderm@gmail.com','avenida circunvalar',5,'$2b$12$WDAK/8j9trj7BqzFUx0SKuZ4b711UATgJzLSPmFTgzbQTnZ2ZWu0K'),(13,'milvia daza','milvia22@gmail.com','cra 1d bis',6,'$2b$12$WDAK/8j9trj7BqzFUx0SKuZ4b711UATgJzLSPmFTgzbQTnZ2ZWu0K'),(19,'daniii','da145@hotmail.com','cra 10 h',6,'$2b$12$bECrB9qY0tF9E1nVoe3lvOcHVCIyEuHdryzdw/FFRMBNH5qQSAaZa'),(20,'maria del carmen','mariadel@gmail.com','cra 1 d bos',6,'$2b$12$HJWzq3gsdFJYYmsz7WD6/ediFybm9Bf1hdLILvMS3fM/fgm4wYDja'),(21,'anyi ','kathy@gmail.com','cra 50 h',6,'$2b$12$GaBfUuZbYRx0.OOH31z2r.eHakbPl4Re9IqNT0VuYJefha882wUYG'),(22,'michael jackson','jackson@gmail.com','avendia 2 oeste ',6,'$2a$10$fN3kW4VT.LvR57vNaYEYO.Ju2.9n6IYQmzWkYlRBrGJdRo8IJDRTu');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_pedido`
--

DROP TABLE IF EXISTS `detalle_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_pedido` (
  `id_detalle` int(11) NOT NULL AUTO_INCREMENT,
  `id_pedido` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `id_personalizacion` int(11) DEFAULT NULL,
  `cantidad` int(11) NOT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_detalle`),
  KEY `idpedido` (`id_pedido`),
  KEY `idproducto` (`id_producto`),
  KEY `idpersonalizacion` (`id_personalizacion`),
  CONSTRAINT `detalle_pedido_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`),
  CONSTRAINT `detalle_pedido_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  CONSTRAINT `detalle_pedido_ibfk_3` FOREIGN KEY (`id_personalizacion`) REFERENCES `personalizacion` (`id_personalizacion`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_pedido`
--

LOCK TABLES `detalle_pedido` WRITE;
/*!40000 ALTER TABLE `detalle_pedido` DISABLE KEYS */;
INSERT INTO `detalle_pedido` VALUES (4,3,26,NULL,2,96000.00),(5,4,6,6,1,48000.00),(6,5,12,6,2,28000.00),(7,7,9,NULL,3,25000.00),(8,6,14,7,3,120000.00),(9,8,14,NULL,1,40000.00),(10,8,18,NULL,1,25000.00),(11,9,14,NULL,1,40000.00),(14,23,35,9,1,200000.00);
/*!40000 ALTER TABLE `detalle_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensaje_contacto`
--

DROP TABLE IF EXISTS `mensaje_contacto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensaje_contacto` (
  `id_mensaje` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  `mensaje` longtext DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `leido` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_mensaje`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensaje_contacto`
--

LOCK TABLES `mensaje_contacto` WRITE;
/*!40000 ALTER TABLE `mensaje_contacto` DISABLE KEYS */;
/*!40000 ALTER TABLE `mensaje_contacto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metodo_pago`
--

DROP TABLE IF EXISTS `metodo_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metodo_pago` (
  `id_pago` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(255) DEFAULT NULL,
  `detalle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_pago`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodo_pago`
--

LOCK TABLES `metodo_pago` WRITE;
/*!40000 ALTER TABLE `metodo_pago` DISABLE KEYS */;
INSERT INTO `metodo_pago` VALUES (1,'nequi','Transferencia digital'),(2,'efectivo','Pago contra entrega');
/*!40000 ALTER TABLE `metodo_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `id_pedido` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `id_pago` int(11) NOT NULL,
  `comprobante` longtext DEFAULT NULL,
  `numero_nequi` varchar(20) DEFAULT NULL,
  `motivo_cancelacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_pedido`),
  KEY `idcliente` (`id_cliente`),
  KEY `fk_pedido_pago` (`id_pago`),
  CONSTRAINT `fk_pedido_pago` FOREIGN KEY (`id_pago`) REFERENCES `metodo_pago` (`id_pago`),
  CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (3,2,'2025-11-01','en_ruta',96000.00,2,NULL,NULL,NULL),(4,11,'2025-11-30','pagado',40000.00,1,NULL,NULL,NULL),(5,13,'2025-11-25','cancelado',28000.00,2,NULL,NULL,NULL),(6,10,'2025-11-15','pagado',48000.00,1,NULL,NULL,NULL),(7,11,'2025-11-05','pagado',25000.00,1,NULL,NULL,NULL),(8,21,'2026-07-24','en_ruta',65000.00,2,NULL,NULL,NULL),(9,21,'2026-07-25','pagado',40000.00,2,NULL,NULL,NULL),(11,22,'2026-08-05','cancelado',55000.00,1,'1785980958987_Adicionales.jpg','','Pago rechazado — el comprobante de Nequi no fue válido.'),(23,22,'2026-08-05','pagado',200000.00,1,'1785990514505_postrestresleche.png','',NULL);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personalizacion`
--

DROP TABLE IF EXISTS `personalizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personalizacion` (
  `id_personalizacion` int(11) NOT NULL AUTO_INCREMENT,
  `tamaño` varchar(30) DEFAULT NULL,
  `sabor` varchar(255) DEFAULT NULL,
  `decoraciones` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `costo_extra` decimal(10,2) DEFAULT NULL,
  `tamano` varchar(255) DEFAULT NULL,
  `imagen` longtext DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_personalizacion`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personalizacion`
--

LOCK TABLES `personalizacion` WRITE;
/*!40000 ALTER TABLE `personalizacion` DISABLE KEYS */;
INSERT INTO `personalizacion` VALUES (1,'pequeño','chocolate','','escribir feliz cumpleaños daniela',NULL,NULL,NULL,NULL,NULL),(2,'grande','vainilla','','escribir feliz aniversario',NULL,NULL,NULL,NULL,NULL),(5,'pequeño','chocolate','fondant','escribir feliz cumpleaños daniela',NULL,NULL,NULL,NULL,NULL),(6,'grande','fresa','flores comestibles','escribir feliz aniversario',NULL,NULL,NULL,NULL,NULL),(7,'pequeño','chocolate','','escribir feliz  grado  daniela',NULL,NULL,NULL,NULL,NULL),(9,NULL,'Chocomani','Frutas','te amo feliz dia',200000.00,'Grande (30 porciones)','data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/4gHYSUNDX1BST0ZJTEUAAQEAAAHIAAAAAAQwAABtbnRyUkdCIFhZWiAH4AABAAEAAAAAAABhY3NwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQAA9tYAAQAAAADTLQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAlkZXNjAAAA8AAAACRyWFlaAAABFAAAABRnWFlaAAABKAAAABRiWFlaAAABPAAAABR3dHB0AAABUAAAABRyVFJDAAABZAAAAChnVFJDAAABZAAAAChiVFJDAAABZAAAAChjcHJ0AAABjAAAADxtbHVjAAAAAAAAAAEAAAAMZW5VUwAAAAgAAAAcAHMAUgBHAEJYWVogAAAAAAAAb6IAADj1AAADkFhZWiAAAAAAAABimQAAt4UAABjaWFlaIAAAAAAAACSgAAAPhAAAts9YWVogAAAAAAAA9tYAAQAAAADTLXBhcmEAAAAAAAQAAAACZmYAAPKnAAANWQAAE9AAAApbAAAAAAAAAABtbHVjAAAAAAAAAAEAAAAMZW5VUwAAACAAAAAcAEcAbwBvAGcAbABlACAASQBuAGMALgAgADIAMAAxADb/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAIAAgADASIAAhEBAxEB/8QAHAABAAEFAQEAAAAAAAAAAAAAAAMCBQYHCAEE/8QAURAAAQMCAgQJBQsKAwgDAQAAAAECAwQFBhESITHSBxYyQVFVYXGUExdWgZMUGCJCUpGho8HR0xUjMzY3cnR1sbNUYpIIJENTY8Ph8DRzgvH/xAAaAQEAAgMBAAAAAAAAAAAAAAAAAwQBAgUG/8QAMxEBAAIBAgMGBAUEAwEAAAAAAAECAwQREhNRBRQhMUHRFVJhkSIycYGhIzOxwQY0QuH/2gAMAwEAAhEDEQA/AOfwAAAAAAAAAAAAAAAAAAAAAAANpX5PtPGcpCQCJW5KeEj9iEYAAAAAAAAAAACVrck7Shia8yQDxzc07SImI3przApAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHrW6Slegh4xeYrAiVMlPCVUzQjVMlyA9ZykJCFFyXMk00AP2EZU52l3FIAkRiJt2hrckz5yoClzUyzQjJXLkhEAAAAJrUFbE5wKkTJMiuKN80rIomOfI9yNa1qZq5V2IhSbW4F8IflG6vxDWR501E7Rp0cmp83T/wDlPpVOgh1GauHHOS3o3pSb24YaslifDK+KVjmSMcrXNcmStVNSopGqZpkbY4aMI/k+6MxDSR5U1Y7RqEampkuW3/8ASJ86L0mqBp81c+OMlfUvSaW4ZQrqUFb05ygmaAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABI12epdpGAJiJy5uGa9J4AAAAAAStXNAqoiEQA9Vc1PAAAAAEqJkmRG1M3ISgfVbbfUXa501vpWaU9RI2Nidqrlr7Drew2amw/Y6S1UifmqeNG6WWSuXarl7VXNfWaT4DbI2sxFWXeVubaGJGR5p8d+aZ+pqO+c36ec7XzzbJGKPKP8r+lptXi6rdfrNTYgsdZaqpPzVRGrc8s1au1HJ2ouS+o5IuVvqLVc6m31TdGemldE9O1Fy1dh2Qc/8ONkbRYmpLrG1EZXw6L8ueRmSKv+lW/MOyM81yTinyn/ACaqm9eLo1YqZpkRExE5MnKejUHgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAVaChnKJAI9Bek8VqoSni7FAiAAHqJmuRKiIhE3lISgUPTnKCR/JIwB6iKqnhXHzgPJ9o8n2lYAic3RPCR/JIwKmcokI2cokA35wDrHxYuaJl5T3b8Lu0G5fabWOZ+DDG0eEL5KytV35NrURszkTNY3Jnovy50TNUXv7DpGjraW4UrKmjqIqiB6Ztkiejmr60PK9p4b0zzaY8JdLT3iaRHROai4epIks1njVU8s6oe5qc+ijdf0q02bd75bLDROq7nWRU0KJqV7tbuxqbVXsQ5nx9jCTGWIVq2sdHRwt8nTRO2o3PW5e1fuTmN+y8F75oybeEMam8RTh9ZYqRv5RIRv5R6dzlIAAAAAAAABWxqZZgUAlVEXmIl1KAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAK9NegoAFflOw8V6qmRSAAPcl6DwD1vKQlIm8pCUCl/JIyR/JIwARcgiKuw9VFTagHumo01KQB6qqu08AAbFJUXNMyI9RVTYBKT01dV0SqtLVTQKu1YpFbn8x8yPRduoqMTG/mJJ6iapkWSeaSWRdrpHK5fnUjBSr0TZrMj1VyTMi2qeqqqus8AAAAMlUEqJkmQEQJVTNCNUyXWB4SM5KEZIzkgVETuUpKRLrVQPACtredQKdFeg8JiN6awKQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAra3nU8YmakgAjemSkirkmakSrmuYBvKQlIm8pCUCl/JIyR/JIwJWpk1D1UzKGuy1KVgRObl3HhMqZoQgAAAAAAm5iEm5gPF2KREq7FIgAAAAAASNdn3kYAmPHJmh4j05zxzs0yQCg9RVTYeAD1XKp4ABUxM3EhEi5LmV6aAequSZqRqua5hVzU8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPWrkpXpp0kYA9c7NTwAD1vKQlIm8pCUCl/JIyR/JIwBU1+WpSkAVq/VqKAAAB6iZrkB4CXRToGinQBETcx5op0HoHi7FIiY80U6AIgS6KdA0U6AIgeqmS5FTW5pmoFAJdFOgaKdAEQPXJkp4APURVXUeEjOSBSrFKSYidylA8AAAEiNREKsk6AIQS5IvMRqmS5AeAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA9bykJSJvKQlApfySMkfySMAAAAAAFTOUUlTOUBIAbZ4EqKkrJb37qpYZ9FsGj5WNHZZ6ezM3x047RVHmycuk36NTA62/Ilp6rovDs+4fkS09V0Xh2fcWe6T1UfiMfK5JB1t+RLT1XReHZ9w/Ilp6rovDs+4d0nqfEY+VySDbPDbRUlHLZPctLDBpNn0vJRo3PLQ25GpitkpwWmq9iycykX6o38oqYurIpfyik0SJgQ5r0jNekCp65qUgACRnJIyRnJAqIncpSUidylA8AAEiORU7SrNCEAS5p0karmuZ4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPW8pCUibykJQKX8kjJH8kjAAAAAABUzlFJUzlASG4OAn9Nff3YP+4afJYamenz8hPJFpbdB6tz+Y3x34LRZFmx8yk06uwAciflOv/xtT7V33j8p1/8Ajan2rvvLffI6KHw6fm/h12DkT8p1/wDjan2rvvH5Tr/8bU+1d9475HQ+HT838NrcO36Wxfuz/wDbNPks1TPUZeXnkl0dmm9XZfORFTJfjtNl/Dj5dIp0Rv5RSVP5RSaJQAqa3PWuwA1mesOZlrJDxdigREjOSRkjOSBURO5SkpE7lKB4iZrkV6HaeMXJxIBErVQ8Jl1kTkyUDwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAet5SEpE3lISgUv5JGSP5JGAAAAAACpnKKSpnKAkPopaCsrtL3JST1GhlpeSjV+jnszyPnN1/7Pn6bEP7tP8A9w2pXinZpktwVmzUn5BvHVNf4Z/3D8g3jqmv8M/7jssE3Ijqrd6no40/IN46pr/DP+4fkG8dU1/hn/cdlgciOp3qejiyqoKyh0fddJPT6eej5WNWaWW3LM+c3V/tB/psP/u1H9YzSpDevDOyzjtx1iyN/KKSp/KKTVuEqbEIj1HKgEpG52fceK5VPABIzkkZIzkgVETuUpKRO5SgeFbXcylAAmKH8yFOkvSeAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB63lISkTeUhKBS/kkZI/kkYAAAAAAKmcopKmcoCQ2pwL4ns2G5b0t3r46RJ2w+S00VdLLTz2IvSnzmqwZrbhndresWrtLq3zn4L6/p/8AQ/dHnPwX1/T/AOh+6cpAl51kHdq9XVvnPwX1/T/6H7o85+C+v6f/AEP3TlIDnWO7V6tqcNGJ7NiSWyraK+OrSBsyS6CKmjnoZbUToX5jVYBFa3FO6elYrXaEb+UUlT+UUmGwAAAAAEjOSRkjOSBURO5SkpE7lKB4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD1vKQlIm8pCUCl/JIyR/JIwAAAAAAVM5RSVM5QEhkGGMHXTFzqpLZ5DOmRqyeVfo8rPLLUvyVMfNwcBP6W+/uwf8AcJMVYveKyh1GSceObV81h8zWKumg9uv3DzNYq6aD26/cdBgu91xuX3/L9HPnmaxV00Ht1+4eZrFXTQe3X7joMDuuM7/l+jlrE+DrphF1K25+QzqUcsfkn6XJyzz1J8pDHzcHDt+msX7s/wD2zT5Sy1il5rDqafJOTHFreaN/KKSp/KKSNMAAAAABIzkkZIzkgVETuUpKRO5SgeAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAeo1VA8PURV2IVo1EKgKGtVFRSsADxyZpkUaC9hIAI9BewaC9hIAI9BewaC9hIAI9Bew9a1UUrAAz7gyxnbMIPua3JlS5KlIkZ5FiO5OlnnmqfKQwEG1LTWeKGmTHGSs1t5OgPPThf/AJNx9i3eHnpwv/ybj7Fu8c/gm71kVu44vq6A89OF/wDk3H2Ld4eenC//ACbj7Fu8c/gd6yHccX1Z9wm4ztmL5LY62sqWpTJKj/LMRvK0csslX5KmAgEN7TeeKVnHjjHWK18lDmqqnmgvYSA1bo9BewaC9hIAI9BewaC9hIAI9BewramSZHoAFDmqqqpWAIlRU2oeExSrUUCMHqtVDwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB6iZrkB61ufcSbAiZJkeoiqqIiZquxEA8LjbrFcrqudJSPez/mL8FvzrqMww1giNjGVl2ZpSLrZTrsb+90r2GcNa1jUaxqNaiZIiJkiIV754jwq7+i7EtkiL552jp6/wDxriDg6r3tRZ6ynjXoaiu+4+jzbS9Zs9iu8bABDz79XWjsbRxHjXf95a/820nWbPYrvDzbSdZs9iu8bAA51+rPwfR/J/M+7X/m2k6zZ7Fd4ebaTrNnsV3jYAHOv1Pg+j+T+Z92v/NtJ1mz2K7w820nWbPYrvGwAOdfqfB9H8n8z7tf+baTrNnsV3h5tpOs2exXeNgAc6/U+D6P5P5n3a/820nWbPYrvDzbSdZs9iu8bAA51+p8H0fyfzPu1/5tpOs2exXeHm2k6zZ7Fd42ABzr9T4Po/k/mfdr/wA20nWbPYrvDzbSdZs9iu8bAA51+p8H0fyfzPu1/wCbaTrNnsV3h5tpOs2exXeNgAc6/U+D6P5P5n3a/wDNtJ1mz2K7w820nWbPYrvGwAOdfqfB9H8n8z7tf+baTrNnsV3h5tpOs2exXeNgAc6/U+D6P5P5n3a/820nWbPYrvDzbSdZs9iu8bAA51+p8H0fyfzPu1/5tpea5s9iv3nzvwBKx2i6vai//V/5NkFEsSStyXbzKY51+rMdj6L1p/M+7XHEKTrBvsv/ACQzYFrWpnDVQSdjkVv3meuarXK1dqHg59+reexNFMeFdv3lqeus9fbddVTPaz5aa2/Oh8JuVzWvarXIitVMlRUzRTEb/hFj2PqrYzRemt0CbHfu9C9hNTURPhZxtd2DfFWb4J4o6ev/ANYRtI3Ny7iRUVFVFTJUCpmmRZeeQg9VMlyPAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABWxNWZQSomSIB6ZvgOwtnet2qWZsjdowNXndzu9XN29xhUbHSyNjYmbnKjUTpVTd9vo2W+309JHyYmI3PpXnX1rrIM9+Gu0ertdiaWM2aclvKv+fR9IAKT2AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPmq480R6c2pT5C5PbpMVvShbQkrPgAANmD4yszYXpcoG5NeuUyJzO5nev/wB2mIm3q2lZW0M1M/kysVvd0Kajex0b3McmTmqqKnaXcF+Ku0+jxfbukjDnjJXyt/n1RPTVmUEqpmikRO4YAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAATEKbSYCSCZ9NURTx5acb0e3NM0zRc0M/suP453tgusTYXLqSaPk+tObvNeA0vSt/Nb0utzaW2+OfDp6S3y1zXsR7HI5rkzRUXNFQ9NY4QxQ62TtoayTOieuTXL/wlX7P/wCmztpSyUmk7S9notbTV4+Ovn6x0ADW+Ica17rjLT26VIIInKzTRqK56ptXXsQxSk3naGdZrcekpFsnr0bIBgWFMYVlVcWUFxekqS6o5dFEVHdC5bUUz0XpNJ2ltpdXj1WPmYwjnqIaWB888jY4mJm5zlyRCQ15wi1k/u2lotJUgSLyuSbHOVVT6ET6Rjpx22a63Vd2wzl23S3fhCdpuitUKaKavLSpt7m/f8xjM2J73O5XPuVQi/5HaCfRkWkF6uOtfKHjM3aGpzTva8/pHhC7wYovdO5HMuU7uyRdNPpzMos/CCj3thusTWZ6vLxJqTvb93zGAAWx1t5wzg7Q1OGd62mfpPjDe8Usc0TZYntfG5M2uauaKhWaqwpiZ9nqW01S9XUMi60X/hr8pOzpQ2o1yOajmqitVM0VNioUsmOaTs9fodbTV4+KPCY84egGvcTYyrY7lLRW2RIY4XKx8iNRXOcm3bsTMxSk3naEmr1mPS048jYQNe4ZxlWy3KKiuMiTRzORjZFaiOa5dmzameo2EL0mk7SaTWY9VTjxhRLLHBE6WV7WRsTNznLkiIVOc1jVc5URqJmqqupENV4rxNJeKl1NTvVtDGupE1eUX5S9nQhnHjm87I9drqaTHxT4zPlC9XjhBRj3Q2qJrkTV5eVNS9zfv+YxefFF7qHK59ynb2RroJ9GRaAXa4618oeQz9oanNO9rTH0jwhdocT3uByOZcqhVT5btNPpzMltHCE/TbFdYUVq6vLRJrTvb93zGCAWx1t5wxh7Q1OGd63n9J8Yb2gniqoGTQSNkiembXNXNFJDUmGMSS2OrSORzn0Ui/nGbdH/ADJ2/wBTbMcjJomSxuR7HojmuRc0VF5ylkxzSXr9BrqavHvHhMecKgDAsV4wrKW4voLc9Iki1SS6KKqu6Ez2IhilJvO0JtVq8elx8eRnpbXplI5O1TCcPY1r23GKnuMqTwSuRmmrURzFXYurahm0n6V/7yi9JpO0s6HW49XWbY/TqpPHOaxquc5GtRM1VVyREPTX2J8ROuEzqOlflSMXJyp/xF+4Y6TedoZ12upo8fHbxn0jqud1xqyJ7obdG2VU1LK/k+pOcwqaV088kz8tORyudkmWtVzKAXqY608niNXrs2qtvknw9I9ICEmIV2m6mAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJtJiFNpMBecJWiG/wCK7baah72Q1cyROezLSbmi60zJcX4Tr8HX6W2VyaSJ8KGZEybMzmcn2pzKfZwa/tIsH8W37To3hJwVFjTDEkEbWpcabOWjkX5WWtir0O2d+S8xUy5+XliJ8pb1rvVyQbLwNfvd1EtuqH51FO34CqutzP8Axs7sjW8sUkEr4pWOZIxytc1yZK1U1KioS0FbNbq6Grp3aMkTs07elF7FLGSnHXZa0GrnS5ov6ev6N5GmL9a57Vdp4ZmORjnq6N6pqe1V1Khty2XGG626GsgX4Eia052rzovcfRLDFOzRljZI3oe1FQp47zjl6vXaKmuxVmttvWJ/VqrBtrnrr9BO1jkgp3eUfJlqRU2J355G2ClkbImIyNjWNTYjUyRCoxkvxzuk0GijSY+CJ3mfGQlk4N0x1bZJHT+5JIM0p5lbpI53Oip8n7fWhLbqCW518VJCnwpF1rzNTnVTbtHSRUNHFSwNyjjbop95Xvlmn5fNU7Y1Na4uT5zb/Dn61/7P18kuLW3W5UUNE13w307nPe5P8qK1ETvXZ0KbssOD7DhqkZT2y2wRK1MllViOkf2ucutf6F8BFk1GTJ+aXl4rEeTHcR4Hw9iikfDcbbD5RUybURMRkrF6Ucmv1LmnYcv43wXXYJvrqGqXytPIivpqlEySVn2KnOn2Kh2EYxj3CFPjPDE9vejW1TPzlLMqciRNnqXYvf2Emm1E47bT5MXrvDj42JgO/eXhW01D/wA5GmcCqu1vO31f07jAaqlnoauakqYnRTwvWOSNya2uRclRTylqZaOqiqYHqyWNyOaqdJ1r1i9dm+i1VtLmjJHl6/o3qafxTa57bfKlZGO8lNI6SN+WpyKueWfSmeRtCzXWK82yKriyRXJk9mfIdzofbJFHMzQlY17V+K5M0KdLzjt4vXazSU1+GvDbb1iWosLWue5XymWNjvJQyNkkflqaiLnln0rlkbgKI4o4WaETGsanxWpkh8V6usVmtktXLkqomTGfLcuxBe85LeBo9JTQYbcVt/WZYxjy/LBClpp3/nJEznVOZvM31/07zXhLVVMtZVS1M71fLI5XOXtPaWlnrquGkponSzzPSOONqa3OVckRC5SsUrs8jrdVbVZpyT5en6MgwPguuxvfW0NKvkqeNEfU1KpmkTPtVeZPsRTqDDuB8PYXpGQ262wpIiZOqJWo+V69KuXX6kyTsIsBYQgwZhiC3sRrqp/5yqlT48i7fUmxO7tMnOTqdROS20eTSlNoWK/YOsGJaR8FztkEquTJJmsRsjO1r01p/Q5ix/gOswNeUgkc6egnzdS1OWWmnO1ehyc/znXRYcY4XpcX4aqbTUojXPTSglVM1ikTku+xexVMafUTjttPkWrvDjUz3AV+1/kiof0up1X51b9qeswy5W6qtFyqbfWxLFU08ixyMXmVPs7SCKWSCZk0TlZIxyOa5NqKh171i9dm2j1NtNmjJX9/0b3NTYytc9Dfp53McsFQ7yjJMtSqu1O/M2Lh+8x3u1R1KZJKnwZWJ8V33LtLk+NkrFZIxr2rtRyZopTpacdvF6/V6anaGCOG23rEtNWC1z3W7QQwscrGvR0j8tTGoutVNqyfpX/vKffFDFCzQijZG3oY1EQs91rorbTT1Uy/BZnknO5eZEGS85Jht2fo6aDHabW39Zn9GP4wvPuSl9wwOymmT4ap8Vn/AJ+8wAnrKuWuq5amZ2ckjs17OwijjfNKyKJjnyPcjWtamauVdiIW8dIpXZ5LtDWW1eeb+np+i9YTwtXYuvkVtok0UX4U0ypm2JnO5fsTnUjxVaobHii4Wune98VLKsbXP5S5JtU6R4PMHR4Pw3HA9rVuFRlJVyJr+FzNRehuzvzXnOfeET9oV8/inHP0utnUam1a/liPv4+avfHw0iZ82MEK7SYhXadRCAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJtJiFNpMBlXBr+0iwfxbftOvjkHg1/aRYP4tv2nXxytf+eP0TYvJz5w44GWhr+NNBF/u1S5G1jWpyJeZ/c7n7e800dwV9BS3Sgnoa2Fs1NOxY5I3bHIpydwg4DrcEXp0TkfLbZnKtLU5anJ8l3Q5Pp2k+jz8UcFvOGuSu3jC3YYxHJYapWvR0lHKv5xibUX5Sdv9TadFcKS4wJNSVDJmL8ldad6bUNHHrXOaubVVF6UUsZMMXnd0tD2tk0teCY4q/4brud3obTAstXO1mrUxFzc7uQ1rdMZXWvqnPgqH0sCL8CONcly7V51MeVVVc1XNelTwUw1r5+JrO182o8K/hj6T/tu/gUxutTfZLLc2pJVVMarTVOxy6KZqxfUirn2G+Tm3gQwhW3DFEOIpI3R26g09GRUySWRWq3RTpyzzVexE5zpI5msisZfwqfMvk/Fed5AAVGQAAaK4dMDKjkxZb4tS5R17Wps5myf0avq7TRh3HU00NZTS01TE2WCViskjemaOaqZKiocq8JPB7VYKu7pIWPls9Q9Vpptuhz+TcvSnN0pr6curo8+8cu3n6IclfWGP4cxBNYa3TRFfTSZJLH09qdqG1rfdKK6QJLR1DJWqmtEX4Te9NqGkD1FVq5oqovShayYYv4uhoe1cmlrwTHFXp0btuF0orXAstZUMiaiakVfhO7k2qaqxHiCa/V2mqKymjzSKPoTpXtUs6qrlzVVVelTwY8MU8TXdq5NVHBEcNenUN58BeBl0lxZcItWuOga5PU6T+rU9fYYNwa8HtTjW7tlnY+Oz07kWom2afP5Nq9K8/Qnqz6pp6eGkpoqanibFBExGRxsTJGtRMkREKusz7Ry6+fq5+OvrKUAHKTAAA0vw5YGWtpExVb4s56dqMrWtTW6NNj+9uxezLoOfzuaSNksb45GNfG9Fa5rkzRyLtRUOXeFLg4mwhc319BE59kqX5xuTX5By/Ed2dC86dqHU0efeOXb9kOSvrDELDfJ7FXpPH8OJ3wZY89Tk+/oNr228UN2gSWkna/VrYq5Ob3oaTPUVUXNFyXsLeTFF/Ff0PamTSRwbb16ezeFbcKS3QLNV1DIWJ8pda9ybVNU4jv771WLoI5lKxyrGxdq9q9pZXOVy5uVVXpVTwxjwxSd22u7WyaqvLiOGv8AkNvcC2Clra3jNXRf7vTuVtI1ycuTnf3N5u3uMNwHgesxneEjajordC5FqqjLkp8lvS5fo2nUdDRU1toYKKjhbDTwMRkcbdjUQ5fa2ujHXk0nxnz+kf8A1RwY954pfQcn8In7Qr5/FOOsDk/hE/aFfP4pxT7D/vW/T/aTUflhjBCu0mIV2nplMAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAATaTEKbSYDKuDX9pFg/i2/adfHIPBr+0iwfxbftOvjla/wDPH6JsXkHxXa0UF9tstvudLHU0sqZOjen0ou1F7U1n2goxO3jCVz1irgFudJK+ow1UNraddaU07kZK3sRy5Nd9BrqrwRimhkVlRh65tVOdtK9zfUqIqKdlAuU12SsbT4o5xw45ocBYtuUiMpsO3Fc9jpIHRt/1OyT6TZ+EOASXy0dXiqoYkaLn7ipnZq7se/m7m596G9wL63JaNo8CMcQhpKSnoKSKkpII4KeJqNjijbotanQiEwBTSAAMAAAB81wt1HdqCahr6eOopZm6MkUiZoqf+859IMxOw0Di3gErIZpKnC9Q2ohVc0o6h+i9vY166nevLvU1rW4FxXb5FZUYduSKnxmUznt/1NRU+k7IBcprslY2nxRzjiXG9FgXFdwkRlPh25Kq/GfTuY3/AFORE+k2VhLgErJpo6nFFQ2CFFz9x079J7uxz01Indn3ob+Avrslo2jwIxxD5rfb6O1UENDQU8dPSwt0Y4o0yRE/95z6QCnM7pAAGAAAAhq6Snr6SWkq4I56eVqtkjkbm1ydCoTAyNEYv4BZfLSVeFahixqufuKpdkrexj+fud86msK7AWLbdIrKnDtxTLa6OB0jf9Tc0+k7GBcprclY2nxRzjiXGtJgjFNdIjKfDtzcq87qV7W/OqIiGfYZ4DblUzMmxHO2jgRc1p4XI+V3Yrk+C36Tow+CT9I7vUra3tLNWsRTw3b48VZnxfDa7VQ2W3xUFupmU9NEmTWMT6V6V7VPsAPPzMzO8rIcn8In7Qr5/FOOsDk/hE/aFfP4px2uw/71v0/2g1H5YYwQrtJiFdp6ZTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAE2kxCm0mAyrg1/aRYP4tv2nXxyDwa/tIsH8W37Tr45Wv8Azx+ibF5AAKCUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA+CT9I7vU+8+CT9I7vUpa38sJMfmpABz0ocn8In7Qr5/FOOsDk/hE/aFfP4px2+w/71v0/2r6j8sMYIV2kxCu09MpgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD1EVdiFSMXnUChNpMU6CdJUBlXBr+0iwfxbftOvjiex3eosF7pLrSsifPSyJIxsqKrVVOlEVF+k2P74HFfV9l9jL+IUdVp75bRNUlLREeLpEHN3vgcV9X2X2Mv4g98Divq+y+xl/EKvcsrfmVdIg5u98Divq+y+xl/EHvgcV9X2X2Mv4g7llOZV0iDm73wOK+r7L7GX8Qe+BxX1fZfYy/iDuWU5lXSIObvfA4r6vsvsZfxB74HFfV9l9jL+IO5ZTmVdIg5u98Divq+y+xl/EHvgcV9X2X2Mv4g7llOZV0iDm73wOK+r7L7GX8Qe+BxX1fZfYy/iDuWU5lXSIObvfA4r6vsvsZfxB74HFfV9l9jL+IO5ZTmVdIg5u98Divq+y+xl/EHvgcV9X2X2Mv4g7llOZV0iDm73wOK+r7L7GX8Qe+BxX1fZfYy/iDuWU5lXSIObvfA4r6vsvsZfxB74HFfV9l9jL+IO5ZTmVdIg5u98Divq+y+xl/EHvgcV9X2X2Mv4g7llOZV0iDm73wOK+r7L7GX8Qe+BxX1fZfYy/iDuWU5lXSIObvfA4r6vsvsZfxB74HFfV9l9jL+IO5ZTmVdIg5u98Divq+y+xl/EHvgcV9X2X2Mv4g7llOZV0ifBJ+kd3qc+++BxX1fZfYy/iEK8POKXOVVoLPrXP9DL+IVtT2bnyREV2+7emWseboYHPPn4xR/gLP7GX8QefjFH+As/sZfxCn8H1XSPu359HQxyfwiftCvn8U4yrz8Yo/wFn9jL+Ia8vV2nvt5q7pVMjZPVSLI9sSKjUVejNVX6Tp9maHNp8k2yesIs2St42h8JCu0mKdBOk7SujBWrF5lKVRU2oB4AAAAAAAAAAAAAAAAAAAAAAAAVtZ0nrW5a12lQAH0UdFU3CobBSQvllX4rU+lehDMrdwdPc1H3Gr0M/wDhwpmv+pfuU0tetfNa0+iz6j+3Xf6+jBQbXiwNYo25Op5JV6Xyu+xUJOJeH+r/AK6TeI+8VdGOwdTPrX7z7NSA23xLw/1f9dJvDiXh/q/66TeHeKs/ANT81fvPs1IDbfEvD/V/10m8OJeH+r/rpN4d4qfANT81fvPs1IDbfEvD/V/10m8OJeH+r/rpN4d4qfANT81fvPs1IDbfEvD/AFf9dJvDiXh/q/66TeHeKnwDU/NX7z7NSA23xLw/1f8AXSbw4l4f6v8ArpN4d4qfANT81fvPs1IDbfEvD/V/10m8OJeH+r/rpN4d4qfANT81fvPs1IDbfEvD/V/10m8OJeH+r/rpN4d4qfANT81fvPs1IDbfEvD/AFf9dJvDiXh/q/66TeHeKnwDU/NX7z7NSA23xLw/1f8AXSbw4l4f6v8ArpN4d4qfANT81fvPs1IDbfEvD/V/10m8OJeH+r/rpN4d4qfANT81fvPs1IDbfEvD/V/10m8OJeH+r/rpN4d4qfANT81fvPs1IDbfEvD/AFf9dJvDiXh/q/66TeHeKnwDU/NX7z7NSA23xLw/1f8AXSbw4l4f6v8ArpN4d4qfANT81fvPs1IDbfEvD/V/10m8OJeH+r/rpN4d4qfANT81fvPs1IDbfEvD/V/10m8OJeH+r/rpN4d4qfANT81fvPs1IDbfEvD/AFf9dJvDiXh/q/66TeHeKnwDU/NX7z7NSA23xLw/1f8AXSbw4l4f6v8ArpN4d4qfANT81fvPs1IDbfEvD/V/10m8QTYLsrPhNos2/wD2v1fSO8VI/wCP6mf/AFX7z7NVg2ZxTsn+C+tf95RJg+zPTJsD4+1si/bmO8UZn/j2qj1r959mtgZhXYGe1quoanT/AOnKmS/On3GK1VJUUU6w1MTopE5nJ/TpJK5K28pc3U6HPpv7tdo6+j5nM6CgmKXNz1ptN1RGAAAAAAAAAAAAAAAAAABUxM1zKSVEyTID0uVks1RfK9tNB8Fqa5JFTUxvT/4LaiKqoiJmq8xuLDVmbZbRHErU90SJpzO/zdHq2EWXJwR9XS7M0Xesu1vyx5+z6rVaKSz0iU9JGjU+M9eU9elVPuAKMzv4y9tSlaVitY2iAAGGwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD4aiLyb805K7CEuMrPKRq3n5i3BJWd4D47jbKW6Uyw1MeafFcnKavSin2ARMx4wXpW9ZraN4lqi7Wqe0VqwTa2rrY9Njk6T4DaOILU27Wx8aInl2fDiXt6PXsNXKiouS6lL+LJxx9XhO1NB3TNtX8s+Xt+yN6ZLmUkqpmmRESuYAAAAAAAAAAAAAAAA9TWqEpE3lISgXTDjaZ2IKNauWOKBkmm50jkRNWtM8+1ENyMeyViPjc17F1o5q5opoc++2XmvtEunR1DmJnm6NdbXd6EOXFN/GJdfsztOukiaWrvEz5+rdYLBh3FNNfWeSciQ1jUzdEq6ndrenuL+U7Vms7S9dhzUzUi+Od4kABqlAAAAAAAAAUve2NjnvcjWNTNznLkiIa/xBjqSR76a0LoRpqWoVPhO/d6E7dvcb0pN52hV1esxaWvFkn9vWWcVlyore3Sq6qKHPYj3oir3JzlokxvYY1ySrc/8Adid9qGqZZZJpHSSvc97lzVzlzVfWUFmNPX1l57L2/mmf6dYiPr4+zbkOM7DM7R926C/543J9OWReaepgq4kkp5o5mL8aNyOT6DRRPSVtVQTpNSTvhkTnYuWff0mLaePSW2H/AJBkif6tYmPo3mDD8N41juD2Udx0YqldTJE1NkXo7F+gzArWrNZ2l6LT6nHqKceOd4AAapwAAAAAAAAAw/EmNY7e99HbtGWpTU+VdbY16E6V+g2rWbTtCDUanHp6ceSdoZXUVVPSReUqJ44WfKkcjU+ks02M7DC7R926ap8iNy/Tlkaqq62pr51mq53zSLzvXPLu6CAsxp49Zedzdv5Jn+lWIj6tsx43sMi5LVuZ+9E77ELvR3GiuDdKkqopstqMeiqnenMaPK4pZIZGyRPdG9q5o5q5KnrMzp6+ktcXb+aJ/qViY+nh7t7g17h/HUkb2U13XTjXU2oRNbf3ulO3+psBj2yMa9jkcxyZo5FzRUK16TSdpeh0usxaqvFjn9vWFQANFoAAAAAC3TN0ZnJ2lxPhqv069yBtTzQgFlvmIqezs8m1ElqlTVGi6m9rjNazadoYzZ8eCk5Mk7RC8Pe2Nive5GtTWquXJENWX1sDb3VLTSMkhc/Sa5i5pr1r9OZHcLrW3OXTqpnOTPUxNTW9yHxFzFimnjMvG9qdqV1kRStdoifP1CJdSqSkTuUpO4zwAAAAAAAAAAAAAAAHreUhKRN5SEoH2220XC8yTx22klqpIIVnkZEmbkYioirltXWqbD4lTJcl2m1OAD9f6r+Wyf3IzL+FzgujuFPPiOxU6NrY0V9XTRpqmbzvanyk5+nv21raiK5eXZvFd43hz/DNJTzMmhe5kjF0muauSoptrDGIWX2h+Hk2riRElYnP/mTsU1EfbabnNaLjFWQLrYuTm56nt50Uly4+OPqvdna62ly+P5Z8/du0EFFWQ19HFVU7tKKVuk1fs7ycoPcVtFo3jyCCetpaVUSoqYYVXYkkiNz+cVtR7koKmpRM/IxOky6ckVTSNTUzVlTJUVEjpJZFzc5SXFi43M7R7S7nwxFd5lvNj2yMR7HI5q60VFzRSo1xwfXGdlykt6vV0EkavRq7GuTLWnq+w2OaXpwTss6LVxqsMZIjYAMUxtf/AMm0PuGnflVVDdaoutjOde9dnzmK1m07Qk1Gemnxzkv5Qx/GWJ3V07rdRyf7rGuUj2r+kd9yfT8xiALzhfDVfi2+wWq3szkk1vkdyYmJtc7sT6VyTnL8RXHX6PB6jUX1OScl/OXyWmzXK+1zaK10U1XUu1oyJueSdKrsRO1dRsmg4AcT1EKSVdbbqRVT9Gr3PcnfkmXzKpvPCeEbXg6zsoLbCiLkizTuT4czuly/0TYhfjm5ddaZ/B5MRjj1cz3XgHxXQQOmpJaG4I1M/JwyK169yORE+k1pVUtRRVUlNVQSQTxO0XxyNVrmr0Ki7DuMwPhJ4OaTGlsdUUzI4b1A38xNs8oifEf2dC8y+s2w66d9shbH0cpmyMF4mWuYltrZM6lifmnuXXI1OZe1PpQ17VUs9DVzUtVE+KeF6skjemStci5KilEM0lPMyaJ6skY5HNcm1FQ6F6ReNkui1d9Lli9fL1jq3uC1YevMd7tTKhMkmb8GZiczvuXaXU58xMTtL3mPJXJSL0nwkI5qiGmZpzzRxM+U9yNT6SQ09ie4z3G/VSyvXQikdFGzmaiLl9OWZJjx8c7KXaGujR44ttvMtuw1ENSzTgmjlZ8pjkcn0Ehp7C9xnt1+pVieuhNI2KRnM5FXL6M8zcIyY+Cdjs/XRrMc222mAAtOIbyyyWp9QuSzO+BCxed33JtI4iZnaF3JkrjpN7z4QseNMTLQsW20UmVQ9PzsjV1xtXmTtX6ENblcssk8z5pXq+R7lc5y7VVSSjo6i4VsNHSQumqJ3pHHGxM1c5VyRDoUpFK7PB63V31WWb28vSOimnp56uojp6aGSaeR2iyONquc5ehETabItHAXi25QNmqlo7c1yZoyokVX/M1Fy9aobh4OeDiiwVbmzTMjnvMzfz9RlnoZ/EZ0J28/zImdHPza6d9saKuPq5wrP9n/ABLDEr6W4W2pcnxNJ7FXuzbl86oa2vNjueHrg6hu1FLS1DdejIm1OlFTUqdqHbBYcW4RtmMbM+33GJNJEVYJ2p8OF/Si/wBU5zXFrrRP4/InHHo41MwwZiZaGdturJP91kXKNzl/Ru6O5foLLiXDtfhW+T2q4x6M0S5tcnJkYuxzV6F/8cxaTpTFb1+jbTai+myRkp5w30DFME3/APKVD7hqH51VO3Uq7Xs5l702fMZWULVms7S93p89M+OMlPKQpe9sbFe9yNamtVVckQqNccINxnfc47ej1bBHGj1ai8py86+r7TOOnHOyPW6uNLhnJMbtgQVtLVKqU9TDMqbUjkR2XzE5oqlqpqOpjqKeR0csa5tchu6iqPddBT1Kpl5aJsmXRmiKb5cXArdndpd84omu0wnPhqv0y9x9xa7rVRUUctTM7RjjbmqkTqxaK7zPlCz4hvbLPR/AydUyZpG3o/zL2GtJZZJ5XSyvV8j1zc5V1qp9FyuEtzr5KqZdbl+C3ma3mQ+QvYsfBH1eG7T19tXl8Pyx5R/sPrr7ZXWt8LK+llpnzRJNG2VuiqsVVRFyXuU3jwW8GMVup4b9fKdH10iI+mp5EzSBOZzk+X/Tv2Yjw7fr1S/y+P8AuSFTHr65dTyaeMR6+ylOKYpxS1iRO5SkpE7lKdBE8AAAAAAAAAAAAAAAB63lISkTeUhKBtTgA/X+q/lsn9yM6UOa+AD9f6r+Wyf3IzpQ4+t/up8f5XNHDJgJMOXhL1bodG11z10mNTVDNtVvYi61T1pzIauO1r/Y6PEdiq7TXM0oKlmiqptau1HJ2ouS+o47v9krMOX2rtNc3Rnpnq1VTY5NqOTsVMl9Zc0efjrwz5w0vXad2QYEv3uSqW11D8oZ3ZxKvxX9Hr/r3myDQyKrXI5qqiouaKnMbZwpf23u3aMrk92QojZU+UnM71/1M58f/qHpOxNdxR3e8+MeXsv0kbJonxyJpMe1WuTpRTWNxwJdYKpyUUbamBV+A5HtaqJ2oqpr7jaAIqZJp5OrrNBi1cRGT06MUwjhaWzOkrKxW+6Xt0GsauaMbtXX06kMrANbWm07ym0+npp8cY8flD5q+uhttDNVzuyjibmvb0Inappi5V81zuE1ZOvw5HZ5czU5kTuQyLG2IEuVYlDTPzpYHfCVF1Pf9ybPnMTLeHHwxvPm8r2xrufk5VJ/DX+ZVMY6R7WMarnuXJrUTNVXoOreC/AzMG4batRG38q1iJJVP529EaL0J/XPsNW8B+CPyrdlxJXRZ0dC/Kma5NUk3T3N296p0KdFlPW5955cfu5mOvqAA5yUAAGleHDASVVK7Fdti/PwoiVzGpy2bEk701IvZ3GgTuWWKOeJ8UrGvje1WuY5M0ci6lRUOS+EnBj8GYplpo2uW31GctG9fkZ6259LV1d2S851NFn3jl2/ZDkr6rThe9rZLq2R6r7mlyZMnZzL6vvNvNc17Uc1Uc1UzRU2KhoY2HgXEKTRJaKp/wCcYn5hyrym/J9XN2dxPnx7/ih2+xNdwW7vefCfL9en7s3MAxNgusqLjLW21rZWzO03xK5Gqjl2qmepUXaZ+CvS80neHoNVpMeqpwZGAYZwXWU9xirbk1sTYXabIkcjlVybFXLUiJtM/AF7zed5NLpMelpwY3jnIxqucqI1EzVV2IhqHFF7W93Z0jFX3NFmyFOznX1/cZTjrECQQLaaZ/52RPz6p8Vvye9f6d5rssYMe34pcDtvXcdu70nwjz/Xp+wdBcB+BEoaJMVXGL/eahqpRNcn6ONdr+93N2d5q7g1wY/GeKYqaRrvyfTZTVj01fAz1Nz6XLq7s15jrWONkMTIo2NZGxqNa1qZIiJsRCDW59o5cfu4eOvqqABy0wAAMD4UsCMxlh50lNGn5Wo2q+mcm2ROeNe/m6Fy7TlRzXMcrXNVrmrkqKmSop3Oc6cN+B/yTdkxJQRZUdc/Kpa1NUc23Pudt70XpQ6Oiz7Ty7fsiyV9WrLbXzWy4Q1kC/DjdnlzOTnRe9Dc9vroblQQ1dO7OOVuadKLzovaho4yrBeIUtlZ7iqX5Uk7tSquqN/T3LsX1FzNj4o3jzdPsfXcjJy7z+G38S2gYpi7C0t5dHWUat90sboOY5cke3myXp1qZWCpW01neHqtRp6ajHOPJ5S1fbsCXWeqalbG2mgRfhu02uVU7ERV195s6ONsUTI2JosY1GtToRCoG18k380Oj0OLSRMY/XqGtMdXlKqvW3wPzihX86qfGf0er+vcZbiq/NsltVI3J7rmRWxN6Ol3q/qakc5XOVzlVXKuaqvOS4Mf/qXK7b13DXu9J8Z8/Z4bN4H8Epfrwt5r4tK30L00GuTVLNtRO1G6lX1dpgdistXiG90tqom5z1D9FFXY1Ody9iJmvqOtbFZaTD1kpbVRNygp2aKKu1y87l7VXNfWVO1dZycfLr+a38Q89hx8U7z5Lic7cO369Uv8vj/uSHRJztw7fr1S/wAvj/uSHJ7H/wCz+0ps/wCRrEidylJSJ3KU9YpPAAAAAAAAAAAAAAAAet5SEpE3lISgbU4AP1/qv5bJ/cjOlDmvgA/X+q/lsn9yM6UOPrf7qfH+UNW8M2A1xFZkvduh0rnQsXTa1Nc0O1U7VbrVPWnQbSBXx5Jx2i0N5jeNnC59duuNRaq6OrpX6MjF2czk50XsNq8L/Bk+01M2I7LAq2+VyvqoGJ/8dy7XInyF+hezZp87mPJXLXeEETbHbePCYbmsl9pL5SJLA5Gyon5yFV+ExftTtLoaJgnmppmywSvikbsexyoqesvceNL9GzR92o5Ol0bVX58iG2nnf8L0mn7fpw7Z6zv1htvYmamDYsxgxsT7fbJUc93wZZ2rqanQ1ent/wDUxGuxDdrkxWVVbI6NdrG5NaveiZZlsNseDad7K+t7bnLWaYY2ifX1C9YVw1W4sxDTWmiaulIuckmWaRRpynL3fSuSc5brfb6u618NDQ0756qdyMjjYmauX/3nOq+DjAVPgex6D1ZLc6lEdVTJsz5mN/yp9K6+xGozxir9XCpXilktmtFHYbPS2ugj8nTUzEYxOdelV7VXNV7VPvAOJM7zvKyAAwAAAGK8IGDoMaYYmoVRrayP87SSr8SRE2KvQuxfn5jKgbVtNZi0MTG7h2rpKigrJqSqidDUQvWOSN6ZK1yLkqKURSvhlZLE9WSMVHNc1clRTovhe4M3Yghdf7NDndImZTwNTXUMTnT/ADonzpq5kOclRWuVrkVFTUqLzHcw5oy13hXmJrLauGMUw3mBtPUObHXNTJWrqSTtb9xkhoZrla5HNVUVFzRU5i90+L77TMRja9z2p/zGtevzqmZpfT7zvV6LSduxWnDniZmPWP8AbbxjeJcV09nhfBTubLXKmSNTWkfa77jA6rFl7q2KySve1q80aIz6UTMsqqqrmq5qopp9p3sxq+3eKvDgjb6yrmlknmfNK9XyPVXOc5daqpXSUlRX1kNJSROmqJnpHHGxM1c5VyREIkRXORrUVVVckROc6N4IeDN2H4W3+8w5XOZmUEDk107F2qvQ9U+ZNXOpvmzVxV3l56Im0sw4P8HQYLwxDQJourJPztXKnx5FTYnYmxPn5zKgDh2tNp4pWIjYABqyAAAfBerRR3+z1Vrr4/KU1TGrHpzp0Knai5Knah94MxO07wOMMVYarcJ4hqbTWtXSiXOOTLJJWLyXp2L9C5pzFmOtOEfAVPjiyaEehFdKZFdSzLsXpY7/ACr9C6+lF5UuFvq7VXz0NdA+CqgcrJI3pkrV/wDec7enzxlr9Va9eGWb4Qxcx0bLbcpdF7fgwzPXUqfJVenoX/1c6NCl1ocS3i3xpHT10iRpsY9Eeid2eeQyYN53q7uh7bnFSMeeN4j1jzblLXer9R2OmWSd6OlVPzcLV+E5fsTtNbzYyv0zFYtdoIu3Qja1fnyzLJLLJPK6SaR8kjtaueuar6zWunnf8SxqO368O2Cs79ZfTc7lUXaukq6l2b3bETY1OZE7D4wbZ4KODeS51MOIbxAraCNdOmhen6dybHKnyU+lezbvnz00+Ob28oec/FlvvPjMsy4IcDrh+0LeK+LRuVcxNFrk1wxbUTsVdSr6k6TZgB4vPmtnyTkt5yvVrFY2gOduHb9eqX+Xx/3JDok524dv16pf5fH/AHJC/wBj/wDZ/aUWf8jWJE7lKSkTuUp6xSeAAAAAAAAAAAAAAAA9bykJSJvKQlA2pwAfr/Vfy2T+5GdKHNfAB+v9V/LZP7kZ0ocfW/3U+P8AKAAppHj2NexzHtRzXJkrVTNFQ0pjrgNZVyy3HCixwyO+E+gkXJir/wBN3xe5dXamw3YCXHltjnestZrE+bie7WO62KpWnutvqKOXPUk0atR3cuxU7ULedyTQQ1MTop4mSxu2skajkX1KWObA2E53q+TDdqVy61VKRiZ/Mher2hH/AKqjnF0cbmYYY4M8T4pkY6moH01I7bVVSLGzLpTPW71Ip1HQ4YsNsej6Gy26memtHw0zGu+dEzLqa3187fhhmMXVh+B+Dq0YIpVWnT3TcJG5TVkjcnKnQ1Pit7PnVTMACha02ne0pIjbyAAasgAAAAAAABrPH/BBb8VSSXK2PZQXZ2avXL81Ov8AmRNi/wCZPWimzAb0yWxzvWWJiJ83GmIMG4gwvM5l2tk8LEXJJ0bpRO7npq9W0sR3O5qOarXIioupUXnLLVYNwxWvV9Th61yPXa51IzNfXlmX66/5oRTi6OMi+4fwbiDE8zWWq2TzMVclnVujE3veur1bTrClwbhiiej6bD1rjemxzaRmaevLMvSIjWo1qIiJqRE5hbX+H4YIxdWtOD/ggt+FXx3K6Pjr7s3WxcvzUC/5UXav+ZfUiGzAChfJbJO9pSxER5AANGQAAAAAAAAw/HPB1aMb0qLUJ7muEbcoayNubkTocnxm9nzKhmANq2ms71YmN/NyPifgzxPhaR7qmgfU0jdlVSosjMulctbfWiGHndBaq7DFgub1fXWS3VL11q+amY53zqmZfpr52/HCOcXRxYXC02K632pSntVvqKyXPWkMauRveuxE7VOuIcDYTgej48N2pHJrRVpGLl86F8hghpomxQRMijbsYxqNRPUhtbtCNvw1YjF1aXwLwGspJYrjit0c0jV0mUEa6TEX/qO+N3Jq7V2G35mNjejGNRrWtREaiZIidB9p8dR+mXuON2hltkpvafVYxViJ8EQAOQnDnbh2/Xql/l8f9yQ6JOduHb9eqX+Xx/3JDq9j/wDZ/aUGf8jWJE7lKSkTuUp6xSeAAAAAAAAAZZnuS9CgeA9yXoUZL0KB4D3JehRkvQoBvKQlI2oukmpSQDanAB+v9V/LZP7kZ0ocw8B9yoLXjipnuNbTUcK0EjEkqJWxtV2nGuWaqiZ6l+Y6D45YX9JLP46LeORrazOXwhPjnwXsFk45YX9JLP46LeHHLC/pJZ/HRbxU4LdG+8L2CyccsL+kln8dFvDjlhf0ks/jot4cFuhvC9gsnHLC/pJZ/HRbw45YX9JLP46LeHBbobwvYLJxywv6SWfx0W8OOWF/SSz+Oi3hwW6G8L2CyccsL+kln8dFvDjlhf0ks/jot4cFuhvC9gsnHLC/pJZ/HRbw45YX9JLP46LeHBbobwvYLJxywv6SWfx0W8OOWF/SSz+Oi3hwW6G8L2CyccsL+kln8dFvDjlhf0ks/jot4cFuhvC9gsnHLC/pJZ/HRbw45YX9JLP46LeHBbobwvYLJxywv6SWfx0W8OOWF/SSz+Oi3hwW6G8L2CyccsL+kln8dFvDjlhf0ks/jot4cFuhvC9gsnHLC/pJZ/HRbw45YX9JLP46LeHBbobwvYLJxywv6SWfx0W8OOWF/SSz+Oi3hwW6G8L2CyccsL+kln8dFvDjlhf0ks/jot4cFuhvC9gsnHLC/pJZ/HRbw45YX9JLP46LeHBbobwvYLJxywv6SWfx0W8OOWF/SSz+Oi3hwW6G8L2CyccsL+kln8dFvDjlhf0ks/jot4cFuhvC9gsnHLC/pJZ/HRbw444XVckxJZ/HRbw4LdDeF7BHBPDUwsmp5WSxPTNr43I5rk7FQkMMgAMAfHUfpl7j5qrE1goZ3QVd8ttPM3U6OarjY5O9FXMt8+L8MrKqpiK0KmXNWx7xBq6WnH4Q2pMbroCzcbsNekVp8bHvDjdhr0itPjY945vKyfLP2S8ULyc7cO369Uv8vj/uSG8ON2GvSK0+Nj3jQ3DRcaG540pp6Csp6uFKFjVkglbI1F036s0XbrT5zp9kY711O8x6SizzHA10RO5SkpG5F0l1KeqUlIPcl6FGS9CgeA9yXoUZL0KB4D3JehTzLICVEyTI9CbAABmUXBTjWeFksVlV8b2o5rkqYclRdaLyyvzS456id4mHfNuG3RpzKdWFAzXzS456id4mHfHmlxz1E7xMO+OC3Q5lOrCgZr5pcc9RO8TDvjzS456id4mHfHBbocynVhQM180uOeoneJh3x5pcc9RO8TDvjgt0OZTqwoGa+aXHPUTvEw7480uOeoneJh3xwW6HMp1YUDNfNLjnqJ3iYd8eaXHPUTvEw744LdDmU6sKBmvmlxz1E7xMO+PNLjnqJ3iYd8cFuhzKdWFAzXzS456id4mHfHmlxz1E7xMO+OC3Q5lOrCgZr5pcc9RO8TDvjzS456id4mHfHBbocynVhQM180uOeoneJh3x5pcc9RO8TDvjgt0OZTqwoGa+aXHPUTvEw7480uOeoneJh3xwW6HMp1YUDNfNLjnqJ3iYd8eaXHPUTvEw744LdDmU6sKBmvmlxz1E7xMO+PNLjnqJ3iYd8cFuhzKdWFAzXzS456id4mHfHmlxz1E7xMO+OC3Q5lOrCgZr5pcc9RO8TDvjzS456id4mHfHBbocynVhQM180uOeoneJh3x5pcc9RO8TDvjgt0OZTqwoGa+aXHPUTvEw7480uOeoneJh3xwW6HMp1YUDNfNLjnqJ3iYd8eaXHPUTvEw744LdDmU6sKBmvmlxz1E7xMO+PNLjnqJ3iYd8cFuhzKdWFAzXzS456id4mHfHmlxz1E7xMO+OC3Q5lOrCgZr5pcc9RO8TDvjzS456id4mHfHBbocynVhQM180uOeoneJh3wnBJjlVRPyGqd9TDvjgt0OZTqyHgMxLVW/FL7M+WR9BVxPf5JEVyMkamaORE2ZoiovTq6Doj8oQf9b2D/uNV8FvBlVYUqZbveHRrXvjWKKGN2kkTV2qq7FcuWWrYme3PVtEiv2dTLPHaZiUdtZNZ2r4wr/KEH/W9g/7jCeFfFU9kwHVS22SaKqqJG07ZUjc1Y0dnmqKqalyRU9ZmZZMW4bgxZhuqtE71j8qiOjlRM/JvRc2rlz9vYqmI7LxVneJliNbaZ2mHICqrlVVVVVdaqp4Z5WcDmNaaodHFbY6piLkksNTGjXepyovzoQeaXHPUTvEw75NwW6JeZTqwoGa+aXHPUTvEw7480uOeoneJh3xwW6HMp1YUDNfNLjnqJ3iYd8eaXHPUTvEw744LdDmU6sKBmvmlxz1E7xMO+PNLjnqJ3iYd8cFuhzKdWFAzXzS456id4mHfHmlxz1E7xMO+OC3Q5lOrCgZr5pcc9RO8TDvlEvBVjWCF80tlVkbGq5zlqYckRNaryxw26HMp1YaeKmaZHoXYat1LFzaVETVyUlA6N4G8ZR3nD7LHVSp+ULezRYjl1yQ/FVP3eSvq6TZxxdbrjWWi4Q19BO+CqhdpRyMXWi/anZznQeDeGW0XmGOlvj47bcMkRZHLlDIvSjvi9y6u1SzjyRttKlmwzE8VWzgURSxzxNlhkZJG5M2vY5FRU7FQrJlYAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACiWWOCJ0s0jI42pm573IiInaqgVmsOGXGUdmw++x0sqflC4M0Xo1dccPxlX97kp2ZjGXDLaLNDJS2N7LlcMlRJGrnDGvSrvjdyau059uVyrLvcJ6+vnfPVTO0pJHrrVfsTs5iHJkjbaFnDhmZ4rPlKXrk0qInLmpWXXhW12WpSgATAiRyoVo9FA+2iutxtyqtDX1VKq7fITOZ/RS4ccsU+kt48dLvFkzQGd5Y2iV7454p9Jbx46XeHHPFPpLePHS7xZAN5OGOi98c8U+kt48dLvDjnin0lvHjpd4sgG8nDHRe+OeKfSW8eOl3hxzxT6S3jx0u8WQDeThjovfHPFPpLePHS7w454p9Jbx46XeLIBvJwx0Xvjnin0lvHjpd4cc8U+kt48dLvFkA3k4Y6L3xzxT6S3jx0u8OOeKfSW8eOl3iyAbycMdF7454p9Jbx46XeHHPFPpLePHS7xZAN5OGOi98c8U+kt48dLvDjnin0lvHjpd4sgG8nDHRe+OeKfSW8eOl3hxzxT6S3jx0u8WQDeThjovfHPFPpLePHS7w454p9Jbx46XeLIBvJwx0Xvjnin0lvHjpd4cc8U+kt48dLvFkA3k4Y6L3xzxT6S3jx0u8OOeKfSW8eOl3iyAbycMdF7454p9Jbx46XeHHPFPpLePHS7xZAN5OGOi98c8U+kt48dLvDjnin0lvHjpd4sgG8nDHRe+OeKfSW8eOl3hxzxT6S3jx0u8WQDeThjovfHPFPpLePHS7w454p9Jbx46XeLIBvJwx0Xvjnin0lvHjpd4cc8U+kt48dLvFkA3k4Y6L3xzxT6S3jx0u8OOeKfSW8eOl3iyAbycMdF7454p9Jbx46XeHHPFPpLePHS7xZAN5OGOi98c8U+kt48dLvDjnin0lvHjpd4sgG8nDHRe+OeKfSW8eOl3hxzxT6S3jx0u8WQDeThjovfHPFPpLePHS7w454p9Jbx46XeLIBvJwx0Xvjnin0lvHjpd4cc8U+kt48dLvFkA3k4Y6L3xzxT6S3jx0u8OOeKfSW8eOl3iyAbycMdF7454p9Jbx46XeHHPFPpLePHS7xZAN5OGOi98c8U+kt48dLvDjnin0lvHjpd4sgG8nDHRe+OWKfSW8eOl3i31t1uNxVFrq+qqlTZ5eZz/6qfIM0G8m0QApV6IUK5VMMqnOz1IUAAf/2Q==','aceptado',22);
/*!40000 ALTER TABLE `personalizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` tinytext DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `imagen` longtext DEFAULT NULL,
  `porciones` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (2,'Torta de fresa','bizcochuelo suave con crema de fresa natural',40000.00,'pastel','imagen/torta de fresa.png','8 porciones:0,12 porciones:10000,16 porciones:20000'),(3,'Combinado','mitad vainilla,mitad chocolate sabor:sabor perfecto y equilibrado',55000.00,'pastel','imagen/Pasteles/Combinado.jpg','8 porciones:0,12 porciones:14000,16 porciones:28000'),(4,'Chocomani','chocolante intenso con toque crocante del mani ',40000.00,'pastel','imagen/Pasteles/CHOCOMANI.jpg','8 porciones:0,12 porciones:10000,16 porciones:20000'),(5,'Chocolate','bizcochuelo humedo y rico en cacao puro',35000.00,'pastel','imagen/Pasteles/CHOCOLATE.jpg','8 porciones:0,12 porciones:9000,16 porciones:18000'),(6,'Vainilla con arequipe','Esponjose pastel de arequipe con relleno cremoso de arequipe',48000.00,'pastel','imagen/Pasteles/VAINILLA-CON-AREQUIPE.jpg','8 porciones:0,12 porciones:12000,16 porciones:24000'),(7,'Vainilla chia','Pastel ligero de vainilla con chía que aporta textura sutil',45000.00,'ceroazucar','imagen/CeroAzucar/Cero-Azucar-Vainilla-Con-Chia.jpg','8 porciones:0,12 porciones:11000,16 porciones:22000'),(8,'Vainilla con Chocolate','Bizcocho suave de vainilla con capa de cacao sin azúcar',40000.00,'ceroazucar','imagen/CeroAzucar/Cero-Azucar-Vainilla-con-Chocolate.jpg','8 porciones:0,12 porciones:10000,16 porciones:20000'),(9,'Vainilla Coco','Esponjoso bizcocho de vainilla con toque tropical de coco.',25000.00,'ceroazucar','imagen/CeroAzucar/Cero-Azucar-Vainilla-con-Coco.jpg','8 porciones:0,12 porciones:6000,16 porciones:12000'),(10,'Galletas Rizadas','Crujientes y ligeras, con dulzor natural sin azúcar',25000.00,'ceroazucar','imagen/CeroAzucar/CeroAzucar.jpg','8 porciones:0,12 porciones:6000,16 porciones:12000'),(11,'Cocadas','Dulce coco rallado con textura tierna y aroma natural.',25000.00,'ceroazucar','imagen/CeroAzucar/COCADA.jpg','8 porciones:0,12 porciones:6000,16 porciones:12000'),(12,'Panderos/Cubanos','Galletas suaves, aireadas y de sabor tradicional.',25000.00,'ceroazucar','imagen/CeroAzucar/PANDERO.jpg','8 porciones:0,12 porciones:6000,16 porciones:12000'),(13,'Piono','Delicado pionono relleno con crema y sabor equilibrado.',45000.00,'postres','imagen/Postres/PIONONO.jpg','8 porciones:0,12 porciones:11000,16 porciones:22000'),(14,'Leches Genovesa','Genovesa clásica, esponjosa, con capas de crema.',40000.00,'postres','imagen/Postres/3-LECHES-GENOVESA.jpg','8 porciones:0,12 porciones:10000,16 porciones:20000'),(15,'Manjar Español','Manjar preparado con receta tradicional y cobertura suave',25000.00,'postres','imagen/Postres/MANJAR-ESAPANOL.jpg','8 porciones:0,12 porciones:6000,16 porciones:12000'),(16,'Napoleon','Napoleón crocante con crema pastelera fina.',25000.00,'postres','imagen/Postres/NAPOLEON.jpg','8 porciones:0,12 porciones:6000,16 porciones:12000'),(17,'Selva Negra','Selva Negra con trozos de cereza y chocolate rallado.',25000.00,'postres','imagen/Postres/SELVA-NEGRA_.jpg','8 porciones:0,12 porciones:6000,16 porciones:12000'),(18,'Trufa de Chocolate','Trufa intensa con ganache y detalles dorados.',25000.00,'postres','imagen/Postres/TRUFAS.jpg','8 porciones:0,12 porciones:6000,16 porciones:12000'),(19,'Tres Leches Arequipe','Bizcochuelo húmedo con mezcla de leches y suave toque de arequipe',45000.00,'adicionales','imagen/Adicionales/EMPACADO-ARQUIPE.jpg','8 porciones:0,12 porciones:11000,16 porciones:22000'),(20,'Tres Leches Chocolate','Pastel esponjoso bañado en leches con cacao intenso.',40000.00,'adicionales','imagen/Adicionales/EMPACADO-CHOCOLATE.jpg','8 porciones:0,12 porciones:10000,16 porciones:20000'),(21,'Tres Leches Tradicional','Clásica textura húmeda con sabor equilibrado y cremoso.',25000.00,'adicionales','imagen/Adicionales/EMPACADO-TRADICIONAL.jpg','8 porciones:0,12 porciones:6000,16 porciones:12000'),(22,'Tropical de frutas','Bizcocho suave con mezcla fresca de frutas naturales.',25000.00,'adicionales','imagen/Adicionales/EMPACADO-TROPICAL-DEL-FRUTAS.jpg','8 porciones:0,12 porciones:6000,16 porciones:12000'),(23,'Napoleon','Hojaldre crujiente con crema suave y dulzor delicado.',30000.00,'adicionales','imagen/Adicionales/Napoleon.jpg','8 porciones:0,12 porciones:8000,16 porciones:15000'),(25,'Torta de chocolate','Torta húmeda de 3 pisos con ganache',85000.00,'tortas',NULL,NULL),(26,'Vainilla Dama','Suave bizcocho con aroma dulce y textura esponjosa.',45000.00,'pastel','imagen/Pasteles/VAINILLA-DAMA.jpg','8 porciones:0,12 porciones:11000,16 porciones:22000'),(29,'Pastel temático de unicornio','Diseño realizado para Mariana Torres',55000.00,'personalizado','imagen/personalizados/personal1.png','8 porciones:0,12 porciones:14000,16 porciones:28000'),(30,'Pastel gamer para cumpleaños','Diseño realizado para Daniel Ortiz',55000.00,'personalizado','imagen/personalizados/personal2.png','8 porciones:0,12 porciones:14000,16 porciones:28000'),(31,'Pastel floral elegante','Diseño realizado para Laura Pérez',60000.00,'personalizado','imagen/personalizados/personal3.png','8 porciones:0,12 porciones:15000,16 porciones:30000'),(32,'Pastel infantil temática dinosaurios','Diseño realizado para Santiago Ruiz',55000.00,'personalizado','imagen/personalizados/personal4.png','8 porciones:0,12 porciones:14000,16 porciones:28000'),(33,'Pastel minimalista chocolate','Diseño realizado para Andrea Molina',50000.00,'personalizado','imagen/personalizados/personal5.png','8 porciones:0,12 porciones:12000,16 porciones:25000'),(34,'Pastel graduación','Diseño realizado para Kevin Morales',50000.00,'personalizado','imagen/personalizados/personal6.png','8 porciones:0,12 porciones:12000,16 porciones:25000'),(35,'Diseño personalizado a la medida','Precio según cotización',0.00,NULL,NULL,NULL);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (5,'admin','Administrador del sistema','activo'),(6,'cliente','Cliente frecuente de la pastelería','activo');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-08-06  0:13:32
