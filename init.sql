-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: bdrenthub
-- ------------------------------------------------------
-- Server version	9.0.1

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
-- Table structure for table `alquiler`
--

DROP TABLE IF EXISTS `alquiler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alquiler` (
  `renovacion_automatica` bit(1) NOT NULL,
  `fecha_fin` datetime(6) DEFAULT NULL,
  `fecha_inicio` datetime(6) DEFAULT NULL,
  `idalquiler` bigint NOT NULL AUTO_INCREMENT,
  `iduser` bigint DEFAULT NULL,
  `idvehiculo` bigint NOT NULL,
  PRIMARY KEY (`idalquiler`),
  KEY `FKdl8xp9sh04vb5v2ul73wb1qiv` (`iduser`),
  KEY `FK97r34acf22eco0t1g03b2hqav` (`idvehiculo`),
  CONSTRAINT `FK97r34acf22eco0t1g03b2hqav` FOREIGN KEY (`idvehiculo`) REFERENCES `vehiculo` (`idvehiculo`),
  CONSTRAINT `FKdl8xp9sh04vb5v2ul73wb1qiv` FOREIGN KEY (`iduser`) REFERENCES `users` (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alquiler`
--

LOCK TABLES `alquiler` WRITE;
/*!40000 ALTER TABLE `alquiler` DISABLE KEYS */;
INSERT INTO `alquiler` VALUES (_binary '\0','2025-07-08 00:00:00.000000','2025-06-08 00:00:00.000000',1,2,2),(_binary '','2025-07-08 00:00:00.000000','2025-06-08 00:00:00.000000',2,2,15),(_binary '','2025-07-08 00:00:00.000000','2025-06-08 00:00:00.000000',3,2,16),(_binary '','2025-07-08 17:55:28.793000','2025-06-08 17:55:28.793000',4,2,19),(_binary '','2025-07-08 21:08:23.670000','2025-06-08 21:08:23.670000',5,4,11),(_binary '','2025-07-08 21:09:01.395000','2025-06-08 21:09:01.395000',6,4,12),(_binary '','2025-07-08 21:13:54.485000','2025-06-08 21:13:54.485000',9,4,13),(_binary '','2025-07-08 21:43:43.917000','2025-06-08 21:43:43.917000',11,4,15),(_binary '','2025-07-08 21:54:35.182000','2025-06-08 21:54:35.182000',12,4,14),(_binary '','2025-07-12 13:12:05.000000','2025-06-12 13:12:05.000000',13,4,16),(_binary '','2025-07-12 13:36:00.362000','2025-06-12 13:36:00.362000',15,4,16),(_binary '','2025-07-12 13:51:09.891000','2025-06-12 13:51:09.891000',16,4,1),(_binary '','2025-07-14 18:54:14.814000','2025-06-14 18:54:14.814000',17,11,33);
/*!40000 ALTER TABLE `alquiler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direccion` (
  `iddireccion` bigint NOT NULL AUTO_INCREMENT,
  `calle` varchar(255) DEFAULT NULL,
  `ciudad` varchar(255) DEFAULT NULL,
  `codigo_postal` varchar(255) DEFAULT NULL,
  `pais` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`iddireccion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
INSERT INTO `direccion` VALUES (1,'dsad','aaaaaaa','41410','España'),(2,'Bulerias 19','Carmona','41410','España');
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pago` (
  `cantidad` double DEFAULT NULL,
  `cvv` int NOT NULL,
  `fecha_pago` datetime(6) DEFAULT NULL,
  `idalquiler` bigint NOT NULL,
  `idpago` bigint NOT NULL AUTO_INCREMENT,
  `fecha_tarjeta` varchar(255) DEFAULT NULL,
  `tarjeta` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idpago`),
  KEY `FK63m1h5rmlm29hhqfef84w3gaq` (`idalquiler`),
  CONSTRAINT `FK63m1h5rmlm29hhqfef84w3gaq` FOREIGN KEY (`idalquiler`) REFERENCES `alquiler` (`idalquiler`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
INSERT INTO `pago` VALUES (200,123,'2025-06-08 17:55:28.798000',4,1,'10/25','1234123412341234'),(100,123,'2025-06-08 21:08:23.675000',5,2,'10/25','1234123412341234'),(200,123,'2025-06-08 21:09:01.398000',6,3,'10/25','1234123412341234'),(100,123,'2025-06-08 21:13:54.488000',9,6,'10/25','1234123412341234'),(10000,123,'2025-06-08 21:43:43.920000',11,8,'10/25','1234123412341234'),(100,123,'2025-06-08 21:54:35.206000',12,9,'10/25','1234123412341234'),(8,123,'2025-06-09 19:06:09.252000',13,10,'10/25','1234123412341234'),(8,0,'2025-06-12 13:11:00.021000',13,13,'MM/AA','XXXX-XXXX-XXXX-XXXX'),(8,0,'2025-06-12 13:19:00.015000',13,15,'MM/AA','XXXX-XXXX-XXXX-XXXX'),(8,152,'2025-06-12 13:36:00.364000',15,17,'12/25','1234123412312341'),(288,144,'2025-06-12 13:51:09.891000',16,18,'10/28','5757575875785785'),(311,123,'2025-06-14 18:54:14.818000',17,19,'12/26','4526565262626655');
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `idrole` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`idrole`),
  UNIQUE KEY `UKofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_AUTONOMA'),(3,'ROLE_EMPRESA');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `enabled` bit(1) NOT NULL,
  `iddireccion` bigint DEFAULT NULL,
  `iduser` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(120) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UKokd2fn055aqq3mbywfengs8rj` (`iddireccion`),
  CONSTRAINT `FKe7lnkp47nrws9ns3ii5pifqu2` FOREIGN KEY (`iddireccion`) REFERENCES `direccion` (`iddireccion`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (_binary '',NULL,1,'prfes','fsdfdsf','dsfdsf','$2a$10$Vmx7RJcmnAW4j3Tavt0Vv.dOdA3e55.NWHbMz/Wt8dzFjAUZ/bJrK'),(_binary '',NULL,2,'prueba@gmail.com','prueba','prueba','$2a$10$ymj2w5TKd26E2Htr82smveBWPlAmYFpK8l2EPZiwj7218qoQA/Clu'),(_binary '',NULL,3,'admin@gmail.com','Admin','Admin','$2a$10$87OxalOOosyJdl7YB3fd7.IKBmT4zI2b6UxgcknnJwbltrpMJJj/C'),(_binary '',1,4,'javier.rodriguez.guerrero.alu@iesjulioverne.es','Rodriguez','Javier','$2a$10$Ju1BxnuSsdIDproNUCNgseisNH.37ycMjXFGvIKPODesXSBHeqPlS'),(_binary '',NULL,5,'prueba10@gmail.com','dsad','dsa','$2a$10$fOo5jDM9tS1Palh145NW4eCaAAUbh92sSK2J/yaOwwReqAmrsKw4S'),(_binary '',NULL,6,'prueba11@gmail.com','fdsf','fds','$2a$10$0iH3ORVgxVkdxtaFpiVITONm00nw1buUGOntvdRnA/2WVp51m0vi6'),(_binary '',NULL,7,'prueba12@gmail.com','fds','fdsf','$2a$10$NVV8gW8Ye6OT3YHpLAz9JOZjsrEP9aoMwajV/zn3k3HjK0f/Ci9t6'),(_binary '',NULL,8,'prueba14@gmail.com','dsf','fdsf','$2a$10$wcrNMwr2cQfodwwQQPXWDe.lJuwMwdnyrNOlS4pQcst/nouXL19wS'),(_binary '',NULL,9,'prueba20@gmail.com','df','fdsf','$2a$10$ncZf45a7XvIxKLq2M0af5Oz89YSQjH5xtu0d5uGCM1tmiA3wFJ2ZO'),(_binary '',NULL,10,'javierzorrito@gmail.com','Rodriguez','Javier','$2a$10$FjgCyYFj.a1kCqIeGaQ6QeDZ2YzFzqJNX5mxZMP2pWozTZ/SSm4iW'),(_binary '',2,11,'javierrg2614@gmail.com','Rodriguez','Javier','$2a$10$DaLlZJYJVnvt6iRjpfeTVOzzKDrBP62U07qwpnZ4SF8rzmxTK42B.');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `idrole` bigint NOT NULL,
  `iduser` bigint NOT NULL,
  KEY `FK8ndyepe59fcih42vjyv8d805q` (`idrole`),
  KEY `FKbjchy6ygb5dcwnry7hv53mrcl` (`iduser`),
  CONSTRAINT `FK8ndyepe59fcih42vjyv8d805q` FOREIGN KEY (`idrole`) REFERENCES `roles` (`idrole`),
  CONSTRAINT `FKbjchy6ygb5dcwnry7hv53mrcl` FOREIGN KEY (`iduser`) REFERENCES `users` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (2,1),(2,2),(1,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculo` (
  `precio_mes` double DEFAULT NULL,
  `idvehiculo` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `km` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `matricula` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `fuel` varchar(255) DEFAULT NULL,
  `transmission` varchar(255) DEFAULT NULL,
  `disponible` bit(1) NOT NULL,
  PRIMARY KEY (`idvehiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` VALUES (288,1,'Coche urbano eficiente y compacto.','10.000 km','Toyota','1234ABC','Aygo X Cross','Compacto','Compacto','Gasolina','Manual',_binary '\0'),(296,2,'Confort y estilo en cada trayecto.','15.500 km','Peugeot','2345BCD','208','Compacto','Compacto','Gasolina','Manual',_binary '\0'),(298,3,'Ideal para desplazamientos diarios.','20.000 km','Opel','3456CDE','Corsa','Compacto','Compacto','Gasolina','Manual',_binary '\0'),(348,4,'Versión híbrida muy eficiente.','5.000 km','Toyota','4567DEF','Yaris','Compacto','Compacto','Híbrido','Automático',_binary '\0'),(320,5,'Espacioso y cómodo para viajes largos.','30.000 km','Ford','5678EFG','Focus','Compacto','Compacto','Diésel','Manual',_binary '\0'),(310,6,'Ideal para ciudad y recorridos cortos.','25.000 km','Renault','6789FGH','Clio','Compacto','Compacto','Gasolina','Manual',_binary '\0'),(450,7,'Deportivo y premium, con alto rendimiento.','12.000 km','BMW','7890GHI','Serie 1','Sedán','Sedán','Gasolina','Automático',_binary '\0'),(330,9,'Ágil y juvenil, perfecto para moverse por la ciudad.','18.000 km','Seat','9012IJK','Ibiza','Compacto','Compacto','Gasolina','Manual',_binary '\0'),(100,10,'edwqdwadsad','100','Prueba','Prueba','Prueba','Prueba','Prueba','Prueba','Prueba',_binary '\0'),(100,11,'Prueba2','Prueba2','Prueba2','Prueba2','Prueba2','Prueba2','Prueba2','Prueba2','Prueba2',_binary '\0'),(200,12,'Prueba3','Prueba3','Prueba3','Prueba3','Prueba3','Prueba3','Prueba3','Prueba3','Prueba3',_binary '\0'),(100,13,'Prueba4','100','Prueba4','Prueba4','Prueba4','Prueba4','Prueba4','Prueba4','Prueba4',_binary '\0'),(100,14,'Prueba5','10000','Prueba5','Prueba5','Prueba5','Prueba5','Prueba5','Prueba5','Prueba5',_binary '\0'),(10000,15,'Prueba6','522565','Prueba6','Prueba6','Prueba6','Prueba6','Prueba6','Prueba6','Prueba6',_binary '\0'),(8,16,'Prueba8Prueba8Prueba8','52522','Prueba8','Prueba8','Prueba8','Prueba8','Prueba8','Prueba8','Prueba8',_binary '\0'),(100,17,'dsfdsf','1000','fsf','fdsf','fsd','dsf','dsf','fsdf','dsf',_binary '\0'),(100,18,'dgfdg','100','Prueba45','sdf','fds','fds','gfd','dfsg','fg',_binary '\0'),(200,19,'sadfdsf','100','prueba','32432','prueba','dsfds','aaa','diesel','dsad',_binary '\0'),(248,20,'Ágil y eficiente para ciudad','12.000','Seat','ABC1234','Ibiza 1.0 MPI Reference Salta','Compacto','Compacto','Gasolina','Manual',_binary ''),(277,21,'Confortable y bien equipado','15.000','Opel','DEF5678','Corsa 1.2T XHL 74kW GS','Compacto','Compacto','Gasolina','Manual',_binary ''),(279,24,'Espacioso y económico','10.500','Škoda','GHI9012','Fabia 1.0 TSI Selection','Compacto','Compacto','Gasolina','Manual',_binary ''),(282,25,'Diseño exterior dinámico con faros delanteros Full LED, pantalla digital configurable de 10 pulgadas y acabado interior de textiles premium. La transmisión automática de 6 velocidades garantiza cambios rápidos y confortables.','11.000','Peugeot','JKL3456','208 Allure 100 SS 6 Vel','Compacto','Compacto','Gasolina','Automática',_binary ''),(289,26,'Versión Style XL con techo bitono, climatizador bizona, sensores de lluvia y luz, y paquete Style Plus. Su altura y tecnología Grip Control permiten afrontar firmes deslizantes con facilidad.','9.500','Seat','MNO7890','Arona 1.0 TSI Style XL','SUV pequeño','SUV','Gasolina','Automático',_binary ''),(296,27,'Stepway con suspensión elevada, protecciones de carrocería y cámara de marcha atrás. Su motor ECO-G, combinado gasolina/GLP, reduce costes de combustible, mientras el sistema de infoentretenimiento Media Nav facilita la navegación.','14.000','Dacia','PQR2345','Sandero Stepway Expression Go 74kW ECO-G','Compacto','Compacto','GLP','Manual',_binary ''),(298,28,'Climatizador automático, detector fatiga, App-Connect y acceso sin llave. TSI de 95 CV con gran silencio de marcha.','12.000','Volkswagen','STU5678','Polo MATCH 1.0 TSI 81 kW','Compacto','Compacto','Gasolina','Manual',_binary ''),(551,29,'Motor diésel de 136 CV con etiqueta C, control de crucero adaptativo, faros LED High Performance y tapicería Artico. Equilibrio perfecto entre prestaciones y bajos consumos en autopista.','12400','Mercedes','ABC456B','Clase A 180d','Compacto','Compacto','Diésel','Automático',_binary ''),(305,30,'Pantalla táctil 8″, Android Auto/Apple CarPlay, cámara trasera y control de crucero. Motor turbo de 100 CV con respuesta viva y consumos reducidos.','10 300','Hyundai','VWX6789','i20 1.0 TGDi Klass','Compacto','Compacto','Gasolina','Manual',_binary ''),(412,31,'Muy práctico en ciudad: climatizador manual, frenada de emergencia, sensores traseros y Bluetooth. Motor 67 CV ágil y fiable.','8.200','Hyundai','YZA7890','i10 1.0 Klass','Compacto','Compacto','Gasolina','Manual',_binary ''),(372,32,'Diseño coupé, faros LED y Drive Assist con control crucero inteligente. Motor 1.0 DIG-T de 117 CV que combina confort y dinamismo.','18.500','Nissan','BCD8901','Juke 1.0 DIG-T E6D-F Acenta','SUV pequeño','SUV','Gasolina','Manual',_binary ''),(311,33,'Sonido Bose, navegador integrado, asientos calefactables y alerta de ángulo muerto. Turbo de 114 CV con caja manual de 6 marchas para un tacto deportivo.','18.600','Nissan','CDE9012','Juke DIG-T 84 kW 6MT Acenta','SUV pequeño','SUV','Gasolina','Manual',_binary '\0'),(321,34,'Híbrido autorecargable 116 CV, i-Activsense (frenada emergencia, lector de señales), acabado Centre-Line y cambio CVT para máximo confort y consumo medio muy bajo.','12.800','Mazda','DEF0123','2 Hybrid 1.5 CVT Centre-Line','Hatchback','Compacto','Híbrido','Automático',_binary ''),(325,35,'SUV compacto con mild-hybrid 48 V, faros LED, cámara 360°, asistente carril y llantas 17″. Motor 100 CV con apoyo eléctrico para eficiencia extra.','13.200','Hyundai','FGH2345','Bayon 1.0 T-GDI 48V Maxx','SUV','SUV','Gasolina','Manual',_binary ''),(328,36,'ST-Line con suspensión deportiva, SYNC 3, cargador inalámbrico, alerta punto ciego y EcoBoost 125 CV + mild-hybrid para un perfecto equilibrio entre potencia y eficiencia.','17.200','Ford','JKL6789','Focus 1.0T EcoBoost mHEV ST-Line','Compacto','Compacto','Híbrido','Manual',_binary ''),(380,37,'Híbrido 116 CV autorrecargable con Toyota Safety Sense (precolisión, crucero adaptativo, lector señales), pantalla 7″ y acabados Active Plus.','9.700','Toyota','LMN8901','Yaris 120H Active Plus','Hatchback','Compacto','Híbrido','Automática',_binary '');
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo_imagenes`
--

DROP TABLE IF EXISTS `vehiculo_imagenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculo_imagenes` (
  `vehiculo_id` bigint NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  KEY `FKci0bn3xpf7jng1tn8p8ex8tjy` (`vehiculo_id`),
  CONSTRAINT `FKci0bn3xpf7jng1tn8p8ex8tjy` FOREIGN KEY (`vehiculo_id`) REFERENCES `vehiculo` (`idvehiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo_imagenes`
--

LOCK TABLES `vehiculo_imagenes` WRITE;
/*!40000 ALTER TABLE `vehiculo_imagenes` DISABLE KEYS */;
INSERT INTO `vehiculo_imagenes` VALUES (1,'https://via.placeholder.com/300x180?text=Aygo+1'),(1,'https://via.placeholder.com/300x180?text=Aygo+2'),(2,'https://via.placeholder.com/300x180?text=208+1'),(2,'https://via.placeholder.com/300x180?text=208+2'),(3,'https://via.placeholder.com/300x180?text=Corsa+1'),(3,'https://via.placeholder.com/300x180?text=Corsa+2'),(4,'https://via.placeholder.com/300x180?text=Yaris+1'),(4,'https://via.placeholder.com/300x180?text=Yaris+2'),(5,'https://placehold.co/300x180?text=Focus+1'),(5,'https://placehold.co/300x180?text=Focus+2'),(6,'https://placehold.co/300x180?text=Clio+1'),(6,'https://placehold.co/300x180?text=Clio+2'),(7,'https://placehold.co/300x180?text=Serie+1+1'),(7,'https://placehold.co/300x180?text=Serie+1+2'),(9,'https://placehold.co/300x180?text=Ibiza+1'),(9,'https://placehold.co/300x180?text=Ibiza+2'),(10,'/images/54633ee7-9e6b-45bd-85d3-82adc5ba41b4.jpg'),(10,'/images/b4a329f2-4d6f-4ccf-9c55-49248eb25059.png'),(11,'/images/03ff407b-bd28-469a-b16f-d4463b7df674.png'),(12,'/images/b2b53494-cc34-4d31-a769-51f71132e9b2.png'),(13,'1e7cb6ec-b806-4db8-a43e-e43487e73d69.png'),(14,'http://localhost:8080/images/058062f7-d5f6-4ed1-b112-ec52159e89ab.png'),(15,'http://localhost:8080/images/83790b88-dfb2-4283-9dec-030c59cfe76a.png'),(15,'http://localhost:8080/images/5d4b0ab8-030f-4fda-aecd-7c4d04c18ea8.png'),(15,'http://localhost:8080/images/4b779d62-29f1-4121-b1cc-b0250987b4c3.png'),(16,'http://localhost:8080/images/4ae62592-f59f-46f4-a69a-340cff70a629.png'),(17,'http://localhost:8080/images/5d31a70b-9d0f-4d00-9402-0779dfe8e285.png'),(18,'http://localhost:8080/images/fd1d44a1-6446-4d6e-b293-21d8c4026289.png'),(19,'http://localhost:8080/images/36e8e943-2085-4332-9de8-85d336319053.png'),(20,'http://localhost:8080/images/e80872c5-584c-46f5-8f43-1ae3038fd668.png'),(20,'http://localhost:8080/images/336f74ae-4cdb-4fa7-bd61-820f3c294f00.png'),(20,'http://localhost:8080/images/fd9d6f7f-c624-4521-b31d-6bfe375e6a3e.png'),(20,'http://localhost:8080/images/d35f637b-c419-434f-a11d-e1c2847c6341.png'),(20,'http://localhost:8080/images/71dd99bf-8ef4-4fe8-8f7d-a9666eff3032.png'),(21,'http://localhost:8080/images/a52ee492-838b-48c5-bde5-c10881cb153d.png'),(21,'http://localhost:8080/images/aa0b5100-ba70-45fe-a45e-adadf5efb96a.png'),(21,'http://localhost:8080/images/e4c539c1-29fd-43dc-8bbb-1aafa6a3a552.png'),(21,'http://localhost:8080/images/1bd5c830-7db8-416d-b8fe-08cec7b010ae.png'),(21,'http://localhost:8080/images/2457e32a-bc88-49dd-93da-e5227553ce05.png'),(24,'http://localhost:8080/images/fe73ea24-c003-4c1f-88cc-766e95330f87.png'),(24,'http://localhost:8080/images/4c8e79ef-ecbd-4b65-b776-85f6bb3b7205.png'),(24,'http://localhost:8080/images/716bef70-39a2-4306-bc28-25d86a56096c.png'),(24,'http://localhost:8080/images/7f6852a2-f5ee-445b-b281-e834698cc58c.png'),(24,'http://localhost:8080/images/f0e33e68-92ce-460f-a3d7-901b08226a4f.png'),(25,'http://localhost:8080/images/29a1d46f-0a22-476d-830e-80101a4d1122.png'),(25,'http://localhost:8080/images/01111e84-1d8c-4adb-a73d-3d9cbb13155d.png'),(25,'http://localhost:8080/images/f56cc188-4bd3-47f7-80f1-419617e08f3f.png'),(25,'http://localhost:8080/images/acf57731-62a1-42e7-9f38-267cbdfd1333.png'),(25,'http://localhost:8080/images/b1de13a4-d72f-4627-8928-df1550edc9c9.png'),(26,'http://localhost:8080/images/6b969017-baf3-44a6-b7bc-335b43becef9.png'),(26,'http://localhost:8080/images/a3896514-cf28-4589-9712-f6dc971c4269.png'),(26,'http://localhost:8080/images/76600684-be1f-4954-a436-dd7408b56f3b.png'),(26,'http://localhost:8080/images/4a653c10-3e51-48ec-909c-f801f6c576bc.png'),(26,'http://localhost:8080/images/f9a1ebe5-4f14-4e8b-a296-fa92103ab5ad.png'),(27,'http://localhost:8080/images/2cfe7f0b-4dc2-42c5-b790-018cad4c75a3.png'),(27,'http://localhost:8080/images/7b9b7822-ce0b-447a-a5bd-c40067cb63d4.png'),(27,'http://localhost:8080/images/e59f7664-f44e-4a24-925b-5e51d1e877f2.png'),(27,'http://localhost:8080/images/f65264e6-302f-4e39-afac-a0211b545da8.png'),(27,'http://localhost:8080/images/c58ca0c9-0e58-4463-9e2e-72dfbeaa332d.png'),(28,'http://localhost:8080/images/543268db-0a5c-497e-b761-60cc63263feb.png'),(28,'http://localhost:8080/images/5bd26c8e-f57a-4bb9-b95d-cdc9b5345727.png'),(28,'http://localhost:8080/images/55d1f446-1307-4f3d-9826-6a536044eeec.png'),(28,'http://localhost:8080/images/ea9a0f6b-d6db-44b0-b9f7-bf165b1e64f4.png'),(28,'http://localhost:8080/images/6b598546-d241-4d7e-823e-805f2511f367.png'),(29,'http://localhost:8080/images/01c3857e-2646-4deb-aec2-1a5e28b14557.png'),(29,'http://localhost:8080/images/574102f6-f40a-43e8-8cab-dcf1c6f339b5.png'),(29,'http://localhost:8080/images/89394083-5c0f-42a3-936f-9f76dcafa098.png'),(29,'http://localhost:8080/images/eec76344-6902-46cb-9551-7e05943d8cb3.png'),(29,'http://localhost:8080/images/79a47df3-3c34-4e6e-84af-ef8520924fdd.png'),(30,'http://localhost:8080/images/50f4b9e5-b289-40e1-bc9f-2771b3b1f8af.png'),(30,'http://localhost:8080/images/16215fd9-f21e-41eb-8c4f-c272c21895eb.png'),(30,'http://localhost:8080/images/dbb53614-b065-49a1-b699-6fd1dcc8ff65.png'),(30,'http://localhost:8080/images/ad0dd730-e3ab-42f5-b54a-e086f5889421.png'),(30,'http://localhost:8080/images/b7bb4a26-893e-45e5-b858-b2cbd3f833f8.png'),(31,'http://localhost:8080/images/06da73f4-e623-475b-bf6f-b13dd8702262.png'),(31,'http://localhost:8080/images/abe4e2e2-7dfc-41b3-8cb2-b40ac1d19b12.png'),(31,'http://localhost:8080/images/7033466f-a446-4f73-a1e0-73fa353b4b15.png'),(31,'http://localhost:8080/images/63ec849b-c5b4-4445-bf59-914282fd80cb.png'),(31,'http://localhost:8080/images/94f2d731-dc0c-4167-a4e6-25a7cdcdf689.png'),(32,'http://localhost:8080/images/6c710cb7-a755-4cad-b1c2-f766f4470a96.png'),(32,'http://localhost:8080/images/a3c073c3-63ff-4aa9-8f03-3e20ded4109b.png'),(32,'http://localhost:8080/images/1650fe15-d3bf-4e0d-9fe8-4645f6c7fda7.png'),(32,'http://localhost:8080/images/9b2f4f8c-bfe0-420b-8418-abc625bfc525.png'),(32,'http://localhost:8080/images/f9dfcd90-3c46-470a-8f62-4af03217797d.png'),(33,'http://localhost:8080/images/d9dbd8b5-116f-49d6-bfd7-2844c6a8fe41.png'),(33,'http://localhost:8080/images/dc54a85e-0e26-4d2d-a72d-b25b80a071bc.png'),(33,'http://localhost:8080/images/b87aba75-019e-4fed-b711-fa0385f6847b.png'),(33,'http://localhost:8080/images/306fe6ad-47be-4b3d-94a2-368dea2c3259.png'),(33,'http://localhost:8080/images/72e56f72-337d-4db2-a3bc-37586e4093fd.png'),(34,'http://localhost:8080/images/38ed29b5-52b8-4567-8ac3-e462394c1c64.png'),(34,'http://localhost:8080/images/2d21caf5-c88d-4b2e-898f-79fe3e3ec7a5.png'),(34,'http://localhost:8080/images/0e83521c-3fb7-4919-a65f-e616d15c6ce1.png'),(34,'http://localhost:8080/images/e349e8b0-a068-44ac-a842-6f52492c32d0.png'),(34,'http://localhost:8080/images/c4df84ff-dd90-4437-aa83-2645cc93c1ba.png'),(35,'http://localhost:8080/images/8c564ba6-44b5-40f3-8cac-50e04d551ade.png'),(35,'http://localhost:8080/images/6e9842a7-6ac2-4d83-bb89-74ddac470d75.png'),(35,'http://localhost:8080/images/6620e003-1a73-4b7f-8ecb-5ce3fc459802.png'),(35,'http://localhost:8080/images/5fa5fe25-8469-4055-877a-9fec5f973579.png'),(35,'http://localhost:8080/images/51f52f78-5a4e-475f-b440-e1cd826cb3a6.png'),(36,'http://localhost:8080/images/70d0b25d-1c47-490b-8659-f2151157d3c8.png'),(36,'http://localhost:8080/images/ee673dec-63eb-4e1f-944e-2441040e8471.png'),(36,'http://localhost:8080/images/8fa45c46-6a1e-4348-9864-d8845fcccb61.png'),(36,'http://localhost:8080/images/bb7ea076-aafb-45e9-b75c-88dfc0f1d5f6.png'),(36,'http://localhost:8080/images/e7da8dc2-9309-46cc-9557-7990c9ef1be1.png'),(37,'http://localhost:8080/images/bcda05ed-08e4-4962-a062-fc4ad898a640.png'),(37,'http://localhost:8080/images/6ec8e38f-6796-46b7-a354-57531f1f9a90.png'),(37,'http://localhost:8080/images/c128c7a5-fa23-4c81-84f8-59f2de2a56a6.png'),(37,'http://localhost:8080/images/368d9e35-6f6c-4cdc-a984-cafedf281068.png'),(37,'http://localhost:8080/images/0c68ce56-d268-40ce-9577-85b1e71c2193.png');
/*!40000 ALTER TABLE `vehiculo_imagenes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-14 21:04:56
