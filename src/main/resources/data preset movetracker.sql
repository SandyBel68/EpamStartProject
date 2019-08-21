
INSERT INTO `movetracker`(`idvisitor`, `idroom`, `timestart`,`timefinish`) VALUES ((SELECT idvisitor FROM visitor WHERE visitorname = 'John Bell'), (SELECT idroom FROM room WHERE numberRoom = 101), '2019-08-18 15:00:01', '2019-08-18 15:01:20');
INSERT INTO `movetracker`(`idvisitor`, `idroom`, `timestart`,`timefinish`) VALUES ((SELECT idvisitor FROM visitor WHERE visitorname = 'John Bell'), (SELECT idroom FROM room WHERE numberRoom = 102), '2019-08-18 16:00:10', '2019-08-18 16:01:25');
INSERT INTO `movetracker`(`idvisitor`, `idroom`, `timestart`,`timefinish`) VALUES ((SELECT idvisitor FROM visitor WHERE visitorname = 'Kate White'), (SELECT idroom FROM room WHERE numberRoom = 103), '2019-08-18 17:00:02', '2019-08-18 17:01:03');
INSERT INTO `movetracker`(`idvisitor`, `idroom`, `timestart`,`timefinish`) VALUES ((SELECT idvisitor FROM visitor WHERE visitorname = 'Kate White'), (SELECT idroom FROM room WHERE numberRoom = 104), '2019-08-18 18:00:05', '2019-08-18 18:01:08');

