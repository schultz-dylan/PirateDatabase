SET FOREIGN_KEY_CHECKS = 0;


-- fabricate-flush

DELETE FROM `pirate`;

-- fabricate-flush

DELETE FROM `treasure`;

-- fabricate-flush

DELETE FROM `crew`;

-- fabricate-flush

DELETE FROM `battle`;

-- fabricate-flush

DELETE FROM `visit`;

-- fabricate-flush

DELETE FROM `participate`;

-- fabricate-flush

DELETE FROM `island`;

-- fabricate-flush

DELETE FROM `ship`;

-- fabricate-flush


INSERT INTO `battle` (`Bid`, `Casualties`, `Winner`, `Name`, `LootAmount`, `Location`, `Date`) VALUES
('1', '5', 'Blackbeard''s Buccaneers', 'Battle of the Titans', '$81436.79', 'Nassau', '11/23/1729'),
('2', '9', 'Calico Jack''s Corsairs', 'Clash of the Champions', '$68701.61', 'Tobago', '3/26/1783'),
('3', '1', 'Redbeard''s Renegades', 'War of the Worlds', '$14193.65', 'Tortuga', '1/11/1653'),
('4', '10', 'Blackbeard''s Buccaneers', 'Skirmish at Sunset', '$38901.04', 'Nassau', '8/5/1669'),
('5', '8', 'Davy Jones'' Raiders', 'Siege of the Stronghold', '$70619.86', 'Tortuga', '6/25/1717'),
('6', '8', 'Davy Jones'' Raiders', 'Rumble in the Jungle', '$23249.95', 'Tortuga', '7/25/1731'),
('7', '10', 'Redbeard''s Renegades', 'Showdown at High Noon', '$60215.18', 'Tortuga', '2/6/1794'),
('8', '4', 'Redbeard''s Renegades', 'Conquest of the Kingdom', '$20625.62', 'Tortuga', '9/10/1753'),
('9', '3', 'Captain Hook''s Crew', 'Brawl at the Barrens', '$76833.28', 'Barbados', '8/15/1663'),
('10', '1', 'Davy Jones'' Raiders', 'Duel in the Desert', '$82318.17', 'Tortuga', '9/1/1691');


-- fabricate-flush


INSERT INTO `crew` (`Cid`, `Name`, `Location`) VALUES
('1', 'Captain Hook''s Crew', 'Tortuga'),
('2', 'Calico Jack''s Corsairs', 'Port Royal'),
('3', 'Blackbeard''s Buccaneers', 'Nassau'),
('4', 'Davy Jones'' Raiders', 'Barbados'),
('5', 'Redbeard''s Renegades', 'Tobago'),
('6', 'Redbeard''s Renegades', 'Tortuga'),
('7', 'Calico Jack''s Corsairs', 'Port Royal'),
('8', 'Calico Jack''s Corsairs', 'Nassau'),
('9', 'Calico Jack''s Corsairs', 'Barbados'),
('10', 'Calico Jack''s Corsairs', 'Tobago'),
('11', 'Davy Jones'' Raiders', 'Tortuga');


-- fabricate-flush


INSERT INTO `island` (`Iid`, `Name`, `Location`) VALUES
('1', 'Tropical Island', 'Tortuga'),
('2', 'Paradise Cove', 'Tortuga'),
('3', 'Sunset Key', 'Tortuga'),
('4', 'Palm Island', 'Port Royal'),
('5', 'Coral Bay', 'Barbados'),
('6', 'Seashell Beach', 'Tortuga'),
('7', 'Mango Island', 'Tortuga'),
('8', 'Coconut Cove', 'Tobago'),
('9', 'Lagoon Bay', 'Port Royal'),
('10', 'Wavecrest Point', 'Port Royal');


-- fabricate-flush


INSERT INTO `participate` (`Bid`, `Sid`, `WasSunk`) VALUES
('2', '6', 'False'),
('6', '5', 'False'),
('9', '10', 'False'),
('7', '3', 'False'),
('9', '1', 'True'),
('3', '3', 'True'),
('2', '10', 'True'),
('4', '6', 'True'),
('7', '7', 'True'),
('2', '3', 'False');


-- fabricate-flush


