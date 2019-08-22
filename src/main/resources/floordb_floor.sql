DROP TABLE IF EXISTS `floor`;
CREATE TABLE `floor` (
  `idFloor` int(11) NOT NULL AUTO_INCREMENT,
  `numberFloor` int(11) DEFAULT NULL,
  `idbuilding` int(11) DEFAULT NULL,
  `maxYsize` varchar(45) DEFAULT NULL,
  `maxXsize` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idFloor`),
  KEY `idBuilding_idx` (`idbuilding`),
  UNIQUE KEY `numberFloor_UNIQUE` (`numberFloor`),
  CONSTRAINT `idBuilding` FOREIGN KEY (`idbuilding`) REFERENCES `building` (`idbuilding`)
)