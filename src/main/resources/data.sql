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
INSERT INTO GYM(name,tel_no)
    VALUES('테스트헬스장','0212345678'),('테스트요가샵','0211112222'),('테스트필라테스','0321112222'),('테스트골프','0621112222');