INSERT INTO `pirate` (`FirstName`, `MiddleName`, `LastName`, `Alias`, `NetWorth`, `Age`, `Role`, `Cid`, `Sid`, `Pid`) VALUES
('Quinlan', '', 'Grinin', '', '$36627.85', '15', 'First Mate', '6', '6', '1'),
('Harlene', '', 'Iozefovich', '', '$966954.28', '71', 'Captain', '11', '3', '2'),
('Vasili', '', 'Landa', '', '$265282.87', '78', 'First Mate', '11', '2', '3'),
('Yehudit', '', 'Oliveti', 'Captain Flint', '$373196.90', '53', 'Quartermaster', '10', '1', '4'),
('Rubina', '', 'Enever', '', '$959152.19', '15', 'Navigator', '8', '6', '5'),
('Florri', 'John', 'Pargetter', '', '$924490.53', '44', 'Boatswain', '10', '8', '6'),
('Vannie', '', 'Warstall', '', '$477738.47', '54', 'Gunner', '7', '4', '7'),
('Ephrayim', 'Nicole', 'Cremer', '', '$364931.70', '71', 'Gunner', '3', '5', '8'),
('Polly', '', 'Greenhaugh', '', '$864457.27', '18', 'Boatswain', '2', '7', '9'),
('Holmes', '', 'Fletham', '', '$296205.83', '21', 'Quartermaster', '10', '6', '10'),
('Ainsley', 'John', 'Olufsen', 'Black Bart', '$766817.71', '30', 'Cabin Boy', '7', '2', '11'),
('Manuel', '', 'Jacmard', 'Mary Read', '$495651.97', '79', 'Captain', '9', '3', '12'),
('Cris', '', 'Barker', '', '$832531.89', '29', 'Captain', '8', '9', '13'),
('Rhody', '', 'Fischer', '', '$446703.34', '61', 'First Mate', '5', '5', '14'),
('Emile', '', 'Alans', 'Redbeard', '$45146.59', '58', 'Navigator', '6', '8', '15'),
('Berta', '', 'Barfitt', 'Mary Read', '$249337.53', '73', 'Cabin Boy', '8', '1', '16'),
('Jsandye', '', 'McGettrick', '', '$270069.41', '26', 'Quartermaster', '1', '4', '17'),
('Ronica', 'Nicole', 'Peatheyjohns', '', '$772982.47', '55', 'First Mate', '11', '1', '18'),
('Nat', '', 'Naile', '', '$898319.99', '59', 'Cabin Boy', '6', '10', '19'),
('Gideon', '', 'De la Feld', '', '$517547.80', '17', 'First Mate', '4', '6', '20'),
('Nomi', '', 'de Amaya', 'Grace O''Malley', '$814203.91', '33', 'Captain', '11', '5', '21'),
('Chris', '', 'Bearn', '', '$437981.48', '75', 'Cabin Boy', '7', '1', '22'),
('Kalvin', '', 'Smoth', '', '$414680.73', '73', 'Boatswain', '6', '5', '23'),
('Marci', 'Jessica', 'Whistlecroft', '', '$925908.77', '24', 'Boatswain', '3', '2', '24'),
('Katlin', '', 'Claesens', '', '$402609.72', '30', 'First Mate', '1', '7', '25'),
('Lonnie', '', 'Sackur', 'Redbeard', '$342981.86', '37', 'Navigator', '5', '8', '26'),
('Delcina', 'Sarah', 'Carass', 'Captain Flint', '$237402.72', '18', 'Captain', '10', '3', '27'),
('Cathrin', 'Ashley', 'Vatini', '', '$416253.04', '55', 'Captain', '9', '10', '28'),
('Mickey', '', 'Seeking', 'Captain Flint', '$517806.07', '78', 'Boatswain', '5', '6', '29'),
('Dell', 'Amanda', 'Kington', '', '$500659.10', '71', 'Boatswain', '11', '1', '30'),
('Judon', '', 'Pitrollo', '', '$588115.54', '30', 'Gunner', '10', '4', '31'),
('Loreen', 'Ashley', 'Dillaway', 'Captain Hook', '$194236.92', '62', 'Cabin Boy', '5', '6', '32'),
('Isis', '', 'D''Adda', '', '$421847.77', '61', 'Navigator', '6', '4', '33'),
('Hadleigh', '', 'Deverill', '', '$270185.00', '23', 'Cabin Boy', '7', '5', '34'),
('Derrek', '', 'O''Finan', '', '$710393.15', '35', 'Boatswain', '6', '4', '35'),
('Salome', '', 'Ashplant', '', '$225355.30', '65', 'Gunner', '11', '3', '36'),
('Chandra', 'Elizabeth', 'Luggar', 'Blackbeard', '$547527.05', '49', 'Boatswain', '9', '9', '37'),
('Lenore', '', 'De Miranda', '', '$531981.65', '34', 'Quartermaster', '6', '10', '38'),
('Darell', 'Sarah', 'Singyard', '', '$316820.68', '44', 'Gunner', '5', '9', '39'),
('Auberon', '', 'Bagott', '', '$751826.09', '50', 'First Mate', '1', '4', '40'),
('Kit', '', 'Arnson', 'Captain Flint', '$308053.48', '23', 'Cabin Boy', '2', '5', '41'),
('Rees', '', 'Mansell', '', '$303916.84', '45', 'Cabin Boy', '9', '10', '42'),
('Jeannette', 'Amanda', 'Blaza', 'Captain Hook', '$279527.47', '18', 'Cabin Boy', '3', '2', '43'),
('Edd', '', 'Cammish', '', '$645644.91', '19', 'Gunner', '2', '4', '44'),
('Fraze', 'Michael', 'Jailler', '', '$210618.80', '11', 'Navigator', '11', '4', '45'),
('Carine', '', 'Pounsett', '', '$327465.81', '45', 'Boatswain', '3', '2', '46'),
('Cole', '', 'Tulleth', 'Mary Read', '$642365.70', '33', 'Boatswain', '6', '6', '47'),
('Loleta', '', 'Consadine', '', '$161004.45', '51', 'Boatswain', '7', '5', '48'),
('Caren', '', 'Jordine', '', '$276014.27', '29', 'Boatswain', '8', '5', '49'),
('Rowen', 'Christopher', 'Creber', 'Bartholomew Roberts', '$256196.15', '20', 'Navigator', '10', '3', '50'),
('Marabel', '', 'McNamee', '', '$246831.81', '28', 'Captain', '7', '8', '51'),
('Dirk', '', 'Willatts', 'Redbeard', '$841894.49', '29', 'Gunner', '8', '9', '52'),
('Zaneta', '', 'Beig', '', '$625642.75', '53', 'Captain', '6', '2', '53'),
('Felipe', '', 'Conor', 'Blackbeard', '$727090.30', '18', 'Captain', '2', '1', '54'),
('Hew', '', 'Rolin', 'Peg Leg Pete', '$712124.72', '48', 'Quartermaster', '11', '4', '55'),
('Inger', '', 'Tumulty', '', '$546544.93', '57', 'First Mate', '2', '3', '56'),
('Gauthier', '', 'Maton', '', '$192609.57', '13', 'First Mate', '7', '2', '57'),
('Justino', 'David', 'Sutor', '', '$242082.05', '46', 'First Mate', '1', '4', '58'),
('Sylas', '', 'Ounsworth', '', '$183490.17', '73', 'Captain', '11', '1', '59'),
('Anna-diana', '', 'Wolton', '', '$37328.51', '58', 'Navigator', '9', '7', '60'),
('Hanny', 'Ashley', 'Stopforth', '', '$848365.74', '39', 'Cabin Boy', '7', '8', '61'),
('Leora', '', 'Laddle', '', '$386156.81', '60', 'Quartermaster', '4', '4', '62'),
('Issiah', '', 'Bigrigg', '', '$790701.32', '11', 'Gunner', '10', '1', '63'),
('Guinevere', 'Elizabeth', 'Soppett', 'Captain Flint', '$240992.58', '27', 'Boatswain', '7', '7', '64'),
('Grannie', 'Amanda', 'Darragh', 'Stede Bonnet', '$679339.37', '77', 'First Mate', '5', '9', '65'),
('Lloyd', '', 'Lait', '', '$65043.21', '50', 'Captain', '11', '4', '66'),
('Hurlee', '', 'Hawkins', '', '$147193.48', '31', 'Gunner', '3', '3', '67'),
('Aube', '', 'Kearton', '', '$339680.87', '67', 'Captain', '6', '5', '68'),
('Lenci', '', 'Costy', '', '$70512.69', '46', 'First Mate', '9', '6', '69'),
('Evvie', '', 'Spera', '', '$681844.19', '31', 'Navigator', '8', '8', '70'),
('Brook', '', 'Whale', '', '$910353.32', '58', 'First Mate', '4', '10', '71'),
('Alexandrina', '', 'Josselsohn', '', '$116718.95', '11', 'Navigator', '2', '2', '72'),
('Romona', '', 'Adiscot', '', '$236631.10', '60', 'Gunner', '1', '6', '73'),
('Cathyleen', '', 'Mylan', '', '$621498.56', '65', 'Captain', '11', '1', '74'),
('Jock', '', 'Grumble', '', '$505203.52', '68', 'Boatswain', '2', '4', '75');


