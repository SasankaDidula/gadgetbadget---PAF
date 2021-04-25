-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2021 at 02:33 PM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gadgetbadget`
--

-- --------------------------------------------------------

--
-- Table structure for table `researchs`
--

CREATE TABLE `researchs` (
  `researchID` int(11) NOT NULL,
  `researchCode` varchar(50) NOT NULL,
  `researcherCode` varchar(50) NOT NULL,
  `researchTopic` varchar(500) NOT NULL,
  `researchPrice` double NOT NULL,
  `createdOn` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `researchs`
--

INSERT INTO `researchs` (`researchID`, `researchCode`, `researcherCode`, `researchTopic`, `researchPrice`, `createdOn`) VALUES
(2, 'R001', 'C005', 'asddasd', 99.9, '2021-04-22'),
(3, 'R002', 'C005', 'Three AI And Tech Trends That Will Transform The Fashion Industry', 199.99, '2021-04-22'),
(4, 'R004', 'C009', 'Sales Predictor for supermarkets', 50, '2021-04-22'),
(5, 'R005', 'C005', 'Voice-based Virtual Assistant for Windows', 20, '2021-04-22');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `researchs`
--
ALTER TABLE `researchs`
  ADD PRIMARY KEY (`researchID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `researchs`
--
ALTER TABLE `researchs`
  MODIFY `researchID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
