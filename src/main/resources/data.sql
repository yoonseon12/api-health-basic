--
-- 권한
--
INSERT INTO authority(authority_name)
VALUES
    ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_TEST');
--
-- 운동목표
--
INSERT INTO exercise_purpose(exercise_purpose_name)
VALUES
    ('기초체력증진'), ('체중감량'), ('근력향상'), ('체형교정'),
    ('재활'), ('유연성향상');
--
-- 사용자
--
INSERT INTO USERS (activated, email, nickname, password, phone, username)
VALUES
    ('Y', 'test1@wizclass.kr' , '테스트닉네임1' , '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정1'),
    ('Y', 'test2@wizclass.kr' , '테스트닉네임2' , '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정2'),
    ('Y', 'test3@wizclass.kr' , '테스트닉네임3' , '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정3'),
    ('Y', 'test4@wizclass.kr' , '테스트닉네임4' , '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정4'),
	('Y', 'test5@wizclass.kr' , '테스트닉네임5' , '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정5'),
	('Y', 'test6@wizclass.kr' , '테스트닉네임6' , '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정6'),
	('Y', 'test7@wizclass.kr' , '테스트닉네임7' , '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정7'),
	('Y', 'test8@wizclass.kr' , '테스트닉네임8' , '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정8'),
	('Y', 'test9@wizclass.kr' , '테스트닉네임9' , '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정9'),
	('Y', 'test10@wizclass.kr', '테스트닉네임10', '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정10'),
	('Y', 'test11@wizclass.kr', '테스트닉네임11', '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정11'),
	('Y', 'test12@wizclass.kr', '테스트닉네임12', '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정12'),
	('Y', 'test13@wizclass.kr', '테스트닉네임13', '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정13'),
	('Y', 'test14@wizclass.kr', '테스트닉네임14', '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정14'),
	('Y', 'test15@wizclass.kr', '테스트닉네임15', '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정15'),
	('Y', 'test16@wizclass.kr', '테스트닉네임16', '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정16'),
	('Y', 'test17@wizclass.kr', '테스트닉네임17', '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정17'),
	('Y', 'test18@wizclass.kr', '테스트닉네임18', '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정18'),
	('Y', 'test19@wizclass.kr', '테스트닉네임19', '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정19'),
	('Y', 'test20@wizclass.kr', '테스트닉네임20', '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정20'),
	('Y', 'test21@wizclass.kr', '테스트닉네임21', '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정21'),
	('Y', 'test22@wizclass.kr', '테스트닉네임22', '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정22'),
	('Y', 'test23@wizclass.kr', '테스트닉네임23', '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정23'),
	('Y', 'test24@wizclass.kr', '테스트닉네임24', '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정24'),
	('Y', 'test25@wizclass.kr', '테스트닉네임25', '$2a$10$JF1l6/3oK1wla7IAevDdyO8ZW67YoWzO1fZkn1PN5fMfFSHA.2f5q', '01012345678', '테스트계정25');
--
-- 사용자 권한 부여
--
INSERT INTO USER_AUTHORITY(USER_ID, AUTHORITY_ID)
VALUES
	(1, 1),
	(2, 1),
	(3, 1),
	(4, 1),
	(5, 1),
	(6, 1),
	(7, 1),
	(8, 1),
	(9, 1),
	(10, 1),
	(11, 1),
	(12, 1),
	(13, 1),
	(14, 1),
	(15, 1),
	(16, 1),
	(17, 1),
	(18, 1),
	(19, 1),
	(20, 1),
	(21, 1),
	(22, 1),
	(23, 1),
	(24, 1),
	(25, 1);


