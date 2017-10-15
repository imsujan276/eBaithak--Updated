-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 05, 2017 at 04:41 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ebaithak`
--

-- --------------------------------------------------------

--
-- Table structure for table `emoji`
--

CREATE TABLE IF NOT EXISTS `emoji` (
`id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=56 ;

--
-- Dumping data for table `emoji`
--

INSERT INTO `emoji` (`id`, `name`, `image`) VALUES
(1, 'emoji01', 'emoji01.png'),
(2, 'emoji02', 'emoji02.png'),
(3, 'emoji03', 'emoji03.png'),
(4, 'emoji04', 'emoji04.png'),
(5, 'emoji05', 'emoji05.png'),
(6, 'emoji06', 'emoji06.png'),
(7, 'emoji07', 'emoji07.png'),
(8, 'emoji08', 'emoji08.png'),
(9, 'emoji09', 'emoji09.png'),
(10, 'emoji10', 'emoji10.png'),
(11, 'emoji11', 'emoji11.png'),
(12, 'emoji12', 'emoji12.png'),
(13, 'emoji13', 'emoji13.png'),
(14, 'emoji14', 'emoji14.png'),
(15, 'emoji15', 'emoji15.png'),
(16, 'emoji16', 'emoji16.png'),
(17, 'emoji17', 'emoji17.png'),
(18, 'emoji18', 'emoji18.png'),
(19, 'emoji19', 'emoji19.png'),
(20, 'emoji20', 'emoji20.png'),
(21, 'emoji21', 'emoji21.png'),
(22, 'emoji22', 'emoji22.png'),
(23, 'emoji23', 'emoji23.png'),
(24, 'emoji24', 'emoji24.png'),
(25, 'emoji25', 'emoji25.png'),
(26, 'emoji26', 'emoji26.png'),
(27, 'emoji27', 'emoji27.png'),
(28, 'emoji28', 'emoji28.png'),
(29, 'emoji29', 'emoji29.png'),
(30, 'emoji30', 'emoji30.png'),
(31, 'emoji31', 'emoji31.png'),
(32, 'emoji32', 'emoji32.png'),
(33, 'emoji33', 'emoji33.png'),
(34, 'emoji34', 'emoji34.png'),
(35, 'emoji35', 'emoji35.png'),
(36, 'emoji36', 'emoji36.png'),
(37, 'emoji37', 'emoji37.png'),
(38, 'emoji38', 'emoji38.png'),
(39, 'emoji39', 'emoji39.png'),
(40, 'emoji40', 'emoji40.png'),
(41, 'emoji41', 'emoji41.png'),
(42, 'emoji42', 'emoji42.png'),
(43, 'sticker1', 'sticker1.png'),
(44, 'sticker2', 'sticker2.png'),
(45, 'sticker3', 'sticker3.png'),
(46, 'sticker4', 'sticker4.png'),
(47, 'sticker5', 'sticker5.png'),
(48, 'sticker6', 'sticker6.png'),
(49, 'sticker7', 'sticker7.png'),
(50, 'sticker8', 'sticker8.png'),
(51, 'sticker9', 'sticker9.png'),
(52, 'sticker10', 'sticker10.png'),
(53, 'sticker11', 'sticker11.png'),
(54, 'sticker12', 'sticker12.png'),
(55, 'sticker100', 'sticker100.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `emoji`
--
ALTER TABLE `emoji`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `emoji`
--
ALTER TABLE `emoji`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=56;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
