-- phpMyAdmin SQL Dump
-- version 4.6.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Erstellungszeit: 21. Mrz 2017 um 17:34
-- Server-Version: 5.5.53-MariaDB
-- PHP-Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `app_relayd`
--
CREATE DATABASE IF NOT EXISTS `app_relayd` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `app_relayd`;

-- --------------------------------------------------------

--
-- Tabellenstruktur fÃ¼r Tabelle `participant`
--

CREATE TABLE `participant` (
  `id` char(36) NOT NULL COMMENT 'UUID as string representation.',
  `personId` char(36) NOT NULL COMMENT 'UUID of the referenced person, string representation.',
  `relayId` char(36) NOT NULL COMMENT 'UUID of the referenced relay, string representation.',
  `relayPosition` int(11) NOT NULL COMMENT 'The relay position the person participates in.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='relayD - The table for the relay participant entities.';

--
-- Daten fÃ¼r Tabelle `participant`
--

INSERT INTO `participant` (`id`, `personId`, `relayId`, `relayPosition`) VALUES
('9893dbc7-3823-4e17-810b-e4a543edcbf8', '02985992-cd7e-4b70-8b0f-4244ad793881', '4b568dda-15e7-4e11-b7f9-47a9153ae17b', 4),
('2fd37267-734c-44e3-a872-478e935d1cda', '02b5d155-2804-4fe6-836f-6f14397c893e', 'e2e1e2fe-6224-4d08-9eb6-7d3f1ff878cb', 4),
('8c2349ad-70b3-46cc-957c-2ff001d63fc5', '03c062d6-b1d5-455c-8ec2-3ae2c37f9093', '4fbf6161-ad4e-40a7-8aa4-04edafc2ff49', 4),
('a7a9ec3f-33c4-4224-8f71-0b4e84fa7838', '03f0e5cb-26fb-4aaa-a258-7fa0ce4718d3', '84d9f07e-fa21-4c2a-b3cb-07d1e7f42e48', 4),
('6f43ff3c-207e-4354-bf05-2ce2086c3509', '046762fb-7dd7-4fd2-9cab-ad105831cbec', '1803135f-bc38-4d92-a398-6a3c62511018', 4),
('55478233-308c-4094-acc2-a7f700413a95', '08840be0-c52b-4061-b451-56c374fc4fac', '7814902f-22b4-4851-9bc8-1153baf55584', 4),
('565c0eb4-c59d-4a8d-93b9-2cf2eb90f4f8', '0ab33c9c-2813-442a-82ad-35415331d5c6', '7814902f-22b4-4851-9bc8-1153baf55584', 3),
('aed2b500-71d7-41b3-995a-e4a039e5ae1a', '0d91c47b-5379-4bb7-9903-d1b469d630d5', '4fbf6161-ad4e-40a7-8aa4-04edafc2ff49', 2),
('3cdd5ad4-7f5f-4dd0-ac21-a714dac295e1', '1111c1ca-e3a0-42f9-b3b4-a3cb66726b08', '5805b0e6-5c80-4a63-ada5-e8196c1631bc', 2),
('6c55d970-c45e-4233-a65a-da60342247c9', '11abf6ad-bc45-46ed-8c0b-a454245687e8', '7814902f-22b4-4851-9bc8-1153baf55584', 1),
('623b1c93-269d-479c-b0f5-b9fe3e2dd656', '14be76b8-84f6-4b3b-8682-cad7d6cdf125', 'fdf0e092-d745-4451-a0b5-de52708ca54d', 4),
('a921e3b9-2a2e-45d5-bfae-cb5c8ad8ca9b', '1a2bc117-deb3-4a1a-9bb5-2386a6346336', 'f77d99b6-f248-466a-ae85-dcde7e4085dc', 4),
('08402f54-642c-4c96-8dc7-ce5842b62461', '1af1870e-c6b6-4520-991b-10b17c5339e3', '4b568dda-15e7-4e11-b7f9-47a9153ae17b', 1),
('949bded7-d93b-4b46-84d5-7b8e36f4706f', '1f7f9347-51c8-48f3-ab3c-9f1d35273221', 'e2e1e2fe-6224-4d08-9eb6-7d3f1ff878cb', 1),
('a0e052e9-8931-4d4a-a59d-f49d0b0fd8e8', '228d490d-2384-4dfc-bd49-360a38fcc474', 'f8001306-2099-4469-9656-791d903c5521', 1),
('6252aa5a-f243-48c3-a459-d7a2c233d5d4', '22fa451c-b556-45e2-b319-0dc901e81191', '2a7caa12-3a10-4e1e-8cc9-14c49d773fef', 4),
('4469ae9e-fb04-4b5a-b065-bf80616624ce', '2949df62-9dd5-441d-ba32-95d37914cb07', '5805b0e6-5c80-4a63-ada5-e8196c1631bc', 4),
('dded1a6a-639a-4970-8e80-77b1f3809d89', '2a9242b3-47ad-4b91-b5bc-42745764627f', 'f77d99b6-f248-466a-ae85-dcde7e4085dc', 1),
('1396d5b4-6e0e-43a9-9bca-d54ad0e889b8', '2b696153-0afe-4a64-8308-87531d65e1e7', '4fbf6161-ad4e-40a7-8aa4-04edafc2ff49', 1),
('d6b4e5ea-aea3-4f7f-8955-e480ccb3ece3', '2d1114c4-ecc1-410e-97bc-ef40fd1b6334', '2fca0250-e200-4150-bd84-ef9c2aa163d6', 4),
('36e7986f-84fa-4b60-a2fc-4bad23f75b2a', '2d2e1cc1-a29f-4754-b950-39832268e2b0', '2a7caa12-3a10-4e1e-8cc9-14c49d773fef', 2),
('9310f842-6a61-48b6-9a9b-051368f4d4cd', '2ff90517-7d88-40a4-af7d-40d4f3c18b9f', '9579f146-3df6-4bba-a77a-b69b25943e75', 4),
('5fa00d98-8920-4087-a990-8541011e0fd2', '32f7d72d-e527-4d95-b036-c0e77c475db1', '4325d69c-429d-4084-a05c-4bfaf8d19a10', 2),
('0fed62c6-af59-4b52-8a1c-0c9d8388e365', '34d228d4-f97b-4b30-a3ba-b7a4e873451e', '9579f146-3df6-4bba-a77a-b69b25943e75', 2),
('f20734aa-d01d-4721-8a93-8aa9d9bcbefd', '34fcb76c-de32-400a-ae27-fd1a36beb8ae', '9579f146-3df6-4bba-a77a-b69b25943e75', 1),
('8f8251e8-18d9-4d4e-a369-a7228102b2fc', '3845967d-c8f9-4214-94e9-8e795724c7a1', '6bde1624-b6c6-42e9-898b-c0ecc1e281f6', 1),
('15cb4fac-63e0-43f4-b340-845544357148', '3c3eb8e2-3586-40c6-8241-337d3eb5cacf', 'f77d99b6-f248-466a-ae85-dcde7e4085dc', 3),
('584feb89-9030-410d-b35a-30390a527e33', '3dd6e3bf-f813-4bd8-b327-346b0246774c', 'f77d99b6-f248-466a-ae85-dcde7e4085dc', 2),
('e40973d7-11e1-49b1-bb14-ac1b976e979e', '44e49a99-66ca-44b2-bfe8-efe87dd40647', '2fca0250-e200-4150-bd84-ef9c2aa163d6', 1),
('ecab05fe-9247-4687-91df-27e441e13a91', '476b370c-942b-4dbe-9054-c4924ccd6329', '1af2a1cc-b1f7-4c32-9e75-2746f62d56fc', 2),
('c1dbb092-6273-46ff-8315-e69eec62208d', '48859b23-7f56-410f-a982-c7c430638879', '2d8ef52b-531f-4fbb-9e98-8c5e390da0fa', 4),
('8f4d80ad-fbc3-4fb3-a611-40fe80b57324', '55c319dd-7814-4241-a34c-3415c0485488', '1af2a1cc-b1f7-4c32-9e75-2746f62d56fc', 1),
('2dd635b2-8454-46ec-939b-b8604e19fbfc', '56c19213-bf13-4338-a1b7-e8ce0c2d3359', '4325d69c-429d-4084-a05c-4bfaf8d19a10', 3),
('8f3978b5-657b-47c6-bc3f-9061da30ff85', '5d32dc6a-b6dd-430e-8dde-8a5f8156edb8', 'd42a83da-4829-490f-8bc1-6b3fe86d70b3', 2),
('9ea3712c-67d4-4c4c-b942-155647072520', '5dcb2365-bc6a-47d2-8837-2f9f43a41f7a', '7814902f-22b4-4851-9bc8-1153baf55584', 2),
('8325aa5a-e79c-434a-a4e1-0ab2faca5286', '5e9a4183-690a-4eaa-85ed-741361e375dc', 'e2e1e2fe-6224-4d08-9eb6-7d3f1ff878cb', 3),
('03d2e8e4-c1b9-4903-b7f1-f2a583574ca4', '61e2bd74-fbc8-4266-ac61-f6482234f658', '1af2a1cc-b1f7-4c32-9e75-2746f62d56fc', 4),
('5d5e3a84-ac1f-4ff2-99aa-40f30ec6863d', '63e97e74-16fd-452f-92fa-f9b4731fa895', '84d9f07e-fa21-4c2a-b3cb-07d1e7f42e48', 1),
('8bd90edd-049a-46f1-a9a2-a8f485525d6f', '75882000-7f9c-4b5f-b091-ed8f77387086', '4b568dda-15e7-4e11-b7f9-47a9153ae17b', 3),
('4974d43f-0eee-4efa-a310-b9f99016723a', '8d42697e-51fb-4627-b845-154826d3228d', '1803135f-bc38-4d92-a398-6a3c62511018', 2),
('0203c8bc-36c8-4537-9598-56a9281cd22c', '8e0cd4b8-7866-4399-a2b7-c84c96165fc1', 'd42a83da-4829-490f-8bc1-6b3fe86d70b3', 3),
('7eb02ec7-cdc1-4d5c-b28b-baa551f24268', '9a621f24-3537-4b11-8ded-a6d06aad8f43', '2fca0250-e200-4150-bd84-ef9c2aa163d6', 3),
('c123a2da-f979-41be-a7da-d157aa6826e4', '9b13929b-0b41-409a-8753-67526d3f995a', 'd42a83da-4829-490f-8bc1-6b3fe86d70b3', 1),
('14420c43-38dd-4237-bd81-ba933825066d', '9f4c9338-bc9d-4542-807e-41fbe9fe0287', '84d9f07e-fa21-4c2a-b3cb-07d1e7f42e48', 2),
('bf11ca90-889d-4bfd-b0bf-1942d80402a5', 'a7386063-3659-41fe-8b12-75938b74f2b9', '9579f146-3df6-4bba-a77a-b69b25943e75', 3),
('962cd51e-278b-41b1-a94b-98de1acf6fed', 'aa43b716-d075-4e61-9f3b-68ecd3566d72', '2a7caa12-3a10-4e1e-8cc9-14c49d773fef', 3),
('0bdb273e-dfea-40a2-9d19-56234fc02abb', 'b0521e7d-84f1-4f0e-9112-4fd2c53ab99a', '6bde1624-b6c6-42e9-898b-c0ecc1e281f6', 2),
('edeb5c48-d6b4-46e0-b778-ffa4e36977de', 'bb22a177-c316-4887-ae6e-bb0e4d26b4fa', '2a7caa12-3a10-4e1e-8cc9-14c49d773fef', 1),
('119b54ae-61f6-4452-91e1-a31674e7da4e', 'bc3882ae-7561-4284-9d56-618006ac5016', '2d8ef52b-531f-4fbb-9e98-8c5e390da0fa', 3),
('d8db77fc-822c-469d-8537-901eea9f064a', 'c3fbdd03-95ee-4b94-bf22-e9adbd805da6', '1803135f-bc38-4d92-a398-6a3c62511018', 1),
('3f579399-2dfd-4cab-9ec6-933e2515d2ae', 'c76b5ec7-982f-4d3d-8201-e71b19b870a7', '4fbf6161-ad4e-40a7-8aa4-04edafc2ff49', 3),
('5d287de8-30b1-4f62-9552-87b367e5bc91', 'cae5fde3-e555-457a-9a71-0beb34198f61', '5805b0e6-5c80-4a63-ada5-e8196c1631bc', 3),
('7e6016e2-f02c-4bdc-b731-c68c50e79eba', 'cb514c0d-92b9-41de-8595-b665488fd601', '1803135f-bc38-4d92-a398-6a3c62511018', 3),
('2bd8bb6f-aeda-4a7c-9edf-7dc2be6e07c7', 'd3115ab5-5d8b-4c1f-ae55-07e6b57bacb9', '6bde1624-b6c6-42e9-898b-c0ecc1e281f6', 3),
('82f1e79f-0292-4d3b-ab1f-f1ba4e716359', 'd33149f3-d37b-46d0-8875-3b31473014b8', '2d8ef52b-531f-4fbb-9e98-8c5e390da0fa', 1),
('3125e783-b0bd-40f3-b464-7968a2d81fad', 'd6d497f0-7df7-4b35-9823-ff76372603f7', 'd42a83da-4829-490f-8bc1-6b3fe86d70b3', 4),
('3c11bc59-86bb-48e6-a9a3-0178bedbf3d1', 'd88cd64c-a458-4368-bb3d-33a5a5fd3687', '2fca0250-e200-4150-bd84-ef9c2aa163d6', 2),
('ca127077-0e2d-48c4-88fe-4a4697eb582b', 'd9da898e-b565-4cfa-b2d7-55b0b6dc1a29', 'fdf0e092-d745-4451-a0b5-de52708ca54d', 3),
('1d4d399e-9c7d-4c10-8d46-fb872e6fcc58', 'da91d412-c9e0-4d92-b21a-5b1b6e904913', '4b568dda-15e7-4e11-b7f9-47a9153ae17b', 2),
('1562937f-a0e2-44c6-87ce-27e1da7c80a7', 'db742e40-ceb2-4111-b564-ed4ae8892b8b', 'fdf0e092-d745-4451-a0b5-de52708ca54d', 2),
('2ec2910d-b208-4a48-9b99-6c07623f3a0e', 'db9764b8-03a1-45da-b1ba-09b538b4039f', 'f8001306-2099-4469-9656-791d903c5521', 2),
('92ac94ad-b1a0-444a-94d6-880bbe127f6f', 'de102adf-27f9-43bc-ab6e-502d64412120', 'fdf0e092-d745-4451-a0b5-de52708ca54d', 1),
('615e2ed5-d1dd-43be-b1ca-70e4486dfbcb', 'e389fb01-70d2-485a-8478-56e4f275bb81', 'f8001306-2099-4469-9656-791d903c5521', 4),
('20d43d40-38e5-4911-9c89-805b7e417d2d', 'e656123c-375c-4094-bd3e-fa212c32da9d', '5805b0e6-5c80-4a63-ada5-e8196c1631bc', 1),
('14032448-862c-4f42-a7dc-223b91c5d357', 'e7c625cf-e0ab-4452-98c1-062301824d7a', 'e2e1e2fe-6224-4d08-9eb6-7d3f1ff878cb', 2),
('b1f5a692-f4b9-4c4d-bbd1-762c35157ad9', 'f07827a1-5d75-407d-8330-537bf5e8305a', '4325d69c-429d-4084-a05c-4bfaf8d19a10', 4),
('e512f6ba-2ed7-4484-a471-0044a5f41d5e', 'f4d8855a-66e4-46ef-8a28-b88eb8b3be3e', '2d8ef52b-531f-4fbb-9e98-8c5e390da0fa', 2),
('c2288192-3243-44d1-aef0-4245c75e746c', 'fd27cd18-dbba-46f0-99e4-3101037bcdd0', '1af2a1cc-b1f7-4c32-9e75-2746f62d56fc', 3),
('4bbf272e-8f18-42f3-8f3d-8bd36a8d3c2e', 'ffbbd7ca-85b8-4bdc-b411-9e60a30540af', 'f8001306-2099-4469-9656-791d903c5521', 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur fÃ¼r Tabelle `person`
--

CREATE TABLE `person` (
  `id` char(36) NOT NULL,
  `surename` varchar(256) DEFAULT NULL,
  `forename` varchar(256) DEFAULT NULL,
  `yearOfBirth` int(11) DEFAULT NULL,
  `shirtsize` int(11) DEFAULT NULL,
  `relayname` varchar(256) DEFAULT NULL,
  `pos` int(11) DEFAULT NULL,
  `email` varchar(256) DEFAULT NULL,
  `info` varchar(1024) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten fÃ¼r Tabelle `person`
--

INSERT INTO `person` (`id`, `surename`, `forename`, `yearOfBirth`, `shirtsize`, `relayname`, `pos`, `email`, `info`) VALUES
('02985992-cd7e-4b70-8b0f-4244ad793881', 'Grunow', 'Oliver', 1970, 8, NULL, NULL, 'Oliver.Grunow@canda.com', NULL),
('02b5d155-2804-4fe6-836f-6f14397c893e', 'Wallhorn', 'Annette', 1965, NULL, NULL, NULL, 'Annette.Wallhorn@canda.com', NULL),
('03c062d6-b1d5-455c-8ec2-3ae2c37f9093', 'Behrendt', 'Nils', 1964, 9, NULL, NULL, 'Nils.Behrendt@canda.com', NULL),
('03f0e5cb-26fb-4aaa-a258-7fa0ce4718d3', 'Angenendt       ', 'Jochen', 1980, NULL, 'Staffel 1', NULL, 'Jochen.Angenendt@canda.com', NULL),
('046762fb-7dd7-4fd2-9cab-ad105831cbec', 'Schulze', 'Janine', 1984, 2, NULL, NULL, 'Janine.Schulze@canda.com', NULL),
('08840be0-c52b-4061-b451-56c374fc4fac', 'van den Biggelaar', 'Jeroen', 1969, 8, NULL, NULL, 'Jeroen.vandenBiggelaar@canda.com', NULL),
('0ab33c9c-2813-442a-82ad-35415331d5c6', 'Schmitz', 'Markus', 1965, 8, NULL, NULL, 'Markus.Schmitz@canda.com', NULL),
('0d91c47b-5379-4bb7-9903-d1b469d630d5', 'Haardt', 'Werner', 1954, 9, NULL, NULL, 'Werner.Haardt@canda.com', NULL),
('1111c1ca-e3a0-42f9-b3b4-a3cb66726b08', 'JÃ¼nemann', 'Peter', 1980, 3, NULL, NULL, 'Peter.Juenemann@canda.com', NULL),
('11abf6ad-bc45-46ed-8c0b-a454245687e8', 'Joerg', 'Hermann', 1962, 9, NULL, NULL, 'Hermann.Joerg@canda.com', NULL),
('13de2b1d-2841-42ab-a576-49e19f00142c', 'Gremme', 'Elmar', 1970, 8, NULL, NULL, 'Elmar.Gremme@canda.com', 'FÃ¤llt aus'),
('14be76b8-84f6-4b3b-8682-cad7d6cdf125', 'Paul ', 'Janine ', 1984, 2, NULL, NULL, 'Janine.Paul@canda.com', NULL),
('18cf5211-9cca-482f-bb34-0105024cda12', 'Heinen', 'Hildegard', NULL, NULL, NULL, NULL, 'Hildegard.Heinen@canda.com', 'MÃ¶chte gerne als Reserve dienen und sich aktuell noch nicht festlegen.'),
('1a2bc117-deb3-4a1a-9bb5-2386a6346336', 'Schoss', 'Tom', 1978, 2, NULL, NULL, 'Tom.Schoss@canda.com', NULL),
('1af1870e-c6b6-4520-991b-10b17c5339e3', 'SchlÃ¤rmann', 'Nils', 1978, 8, NULL, NULL, 'Nils.Schlaermann@canda.com', NULL),
('1f7f9347-51c8-48f3-ab3c-9f1d35273221', 'Bavel', 'David', 1988, 8, NULL, NULL, 'David.Bavel@canda.com', 'War erst backup, jetzt Ersatz fÃ¼r Engenhorst'),
('228d490d-2384-4dfc-bd49-360a38fcc474', 'JanÃŸen', 'Daniel', 1988, 8, 'Run & Sprint Champions', 1, 'Daniel.Janssen@canda.com', NULL),
('22fa451c-b556-45e2-b319-0dc901e81191', 'Seel', 'Elena', 1983, 2, 'I love it when a PLAN comes together', 2, 'Elena.Seel@canda.com', NULL),
('2812c414-c592-4b45-b01b-680da29e3dbe', 'Bartmann', 'Frank', 1969, 9, NULL, NULL, 'Frank.Bartmann@canda.com', 'FÃ¤llt aus'),
('2949df62-9dd5-441d-ba32-95d37914cb07', 'Gergs', 'Martin', 1974, 8, NULL, NULL, 'Martin.Gergs@canda.com', NULL),
('2a9242b3-47ad-4b91-b5bc-42745764627f', 'Robinet', 'Anne-Sophie', 1976, 3, NULL, NULL, 'Anne-Sophie.Robinet@canda.com', NULL),
('2b696153-0afe-4a64-8308-87531d65e1e7', 'Heinz', 'Joachim', 1968, 9, NULL, NULL, 'Joachim.Heinz@canda.com', NULL),
('2d1114c4-ecc1-410e-97bc-ef40fd1b6334', 'Wils', 'Stephen', 1984, 8, NULL, NULL, 'Stephen.Wils@canda.com', 'letzter Teil des Streckenabschnitts\\r\\n'),
('2d2e1cc1-a29f-4754-b950-39832268e2b0', 'Ruether', 'Daniel', 1976, NULL, NULL, NULL, 'Daniel.Ruether@canda.com', 'Externer'),
('2ff90517-7d88-40a4-af7d-40d4f3c18b9f', 'Sandoval', 'Carlos', 1980, 8, NULL, NULL, 'Carlos.Sandoval@canda.com', NULL),
('32f7d72d-e527-4d95-b036-c0e77c475db1', 'Hinz', 'Cosima', 1965, 2, NULL, NULL, 'Cosima.Hinz@canda.com', NULL),
('34d228d4-f97b-4b30-a3ba-b7a4e873451e', 'Hanisch', 'David', 1982, 7, NULL, NULL, 'David.Hanisch@canda.com', NULL),
('34fcb76c-de32-400a-ae27-fd1a36beb8ae', 'Hammer', 'Andreas', 1975, 8, NULL, NULL, 'Andreas.Hammer@canda.com', 'MÃ¶chte in eine schnelle Staffel - 44min\\r\\n'),
('3845967d-c8f9-4214-94e9-8e795724c7a1', 'Koch', 'Tobias', 1980, 7, NULL, NULL, 'Tobias.Koch@canda.com', NULL),
('3c3eb8e2-3586-40c6-8241-337d3eb5cacf', 'Stein', 'Freddy', 1994, NULL, NULL, NULL, 'Freddy.Stein@canda.com', NULL),
('3dd6e3bf-f813-4bd8-b327-346b0246774c', 'Birck', 'Orsolya', 1978, 2, NULL, NULL, 'Orsolya.Birck@canda.com', NULL),
('3f812478-8101-4e46-a696-9a3987ca0d99', 'Engenhorst', 'Carolin', 1989, 3, NULL, NULL, 'Carolin.Engenhorst@canda.com', 'FÃ¤llt aus'),
('4458641e-7e68-4bc6-a8d6-14989011a57c', 'Trollmann', 'Thomas', 1987, 7, NULL, NULL, 'Thomas.Trollmann@canda.com', 'FÃ¤llt aus'),
('44e49a99-66ca-44b2-bfe8-efe87dd40647', 'Aderhold', 'Dirk', 1968, 9, NULL, NULL, 'Dirk.Aderhold@canda.com', NULL),
('476b370c-942b-4dbe-9054-c4924ccd6329', 'Medici', 'Francesca', 1989, 2, 'hot runners@C&A', 1, 'Francesca.Medici@canda.com', NULL),
('48859b23-7f56-410f-a982-c7c430638879', 'HÃ¼ning', 'Jan', 1984, 6, NULL, NULL, 'Jan.Huening@canda.com', NULL),
('55c319dd-7814-4241-a34c-3415c0485488', 'Schlapkohl', 'Daniel', 1987, 8, NULL, NULL, 'Daniel.Schlapkohl@canda.com', NULL),
('56c19213-bf13-4338-a1b7-e8ce0c2d3359', 'Parker', 'Antonia', 1971, 4, NULL, NULL, 'Antonia.Parker@canda.com', NULL),
('5d32dc6a-b6dd-430e-8dde-8a5f8156edb8', 'RÃ¼schoff', 'Georg', 1963, 8, NULL, NULL, 'Georg.Rueschoff@canda.com', NULL),
('5da7d5b4-947f-4db9-a313-f58506277b5c', 'Kaspers', 'Jan Philip', 1988, 7, NULL, NULL, 'JanPhilip.Kaspers@canda.com', 'Hat gekÃ¼ndigt'),
('5dcb2365-bc6a-47d2-8837-2f9f43a41f7a', 'Dick', 'Volker ', 1971, 7, NULL, NULL, 'Volker.Dick@canda.com', NULL),
('5e9a4183-690a-4eaa-85ed-741361e375dc', 'Proppe', 'Corinna', 1987, 1, NULL, NULL, 'Corinna.Proppe@canda.com', NULL),
('61e2bd74-fbc8-4266-ac61-f6482234f658', 'Krause', 'Robert', 1971, 7, 'hot runners@C&A', 4, 'Robert.Krause@canda.com', NULL),
('63e97e74-16fd-452f-92fa-f9b4731fa895', 'Gebhardt ', 'Jens', 1977, NULL, 'Staffel 1', 1, 'Jens.Gebhardt@canda.com', NULL),
('6a60b4b9-3256-4d4b-a3f9-efd06a0ce090', 'de Jong', 'Duco', 1970, 7, NULL, NULL, 'Duco.deJong@canda.com', 'FÃ¤llt aus'),
('75882000-7f9c-4b5f-b091-ed8f77387086', 'Schmidt', 'Ulf', 1977, 8, NULL, NULL, 'Ulf.Schmidt@canda.com', NULL),
('8b566b5c-51bf-45e4-be2b-a968e94f5b5e', 'Swinburne', 'Dan', 1981, 7, 'I love it when a PLAN comes together', 3, 'Dan.Swinburne@canda.com', 'FÃ¤llt aus'),
('8d42697e-51fb-4627-b845-154826d3228d', 'KÃ¼hnel', 'Alexander', 1985, 8, NULL, NULL, 'Alexander.Kuehnel@canda.com', NULL),
('8e0cd4b8-7866-4399-a2b7-c84c96165fc1', 'Riewe', 'Nicole', 1972, 4, NULL, NULL, 'Nicole.Riewe@canda.com', NULL),
('990ae7dd-272a-4602-b9ec-0b56da32c2ad', 'Forian', 'Orsolya', 1976, 1, 'I love it when a PLAN comes together', 4, 'Orsolya.Forian@canda.com', 'FÃ¤llt aus'),
('9a621f24-3537-4b11-8ded-a6d06aad8f43', 'van Gember', 'Robert ', 1961, NULL, NULL, NULL, 'robert.van.gember@canda.com', NULL),
('9b13929b-0b41-409a-8753-67526d3f995a', 'Moesges', 'Kira', 1992, 2, NULL, NULL, 'Kira.Moesges@canda.com', NULL),
('9f4c9338-bc9d-4542-807e-41fbe9fe0287', 'Schmoll', 'Christian', 1971, NULL, 'Staffel 1', 2, 'Christian.Schmoll@canda.com', NULL),
('a7386063-3659-41fe-8b12-75938b74f2b9', 'Laufs', 'William', 1965, 8, NULL, NULL, 'William.Laufs@canda.com', NULL),
('aa43b716-d075-4e61-9f3b-68ecd3566d72', 'Brueckner', 'Benedikt', 1988, NULL, NULL, NULL, 'Benedikt.Brueckner@canda.com', NULL),
('ac3ddb59-3b93-4ca9-8451-781cfb0430ec', 'Roin', 'Andreas', 1973, NULL, 'Staffel 1', 3, 'Andreas.Roin@canda.com', 'Ist an dem WoE verreist.'),
('b0521e7d-84f1-4f0e-9112-4fd2c53ab99a', 'Mansith', 'Daniela', 1973, 2, NULL, NULL, 'Daniela.Mansith@canda.com', NULL),
('bb22a177-c316-4887-ae6e-bb0e4d26b4fa', 'Campbell', 'Maxine', 1976, 1, 'I love it when a PLAN comes together', 1, 'Maxine.Campbell@canda.com', NULL),
('bc3882ae-7561-4284-9d56-618006ac5016', 'Nowak', 'Maria', 1985, 2, NULL, NULL, 'Maria.Nowak@canda.com', NULL),
('bda1cc68-5513-441a-926a-f66b289476fc', 'Ehrich', 'Simon', 1980, 7, NULL, NULL, 'Simon.Ehrich@canda.com', 'Verletzt'),
('c3fbdd03-95ee-4b94-bf22-e9adbd805da6', 'Haferkamp', 'Martin', 1987, 7, NULL, NULL, 'Martin.Haferkamp@canda.com', NULL),
('c76b5ec7-982f-4d3d-8201-e71b19b870a7', 'Hildmann', 'Thomas', 1971, 7, NULL, NULL, 'Thomas.Hildmann@canda.com', NULL),
('cae5fde3-e555-457a-9a71-0beb34198f61', 'Holz', 'Martin', 1972, 8, NULL, NULL, 'Martin.Holz@canda.com', NULL),
('cb514c0d-92b9-41de-8595-b665488fd601', 'Glauche', 'Michael', 1971, 9, NULL, NULL, 'Michael.Glauche@canda.com', NULL),
('d3115ab5-5d8b-4c1f-ae55-07e6b57bacb9', 'Bellinghoven', 'Alexandra', 1985, 2, NULL, NULL, 'Alexandra.Bellinghoven@canda.com', NULL),
('d33149f3-d37b-46d0-8875-3b31473014b8', 'Grimm', 'Gerhard', 1968, 7, NULL, NULL, 'Gerhard.Grimm@canda.com', NULL),
('d52faeb7-e72d-4561-a672-9bc1c459bfe7', 'HÃ¤usler', 'Ingrid', 1983, 3, NULL, NULL, 'Ingrid.Haeusler@canda.com', 'FÃ¤llt aus'),
('d603450d-605d-41a9-8cda-de310c1218b8', 'DÃ¶ring', 'Jens', 1973, 6, NULL, NULL, 'Jens.Doering@canda.com', 'FÃ¤llt aus'),
('d6d497f0-7df7-4b35-9823-ff76372603f7', 'SchÃ¼rings', 'Janine', 1981, 2, NULL, NULL, 'Janine.Hofmann@canda.com', 'Vorher Hofmann (Heirat?)'),
('d88cd64c-a458-4368-bb3d-33a5a5fd3687', 'Stein', 'Ulla', 1969, NULL, NULL, NULL, 'Ulla.Stein@canda.com', NULL),
('d9da898e-b565-4cfa-b2d7-55b0b6dc1a29', 'BÃ¶ggemann', 'Markus', 1969, 9, NULL, NULL, 'Markus.Boeggemann@canda.com', NULL),
('da91d412-c9e0-4d92-b21a-5b1b6e904913', 'Weintrit', 'Jacek', 1970, 7, NULL, NULL, 'Jacek.Weintrit@canda.com', 'MÃ¶chte gerne Etappe 1 laufen\\r\\n'),
('db742e40-ceb2-4111-b564-ed4ae8892b8b', 'Percival', 'Steve', 1987, 8, NULL, NULL, 'stephen.percival@canda.com', NULL),
('db9764b8-03a1-45da-b1ba-09b538b4039f', 'Seidler', 'Frederik', 1990, 7, 'Run & Sprint Champions', 2, 'frederik.seidler@gmx.de', 'Hat gekÃ¼ndigt. Bleibt erstmal erhalten.'),
('de102adf-27f9-43bc-ab6e-502d64412120', 'Simion', 'Daniela', 1989, 2, NULL, NULL, 'Daniela.Simion@canda.com', 'Ich bevorzuge die erste Strecke (11,3 km); eingeschÃ¤tzte Laufzeit: 60 - 70 Minuten.\\r\\n'),
('e176d4d6-1742-43d8-acb1-c69f069bd953', 'Bienert', 'Helge', 1975, 9, NULL, NULL, 'Helge.Bienert@canda.com', 'FÃ¤llt aus'),
('e389fb01-70d2-485a-8478-56e4f275bb81', 'Benaicha', 'Hafid', 1974, 8, 'Run & Sprint Champions', 4, 'Hafid.Benaicha@canda.com', NULL),
('e4e37582-8a77-48f1-bfe4-cc043d4472b5', 'Heinemann', 'Benjamin', 1992, 8, NULL, NULL, 'Benjamin.Heinemann@canda.com', 'FÃ¤llt aus'),
('e656123c-375c-4094-bd3e-fa212c32da9d', 'Eisenreich', 'Klaus', 1963, 3, NULL, NULL, 'Klaus.Eisenreich@canda.com', NULL),
('e7c625cf-e0ab-4452-98c1-062301824d7a', 'Heinz', 'Julian', 1997, NULL, NULL, NULL, 'Julian.Heinz@canda.com', NULL),
('f07827a1-5d75-407d-8330-537bf5e8305a', 'Parizek', 'Janina', 1982, 2, NULL, NULL, 'janinagabriele.parizek@canda.com', NULL),
('f4d8855a-66e4-46ef-8a28-b88eb8b3be3e', 'Weisweiler', 'Andrea', 1967, 1, NULL, NULL, 'Andrea.Weisweiler@canda.com', NULL),
('fd27cd18-dbba-46f0-99e4-3101037bcdd0', 'Braynina', 'Julia', 1989, 2, 'hot runners@C&A', 2, 'Julia.Braynina@canda.com', NULL),
('ffbbd7ca-85b8-4bdc-b411-9e60a30540af', 'Bartocha', 'Adrian', 1987, 7, 'Run & Sprint Champions', 3, 'Adrian.Bartocha@canda.com', NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur fÃ¼r Tabelle `relay`
--

CREATE TABLE `relay` (
  `id` char(36) NOT NULL COMMENT 'UUID as string representation.',
  `eventId` char(36) NOT NULL COMMENT 'UUID from the Event string representation.',
  `relayname` varchar(256) NOT NULL COMMENT 'The name of the relay.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='relayD - The table for the relay entities.';

--
-- Daten fÃ¼r Tabelle `relay`
--

INSERT INTO `relay` (`id`, `eventId`, `relayname`) VALUES
('1803135f-bc38-4d92-a398-6a3c62511018', '5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'Staffel 13'),
('1af2a1cc-b1f7-4c32-9e75-2746f62d56fc', '5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'hot runners @ C&A'),
('2a7caa12-3a10-4e1e-8cc9-14c49d773fef', '5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'I love it when a PLAN comes together @ C&A'),
('2d8ef52b-531f-4fbb-9e98-8c5e390da0fa', '5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'Minimum Competent Runner @ C&A'),
('2fca0250-e200-4150-bd84-ef9c2aa163d6', '5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'Charmant & Ausdauernd @ C&A'),
('4325d69c-429d-4084-a05c-4bfaf8d19a10', '5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'street sweeper @ C&A'),
('4b568dda-15e7-4e11-b7f9-47a9153ae17b', '5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'Chili Runners @ C&A'),
('4fbf6161-ad4e-40a7-8aa4-04edafc2ff49', '5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'Die Hausis @ C&A'),
('5805b0e6-5c80-4a63-ada5-e8196c1631bc', '5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'Staffel 3'),
('6bde1624-b6c6-42e9-898b-c0ecc1e281f6', '5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'Staffel 18'),
('7814902f-22b4-4851-9bc8-1153baf55584', '5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'die doubledigits @ C&A'),
('84d9f07e-fa21-4c2a-b3cb-07d1e7f42e48', '5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'Staffel 1'),
('9579f146-3df6-4bba-a77a-b69b25943e75', '5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'Schweinehunde @ C&A'),
('d42a83da-4829-490f-8bc1-6b3fe86d70b3', '5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'Die Transformers @ C&A'),
('e2e1e2fe-6224-4d08-9eb6-7d3f1ff878cb', '5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'Staffel 7'),
('f77d99b6-f248-466a-ae85-dcde7e4085dc', '5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'Insights on Tour @ C&A'),
('f8001306-2099-4469-9656-791d903c5521', '5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'Run & Sprint Champions @ C&A'),
('fdf0e092-d745-4451-a0b5-de52708ca54d', '5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'DE, RO & UK united @ C&A');

-- --------------------------------------------------------

--
-- Tabellenstruktur fÃ¼r Tabelle `relay_event`
--

CREATE TABLE `relay_event` (
  `id` char(36) NOT NULL,
  `eventname` varchar(256) NOT NULL,
  `eventDay` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten fÃ¼r Tabelle `relay_event`
--

INSERT INTO `relay_event` (`id`, `eventname`, `eventDay`) VALUES
('5697d710-8967-4b2d-9ab2-8fc50ddc6138', 'Metro Group Marathon DÃ¼sseldorf', '2017-04-30');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes fÃ¼r die Tabelle `participant`
--
ALTER TABLE `participant`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `participant_idx` (`id`),
  ADD UNIQUE KEY `person_relay_position_IDX` (`personId`,`relayId`,`relayPosition`),
  ADD KEY `fk_participant_relay` (`relayId`);

--
-- Indizes fÃ¼r die Tabelle `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `person_idx` (`id`);

--
-- Indizes fÃ¼r die Tabelle `relay`
--
ALTER TABLE `relay`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `relay_idx` (`id`),
  ADD KEY `fk_relay_relay_event` (`eventId`);

--
-- Indizes fÃ¼r die Tabelle `relay_event`
--
ALTER TABLE `relay_event`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `relay_event_idx` (`id`);

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `participant`
--
ALTER TABLE `participant`
  ADD CONSTRAINT `fk_participant_person` FOREIGN KEY (`personId`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `fk_participant_relay` FOREIGN KEY (`relayId`) REFERENCES `relay` (`id`) ON DELETE CASCADE;

--
-- Constraints der Tabelle `relay`
--
ALTER TABLE `relay`
  ADD CONSTRAINT `fk_relay_relay_event` FOREIGN KEY (`eventId`) REFERENCES `relay_event` (`id`) ON DELETE CASCADE;
--
-- Datenbank: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `test`;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;