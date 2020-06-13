-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 22 Sty 2020, 15:16
-- Wersja serwera: 10.4.10-MariaDB
-- Wersja PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `oesk`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `benchmark`
--

CREATE TABLE `benchmark` (
  `id` int(255) NOT NULL,
  `downloadAverage` float NOT NULL,
  `uploadAverage` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `benchmark`
--

INSERT INTO `benchmark` (`id`, `downloadAverage`, `uploadAverage`) VALUES
(1, 4.76351, 17.3646),
(2, 6.98127, 17.1856),
(4, 9.40958, 18.1868),
(5, 8.30449, 17.9655),
(6, 9.53593, 16.9685),
(8, 5.59154, 15.9113),
(9, 8.1794, 18.5851),
(10, 10.3157, 16.8889),
(11, 9.38737, 21.4876),
(12, 5.28179, 13.5212),
(14, 4.35147, 15.1921),
(15, 7.05622, 22.714),
(16, 10.3592, 20.9845),
(17, 10.4843, 23.1238),
(18, 10.906, 16.0546),
(19, 13.1241, 16.7323),
(20, 10.4006, 17.8531),
(21, 10.8472, 23.7998),
(22, 11.2893, 19.2761),
(23, 17.5429, 18.854),
(24, 14.7388, 18.9467),
(25, 15.0649, 20.915),
(26, 14.1202, 17.6236),
(27, 8.5029, 18.9751),
(28, 14.4095, 19.7275),
(29, 14.1696, 20.108),
(30, 13.9482, 16.7093),
(31, 14.5399, 18.3354),
(32, 15.311, 18.3859),
(34, 13.0384, 18.1489),
(35, 13.8467, 19.5701),
(36, 13.4506, 18.5269),
(41, 16.0884, 18.1216),
(42, 15.3844, 17.4458),
(43, 15.6579, 19.9948),
(46, 13.7069, 17.2509),
(47, 12.9028, 21.7101),
(48, 13.4732, 20.7894),
(49, 9.63549, 18.4363),
(50, 13.2465, 21.6796),
(51, 12.736, 18.6929),
(52, 13.3905, 17.0399),
(53, 21.8214, 24.3514),
(54, 16.045, 20.8504),
(57, 16.8469, 18.4564),
(58, 17.4841, 21.0894),
(59, 14.4278, 21.7254),
(60, 14.5216, 25.3407),
(64, 15.9834, 19.3111),
(65, 15.7693, 18.5246),
(66, 15.239, 17.9426),
(67, 15.5876, 18.2039),
(68, 15.6952, 18.8424),
(69, 16.4531, 20.3037),
(71, 7.27773, 4.83761),
(73, 10.8015, 17.949),
(74, 10.893, 22.5599),
(75, 12.1537, 20.4646),
(76, 9.04855, 3.78369),
(77, 15.151, 21.2013),
(78, 6.92371, 24.9243),
(79, 19.646, 19.785),
(80, 17.7096, 21.716),
(81, 15.3586, 18.4228),
(82, 15.8112, 23.4347),
(83, 15.2758, 19.1429),
(84, 11.6847, 19.8839);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `benchmark`
--
ALTER TABLE `benchmark`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT dla tabel zrzutów
--

--
-- AUTO_INCREMENT dla tabeli `benchmark`
--
ALTER TABLE `benchmark`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
