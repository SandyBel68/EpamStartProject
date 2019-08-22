DROP TABLE IF EXISTS `building`;
CREATE TABLE `building` (
  `idbuilding` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idbuilding`),
  UNIQUE KEY `address_UNIQUE` (`address`)
)