--
-- 사용자 운동목적 부여
--
INSERT INTO USER_EXERCISE_PURPOSE (USER_ID, EXERCISE_PURPOSE_ID)
	VALUES
	(1, 1), (1, 2), (1, 3),
	(2, 1), (2, 2),
	(3, 1), (3, 2), (3, 3),
	(4, 1), (4, 2),
	(5, 1), (5, 2), (5, 3),
	(6, 1), (6, 2),
	(7, 1), (7, 2), (7, 3),
	(8, 1), (8, 2),
	(9, 1), (9, 2), (9, 3),
	(10, 1), (10, 2),
	(11, 1), (11, 2), (11, 3),
	(12, 1), (12, 2),
	(13, 1), (13, 2), (13, 3),
	(14, 1), (14, 2),
	(15, 1), (15, 2), (15, 3),
	(16, 1), (16, 2),
	(17, 1), (17, 2), (17, 3),
	(18, 1), (18, 2),
	(19, 1), (19, 2), (19, 3),
	(20, 1), (20, 2),
	(21, 1), (21, 2), (21, 3),
	(22, 1), (22, 2),
	(23, 1), (23, 2), (23, 3),
	(24, 1), (24, 2),
	(25, 1), (25, 2), (25, 3);

--
-- 카테고리
--
INSERT INTO CATEGORY(category_name)
    VALUES('헬스'),('플라잉요가'),('필라테스'),('골프'),('기구필라테스');
--
-- 운동시설
--
INSERT INTO GYM(gym_name, tel_no)
    VALUES
    ('테스트운동시설1', '01012345678'),
    ('테스트운동시설2', '01012345678'),
    ('테스트운동시설3', '01012345678'),
    ('테스트운동시설4', '01012345678'),
    ('테스트운동시설5', '01012345678'),
    ('테스트운동시설6', '01012345678'),
    ('테스트운동시설7', '01012345678'),
    ('테스트운동시설8', '01012345678'),
    ('테스트운동시설9', '01012345678'),
    ('테스트운동시설10', '01012345678'),
    ('테스트운동시설11', '01012345678'),
    ('테스트운동시설12', '01012345678'),
    ('테스트운동시설13', '01012345678'),
    ('테스트운동시설14', '01012345678'),
    ('테스트운동시설15', '01012345678'),
    ('테스트운동시설16', '01012345678'),
    ('테스트운동시설17', '01012345678'),
    ('테스트운동시설18', '01012345678'),
    ('테스트운동시설19', '01012345678'),
    ('테스트운동시설20', '01012345678'),
    ('테스트운동시설21', '01012345678'),
    ('테스트운동시설22', '01012345678'),
    ('테스트운동시설23', '01012345678'),
    ('테스트운동시설24', '01012345678'),
    ('테스트운동시설25', '01012345678'),
    ('테스트운동시설26', '01012345678'),
    ('테스트운동시설27', '01012345678'),
    ('테스트운동시설28', '01012345678'),
    ('테스트운동시설29', '01012345678'),
    ('테스트운동시설30', '01012345678'),
    ('테스트운동시설31', '01012345678'),
    ('테스트운동시설32', '01012345678'),
    ('테스트운동시설33', '01012345678'),
    ('테스트운동시설34', '01012345678'),
    ('테스트운동시설35', '01012345678'),
    ('테스트운동시설36', '01012345678'),
    ('테스트운동시설37', '01012345678'),
    ('테스트운동시설38', '01012345678'),
    ('테스트운동시설39', '01012345678'),
    ('테스트운동시설40', '01012345678'),
    ('테스트운동시설41', '01012345678'),
    ('테스트운동시설42', '01012345678'),
    ('테스트운동시설43', '01012345678'),
    ('테스트운동시설44', '01012345678'),
    ('테스트운동시설45', '01012345678'),
    ('테스트운동시설46', '01012345678'),
    ('테스트운동시설47', '01012345678'),
    ('테스트운동시설48', '01012345678'),
    ('테스트운동시설49', '01012345678'),
    ('테스트운동시설50', '01012345678'),
    ('테스트운동시설51', '01012345678'),
    ('테스트운동시설52', '01012345678'),
    ('테스트운동시설53', '01012345678'),
    ('테스트운동시설54', '01012345678'),
    ('테스트운동시설55', '01012345678'),
    ('테스트운동시설56', '01012345678'),
    ('테스트운동시설57', '01012345678'),
    ('테스트운동시설58', '01012345678'),
    ('테스트운동시설59', '01012345678'),
    ('테스트운동시설60', '01012345678'),
    ('테스트운동시설61', '01012345678'),
    ('테스트운동시설62', '01012345678'),
    ('테스트운동시설63', '01012345678'),
    ('테스트운동시설64', '01012345678'),
    ('테스트운동시설65', '01012345678'),
    ('테스트운동시설66', '01012345678'),
    ('테스트운동시설67', '01012345678'),
    ('테스트운동시설68', '01012345678'),
    ('테스트운동시설69', '01012345678'),
    ('테스트운동시설70', '01012345678'),
    ('테스트운동시설71', '01012345678'),
    ('테스트운동시설72', '01012345678'),
    ('테스트운동시설73', '01012345678'),
    ('테스트운동시설74', '01012345678'),
    ('테스트운동시설75', '01012345678'),
    ('테스트운동시설76', '01012345678'),
    ('테스트운동시설77', '01012345678'),
    ('테스트운동시설78', '01012345678'),
    ('테스트운동시설79', '01012345678'),
    ('테스트운동시설80', '01012345678'),
    ('테스트운동시설81', '01012345678'),
    ('테스트운동시설82', '01012345678'),
    ('테스트운동시설83', '01012345678'),
    ('테스트운동시설84', '01012345678'),
    ('테스트운동시설85', '01012345678'),
    ('테스트운동시설86', '01012345678'),
    ('테스트운동시설87', '01012345678'),
    ('테스트운동시설88', '01012345678'),
    ('테스트운동시설89', '01012345678'),
    ('테스트운동시설90', '01012345678'),
    ('테스트운동시설91', '01012345678'),
    ('테스트운동시설92', '01012345678'),
    ('테스트운동시설93', '01012345678'),
    ('테스트운동시설94', '01012345678'),
    ('테스트운동시설95', '01012345678'),
    ('테스트운동시설96', '01012345678'),
    ('테스트운동시설97', '01012345678'),
    ('테스트운동시설98', '01012345678'),
    ('테스트운동시설99', '01012345678'),
    ('테스트운동시설100', '01012345678'),
    ('테스트헬스장','0212345678'),('테스트요가샵','0211112222'),('테스트필라테스','0321112222'),('테스트골프','0621112222');
