-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 19, 2014 at 05:56 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cungonbai_data`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_answer`
--

CREATE TABLE IF NOT EXISTS `tbl_answer` (
`answer_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `content` text NOT NULL,
  `point` int(11) NOT NULL COMMENT 'Value from -100 to 100'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_favorite`
--

CREATE TABLE IF NOT EXISTS `tbl_favorite` (
`id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_question`
--

CREATE TABLE IF NOT EXISTS `tbl_question` (
`question_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `content` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_session`
--

CREATE TABLE IF NOT EXISTS `tbl_session` (
`session_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `ended_date` int(11) NOT NULL COMMENT 'second',
  `started_date` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_session_question`
--

CREATE TABLE IF NOT EXISTS `tbl_session_question` (
`id` int(11) NOT NULL,
  `session_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `answer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_subject`
--

CREATE TABLE IF NOT EXISTS `tbl_subject` (
  `subject_id` int(11) NOT NULL,
  `subject_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE IF NOT EXISTS `tbl_user` (
`user_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `score` int(11) NOT NULL,
  `is_admin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_answer`
--
ALTER TABLE `tbl_answer`
 ADD PRIMARY KEY (`answer_id`), ADD KEY `question_id` (`question_id`);

--
-- Indexes for table `tbl_favorite`
--
ALTER TABLE `tbl_favorite`
 ADD PRIMARY KEY (`id`), ADD KEY `user_id` (`user_id`), ADD KEY `question_id` (`question_id`);

--
-- Indexes for table `tbl_question`
--
ALTER TABLE `tbl_question`
 ADD PRIMARY KEY (`question_id`), ADD KEY `subject_id` (`subject_id`);

--
-- Indexes for table `tbl_session`
--
ALTER TABLE `tbl_session`
 ADD PRIMARY KEY (`session_id`), ADD KEY `user_id` (`user_id`), ADD KEY `subject_id` (`subject_id`);

--
-- Indexes for table `tbl_session_question`
--
ALTER TABLE `tbl_session_question`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `session_id` (`session_id`,`question_id`,`answer_id`), ADD KEY `session_id_2` (`session_id`), ADD KEY `question_id` (`question_id`), ADD KEY `answer_id` (`answer_id`);

--
-- Indexes for table `tbl_subject`
--
ALTER TABLE `tbl_subject`
 ADD PRIMARY KEY (`subject_id`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
 ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_answer`
--
ALTER TABLE `tbl_answer`
MODIFY `answer_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbl_favorite`
--
ALTER TABLE `tbl_favorite`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbl_question`
--
ALTER TABLE `tbl_question`
MODIFY `question_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbl_session`
--
ALTER TABLE `tbl_session`
MODIFY `session_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbl_session_question`
--
ALTER TABLE `tbl_session_question`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_answer`
--
ALTER TABLE `tbl_answer`
ADD CONSTRAINT `tbl_answer_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `tbl_question` (`question_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_favorite`
--
ALTER TABLE `tbl_favorite`
ADD CONSTRAINT `tbl_favorite_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `tbl_favorite_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `tbl_question` (`question_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_question`
--
ALTER TABLE `tbl_question`
ADD CONSTRAINT `tbl_question_ibfk_1` FOREIGN KEY (`subject_id`) REFERENCES `tbl_subject` (`subject_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_session`
--
ALTER TABLE `tbl_session`
ADD CONSTRAINT `tbl_session_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `tbl_session_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `tbl_subject` (`subject_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_session_question`
--
ALTER TABLE `tbl_session_question`
ADD CONSTRAINT `tbl_session_question_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `tbl_question` (`question_id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `tbl_session_question_ibfk_3` FOREIGN KEY (`answer_id`) REFERENCES `tbl_answer` (`answer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `tbl_session_question_ibfk_4` FOREIGN KEY (`session_id`) REFERENCES `tbl_session` (`session_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
