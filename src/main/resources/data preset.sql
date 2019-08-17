INSERT INTO `building` (`address`) VALUES ('Zastavskaya 22');

INSERT INTO `floor` (`numberfloor`, `idbuilding`, `maxYsize`,`maxXsize`) VALUES (1, (SELECT idbuilding FROM building WHERE address = 'Zastavskaya 22'),'600', '1300');

INSERT INTO `room` (`numberRoom`, `idfloor`, `x1`,`x2`, `y1`,`y2`) VALUES (
101, (SELECT idfloor FROM floor WHERE numberFloor = 1 AND maxYsize = '600'AND maxXsize = '1300'), '0', '0', '400', '400');

INSERT INTO `room` (`numberRoom`, `idfloor`, `x1`,`x2`, `y1`,`y2`) VALUES (102, (SELECT idfloor FROM floor WHERE numberFloor = 1 AND maxYsize = '600'AND maxXsize = '1300'), '400', '0', '1000', '400');

INSERT INTO `room` (`numberRoom`, `idfloor`, `x1`,`x2`, `y1`,`y2`) VALUES (  103, (SELECT idfloor FROM floor WHERE numberFloor = 1 AND maxYsize = '600'AND maxXsize = '1300'), '0', '400', '1300', '400');

INSERT INTO `room` (`numberRoom`, `idfloor`, `x1`,`x2`, `y1`,`y2`) VALUES ( 104, (SELECT idfloor FROM floor WHERE numberFloor = 1 AND maxYsize = '600'AND maxXsize = '1300'), '0', '400', '400', '600');

INSERT INTO `room` (`numberRoom`, `idfloor`, `x1`,`x2`, `y1`,`y2`) VALUES (
 105, (SELECT idfloor FROM floor WHERE numberFloor = 1 AND maxYsize = '600'AND maxXsize = '1300'), '400', '400', '1300', '600');

INSERT INTO `visitor` (`visitorname`) VALUES ('John Bell');
INSERT INTO `visitor` (`visitorname`) VALUES ('Kate White');