--
-- 운동시설의 카테고리
--
INSERT INTO GYM_CATEGORY(GYM_ID, CATEGORY_ID)
VALUES
(1, 1), (1, 2),
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5),
(3, 1), (3, 2),
(4, 1), (4, 2), (4, 3), (4, 4), (4, 5),
(5, 1), (5, 2),
(6, 1), (6, 2), (6, 3), (6, 4), (6, 5),
(7, 1), (7, 2),
(8, 1), (8, 2), (8, 3), (8, 4), (8, 5),
(9, 1), (9, 2),
(10, 1), (10, 2), (10, 3), (10, 4), (10, 5),
(11, 1), (11, 2),
(12, 1), (12, 2), (12, 3), (12, 4), (12, 5),
(13, 1), (13, 2),
(14, 1), (14, 2), (14, 3), (14, 4), (14, 5),
(15, 1), (15, 2),
(16, 1), (16, 2), (16, 3), (16, 4), (16, 5),
(17, 1), (17, 2),
(18, 1), (18, 2), (18, 3), (18, 4), (18, 5),
(19, 1), (19, 2),
(20, 1), (20, 2), (20, 3), (20, 4), (20, 5),
(21, 1), (21, 2),
(22, 1), (22, 2), (22, 3), (22, 4), (22, 5),
(23, 1), (23, 2),
(24, 1), (24, 2), (24, 3), (24, 4), (24, 5),
(25, 1), (25, 2),
(26, 1), (26, 2), (26, 3), (26, 4), (26, 5),
(27, 1), (27, 2),
(28, 1), (28, 2), (28, 3), (28, 4), (28, 5),
(29, 1), (29, 2),
(30, 1), (30, 2), (30, 3), (30, 4), (30, 5),
(31, 1), (31, 2),
(32, 1), (32, 2), (32, 3), (32, 4), (32, 5),
(33, 1), (33, 2),
(34, 1), (34, 2), (34, 3), (34, 4), (34, 5),
(35, 1), (35, 2),
(36, 1), (36, 2), (36, 3), (36, 4), (36, 5),
(37, 1), (37, 2),
(38, 1), (38, 2), (38, 3), (38, 4), (38, 5),
(39, 1), (39, 2),
(40, 1), (40, 2), (40, 3), (40, 4), (40, 5),
(41, 1), (41, 2),
(42, 1), (42, 2), (42, 3), (42, 4), (42, 5),
(43, 1), (43, 2),
(44, 1), (44, 2), (44, 3), (44, 4), (44, 5),
(45, 1), (45, 2),
(46, 1), (46, 2), (46, 3), (46, 4), (46, 5),
(47, 1), (47, 2),
(48, 1), (48, 2), (48, 3), (48, 4), (48, 5),
(49, 1), (49, 2),
(50, 1), (50, 2), (50, 3), (50, 4), (50, 5),
(51, 1), (51, 2),
(52, 1), (52, 2), (52, 3), (52, 4), (52, 5),
(53, 1), (53, 2),
(54, 1), (54, 2), (54, 3), (54, 4), (54, 5),
(55, 1), (55, 2),
(56, 1), (56, 2), (56, 3), (56, 4), (56, 5),
(57, 1), (57, 2),
(58, 1), (58, 2), (58, 3), (58, 4), (58, 5),
(59, 1), (59, 2),
(60, 1), (60, 2), (60, 3), (60, 4), (60, 5),
(61, 1), (61, 2),
(62, 1), (62, 2), (62, 3), (62, 4), (62, 5),
(63, 1), (63, 2),
(64, 1), (64, 2), (64, 3), (64, 4), (64, 5),
(65, 1), (65, 2),
(66, 1), (66, 2), (66, 3), (66, 4), (66, 5),
(67, 1), (67, 2),
(68, 1), (68, 2), (68, 3), (68, 4), (68, 5),
(69, 1), (69, 2),
(70, 1), (70, 2), (70, 3), (70, 4), (70, 5),
(71, 1), (71, 2),
(72, 1), (72, 2), (72, 3), (72, 4), (72, 5),
(73, 1), (73, 2),
(74, 1), (74, 2), (74, 3), (74, 4), (74, 5),
(75, 1), (75, 2),
(76, 1), (76, 2), (76, 3), (76, 4), (76, 5),
(77, 1), (77, 2),
(78, 1), (78, 2), (78, 3), (78, 4), (78, 5),
(79, 1), (79, 2),
(80, 1), (80, 2), (80, 3), (80, 4), (80, 5),
(81, 1), (81, 2),
(82, 1), (82, 2), (82, 3), (82, 4), (82, 5),
(83, 1), (83, 2),
(84, 1), (84, 2), (84, 3), (84, 4), (84, 5),
(85, 1), (85, 2),
(86, 1), (86, 2), (86, 3), (86, 4), (86, 5),
(87, 1), (87, 2),
(88, 1), (88, 2), (88, 3), (88, 4), (88, 5),
(89, 1), (89, 2),
(90, 1), (90, 2), (90, 3), (90, 4), (90, 5),
(91, 1), (91, 2),
(92, 1), (92, 2), (92, 3), (92, 4), (92, 5),
(93, 1), (93, 2),
(94, 1), (94, 2), (94, 3), (94, 4), (94, 5),
(95, 1), (95, 2),
(96, 1), (96, 2), (96, 3), (96, 4), (96, 5),
(97, 1), (97, 2),
(98, 1), (98, 2), (98, 3), (98, 4), (98, 5),
(99, 1), (99, 2),
(100, 1), (100, 2), (100, 3), (100, 4), (100, 5);