-- fabricate-flush


INSERT INTO `ship` (`Sid`, `Name`, `Type`) VALUES
('1', 'Buccaneer''s Bounty', 'Barque'),
('2', 'Sea Serpent', 'Barque'),
('3', 'Skull and Crossbones', 'Frigate'),
('4', 'Jolly Roger', 'Brigantine'),
('5', 'Rogue Wave', 'Galleon'),
('6', 'Buccaneer''s Bounty', 'Sloop'),
('7', 'Buccaneer''s Bounty', 'Brigantine'),
('8', 'Pirate''s Plunder', 'Brigantine'),
('9', 'Blackbeard''s Revenge', 'Frigate'),
('10', 'Skull and Crossbones', 'Barque');


-- fabricate-flush


INSERT INTO `treasure` (`Tid`, `Value`, `Location`, `Iid`, `Pid`) VALUES
('1', '$34403.05', 'Shrine', '10', '75'),
('2', '$83466.62', 'Beach', '9', '40'),
('3', '$69902.08', 'Underground', '2', '74'),
('4', '$46955.78', 'Underground', '1', '29'),
('5', '$72373.46', 'Tree', '6', '25'),
('6', '$43059.97', 'Beach', '4', '17'),
('7', '$32854.05', 'Unknown', '2', '36'),
('8', '$14855.82', 'Temple', '9', '21'),
('9', '$53213.02', 'Underground', '9', '73'),
('10', '$40990.98', 'Shrine', '6', '17'),
('11', '$56723.08', 'Shrine', '8', '54'),
('12', '$26032.52', 'Tree', '10', '13'),
('13', '$48199.41', 'Temple', '10', '43'),
('14', '$62711.86', 'Unknown', '4', '65'),
('15', '$6656.48', 'Beach', '2', '75'),
('16', '$25077.05', '', '6', '66'),
('17', '$23798.24', 'Tree', '8', '48'),
('18', '$18707.91', 'Shrine', '7', '60'),
('19', '$24176.28', 'Unknown', '1', '9'),
('20', '$93488.00', '', '1', '20'),
('21', '$50040.43', 'Temple', '1', '7'),
('22', '$21551.67', 'Underground', '9', '12'),
('23', '$9036.18', '', '1', '19'),
('24', '$60816.07', 'Cave', '1', '53'),
('25', '$9871.75', '', '10', '45');


