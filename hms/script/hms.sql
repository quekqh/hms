-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 12, 2017 at 12:10 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `hms`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE IF NOT EXISTS `appointment` (
  `appointment_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `customer_name` varchar(45) NOT NULL,
  `appointment_date` date NOT NULL,
  `appointment_time` time NOT NULL,
  `end_time` time NOT NULL,
  `remarks` varchar(1000) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`appointment_id`),
  KEY `customer_id` (`customer_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=52 ;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`appointment_id`, `customer_id`, `username`, `customer_name`, `appointment_date`, `appointment_time`, `end_time`, `remarks`, `status`) VALUES
(1, 12345678, 'noel', 'Low Kang Ka', '2016-12-03', '00:59:00', '00:00:00', 'change hairstylist, wash hair', 'Done'),
(3, 12345678, 'noel', 'Low Kang Ka', '2016-12-03', '13:59:00', '00:00:00', 'testing', 'Done'),
(5, 12345678, 'sam', 'Bunny Low', '2017-01-21', '00:59:00', '00:00:00', 'cut hair', 'Done'),
(6, 12345678, 'sam', 'Bunny Low', '2016-12-16', '00:59:00', '00:00:00', '', 'Done'),
(22, 12345678, 'sam', 'Low Kang Ka', '2017-01-14', '00:59:00', '00:00:00', 'wash', 'Ongoing'),
(10, 23456789, 'noel', 'Bunny Low', '2016-11-01', '10:59:00', '00:00:00', '', 'Done'),
(11, 12345678, 'noel', 'Bunny Low', '2016-11-01', '11:27:00', '00:00:00', '', 'Done'),
(14, 12345678, 'noel', 'Low Kang Ka', '2016-11-01', '13:30:00', '00:00:00', 'wash', 'Done'),
(15, 12345678, 'sam', 'Low Kang Ka', '2017-01-19', '13:00:00', '00:00:00', 'dye', 'Done'),
(16, 12345678, 'sam', 'Low Kang Ka', '2016-12-03', '15:30:00', '00:00:00', 'testing', 'Done'),
(17, 12345678, 'hui', 'Low Kang Ka', '2016-12-03', '17:15:00', '00:00:00', '', 'Ongoing'),
(18, 12345678, 'hui', 'Low Kang Ka', '2016-12-03', '20:00:00', '00:00:00', '', 'Ongoing'),
(19, 23456789, 'noel', 'Bunny Low', '2016-12-03', '21:15:00', '00:00:00', '', 'Ongoing'),
(20, 23456789, 'noel', 'Bunny Low', '2016-12-03', '22:30:00', '00:00:00', '', 'Ongoing'),
(30, 23456789, 'noel', 'Bunny Low', '2017-01-12', '13:00:00', '00:00:00', '', 'Ongoing'),
(25, 12345678, 'sam', 'Low Kang Ka', '2016-11-01', '21:00:00', '00:00:00', '', 'Done'),
(36, 23456789, 'hui', 'Bunny Low', '2016-11-06', '08:00:00', '00:00:00', '', 'Done'),
(27, 12345678, 'sam', 'Low Kang Ka', '2016-11-06', '14:00:00', '00:00:00', '', 'Done'),
(28, 12345678, 'sam', 'Low Kang Ka', '2017-02-06', '03:59:00', '00:00:00', '', 'Ongoing'),
(29, 12345678, 'noel', 'Low Kang Ka', '2017-03-17', '14:00:00', '00:00:00', '', 'Ongoing'),
(34, 23456789, 'hui', 'Bunny Low', '2016-11-06', '09:00:00', '00:00:00', '', 'Done'),
(39, 12345678, 'hui', 'Low Kang Ka', '2016-11-06', '13:00:00', '00:00:00', '', 'Done'),
(45, 12345678, 'sam', 'Low Kang Ka', '2016-12-19', '14:00:00', '15:00:00', '', 'Ongoing'),
(47, 12345678, 'hui', 'Low Kang Ka', '2016-12-19', '08:00:00', '11:00:00', '', 'Ongoing'),
(50, 12345678, 'hui', 'Low Kang Ka', '2016-12-20', '15:35:00', '17:05:00', '', 'Ongoing'),
(51, 98571834, 'goh', 'Jane Doe', '2017-02-06', '00:33:00', '01:03:00', '', 'Ongoing');

-- --------------------------------------------------------

--
-- Table structure for table `appointment_service`
--

