DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `idroom` int(11) NOT NULL AUTO_INCREMENT,
  `numberRoom` int(11) DEFAULT NULL,
  `idfloor` int(11) DEFAULT NULL,
  `x1` varchar(45) DEFAULT NULL,
  `y1` varchar(45) DEFAULT NULL,
  `x2` varchar(45) DEFAULT NULL,
  `y2` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idroom`),
  UNIQUE KEY `numberRoom_UNIQUE` (`numberRoom`),
  KEY `idFloor_idx` (`idfloor`),
  CONSTRAINT `idFloor` FOREIGN KEY (`idfloor`) REFERENCES `floor` (`idfloor`)
)