-- fabricate-flush


INSERT INTO `visit` (`Iid`, `Sid`, `Date`) VALUES
('8', '8', '11/6/1654'),
('6', '2', '11/26/1648'),
('9', '3', '5/22/1764'),
('10', '8', '3/2/1606'),
('4', '8', '6/17/1743'),
('2', '4', '12/1/1690'),
('9', '6', '4/13/1752'),
('3', '3', '2/20/1783'),
('10', '4', '9/8/1757'),
('9', '6', '1/14/1759'),
('4', '6', '5/23/1647'),
('1', '7', '10/5/1708'),
('1', '4', '9/5/1708'),
('7', '3', '9/3/1725'),
('4', '7', '4/23/1768'),
('10', '2', '5/17/1667'),
('4', '7', '10/30/1681'),
('6', '8', '2/10/1695'),
('7', '2', '3/13/1639'),
('7', '4', '3/11/1629'),
('6', '4', '10/3/1704'),
('2', '9', '3/25/1681'),
('6', '4', '10/29/1669'),
('2', '9', '10/21/1719'),
('9', '3', '5/5/1762'),
('7', '4', '4/30/1704'),
('2', '5', '1/23/1799'),
('4', '4', '11/25/1757'),
('5', '3', '3/5/1663'),
('5', '2', '11/18/1795'),
('8', '2', '1/5/1612'),
('3', '2', '12/12/1762'),
('9', '7', '9/7/1799'),
('10', '6', '11/21/1629'),
('4', '3', '12/12/1753'),
('8', '1', '12/5/1720'),
('1', '4', '12/12/1632'),
('7', '8', '5/27/1607'),
('5', '5', '7/1/1605'),
('2', '10', '1/30/1614'),
('6', '9', '1/24/1734'),
('10', '5', '11/3/1604'),
('5', '3', '9/13/1656'),
('2', '8', '10/30/1745'),
('1', '2', '1/10/1771'),
('3', '10', '5/19/1670'),
('4', '6', '12/20/1744'),
('6', '4', '6/9/1716'),
('8', '1', '6/13/1741'),
('7', '7', '7/7/1664');


-- fabricate-flush


SET FOREIGN_KEY_CHECKS = 1;
