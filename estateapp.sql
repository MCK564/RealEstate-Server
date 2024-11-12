-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: mysql
-- Thời gian đã tạo: Th10 12, 2024 lúc 08:57 AM
-- Phiên bản máy phục vụ: 8.0.28
-- Phiên bản PHP: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `estateapp`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `building`
--
create database estateapp;
use estateapp;

CREATE TABLE `building` (
  `id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `ward` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `structure` varchar(255) DEFAULT NULL,
  `numberofbasement` int DEFAULT NULL,
  `floorarea` int DEFAULT NULL,
  `direction` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `rentprice` int DEFAULT NULL,
  `rentpricedescription` text,
  `servicefee` varchar(255) DEFAULT NULL,
  `carfee` varchar(255) DEFAULT NULL,
  `motofee` varchar(255) DEFAULT NULL,
  `overtimefee` varchar(255) DEFAULT NULL,
  `waterfee` varchar(255) DEFAULT NULL,
  `electricityfee` varchar(255) DEFAULT NULL,
  `deposit` varchar(255) DEFAULT NULL,
  `payment` varchar(255) DEFAULT NULL,
  `renttime` varchar(255) DEFAULT NULL,
  `decorationtime` varchar(255) DEFAULT NULL,
  `brokeragetee` decimal(38,2) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `linkofbuilding` varchar(255) DEFAULT NULL,
  `map` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `managername` varchar(255) DEFAULT NULL,
  `managerphone` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `owner_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Đang đổ dữ liệu cho bảng `building`
--

INSERT INTO `building` (`id`, `name`, `street`, `ward`, `district`, `structure`, `numberofbasement`, `floorarea`, `direction`, `level`, `rentprice`, `rentpricedescription`, `servicefee`, `carfee`, `motofee`, `overtimefee`, `waterfee`, `electricityfee`, `deposit`, `payment`, `renttime`, `decorationtime`, `brokeragetee`, `type`, `note`, `linkofbuilding`, `map`, `avatar`, `createddate`, `modifieddate`, `createdby`, `modifiedby`, `managername`, `managerphone`, `status`, `owner_id`) VALUES
(1, 'Nam Giao Building Tower', '59 phan xích long', 'Phường 2', 'QUAN_1', '', 2, 500, '', NULL, 15, NULL, '', '', '', '', '', '', NULL, NULL, NULL, '', NULL, 'TANG_TRET,NGUYEN_CAN,BAN,CHO_THUE', NULL, '', NULL, 'https://drive.google.com/thumbnail?id=1oKya8yl_JuLNBU-_EwKOM0LelXe0y4a4', NULL, '2024-10-23 06:34:12', NULL, NULL, 'Anh Nam-Chị Linh', '2345562', 1, 1),
(2, 'ACM Tower', '96 cao thắng', 'Phường 4', 'QUAN_2', NULL, 2, 650, NULL, NULL, 18, '18 triệu/m2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NGUYEN_CAN', NULL, NULL, NULL, 'https://drive.google.com/thumbnail?id=12O-SRGHb49AhKHx4whPZC4epRupYDj-Y', NULL, '2024-05-01 10:36:26', NULL, NULL, 'Chú Thuận', '0173546263', 1, 1),
(3, 'Alpha 2 Building Tower', '153 nguyễn đình chiểu', 'Phường 6', 'QUAN_1', NULL, 1, 200, NULL, NULL, 20, '20 triệu/m2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NOI_THAT', NULL, NULL, NULL, 'https://drive.google.com/thumbnail?id=1Hoh3POxjrrTN53Gsw3mEXWvFbJucdxBD', NULL, '2024-05-01 10:37:50', NULL, NULL, 'Cô Lý', '0555532578', 1, 1),
(4, 'IDD 1 Building', '111 Lý Chính Thắng', 'Phường 7', 'QUAN_4', NULL, 1, 200, NULL, NULL, 12, '12 triệu/m2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'TANG_TRET,NGUYEN_CAN,NOI_THAT', NULL, NULL, NULL, 'https://drive.google.com/thumbnail?id=1kIvfO6MAEMka6sAgEvI-xaXz0tKcIXtw', NULL, '2024-05-01 10:41:47', NULL, NULL, 'Anh Long', '017345253', 1, 1),
(9, 'Bitexco', 'Nguyễn Huệ', 'Bến Nghé', 'QUAN_1', '', 68, 500, '', NULL, 50, '5', '', '', '', '', '', '', '', '', '', '', NULL, 'NOI_THAT,NGUYEN_CAN,TANG_TRET,CHO_THUE,BAN', '', '', '', 'https://drive.google.com/thumbnail?id=1tGMCtVZEZ-EiMGx8NBUR2fiyULqWAchX', '2024-05-01 07:54:44', '2024-05-01 10:42:45', NULL, NULL, 'Thu', '0987899977', 1, 1),
(10, 'Bitexco', 'Nguyễn Huệ', 'Bến Nghé', 'QUAN_2', '', 68, 500, '', NULL, 50, '5', '', '', '', '', '', '', '', '', '', '', NULL, 'NOI_THAT,NGUYEN_CAN,TANG_TRET,CHO_THUE,BAN', '', '', '', 'https://drive.google.com/thumbnail?id=1Gu9lX5_ZlAQ37rrKd3Y6ItyGDMJx9bny', '2024-05-01 07:58:17', '2024-05-06 00:57:06', NULL, NULL, 'Thu', '0987899977', 1, 1),
(11, 'Phú Mỹ Hưng Glat', 'Tân Mỹ', 'Tân Phú', 'QUAN_7', 'nhà nhiều tầng', 8, 400, 'Đông - Nam', NULL, 20, '20 triệu/ tầng', '', '', '', '', '', '', '', '', '', '', NULL, 'NOI_THAT,NGUYEN_CAN,TANG_TRET,CHO_THUE', '', '', '', 'https://drive.google.com/thumbnail?id=1hkDDLFUN6jQZBv9oFFW7Gy8Zi20h3F56', '2024-05-01 08:03:07', '2024-05-01 20:06:33', NULL, NULL, 'Long', '077777777', 1, 2),
(12, 'Căn gác cao ban công full Nt mới 100% GẦN RMIT, TDTU, LOTTE Q7', 'Lâm Văn Bền', 'Tân Kiểng', 'HUYEN_NHA_BE', '', 2, 20, '', NULL, 5, '5 triệu/ tháng', '', '', '', '', '', '', '', '', '', '', NULL, 'NOI_THAT,TANG_TRET,CHO_THUE', '', '', '', 'https://drive.google.com/thumbnail?id=1_MttTr-MONh4fYiFDgsh8hE5IfyeMLVQ', '2024-05-01 20:12:31', '2024-05-05 15:45:51', NULL, NULL, 'Thiện', '056565321', 1, 2),
(13, 'Căn hộ DUPLEX gác cao full nội thất ngay trung tâm Q7', 'Đường số 3', 'Tân Kiểng', 'QUAN_7', '', 2, 25, '', NULL, 6, '6 triệu / tháng', '', '', '', '', '', '', '', '', '', '', NULL, 'NOI_THAT,CHO_THUE', '', '', '', 'https://drive.google.com/thumbnail?id=1BCvTrls15DlNr0jxxXkHeSfrA96_qwN_', '2024-05-01 20:15:14', '2024-05-05 15:45:18', NULL, NULL, 'Tèo', '0881818181', 1, 2),
(14, 'DUPLEX RỘNG 40m2 gần Đh Ufm, Đh Luật, KCX TÂN THUẬN Q7', 'Huỳnh Tấn Phát', 'Tân Thuận Đông', 'QUAN_7', '', 2, 25, '', NULL, 5, '5 triệu 400 nghìn', '', '', '', '', '', '', '', '', '', '', NULL, 'NOI_THAT,TANG_TRET,CHO_THUE', '', '', '', 'https://drive.google.com/thumbnail?id=1S6h4FmLa3Xbziu4Fj6NDl-9lw_8mfV1c', '2024-05-01 20:23:55', '2024-10-22 11:09:57', NULL, NULL, 'Lý', '087858583', 1, 10),
(18, 'Hoàng Anh Gia Lai Phòng 5 người ở ', 'Lê Văn Lương', 'Tân Phong', 'QUAN_7', '', 2, 35, '', NULL, 10, '10 triệu / tháng', '', '', '', '', '', '', '', '', '', '', NULL, 'NOI_THAT,TANG_TRET,CHO_THUE', '', '', '', 'https://drive.google.com/thumbnail?id=1UF1HtIO_qYDljV-qo-fQOLwNzVvCevU8', '2024-05-02 18:02:18', '2024-05-02 18:03:34', NULL, NULL, 'Lý', '0987899977', 1, 11),
(19, 'Nhà hai mặt tiền, đường Nguyễn Thị Minh Khai', ' Nguyễn Thị Minh Khai', 'test', 'QUAN_1', '', 15, 400, '', NULL, 50000, '50 tỷ', '', '', '', '', '', '', '', '', '', '', NULL, 'NGUYEN_CAN,BAN', '', '', '', 'https://drive.google.com/thumbnail?id=1Y6vl-nKg8LhCGeKIXyI_ZMOT0PL580Z6', '2024-05-02 18:27:35', '2024-05-02 18:28:23', NULL, NULL, '', '', 1, 12),
(22, 'test', 'test', 'test', NULL, '', 5, 500, '', NULL, 50, NULL, '', '', '', '', '', '', NULL, NULL, NULL, '', NULL, 'NOI_THAT,NGUYEN_CAN,CHO_THUE,BAN', NULL, NULL, NULL, NULL, '2024-05-05 08:35:15', '2024-05-05 08:35:15', NULL, NULL, NULL, NULL, 1, 1),
(23, 'landmark', 'xã Mỹ Hạnh Bắc, Đức Hòa, Long An', 'Phường 2', 'QUAN_1', '', 81, 560, '', NULL, 1000, NULL, '', '', '', '', '', '', NULL, NULL, NULL, '', NULL, 'NOI_THAT,NGUYEN_CAN,CHO_THUE,BAN', NULL, '', NULL, 'https://drive.google.com/thumbnail?id=1Nwl3Ii_fovKvMkbmNWzJcW87sVWVa47L', NULL, '2024-05-05 16:09:05', NULL, NULL, 'NAM', '', 1, 15),
(25, 'P 2.5tr giảm còn 2tr, full NT gần vivo city, siêu thị, công viên', 'Nguyễn Hữu Thọ ', 'Tân Phong', NULL, '', 10, 25, '', NULL, 2, '2 triệu/ tháng', '', '', '', '', '', '', NULL, NULL, NULL, '', NULL, 'NOI_THAT,CHO_THUE', NULL, NULL, NULL, NULL, '2024-05-06 00:38:38', '2024-05-06 00:38:38', NULL, NULL, 'Cao', '373874298', 1, 1),
(26, 'test 10', 'test', 'test', NULL, '', 5, 25, '', NULL, 5, '15 triệu/m2', '', '', '', '', '', '', NULL, NULL, NULL, '', NULL, 'NOI_THAT,CHO_THUE,BAN', NULL, NULL, NULL, NULL, '2024-05-06 00:53:30', '2024-05-06 00:53:30', NULL, NULL, 'Cao', '0987899977', 1, 1),
(27, 'tesst', 'tesst', 'tesst', NULL, '', 5, 500, '', NULL, 5, '15 triệu/m2', '', '', '', '', '', '', NULL, NULL, NULL, '', NULL, 'NOI_THAT,CHO_THUE', NULL, NULL, NULL, NULL, '2024-05-06 00:56:25', '2024-05-06 00:56:25', NULL, NULL, 'NAM', '0333279377', 1, 1),
(28, 'tessst', 'tessst', 'tessst', 'QUAN_4', '', 5, 500, '', NULL, 5, '15 triệu/m2', '', '', '', '', '', '', NULL, NULL, NULL, '', NULL, 'NOI_THAT,CHO_THUE', NULL, NULL, NULL, 'https://drive.google.com/thumbnail?id=1nIATEM80LLOPVyPy1-TQ6lcw4WFxU2uu', '2024-05-06 00:58:35', '2024-05-06 01:02:56', NULL, NULL, 'tessst', 'tessst', 1, 1),
(29, 'tesst 11', 'tesst 11', 'tesst 11', 'QUAN_6', '', 50, 500, '', NULL, 25, '25 triệu /tháng', '', '', '', '', '', '', NULL, NULL, NULL, '', NULL, 'NOI_THAT,NGUYEN_CAN,CHO_THUE', NULL, NULL, NULL, 'https://drive.google.com/thumbnail?id=1_rbVAN0OVztySb608K0vuQYW1NaJ0I8W', '2024-05-06 01:47:43', '2024-05-06 01:49:45', NULL, NULL, 'Long', '435234524', 1, 1),
(30, 'Chí Kha', '', 'Bến Nghé', NULL, 'ádfasdf', 5, 5000, '', NULL, 200, '15 triệu/m2', '', '', '', '', '', '', NULL, NULL, NULL, '', NULL, 'NOI_THAT,BAN', NULL, NULL, NULL, NULL, '2024-09-14 03:32:37', '2024-09-14 03:32:37', NULL, NULL, 'Anh Nam-Chị Linh', '1234554321', 1, 17),
(31, 'TDT', 'Nhà Bè, TP Hồ Chí Minh', 'Bến Nghé', NULL, 'nooooo', 5, 5000, '', NULL, 15, '15 triệu/m2', '', '', '', '', '', '', NULL, NULL, NULL, '', NULL, 'NOI_THAT,BAN', NULL, NULL, NULL, NULL, '2024-09-14 03:34:27', '2024-09-14 03:34:27', NULL, NULL, 'Anh Nam-Chị Linh', '1234554321', 1, 17),
(32, 'the cofffe house', 'Nguyễn Thị Thập', 'Tân Phong', NULL, 'nhà nhiều tầng', 2, 200, 'aaa', NULL, 50, '50 triệu / m2', '155', '155', '1111', '1111', '111', '11', NULL, NULL, NULL, '111', 111.00, 'NGUYEN_CAN,TANG_TRET,CHO_THUE', NULL, NULL, NULL, 'https://drive.google.com/thumbnail?id=1JklyjH4HuNlYq_kQU6GqSon2-T2gyoGj', '2024-09-30 11:57:36', '2024-09-30 11:59:27', NULL, NULL, 'Lam', '0987899977', 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `buildingimage`
--

CREATE TABLE `buildingimage` (
  `id` bigint NOT NULL,
  `building_id` bigint NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Đang đổ dữ liệu cho bảng `buildingimage`
--

INSERT INTO `buildingimage` (`id`, `building_id`, `image_url`, `description`, `createddate`, `modifieddate`, `createdby`, `modifiedby`) VALUES
(19, 11, 'https://drive.google.com/thumbnail?id=1qKLw0Ex7RVtimUA-bpWtDe3sxwfcA6-N', 'slider image for Phú Mỹ Hưng Glat', '2024-05-01 20:06:33', '2024-05-01 20:06:33', NULL, NULL),
(20, 11, 'https://drive.google.com/thumbnail?id=17We_5ddfjqPcsIi2MkPXTpYnsyl8HI90', 'slider image for Phú Mỹ Hưng Glat', '2024-05-01 20:06:33', '2024-05-01 20:06:33', NULL, NULL),
(21, 11, 'https://drive.google.com/thumbnail?id=1rWwv8ojNQnt91iVdjBCwKSskCpOp-19Z', 'slider image for Phú Mỹ Hưng Glat', '2024-05-01 20:06:33', '2024-05-01 20:06:33', NULL, NULL),
(32, 18, 'https://drive.google.com/thumbnail?id=1-p-WzEMC9JeArqipEDF56Mu-oagBN32k', 'slider image for Hoàng Anh Gia Lai Phòng 5 người ở ', '2024-05-02 18:03:34', '2024-05-02 18:03:34', NULL, NULL),
(33, 18, 'https://drive.google.com/thumbnail?id=1xwvSwV-YTHOJStMpjzbZrRbiWQNUy37d', 'slider image for Hoàng Anh Gia Lai Phòng 5 người ở ', '2024-05-02 18:03:34', '2024-05-02 18:03:34', NULL, NULL),
(34, 18, 'https://drive.google.com/thumbnail?id=1ON-icgX1QWFOcs0zYILhPiAXaHvtTAbL', 'slider image for Hoàng Anh Gia Lai Phòng 5 người ở ', '2024-05-02 18:03:34', '2024-05-02 18:03:34', NULL, NULL),
(35, 19, 'https://drive.google.com/thumbnail?id=1M4YgR5xNvkhQXjQI-L22p7CtMgI5Zvu4', 'slider image for Nhà hai mặt tiền, đường Nguyễn Thị Minh Khai', '2024-05-02 18:28:23', '2024-05-02 18:28:23', NULL, NULL),
(36, 19, 'https://drive.google.com/thumbnail?id=1Ngw9MlsaeoNk0BpljYTS-BngEBcHB2Lp', 'slider image for Nhà hai mặt tiền, đường Nguyễn Thị Minh Khai', '2024-05-02 18:28:23', '2024-05-02 18:28:23', NULL, NULL),
(37, 19, 'https://drive.google.com/thumbnail?id=1D9Gk5Inimde2TuLqW8eTBo_OFxHZb1xv', 'slider image for Nhà hai mặt tiền, đường Nguyễn Thị Minh Khai', '2024-05-02 18:28:23', '2024-05-02 18:28:23', NULL, NULL),
(38, 19, 'https://drive.google.com/thumbnail?id=194trwKrByUx6CVu1o_nQrFjJ-N1DezmD', 'slider image for Nhà hai mặt tiền, đường Nguyễn Thị Minh Khai', '2024-05-02 18:28:23', '2024-05-02 18:28:23', NULL, NULL),
(39, 19, 'https://drive.google.com/thumbnail?id=1JrY8-nH2R9ybWMZgcRgHMFRl8v4QNQVD', 'slider image for Nhà hai mặt tiền, đường Nguyễn Thị Minh Khai', '2024-05-02 18:28:23', '2024-05-02 18:28:23', NULL, NULL),
(40, 23, 'https://drive.google.com/thumbnail?id=1R6ZynsvD6hntindeoyATIMjRgOV5DKAh', 'slider image for landmark', '2024-05-05 15:43:09', '2024-05-05 15:43:09', NULL, NULL),
(41, 23, 'https://drive.google.com/thumbnail?id=15GsN4naC16pFK-gcMV2hvkY1WzLjf76u', 'slider image for landmark', '2024-05-05 15:43:09', '2024-05-05 15:43:09', NULL, NULL),
(42, 23, 'https://drive.google.com/thumbnail?id=19zTW1_qMCP6RY3RYAWo300hA4TLuHdx0', 'slider image for landmark', '2024-05-05 15:43:09', '2024-05-05 15:43:09', NULL, NULL),
(43, 13, 'https://drive.google.com/thumbnail?id=1wZcV1UEV01uvTLsCJMdDhmHriorbMU_P', 'slider image for Căn hộ DUPLEX gác cao full nội thất ngay trung tâm Q7', '2024-05-05 15:45:18', '2024-05-05 15:45:18', NULL, NULL),
(44, 13, 'https://drive.google.com/thumbnail?id=1eermd8nrveR0TfkuYL6ElJJ5zw5MSr1H', 'slider image for Căn hộ DUPLEX gác cao full nội thất ngay trung tâm Q7', '2024-05-05 15:45:18', '2024-05-05 15:45:18', NULL, NULL),
(45, 13, 'https://drive.google.com/thumbnail?id=1xmeyWV0J4-Sx_LndBj2k5KGMjvDWhVJs', 'slider image for Căn hộ DUPLEX gác cao full nội thất ngay trung tâm Q7', '2024-05-05 15:45:18', '2024-05-05 15:45:18', NULL, NULL),
(46, 12, 'https://drive.google.com/thumbnail?id=1w8UxBc5wDqfKRBwIq5Ph5H5D8jlEiQ8M', 'slider image for Căn gác cao ban công full Nt mới 100% GẦN RMIT, TDTU, LOTTE Q7', '2024-05-05 15:45:51', '2024-05-05 15:45:51', NULL, NULL),
(47, 12, 'https://drive.google.com/thumbnail?id=1izvUtc707hMuvQHlmPxktUnrnA4djEjy', 'slider image for Căn gác cao ban công full Nt mới 100% GẦN RMIT, TDTU, LOTTE Q7', '2024-05-05 15:45:51', '2024-05-05 15:45:51', NULL, NULL),
(48, 12, 'https://drive.google.com/thumbnail?id=1r4B_Ay55hBYwwEc2EEISnU8bGmm5iniR', 'slider image for Căn gác cao ban công full Nt mới 100% GẦN RMIT, TDTU, LOTTE Q7', '2024-05-05 15:45:51', '2024-05-05 15:45:51', NULL, NULL),
(49, 28, 'https://drive.google.com/thumbnail?id=1h3FIA6lTWYC7fYBvHSLy1zYxjP1Up5cZ', 'slider image for tessst', '2024-05-06 01:02:56', '2024-05-06 01:02:56', NULL, NULL),
(50, 28, 'https://drive.google.com/thumbnail?id=1pgLBg1rSvrljJ4nASjc59N45Ffio8Is-', 'slider image for tessst', '2024-05-06 01:02:56', '2024-05-06 01:02:56', NULL, NULL),
(51, 28, 'https://drive.google.com/thumbnail?id=1MsBlxwLcCp9ReJWb_syT-T4YfOkOUnsn', 'slider image for tessst', '2024-05-06 01:02:56', '2024-05-06 01:02:56', NULL, NULL),
(52, 29, 'https://drive.google.com/thumbnail?id=1WF_M2e3hyIpGMGkECKzYyNJbaN5CpYDs', 'slider image for tesst 11', '2024-05-06 01:49:45', '2024-05-06 01:49:45', NULL, NULL),
(53, 29, 'https://drive.google.com/thumbnail?id=1O5N8jKiz28r2gJhrytUjt_n0SOmxkMWf', 'slider image for tesst 11', '2024-05-06 01:49:45', '2024-05-06 01:49:45', NULL, NULL),
(54, 29, 'https://drive.google.com/thumbnail?id=1tT8HfTymVZFjOtnhDA0p0Y0CpeKLsH6i', 'slider image for tesst 11', '2024-05-06 01:49:45', '2024-05-06 01:49:45', NULL, NULL),
(55, 32, 'https://drive.google.com/thumbnail?id=12h38QkEWcoddxi6sSl7-dxB6POHmhUoP', 'slider image for the cofffe house', '2024-09-30 11:59:27', '2024-09-30 11:59:27', NULL, NULL),
(56, 32, 'https://drive.google.com/thumbnail?id=1f0IhZE6S3VS7psjNUaBSsdt1iU4FC8-h', 'slider image for the cofffe house', '2024-09-30 11:59:27', '2024-09-30 11:59:27', NULL, NULL),
(87, 14, 'https://drive.google.com/thumbnail?id=1Tu08-6BO2Jz87Seoks2u6LaZ0ORcLsEm', 'slider image for DUPLEX RỘNG 40m2 gần Đh Ufm, Đh Luật, KCX TÂN THUẬN Q7', '2024-10-22 11:09:57', '2024-10-22 11:09:57', NULL, NULL),
(88, 14, 'https://drive.google.com/thumbnail?id=1nNSHpaON18cL8RIGwJ34uM9728HjXFHs', 'slider image for DUPLEX RỘNG 40m2 gần Đh Ufm, Đh Luật, KCX TÂN THUẬN Q7', '2024-10-22 11:09:57', '2024-10-22 11:09:57', NULL, NULL),
(89, 14, 'https://drive.google.com/thumbnail?id=1X_gM71MZyGo1W9CAFaeEBasISfcrSlPs', 'slider image for DUPLEX RỘNG 40m2 gần Đh Ufm, Đh Luật, KCX TÂN THUẬN Q7', '2024-10-22 11:09:57', '2024-10-22 11:09:57', NULL, NULL),
(90, 14, 'https://drive.google.com/thumbnail?id=142jMDxtq64ucpSsNAzPyIoVmYrH7hu9k', 'slider image for DUPLEX RỘNG 40m2 gần Đh Ufm, Đh Luật, KCX TÂN THUẬN Q7', '2024-10-22 11:09:57', '2024-10-22 11:09:57', NULL, NULL),
(91, 14, 'https://drive.google.com/thumbnail?id=11gJk0eeYp8j6FAmH9P8OjiovRSRP2KAT', 'slider image for DUPLEX RỘNG 40m2 gần Đh Ufm, Đh Luật, KCX TÂN THUẬN Q7', '2024-10-22 11:09:57', '2024-10-22 11:09:57', NULL, NULL),
(92, 1, 'https://drive.google.com/thumbnail?id=16UEGEV4oLnjwEGQBBp03eB6ArErRhPh8', 'slider image for Nam Giao Building Tower', '2024-10-22 17:22:14', '2024-10-22 17:22:14', NULL, NULL),
(93, 1, 'https://drive.google.com/thumbnail?id=1qErENzqF7JVmEiiaTAfGlG6l4xir3Nt4', 'slider image for Nam Giao Building Tower', '2024-10-22 17:22:14', '2024-10-22 17:22:14', NULL, NULL),
(94, 1, 'https://drive.google.com/thumbnail?id=1OH5lCXQu4T5xhvY0p6Sq4SBWjzfUZgDi', 'slider image for Nam Giao Building Tower', '2024-10-22 17:22:14', '2024-10-22 17:22:14', NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chat_messages`
--

CREATE TABLE `chat_messages` (
  `id` bigint NOT NULL,
  `message` varchar(255) NOT NULL,
  `status` bit(1) DEFAULT NULL,
  `timestamp` datetime(6) NOT NULL,
  `conversation_id` bigint NOT NULL,
  `sender_id` bigint NOT NULL,
  `receiver_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `chat_messages`
--

INSERT INTO `chat_messages` (`id`, `message`, `status`, `timestamp`, `conversation_id`, `sender_id`, `receiver_id`) VALUES
(64, 'hé lô mck', b'0', '2024-11-08 10:48:38.611067', 10, 1, 10),
(65, 'cái rì', b'0', '2024-11-08 10:49:17.300349', 10, 10, 1),
(66, 'hỗn', b'0', '2024-11-08 10:49:29.267213', 10, 1, 10),
(67, 'hả mại', b'0', '2024-11-08 10:49:32.253121', 10, 1, 10),
(68, 'm á', b'0', '2024-11-08 10:49:35.767191', 10, 10, 1),
(69, 'nà ní', b'0', '2024-11-08 12:34:38.658788', 10, 1, 10),
(70, 'jahsdf', b'0', '2024-11-08 12:35:05.610785', 10, 1, 10),
(71, 'ddddd', b'0', '2024-11-08 12:35:10.614610', 10, 10, 1),
(72, 'asdfasf', b'0', '2024-11-08 12:41:16.830865', 12, 1, 17),
(73, 'hé lô mck', b'0', '2024-11-08 13:11:04.568733', 10, 1, 10),
(74, 'hé lô nhé admin', b'0', '2024-11-08 13:11:14.143708', 10, 10, 1),
(75, 'asdfas', b'0', '2024-11-11 00:31:04.893265', 10, 1, 10),
(76, 'hảa', b'0', '2024-11-11 12:50:01.514232', 12, 1, 17),
(77, 'test 1', b'0', '2024-11-11 22:41:35.595708', 10, 1, 10),
(78, 'test', b'0', '2024-11-11 22:42:38.280615', 10, 1, 10),
(79, 'eeeee', b'0', '2024-11-11 22:42:54.443968', 12, 1, 17),
(80, 'eeee', b'0', '2024-11-11 22:44:11.754193', 10, 1, 10),
(81, 'dddd', b'0', '2024-11-11 22:44:20.708529', 10, 10, 1),
(82, 'ddd', b'0', '2024-11-11 22:48:44.021161', 12, 1, 17),
(83, 'dddd', b'0', '2024-11-11 22:49:02.827559', 10, 1, 10),
(84, 'ffa', b'0', '2024-11-11 22:51:00.890306', 10, 1, 10),
(85, 'ádfasdf', b'0', '2024-11-11 22:51:04.417567', 10, 10, 1),
(86, 'ádfasdf', b'0', '2024-11-11 22:56:30.384152', 12, 1, 17),
(87, 'ddfadf', b'0', '2024-11-11 22:56:33.718447', 10, 1, 10),
(88, 'hé luuuu', b'0', '2024-11-11 22:56:45.501180', 10, 10, 1),
(89, 'eeee', b'0', '2024-11-11 23:01:55.124592', 10, 10, 1),
(90, 'eeee', b'0', '2024-11-11 23:02:10.768910', 10, 10, 1),
(91, 'eeee', b'0', '2024-11-11 23:02:26.259633', 10, 10, 1),
(94, 'cddd', b'0', '2024-11-11 23:21:52.730944', 10, 1, 10),
(95, 'ttttt', b'0', '2024-11-11 23:22:05.426907', 10, 10, 1),
(96, 'dddasdfa', b'0', '2024-11-11 23:23:52.579600', 10, 1, 10),
(97, 'đếa', b'0', '2024-11-11 23:24:00.276257', 10, 10, 1),
(98, 'ấdfas', b'0', '2024-11-11 23:24:02.710181', 10, 1, 10),
(99, 'ádfasgf', b'0', '2024-11-11 23:24:11.303637', 12, 1, 17),
(100, 'adfe', b'0', '2024-11-11 23:24:17.874826', 10, 1, 10),
(101, 'ê', b'0', '2024-11-11 23:33:25.075035', 14, 1, 2),
(102, 'adf', b'0', '2024-11-12 07:38:38.300192', 10, 1, 10),
(103, 'ddddd', b'0', '2024-11-12 07:38:45.022856', 10, 1, 10),
(104, 'ádfas', b'0', '2024-11-12 08:43:38.440352', 10, 10, 1),
(105, 'hahahaah', b'0', '2024-11-12 08:43:42.364697', 10, 10, 1),
(106, 'huhuuuu', b'0', '2024-11-12 08:43:46.779175', 10, 1, 10);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `communication`
--

CREATE TABLE `communication` (
  `id` bigint NOT NULL,
  `buyer_renter_id` bigint DEFAULT NULL,
  `sale_id` bigint DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `building_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Đang đổ dữ liệu cho bảng `communication`
--

INSERT INTO `communication` (`id`, `buyer_renter_id`, `sale_id`, `phone`, `note`, `building_id`) VALUES
(8, 10, 1, '21341234', 'hehehe55\n', 1),
(9, 10, 2, '505505959', 'nuuuuuuuu\n', 13),
(10, 1, 2, '0888888888', 'hé luuuu\n', 12),
(11, 11, 1, '1234554321', 'hãy gọi lại cho tôi theo số trên', 1),
(12, 12, 11, '1234554321', 'gọi lại cho mình nhé\n', 18),
(13, 12, 2, '7777777', 'hé lô admin', 11),
(14, 1, 12, '123456', 'asdfasdfa', 19),
(15, 2, 1, '98989898', 'nunununu', 1),
(16, 1, 1, '333333', 'he loooooo', 29),
(17, 1, 12, '9999999999', 'heeloo 3', 19),
(18, 10, 17, '99988877', 'heloo Tiêuuu', 31),
(19, 2, 1, '131241211', 'helloooo\nông\n', 32);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `conversations`
--

CREATE TABLE `conversations` (
  `id` bigint NOT NULL,
  `receiver_id` bigint NOT NULL,
  `sender_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `conversations`
--

INSERT INTO `conversations` (`id`, `receiver_id`, `sender_id`) VALUES
(10, 10, 1),
(12, 17, 1),
(14, 2, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `flyway_schema_history`
--

CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `flyway_schema_history`
--

INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
(1, '0', '<< Flyway Baseline >>', 'BASELINE', '<< Flyway Baseline >>', NULL, 'root', '2024-05-24 16:21:32', 0, 1),
(2, '1', 'alter users tables', 'SQL', 'V1__alter_users_tables.sql', -1299320284, 'root', '2024-05-24 16:41:08', 82, 1),
(3, '2', 'create some more tables', 'SQL', 'V2__create_some_more_tables.sql', -1299320284, 'root', '2024-05-24 16:41:08', 33, 1),
(4, '3', 'create payment history table', 'SQL', 'V3__create_payment_history_table.sql', 0, 'root', '2024-10-13 05:56:15', 6, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `love`
--

CREATE TABLE `love` (
  `id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `building_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Đang đổ dữ liệu cho bảng `love`
--

INSERT INTO `love` (`id`, `user_id`, `building_id`) VALUES
(10, 2, 2),
(17, 1, 2),
(18, 1, 3),
(21, 2, 11),
(23, 10, 1),
(24, 10, 2),
(25, 10, 3),
(26, 10, 12),
(27, 10, 14),
(28, 10, 9),
(29, 10, 4),
(31, 11, 13),
(32, 11, 9),
(34, 11, 1),
(35, 11, 18),
(36, 12, 19),
(37, 12, 18),
(38, 12, 12),
(45, 1, 18),
(46, 1, 19),
(48, 15, 22),
(49, 15, 19),
(50, 15, 18),
(51, 15, 10),
(52, 10, 22),
(53, 10, 23),
(54, 1, 9),
(56, 10, 31),
(57, 10, 30),
(60, 1, 31),
(61, 1, 32);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payments`
--

CREATE TABLE `payments` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `status` int NOT NULL,
  `money` float NOT NULL,
  `posts` bigint NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `payments`
--

INSERT INTO `payments` (`id`, `user_id`, `status`, `money`, `posts`, `created_at`, `updated_at`, `email`) VALUES
(14, 1, 0, 2000000, 2, '2024-10-16 09:31:40', '2024-10-16 09:31:40', 'nguyenvana@gmail.com'),
(15, 1, 0, 12000000, 12, '2024-10-16 09:41:18', '2024-10-16 09:41:18', 'chikha13122@gmail.com'),
(16, 1, 1, 12000000, 12, '2024-10-16 09:42:02', '2024-10-16 09:42:47', 'chikha13122@gmail.com'),
(17, 1, 0, 4000000, 4, '2024-10-16 10:09:22', '2024-10-16 10:09:22', 'chikha13122@gmail.com'),
(18, 1, 1, 4000000, 4, '2024-10-16 10:19:42', '2024-10-16 10:20:14', 'chikha13122@gmail.com'),
(19, 1, 1, 5000000, 5, '2024-10-16 13:14:45', '2024-10-16 13:15:43', 'chomatdinh@gmail.com'),
(20, 1, 0, 1000000, 1, '2024-10-16 13:31:27', '2024-10-16 13:31:27', 'khamai05767@gmail.com'),
(21, 1, 0, 1000000, 1, '2024-10-16 13:36:34', '2024-10-16 13:36:34', 'abc123@gmail.com'),
(22, 1, 0, 1000000, 1, '2024-10-16 13:40:23', '2024-10-16 13:40:23', 'khamai05767@gmail.com'),
(23, 1, 1, 5000000, 5, '2024-10-16 13:44:18', '2024-10-16 13:44:56', 'khamai05767@gmail.com'),
(24, 1, 1, 5000000, 5, '2024-10-17 16:41:21', '2024-10-17 16:41:51', 'chikha13122@gmail.com'),
(25, 1, 1, 5000000, 5, '2024-10-19 06:20:13', '2024-10-19 06:21:16', 'khamai05767@gmail.com'),
(26, 1, 0, 1231000000, 1231, '2024-10-19 06:21:45', '2024-10-19 06:21:45', 'khamai05767@gmail.com'),
(27, 1, 0, 5000000, 5, '2024-10-21 11:18:38', '2024-10-21 11:18:38', 'nguyenduy.6203@gmail.com'),
(28, 1, 0, 5000000, 5, '2024-10-21 11:22:00', '2024-10-21 11:22:00', 'nguyenduy.6203@gmail.com'),
(29, 17, 0, 5000000, 5, '2024-10-21 11:36:24', '2024-10-21 11:36:24', 'chikha13122@gmail.com'),
(30, 17, 1, 5000000, 5, '2024-10-21 13:48:53', '2024-10-21 13:50:05', 'chikha13122@gmail.com'),
(31, 1, 1, 5000000, 5, '2024-10-21 13:50:56', '2024-10-21 13:55:21', 'nguyenduy.6203@gmail.com'),
(32, 17, 0, 5000000, 5, '2024-10-21 13:52:51', '2024-10-21 13:52:51', 'chikha13122@gmail.com'),
(33, 1, 1, 5000000, 5, '2024-10-22 09:26:26', '2024-10-22 09:26:54', 'nguyenduy.6203@gmail.com'),
(34, 1, 1, 5000000, 5, '2024-10-22 09:33:48', '2024-10-22 09:38:47', 'nguyenduy.6203@gmail.com'),
(35, 1, 1, 5000000, 5, '2024-10-22 09:39:29', '2024-10-22 09:41:58', 'nguyenduy.6203@gmail.com'),
(36, 1, 1, 5000000, 5, '2024-10-22 09:43:37', '2024-10-22 09:45:37', 'nguyenduy.6203@gmail.com'),
(37, 1, 1, 5000000, 5, '2024-10-22 09:45:49', '2024-10-22 09:46:41', 'nguyenduy.6203@gmail.com'),
(38, 1, 1, 5000000, 5, '2024-10-22 09:49:32', '2024-10-22 09:51:24', 'nguyenduy.6203@gmail.com'),
(39, 1, 1, 5000000, 5, '2024-10-22 09:53:36', '2024-10-22 09:58:55', 'nguyenduy.6203@gmail.com'),
(40, 1, 0, 5000000, 5, '2024-10-22 09:53:38', '2024-10-22 09:53:38', 'nguyenduy.6203@gmail.com'),
(41, 1, 0, 5000000, 5, '2024-10-22 10:04:24', '2024-10-22 10:04:24', 'nguyenduy.6203@gmail.com'),
(42, 1, 1, 5000000, 5, '2024-10-22 10:04:24', '2024-10-22 10:07:14', 'nguyenduy.6203@gmail.com'),
(43, 1, 1, 5000000, 5, '2024-10-22 10:08:16', '2024-10-22 10:10:26', 'nguyenduy.6203@gmail.com'),
(44, 1, 1, 5000000, 5, '2024-10-22 10:11:52', '2024-10-22 10:12:20', 'nguyenduy.6203@gmail.com'),
(45, 1, 1, 5000000, 5, '2024-10-22 10:13:57', '2024-10-22 10:16:27', 'chikha13122@gmail.com'),
(46, 1, 1, 5000000, 5, '2024-10-22 10:25:10', '2024-10-22 10:25:40', 'chikha13122@gmail.com'),
(47, 17, 1, 5000000, 5, '2024-10-22 10:35:00', '2024-10-22 10:36:23', 'nguyenduy.6203@gmail.com'),
(48, 1, 1, 5000000, 5, '2024-10-22 17:24:40', '2024-10-22 17:25:09', 'chikha13122@gmail.com'),
(49, 1, 1, 5000000, 5, '2024-10-23 01:08:54', '2024-10-23 01:09:23', 'chikha13122@gmail.com'),
(50, 1, 0, 50000, 5, '2024-10-23 06:33:14', '2024-10-23 06:33:14', 'khamai05767@gmail.com'),
(51, 1, 1, 20000, 2, '2024-11-11 00:31:54', '2024-11-11 00:32:24', 'chikha13122@gmail.com'),
(52, 1, 0, 20000, 2, '2024-11-11 00:40:32', '2024-11-11 00:40:32', 'chikha13122@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `rentarea`
--

CREATE TABLE `rentarea` (
  `id` bigint NOT NULL,
  `value` bigint DEFAULT NULL,
  `buildingid` bigint DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Đang đổ dữ liệu cho bảng `rentarea`
--

INSERT INTO `rentarea` (`id`, `value`, `buildingid`, `createddate`, `modifieddate`, `createdby`, `modifiedby`) VALUES
(3, 200, 2, NULL, NULL, NULL, NULL),
(4, 300, 2, NULL, NULL, NULL, NULL),
(5, 400, 2, NULL, NULL, NULL, NULL),
(6, 300, 3, NULL, NULL, NULL, NULL),
(7, 400, 3, NULL, NULL, NULL, NULL),
(8, 500, 3, NULL, NULL, NULL, NULL),
(9, 100, 4, NULL, NULL, NULL, NULL),
(10, 400, 4, NULL, NULL, NULL, NULL),
(11, 250, 4, NULL, NULL, NULL, NULL),
(34, NULL, NULL, '2024-05-01 07:54:44', '2024-05-01 07:54:44', NULL, NULL),
(35, NULL, NULL, '2024-05-01 07:58:17', '2024-05-01 07:58:17', NULL, NULL),
(37, 300, 11, '2024-05-01 08:03:08', '2024-05-01 08:03:08', NULL, NULL),
(38, 20, 12, '2024-05-01 20:12:31', '2024-05-01 20:12:31', NULL, NULL),
(39, 25, 13, '2024-05-01 20:15:14', '2024-05-01 20:15:14', NULL, NULL),
(40, 25, 14, '2024-05-01 20:23:55', '2024-05-01 20:23:55', NULL, NULL),
(43, 300, 18, '2024-05-02 18:02:18', '2024-05-02 18:02:18', NULL, NULL),
(44, 300, 19, '2024-05-02 18:27:35', '2024-05-02 18:27:35', NULL, NULL),
(45, 200, 22, '2024-05-05 08:35:15', '2024-05-05 08:35:15', NULL, NULL),
(46, 300, 22, '2024-05-05 08:35:15', '2024-05-05 08:35:15', NULL, NULL),
(50, 100, 1, '2024-05-05 15:47:51', '2024-05-05 15:47:51', NULL, NULL),
(51, 200, 1, '2024-05-05 15:47:51', '2024-05-05 15:47:51', NULL, NULL),
(52, 300, 1, '2024-05-05 15:47:51', '2024-05-05 15:47:51', NULL, NULL),
(58, 100, 23, '2024-05-05 16:09:05', '2024-05-05 16:09:05', NULL, NULL),
(59, 200, 23, '2024-05-05 16:09:05', '2024-05-05 16:09:05', NULL, NULL),
(60, 500, 23, '2024-05-05 16:09:05', '2024-05-05 16:09:05', NULL, NULL),
(76, 15, 25, '2024-05-06 00:38:38', '2024-05-06 00:38:38', NULL, NULL),
(77, 20, 25, '2024-05-06 00:38:38', '2024-05-06 00:38:38', NULL, NULL),
(78, 25, 25, '2024-05-06 00:38:38', '2024-05-06 00:38:38', NULL, NULL),
(79, 100, 26, '2024-05-06 00:53:30', '2024-05-06 00:53:30', NULL, NULL),
(80, 200, 26, '2024-05-06 00:53:30', '2024-05-06 00:53:30', NULL, NULL),
(81, 100, 27, '2024-05-06 00:56:25', '2024-05-06 00:56:25', NULL, NULL),
(82, 200, 27, '2024-05-06 00:56:25', '2024-05-06 00:56:25', NULL, NULL),
(83, 300, 10, '2024-05-06 00:57:06', '2024-05-06 00:57:06', NULL, NULL),
(84, 450, 10, '2024-05-06 00:57:06', '2024-05-06 00:57:06', NULL, NULL),
(85, 100, 28, '2024-05-06 00:58:35', '2024-05-06 00:58:35', NULL, NULL),
(86, 200, 28, '2024-05-06 00:58:35', '2024-05-06 00:58:35', NULL, NULL),
(87, 500, 29, '2024-05-06 01:47:43', '2024-05-06 01:47:43', NULL, NULL),
(88, 600, 29, '2024-05-06 01:47:43', '2024-05-06 01:47:43', NULL, NULL),
(89, 2555, 30, '2024-09-14 03:32:37', '2024-09-14 03:32:37', NULL, NULL),
(90, 2555, 31, '2024-09-14 03:34:27', '2024-09-14 03:34:27', NULL, NULL),
(91, 200, 32, '2024-09-30 11:57:36', '2024-09-30 11:57:36', NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`id`, `name`, `code`, `createddate`, `modifieddate`, `createdby`, `modifiedby`) VALUES
(1, 'Quản trị hệ thống', 'ADMIN', NULL, NULL, NULL, NULL),
(2, 'Người dùng', 'USER', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tokens`
--

CREATE TABLE `tokens` (
  `id` bigint NOT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `token_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `expiration_date` datetime DEFAULT NULL,
  `revoked` tinyint(1) NOT NULL,
  `expired` tinyint(1) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `is_mobile` tinyint(1) DEFAULT '0',
  `refresh_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '',
  `refresh_expiration_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tokens`
--

INSERT INTO `tokens` (`id`, `token`, `token_type`, `expiration_date`, `revoked`, `expired`, `user_id`, `is_mobile`, `refresh_token`, `refresh_expiration_date`) VALUES
(89, 'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjExLCJlbWFpbCI6InVzZXIyQGdtYWlsLmNvbSIsInN1YiI6IjQ0NDQ0NCIsImV4cCI6MTcxNDY3NDk3MH0.5rf72TqzOyKk665BJWCqNN3rQhMfowZME0h1YYrDgn8', 'Bearer', '2024-06-01 17:52:59', 0, 0, 11, 0, '4739f261-a56c-40ce-aaac-eb5fccd40cdf', '2024-07-01 17:52:59'),
(90, 'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjExLCJlbWFpbCI6InVzZXIyQGdtYWlsLmNvbSIsInN1YiI6IjQ0NDQ1NSIsImV4cCI6MTcxNDY3NTM3MX0.YUHHkgRywvK33D8sHMvvgr656i7Slarks0N_Bvwbbhg', 'Bearer', '2024-06-01 17:59:39', 0, 0, 11, 0, '9567ba44-c8c5-48ed-80fe-2ad7dc5c6434', '2024-07-01 17:59:39'),
(92, 'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEyLCJlbWFpbCI6InVzZXIzQGdtYWlsLmNvbSIsInN1YiI6IjU1NTU1NSIsImV4cCI6MTcxNDY3NjYyNH0.vKcntwwmZpsYtMvqD6c07NbQ7sYo9tp0DDjYYFrv1D8', 'Bearer', '2024-06-01 18:20:33', 0, 0, 12, 0, '778bbe30-6cb3-43de-b18c-be6430500c06', '2024-07-01 18:20:33'),
(129, 'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjE1LCJlbWFpbCI6InVzZXI0QGdtYWlsLmNvbSIsInN1YiI6Ijc3Nzc3NyIsImV4cCI6MTcxNDkyNTg3OH0.WV6LCeDa6TLK3DZrFnn2UOz1n6ycB0kBu218TdLWmz8', 'Bearer', '2024-06-04 15:34:46', 0, 0, 15, 0, '2041328e-6d00-4b94-9efd-7bdf4a59e8ac', '2024-07-04 15:34:46'),
(131, 'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjE1LCJlbWFpbCI6InVzZXI0QGdtYWlsLmNvbSIsInN1YiI6Ijc3Nzc3NyIsImV4cCI6MTcxNDkyNjIxNH0.5-vqZbysvCJ19hicuRnk_-vA-Cj8fsDbTHHCJpSDx-s', 'Bearer', '2024-06-04 15:40:22', 0, 0, 15, 0, 'f0070671-9882-4dca-9b49-0d0b97d49e0e', '2024-07-04 15:40:22'),
(135, 'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjE1LCJlbWFpbCI6InVzZXI0QGdtYWlsLmNvbSIsInN1YiI6Ijc3Nzc3NyIsImV4cCI6MTcxNDkyNjkzMX0.vQ3Wo3eXM48nQ0X1yDRpyFXIwlc97Z8kbg5oP6ck27I', 'Bearer', '2024-06-04 15:52:19', 0, 0, 15, 0, '688c3940-e140-4e0b-a735-bb069420ec91', '2024-07-04 15:52:19'),
(148, 'eyJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjE2LCJlbWFpbCI6ImFiYzEyM0BnbWFpbC5jb20iLCJzdWIiOiIxMjM0NTY3IiwiaWF0IjoxNzI2MjgzMjMyLCJleHAiOjE3MjYyODU4MjR9.YibAInYC087N2ydJgdhjlMx19p4hnJoTNTasQGsqSMHIYbdxDe1xwYNLiLdaWaS6', 'Bearer', '2024-10-14 03:07:13', 0, 0, 16, 0, 'bf982f09-a050-405e-9e4a-af083aab08d3', '2024-11-13 03:07:13'),
(158, 'eyJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjIsImVtYWlsIjoidXNlcjFAZ21haWwuY29tIiwic3ViIjoiMjIyMjIyIiwiaWF0IjoxNzI3Njk3NjEzLCJleHAiOjE3Mjc3MDAyMDV9.GMMvmi9b_7z3A-A8sCBQEIsMRUJJlp1Ld0fPwrVG1htJ76jVkGtxx0sgRswuFqKD', 'Bearer', '2024-10-30 12:00:14', 0, 0, 2, 0, 'c2343c7c-882d-4be5-a235-a3ed3a325b7a', '2024-11-29 12:00:14'),
(186, 'eyJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjE3LCJlbWFpbCI6InRpZXVAZ21haWwuY29tIiwic3ViIjoiMDUwNTA1NTAiLCJpYXQiOjE3Mjk1MTA1NTUsImV4cCI6MTcyOTUxMzE0N30.gvrCoK67MAfqf0HUqowbWK-GQ-lvs91rW2t13cinJSpvzO4AenNFqWPCvM4ns1dI', 'Bearer', '2024-11-20 11:35:55', 0, 0, 17, 0, '9d40d7a2-06a9-45d5-b683-cdd9f1bc0764', '2024-12-20 11:35:55'),
(187, 'eyJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjE3LCJlbWFpbCI6InRpZXVAZ21haWwuY29tIiwic3ViIjoiMDUwNTA1NTAiLCJpYXQiOjE3Mjk1MTg1MjQsImV4cCI6MTcyOTUyMTExNn0.Az5Ilbuqmgq10xVJFjgSoqg0MKMPHBjc5eeUz0Rae1Tsr1YD2pEGnEdND47qCrpg', 'Bearer', '2024-11-20 13:48:44', 0, 0, 17, 0, 'd26a9baf-e535-4bf0-b972-12a86d2c488c', '2024-12-20 13:48:44'),
(194, 'eyJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjE3LCJlbWFpbCI6InRpZXVAZ21haWwuY29tIiwic3ViIjoiMDUwNTA1NTAiLCJpYXQiOjE3Mjk1OTMyOTEsImV4cCI6MTcyOTU5NTg4M30.b2wGVW8MzzgBC5RmoFvYV6X6fhBW9kIjJCyOd-1Lp2D7SDCXKtyKY_NE89pH9d1m', 'Bearer', '2024-11-21 10:34:51', 0, 0, 17, 0, '8817ff30-34c0-4c25-b495-e427d4cbadf1', '2024-12-21 10:34:51'),
(239, 'eyJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjIsImVtYWlsIjoidXNlcjFAZ21haWwuY29tIiwic3ViIjoiMjIyMjIyIiwiaWF0IjoxNzMxMDUwMDA3LCJleHAiOjE3MzEwNTI1OTl9.2SSl6X4LJR7h-pRlyK6Jc_3KVTZGXYzxdG8s17wDpq1AAVYAOHvMz4BHB2RuzUYs', 'Bearer', '2024-12-08 07:13:28', 0, 0, 2, 0, 'a70e8980-40e4-42b1-b44e-994131f663fd', '2025-01-07 07:13:28'),
(244, 'eyJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjIsImVtYWlsIjoidXNlcjFAZ21haWwuY29tIiwic3ViIjoiMjIyMjIyIiwiaWF0IjoxNzMxMDUzMDYzLCJleHAiOjE3MzEwNTU2NTV9.C6leSXrcDv1EHXE6XwzI2yJdQ2FG4qUPsg3jB2GF6UE5PbMyGxBFmkow4r7TPGWn', 'Bearer', '2024-12-08 08:04:24', 0, 0, 2, 0, '9c4ffad7-45f3-4103-9dfc-cad0f5201697', '2025-01-07 08:04:24'),
(274, 'eyJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjEwLCJlbWFpbCI6Im1ja0BnbWFpbC5jb20iLCJzdWIiOiIzMzMzMzMiLCJpYXQiOjE3MzEzNjUwMDQsImV4cCI6MTczMTM2NzU5Nn0.ImrL1shVeTFg2M5ePimfciDsMwpNgDg8hsx4asomcvi4YoOP1euZDlNGltQ5qSwR', 'Bearer', '2024-12-11 22:43:24', 0, 0, 10, 0, '5843c3bd-3422-49fc-a41e-64694595afb8', '2025-01-10 22:43:24'),
(277, 'eyJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjEwLCJlbWFpbCI6Im1ja0BnbWFpbC5jb20iLCJzdWIiOiIzMzMzMzMiLCJpYXQiOjE3MzEzNjYwNjMsImV4cCI6MTczMTM2ODY1NX0.n_oYADIdzH0tMTRhIIYvc5w5Hcu4I6Rgx0ia4MoUBISusIJcLmEzS2TDDZtvviMn', 'Bearer', '2024-12-11 23:01:03', 0, 0, 10, 0, 'c88dffe6-d985-4ffd-b974-d1aa6646fc74', '2025-01-10 23:01:03'),
(279, 'eyJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjEsImVtYWlsIjoiYWRtaW5AZ21haWwuY29tIiwic3ViIjoiMTIzNDU2IiwiaWF0IjoxNzMxMzk1NjQ4LCJleHAiOjE3MzEzOTgyNDB9.aO-sjqNCMmlrK6YZibScgXDF5nq41-rTieOSAvB629k-MDFBbu3Pfg5Fj7c1xWCk', 'Bearer', '2024-12-12 07:14:08', 0, 0, 1, 0, '54e4239f-ea12-48b6-a1c9-430234ad710b', '2025-01-11 07:14:08'),
(280, 'eyJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjEsImVtYWlsIjoiYWRtaW5AZ21haWwuY29tIiwic3ViIjoiMTIzNDU2IiwiaWF0IjoxNzMxMzk4MjcyLCJleHAiOjE3MzE0MDA4NjR9.DuvCua4OCFehq3jMDVLQwKo6XEjIH2TDuJsWRxcrw_U2xbeZ7ACo-dtlaRUkY5RN', 'Bearer', '2024-12-12 07:57:53', 0, 0, 1, 0, 'cf336f22-fbaa-4891-a9e1-760817c95cad', '2025-01-11 07:57:53'),
(281, 'eyJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjEwLCJlbWFpbCI6Im1ja0BnbWFpbC5jb20iLCJzdWIiOiIzMzMzMzMiLCJpYXQiOjE3MzE0MDA5MDksImV4cCI6MTczMTQwMzUwMX0.Zv_caRoulAR5Y5_z31VAaIPAGmQ0JSHtQ05d17G94awkOdayTfbR5AYB38MoIV_q', 'Bearer', '2024-12-12 08:41:49', 0, 0, 10, 0, '0a84bc77-52b0-4989-aca5-bed866089e1b', '2025-01-11 08:41:49'),
(282, 'eyJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjEsImVtYWlsIjoiYWRtaW5AZ21haWwuY29tIiwic3ViIjoiMTIzNDU2IiwiaWF0IjoxNzMxNDAwOTE3LCJleHAiOjE3MzE0MDM1MDl9.V_LBjgwuIBIjHA9bYSn_5c3B9n-VlmsgoFsaTByAdRpbKhXlHmCM-p31XlOw3Dqe', 'Bearer', '2024-12-12 08:41:58', 0, 0, 1, 0, '335bcf62-a1f1-479c-8f31-0c97790988e4', '2025-01-11 08:41:58');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `role_id` bigint NOT NULL,
  `used_posts` bigint DEFAULT NULL,
  `remain_posts` bigint DEFAULT NULL,
  `money` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `phone`, `password`, `fullname`, `email`, `status`, `avatar`, `createddate`, `modifieddate`, `createdby`, `modifiedby`, `role_id`, `used_posts`, `remain_posts`, `money`) VALUES
(1, '123456', '$2a$10$ymo6iuWNo3UMTtplnwmAoevJO0YYnHfX7cC9elFfT3/IGFZIysvE.', 'admin', 'admin@gmail.com', 1, 'https://drive.google.com/thumbnail?id=19HlnSuvXPU_PPgR8XMSA91jrtzWL66Lk', NULL, '2024-11-11 00:32:24', NULL, NULL, 1, 0, 111, NULL),
(2, '222222', '$2a$10$F.4888BPYha3mmRaRpw.bOEldNcSHkw4MkA.P2smtQSCPI1MMX/7u', 'user1', 'user1@gmail.com', 1, 'https://drive.google.com/thumbnail?id=1e8m-zT_spXuIE52uxx1xbadVdd-3SKNG', NULL, '2024-10-15 05:34:27', NULL, NULL, 2, 0, 2, NULL),
(10, '333333', '$2a$10$r6dVJdsGWZD9jooc7NEau.iyVie8bJNcUt5zyRKW0sA91FrF4kBoG', 'mck', 'mck@gmail.com', 1, 'https://drive.google.com/thumbnail?id=1gErPd8JWKR-Y-xiWdZv4mxmvOvTDOLAv', '2024-05-01 20:21:38', '2024-10-15 05:34:27', NULL, NULL, 2, 0, 2, NULL),
(11, '444455', '$2a$10$l35AfZEI0Kc0CRanXwVF9OkHTds/PRUNlzu/TJi0Qy0q54o9Y02Wm', 'user2', 'user2@gmail.com', 1, 'https://drive.google.com/thumbnail?id=1yy77lsKavW3pAxN_Xss6IeQAwD3vFYEw', '2024-05-02 17:52:48', '2024-10-15 05:34:27', NULL, NULL, 2, 0, 2, NULL),
(12, '555555', '$2a$10$YB4Q.RqhVS5lUPfYrR0ptOnhioQzKJfgAavV7By/FRf3Q7IdGxAqK', 'user3', 'user3@gmail.com', 1, 'https://drive.google.com/thumbnail?id=1Kq5rBrQWsU5gTb4oom-4zg3UGrHaFWQZ', '2024-05-02 18:20:24', '2024-10-15 05:34:27', NULL, NULL, 2, 0, 2, NULL),
(15, '777777', '$2a$10$o7PGxW/5D5wCe02NgEnZNOFrsu6ngUIMp.yV97ui/QWLbXuSzqiUu', 'user3', 'user4@gmail.com', 1, 'https://drive.google.com/thumbnail?id=1_L9UFd_MU5J3jCJbjCTl67FEe691XwUX', '2024-05-05 15:34:36', '2024-10-15 05:34:27', NULL, NULL, 2, 0, 2, NULL),
(16, '1234567', '$2a$10$GzUZRaDfM6XWEVrzZuEnG.DWSzYCdTl9XYyOKZ1bxo0pyFwW5U2W2', 'cus2', 'abc123@gmail.com', 1, NULL, '2024-09-14 03:07:05', '2024-10-15 05:34:27', NULL, NULL, 2, 0, 2, NULL),
(17, '05050550', '$2a$10$UuHuymczTH5GSzTTSqkz7.eqaOvUzTxr7HK57etVmsGcIF4jIM2uO', 'Tiêu', 'tieu@gmail.com', 1, 'https://drive.google.com/thumbnail?id=1TVyjNbZb9SVmnbJnhJtfNvOiP01HE3-9', '2024-09-14 03:29:09', '2024-10-22 10:36:22', NULL, NULL, 2, 0, 12, NULL),
(18, '085253111', '$2a$10$W/7VeQSrQAvBnAceCCsgXudPPgcx6bOiKtgYMG1D5V86SlmsKtoZ2', 'Long', 'chikha13122@gmail.com', 1, NULL, '2024-10-23 10:13:31', '2024-10-23 10:13:31', NULL, NULL, 2, NULL, NULL, NULL);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `building`
--
ALTER TABLE `building`
  ADD PRIMARY KEY (`id`),
  ADD KEY `owner_id` (`owner_id`);

--
-- Chỉ mục cho bảng `buildingimage`
--
ALTER TABLE `buildingimage`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_building_image_building` (`building_id`);

--
-- Chỉ mục cho bảng `chat_messages`
--
ALTER TABLE `chat_messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKc8ljv426x8fj9tcywei40stu9` (`conversation_id`),
  ADD KEY `FKdrg1riwsto0x2cn9yxdu1sc4p` (`sender_id`);

--
-- Chỉ mục cho bảng `communication`
--
ALTER TABLE `communication`
  ADD PRIMARY KEY (`id`),
  ADD KEY `building_id` (`building_id`),
  ADD KEY `buyer_renter_id` (`buyer_renter_id`),
  ADD KEY `sale_id` (`sale_id`);

--
-- Chỉ mục cho bảng `conversations`
--
ALTER TABLE `conversations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdgwqqv7xuq6w2x1f5oxoe2st4` (`receiver_id`),
  ADD KEY `FKgnabflt5pdyxn0gn2ebyuy72r` (`sender_id`);

--
-- Chỉ mục cho bảng `flyway_schema_history`
--
ALTER TABLE `flyway_schema_history`
  ADD PRIMARY KEY (`installed_rank`),
  ADD KEY `flyway_schema_history_s_idx` (`success`);

--
-- Chỉ mục cho bảng `love`
--
ALTER TABLE `love`
  ADD PRIMARY KEY (`id`),
  ADD KEY `building_id` (`building_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Chỉ mục cho bảng `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Chỉ mục cho bảng `rentarea`
--
ALTER TABLE `rentarea`
  ADD PRIMARY KEY (`id`),
  ADD KEY `buildingid` (`buildingid`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tokens`
--
ALTER TABLE `tokens`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgh73xiagbl0no2bm4i7q29isu` (`user_id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `building`
--
ALTER TABLE `building`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT cho bảng `buildingimage`
--
ALTER TABLE `buildingimage`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=95;

--
-- AUTO_INCREMENT cho bảng `chat_messages`
--
ALTER TABLE `chat_messages`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=107;

--
-- AUTO_INCREMENT cho bảng `communication`
--
ALTER TABLE `communication`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT cho bảng `conversations`
--
ALTER TABLE `conversations`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `love`
--
ALTER TABLE `love`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT cho bảng `payments`
--
ALTER TABLE `payments`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT cho bảng `rentarea`
--
ALTER TABLE `rentarea`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=92;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `tokens`
--
ALTER TABLE `tokens`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=283;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `building`
--
ALTER TABLE `building`
  ADD CONSTRAINT `building_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `buildingimage`
--
ALTER TABLE `buildingimage`
  ADD CONSTRAINT `fk_building_image_building` FOREIGN KEY (`building_id`) REFERENCES `building` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `chat_messages`
--
ALTER TABLE `chat_messages`
  ADD CONSTRAINT `FKc8ljv426x8fj9tcywei40stu9` FOREIGN KEY (`conversation_id`) REFERENCES `conversations` (`id`),
  ADD CONSTRAINT `FKdrg1riwsto0x2cn9yxdu1sc4p` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `communication`
--
ALTER TABLE `communication`
  ADD CONSTRAINT `communication_ibfk_1` FOREIGN KEY (`building_id`) REFERENCES `building` (`id`),
  ADD CONSTRAINT `communication_ibfk_2` FOREIGN KEY (`buyer_renter_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `communication_ibfk_3` FOREIGN KEY (`sale_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `conversations`
--
ALTER TABLE `conversations`
  ADD CONSTRAINT `FKdgwqqv7xuq6w2x1f5oxoe2st4` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKgnabflt5pdyxn0gn2ebyuy72r` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `love`
--
ALTER TABLE `love`
  ADD CONSTRAINT `love_ibfk_1` FOREIGN KEY (`building_id`) REFERENCES `building` (`id`),
  ADD CONSTRAINT `love_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `rentarea`
--
ALTER TABLE `rentarea`
  ADD CONSTRAINT `rentarea_ibfk_1` FOREIGN KEY (`buildingid`) REFERENCES `building` (`id`);

--
-- Các ràng buộc cho bảng `tokens`
--
ALTER TABLE `tokens`
  ADD CONSTRAINT `FKgh73xiagbl0no2bm4i7q29isu` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