CREATE TABLE IF NOT EXISTS `appointment_service` (
  `appointment_id` int(11) NOT NULL,
  `service_id` varchar(100) NOT NULL,
  PRIMARY KEY (`service_id`,`appointment_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` int(10) NOT NULL,
  `customer_name` varchar(100) NOT NULL,
  `dob` date NOT NULL,
  `gender` varchar(45) NOT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  `regdate` date NOT NULL,
  `email` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `customer_name`, `dob`, `gender`, `remarks`, `regdate`, `email`, `username`) VALUES
(12345678, 'Low Kang Ka', '1992-05-02', 'Female', 'i love fyp', '2016-05-23', 'lowk@gmail.com', 'kllow'),
(23456789, 'Bunny Low', '1985-05-20', 'Male', ' I like boys', '2001-12-13', 'bunny@yahoo.com', 'kllow'),
(98571834, 'Jane Doe', '1991-05-23', 'Female', 'I am a girl', '2016-05-23', 'haja@gmail.com', 'qh123'),
(81234567, 'Qiao Hui', '1994-08-01', 'Female', '', '2014-11-01', 'qhquek.2014@sis.smu.edu.sg', 'sis'),
(98794545, 'Goh Shi Ying', '1993-10-05', 'Female', '', '1993-10-05', 'sy1005@gmail.com', 'smtp'),
(67899982, 'Benjamin Button', '1941-12-08', 'Male', ' I have 2 legs ', '1941-12-08', 'buttons@gmail.com', 'shuxin');

-- --------------------------------------------------------

--
-- Table structure for table `leavedb`
--

CREATE TABLE IF NOT EXISTS `leavedb` (
  `leaveId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `leave_types` varchar(45) NOT NULL,
  `remarks` varchar(500) NOT NULL,
  `leave_status` varchar(45) NOT NULL,
  PRIMARY KEY (`leaveId`),
  KEY `username_idx` (`username`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `leavedb`
--

INSERT INTO `leavedb` (`leaveId`, `username`, `name`, `date`, `leave_types`, `remarks`, `leave_status`) VALUES
(2, 'hui', 'Hui Yu Chen', '2017-01-25', 'Weekly Leave', 'take leave for holiday', 'Approved'),
(3, 'sam', 'Samson Yu', '2016-12-31', 'Weekly Leave', 'travel overseas', 'Approved'),
(6, 'admin', 'admin', '2013-01-20', 'Weekly Leave', '', 'Pending'),
(7, 'admin', 'admin', '1991-05-23', 'Weekly Leave', '', 'Pending'),
(8, 'admin', 'admin', '2017-01-20', 'Weekly Leave', '', 'Pending'),
(9, 'admin', 'admin', '2017-01-20', 'Weekly Leave', '', 'Pending'),
(10, 'admin', 'admin', '2017-01-21', 'Weekly Leave', '', 'Pending'),
(11, 'noel', 'Noel Ng', '2017-01-21', 'Weekly Leave', '', 'Approved');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
  `apptId` int(11) NOT NULL,
  `hairstylist` varchar(45) NOT NULL,
  `customerId` int(11) NOT NULL,
  `payment` double NOT NULL,
  `services` varchar(1000) NOT NULL,
  `paymentDate` date NOT NULL,
  `remarks` varchar(1000) NOT NULL,
  `comment` varchar(1000) NOT NULL,
  PRIMARY KEY (`apptId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`apptId`, `hairstylist`, `customerId`, `payment`, `services`, `paymentDate`, `remarks`, `comment`) VALUES
(6, 'sam', 12345678, 250, 'Partial Highlight (Medium),Soft Rebonding (Medium),Fringe (Short)', '2016-12-28', '', ''),
(50, 'hui', 12345678, 230, 'Soft Rebonding (Long)', '2016-12-30', '', ''),
(47, 'hui', 12345678, 560, 'Korean S & C Waves (Long),Tokio Smoothening Treatment (Medium)', '2016-12-30', 'Satisfied', 'not bad'),
(1, 'noel', 12345678, 600, 'Inkarami Hair Treatment (Long),Tokio Smoothening Treatment (Long)', '2016-12-28', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `payroll`
--

CREATE TABLE IF NOT EXISTS `payroll` (
  `userId` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `basic` int(11) NOT NULL,
  `comm` int(11) NOT NULL,
  `deduct` int(11) NOT NULL,
  `gross` int(11) NOT NULL,
  `cpf` int(11) NOT NULL,
  `nett` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payroll`
--

INSERT INTO `payroll` (`userId`, `date`, `basic`, `comm`, `deduct`, `gross`, `cpf`, `nett`) VALUES
('goh', '2017-01-01', 2500, 3000, 50, 5500, 935, 4565),
('hui', '2017-01-01', 2500, 3000, 0, 5500, 935, 4565);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `role_id` int(5) NOT NULL,
  `role_description` varchar(25) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_description`) VALUES
(1, 'admin'),
(2, 'staff'),
(3, 'freelancer');

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE IF NOT EXISTS `service` (
  `serviceId` int(11) NOT NULL AUTO_INCREMENT,
  `serviceDesc` varchar(100) NOT NULL,
  `short` double NOT NULL,
  `medium` double NOT NULL,
  `long_price` double NOT NULL,
  `category` varchar(45) NOT NULL,
  PRIMARY KEY (`serviceId`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=35 ;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`serviceId`, `serviceDesc`, `short`, `medium`, `long_price`, `category`) VALUES
(1, 'Director Cut', 50, 55, 60, 'colour'),
(2, 'Creative Hair Stylist Cut', 45, 50, 55, 'cut'),
(3, 'Hair Stylist Cut', 35, 40, 45, 'colour'),
(4, 'Director Kids (below 16)', 35, 35, 35, 'colour'),
(5, 'Hair Stylist Kids (below 16)', 30, 30, 30, 'colour'),
(6, 'Stylist Kids (below 16)', 25, 25, 25, 'cut'),
(7, 'Fringe', 10, 10, 10, 'cut'),
(8, 'Wash & Blow-dry', 30, 35, 40, 'style'),
(9, 'Styling', 40, 50, 60, 'style'),
(10, 'Full Head', 120, 150, 180, 'colour'),
(11, 'Roots Retouch (1 inch)', 90, 90, 90, 'colour'),
(12, 'Toning', 70, 90, 110, 'colour'),
(13, 'Partial Highlight', 50, 60, 70, 'colour'),
(14, 'Half Head Highlight', 130, 160, 190, 'colour'),
(15, 'Full Head Highlight', 160, 190, 220, 'colour'),
(16, 'Half Head Highlight + 2nd Color', 210, 230, 260, 'colour'),
(17, 'Full Head Highlight + 2nd Color', 240, 270, 300, 'colour'),
(18, 'Harts Dimension', 250, 300, 350, 'colour'),
(19, 'Pre-lightening/Bleaching', 120, 150, 180, 'colour'),
(20, 'Natural Cold Waves', 120, 150, 180, 'rebonding/perm'),
(21, 'Tokio Digital Curls', 200, 290, 280, 'rebonding/perm'),
(22, 'Korean S & C Waves', 170, 220, 270, 'rebonding/perm'),
(23, 'Fringe Perm/Rebond', 30, 30, 30, 'rebonding/perm'),
(24, 'Soft Rebonding', 130, 180, 230, 'rebonding/perm'),
(25, 'Exclusive Scalp Care', 80, 80, 80, 'treatment'),
(26, 'Rebuilding Hair Treatment', 90, 150, 210, 'treatment'),
(27, 'Inkarami Hair Treatment', 130, 190, 250, 'treatment'),
(28, 'Tokio Smoothening Treatment', 230, 290, 350, 'colour');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(25) NOT NULL,
  `name` varchar(70) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` int(5) NOT NULL,
  `phone_number` int(15) NOT NULL,
  `email` varchar(70) NOT NULL,
  `address` varchar(150) NOT NULL,
  `security_code` varchar(50) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `name`, `password`, `role`, `phone_number`, `email`, `address`, `security_code`) VALUES
('admin', 'admin', 'admin', 1, 42376543, 'harts@gmail.com', 'jurong east ave 10', 'WeloveHarts@2016'),
('noel', 'Noel Ng', 'noel', 2, 67248977, 'noel@sis.smu.edu.sg', 'amk ave 6', ''),
('sam', 'Samson Yu', 'sam', 2, 81262138, 'sam@smu.edu.sg', 'bukit batok ave 6', ' '),
('hui', 'Hui Yu Chen', 'hui', 2, 94884048, 'huiyuchen@gmail.com', 'yio chu kang avenue 9', ' '),
('samk', 'Samuel Kok', 'samuel', 3, 98765432, 'samuelkok@hotmail.com', 'Bras Basah avenue 1', ' '),
('goh', 'Goh Jin Qiang', 'goh', 3, 23456889, 'jqgohr@yahoo.com', 'melacca avenue 10', ' '),
('shuxin', 'Shu Xin Yoong', 'shuxin', 3, 93846474, 'shuxin@gmail.com', 'khatib avenue 10, 364483', ''),
('jesmine', 'Jesmine Ong', 'jesmine', 3, 63844848, 'jesmineong@gmail.com', 'choa chu kang ave 10,625373', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
