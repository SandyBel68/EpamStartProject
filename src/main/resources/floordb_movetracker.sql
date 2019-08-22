DROP TABLE IF EXISTS `movetracker`;
CREATE TABLE `movetracker` (
  `idmove` int(11) NOT NULL AUTO_INCREMENT,
  `idvisitor` int(11) DEFAULT NULL,
  `idroom` int(11) DEFAULT NULL,
  `timestart` timestamp NULL DEFAULT NULL,
  `timefinish` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idmove`),
  KEY `idRoom_idx` (`idroom`),
  KEY `idVisitor_idx` (`idvisitor`),
  CONSTRAINT `idRoom` FOREIGN KEY (`idroom`) REFERENCES `room` (`idroom`),
  CONSTRAINT `idVisitor` FOREIGN KEY (`idvisitor`) REFERENCES `visitor` (`idvisitor`)
)