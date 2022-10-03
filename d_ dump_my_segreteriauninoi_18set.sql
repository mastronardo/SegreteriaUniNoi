-- MySQL dump 10.13  Distrib 8.0.30, for macos12.4 (arm64)
--
-- Host: localhost    Database: my_segreteriauninoi
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `my_segreteriauninoi`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `my_segreteriauninoi` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `my_segreteriauninoi`;

--
-- Table structure for table `Membri`
--

DROP TABLE IF EXISTS `Membri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Membri` (
  `nome` varchar(256) NOT NULL,
  `cognome` varchar(256) NOT NULL,
  `codice` int NOT NULL AUTO_INCREMENT,
  `password` varchar(256) NOT NULL,
  PRIMARY KEY (`codice`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Membri`
--

LOCK TABLES `Membri` WRITE;
/*!40000 ALTER TABLE `Membri` DISABLE KEYS */;
INSERT INTO `Membri` VALUES ('Luca','Rossi',1,'Unime'),('Tiffany','Smart',2,'Ferrari76'),('Lorraine','Curry',3,'Steph30'),('Patricia','Adams',4,'Famiglia85'),('Barbara','Gill',5,'Computer42'),('James','Miller',6,'Tex56');
/*!40000 ALTER TABLE `Membri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Richieste`
--

DROP TABLE IF EXISTS `Richieste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Richieste` (
  `id` int NOT NULL AUTO_INCREMENT,
  `richiesta` varchar(256) NOT NULL,
  `matricola` int NOT NULL,
  `codice` int DEFAULT NULL,
  `giorno` date DEFAULT NULL,
  `stato` varchar(20) DEFAULT 'In Attesa',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Richieste`
--

LOCK TABLES `Richieste` WRITE;
/*!40000 ALTER TABLE `Richieste` DISABLE KEYS */;
INSERT INTO `Richieste` VALUES (1,'Buongiorno, avrei un dubbio per quanto riguarda dei CFU della TAF-D, è possibile avere un appuntamento?',514430,1,'2022-08-04','Accettata'),(2,'Buongiorno, vorrei chiedere se è possibile essere esonerato poiché in possesso della certificazione C1. Grazie in anticipo.',514430,3,'2022-08-04','Integrazione'),(3,'Salve, vorrei sapere se è possibile scegliere come Materia Tecnlogia e innovazione. ',514430,2,'2022-08-05','Rifiutata'),(5,'Buongiorno, ho terminato le 150 ore di Inglese, è possibile effettuare il test? ',514430,3,'2022-09-01','Rifiutata'),(6,'Vediamo se il client-server funziona',514430,4,'2022-09-01','Accettata'),(7,'Salve, la materia Diritto è solo orale o anche scritta? ',514430,2,'2022-09-04','Accettata');
/*!40000 ALTER TABLE `Richieste` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Risposte`
--

DROP TABLE IF EXISTS `Risposte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Risposte` (
  `id` int NOT NULL,
  `risposta` varchar(256) NOT NULL,
  `utente` int NOT NULL,
  `codice` int NOT NULL,
  `giorno` date NOT NULL,
  `stato` varchar(256) DEFAULT 'In Attesa',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Risposte`
--

LOCK TABLES `Risposte` WRITE;
/*!40000 ALTER TABLE `Risposte` DISABLE KEYS */;
INSERT INTO `Risposte` VALUES (1,'Certamente Matteo, la prima data disponibile è il 28 agosto alle 11:00.',1,1,'2022-08-04','Accettata'),(2,'Buongiorno Matteo, è certamente possibile ma necessito di una copia del certificato. Quando possibile, invii una nuova richiesta con un link OneDrive. Buona Giornata.',1,3,'2022-08-05','Accettata'),(3,'Mi spiace, ma le iscrizioni sono terminate. Buona giornata.',1,2,'2022-08-05','Rifiutata'),(4,'Mi dispiace Matteo, dovrai aspettare che inizi il nuovo A.A. per conoscere le nuove date',1,3,'2022-09-01','Rifiutata'),(5,'Mi spiace Matteo, dovrai aspettare che inizi il prossimo A.A. per conoscere le date.',1,3,'2022-09-01','Rifiutata'),(6,'Eh si.',1,4,'2022-09-18','Accettata'),(7,'Salve Matteo, è da farsi solo oralmente.',1,2,'2022-09-18','Accettata');
/*!40000 ALTER TABLE `Risposte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Studenti`
--

DROP TABLE IF EXISTS `Studenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Studenti` (
  `nome` varchar(25) NOT NULL,
  `cognome` varchar(25) NOT NULL,
  `matricola` int NOT NULL,
  `aa` varchar(9) NOT NULL,
  `password` varchar(25) NOT NULL,
  PRIMARY KEY (`matricola`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Studenti`
--

LOCK TABLES `Studenti` WRITE;
/*!40000 ALTER TABLE `Studenti` DISABLE KEYS */;
INSERT INTO `Studenti` VALUES ('Thomas','Luppolo',458978,'2008-209','birra56'),('Edward','Collins',475621,'2014-2015','montagna45'),('Ana','Mcintire',475622,'2013-2014','intero49'),('Kathleen','Duarte',487963,'2018-2019','alternativo45'),('Paulo','Dybala',492110,'2016-2017','forzajuve10'),('John','Parker',496532,'2014-2015','spiderman56'),('Lawrence','Lamontagne',498751,'2016-2017','collina89'),('Terry','Griffin',502363,'2019-2020','peter89'),('Anna','Sale',504956,'2019-2020','bando12'),('Antonino','Mastronardo',513936,'2020-2021','prova3233'),('Matteo','Piccadaci',514430,'2020-2021','Cisco1234');
/*!40000 ALTER TABLE `Studenti` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-18 14:30:56
