DROP TABLE IF EXISTS `visitor`;
CREATE TABLE `visitor` (
  `idvisitor` int(11) NOT NULL AUTO_INCREMENT,
  `visitorname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idvisitor`),
    UNIQUE KEY `visitorname_UNIQUE` (`visitorname`)
)