--CREATE DATABASE buddylist;

CREATE TABLE `user` (
  `Id` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `Email` varchar(75) NOT NULL,
  `Password` varchar(32) NOT NULL,
  `FName` varchar(25) NOT NULL,
  `LName` varchar(25) NOT NULL,
   PRIMARY KEY (Id),
   UNIQUE KEY (Email)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

--
-- Dumping data for table `user`
--
INSERT INTO `user` (`Id`, `Email`, `Password`, `FName`, `LName`)
VALUES(2, 'test', '202cb962ac59075b964b07152d234b70', 'Jeremy', 'Clarkson');

-- password is 123 for ^^