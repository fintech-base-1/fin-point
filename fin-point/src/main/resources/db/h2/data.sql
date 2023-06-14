-- CREATE TABLE MEMBER (
--     CREATED_AT TIMESTAMP,
--     MODIFIED_AT TIMESTAMP,
--     CODE VARCHAR(255),
--     EMAIL VARCHAR(255),
--     FIN_POINT BIGINT,
--     FINTECH_USE_NUM VARCHAR(255),
--     OAUTH_CLIENT VARCHAR(255),
--     PASSWORD VARCHAR(255),
--     SALT VARCHAR(255),
--     TARGET_SPEND BIGINT,
--     TOKEN_ID BIGINT
-- );

INSERT INTO MEMBER(CREATED_AT, MODIFIED_AT, CODE, EMAIL, FIN_POINT, FINTECH_USE_NUM, OAUTH_CLIENT, PASSWORD, SALT, TARGET_SPEND,FILE_ENTITY_ID,TOKEN_ID) VALUES
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'DAUSTIN@naver.com',100000000,null,'NOTING',null,null,100000,2,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'VPATABAL@naver.com',100000000,null,'NOTING',null,null,100000,3,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'DLORENTZ@naver.com',100000000,null,'NOTING',null,null,100000,4,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'NGREENBE@naver.com',100000000,null,'NOTING',null,null,100000,5,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'DFAVIET@naver.com',100000000,null,'NOTING',null,null,100000,6,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'JCHEN@naver.com',100000000,null,'NOTING',null,null,100000,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'ISCIARRA@naver.com',100000000,null,'NOTING',null,null,100000,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'JMURMAN@naver.com',100000000,null,'NOTING',null,null,100000,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'LPOPP@naver.com',100000000,null,'NOTING',null,null,100000,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'DRAPHEAL@naver.com',100000000,null,'NOTING',null,null,100000,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'AKHOO@naver.com',100000000,null,'NOTING',null,null,100000,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'SBAIDA@naver.com',100000000,null,'NOTING',null,null,100000,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'STOBIAS@naver.com',100000000,null,'NOTING',null,null,100000,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'GHIMURO@naver.com',100000000,null,'NOTING',null,null,100000,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'KCOLMENA@naver.com',100000000,null,'NOTING',null,null,100000,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'MWEISS@naver.com',100000000,null,'NOTING',null,null,100000,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'AFRIPP@naver.com',100000000,null,'NOTING',null,null,100000,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'PKAUFLIN@naver.com',100000000,null,'NOTING',null,null,100000,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'SVOLLMAN@naver.com',100000000,null,'NOTING',null,null,100000,null),
('2023-06-13 21:02:49.00','2023-06-13 21:02:49.00',null,'KMOURGOS@naver.com',100000000,null,'NOTING',null,null,100000,null);





INSERT INTO FILE (ORIGIN_NAME, SAVED_NAME, SAVED_PATH)
VALUES
    ('example1.png', '97de8c1a-8d6f-4e87-b650-d3f9b6ae9e6d.png', 'c:/images/97de8c1a-8d6f-4e87-b650-d3f9b6ae9e6d.png'),
    ('example2.png', '08a97c1e-6d93-4dfe-8651-91e2cb0b5f91.png', 'c:/images/08a97c1e-6d93-4dfe-8651-91e2cb0b5f91.png'),
    ('example3.png', '4b3d7ef9-3200-4800-afda-7245647c67e8.png', 'c:/images/4b3d7ef9-3200-4800-afda-7245647c67e8.png'),
    ('example4.png', 'c671f478-4db6-4eeb-89d0-166d8e8949c3.png', 'c:/images/c671f478-4db6-4eeb-89d0-166d8e8949c3.png'),
    ('example5.png', 'f2dd3c02-034e-41ea-b37b-73e82668ce3e.png', 'c:/images/f2dd3c02-034e-41ea-b37b-73e82668ce3e.png'),
    ('example6.png', 'adf30b84-8d4c-4f70-939d-759f3099d641.png', 'c:/images/adf30b84-8d4c-4f70-939d-759f3099d641.png'),
    ('example7.png', '51a8b994-6f78-45da-8e97-dc40b156a3b9.png', 'c:/images/51a8b994-6f78-45da-8e97-dc40b156a3b9.png'),
    ('example8.png', '2f438dfe-16f4-4df5-a618-bbaa0a795a48.png', 'c:/images/2f438dfe-16f4-4df5-a618-bbaa0a795a48.png'),
    ('example9.png', '9df20326-9297-4862-84d1-b2ebfdf828b6.png', 'c:/images/9df20326-9297-4862-84d1-b2ebfdf828b6.png');



INSERT INTO MEMBER (CREATED_AT, MODIFIED_AT, CODE, EMAIL, FIN_POINT, FINTECH_USE_NUM, OAUTH_CLIENT, PASSWORD, SALT, TARGET_SPEND, FILE_ENTITY_ID, TOKEN_ID)
VALUES
    ('2023-06-13 23:44:56.276999', '2023-06-13 23:45:09.015258', NULL, 'example2@example.com', (FLOOR(RAND() * 1000000) + 1), NULL, 'NAVER', NULL, NULL, (FLOOR(RAND() * 6) + 5) * 10000, 2, NULL),
    ('2023-06-13 23:44:56.276999', '2023-06-13 23:45:09.015258', NULL, 'example3@example.com', (FLOOR(RAND() * 1000000) + 1), NULL, 'KAKAO', NULL, NULL, (FLOOR(RAND() * 6) + 5) * 10000, 3, NULL),
    ('2023-06-13 23:44:56.276999', '2023-06-13 23:45:09.015258', NULL, 'example4@example.com', (FLOOR(RAND() * 1000000) + 1), NULL, 'GOOGLE', NULL, NULL, (FLOOR(RAND() * 6) + 5) * 10000, 4, NULL),
    ('2023-06-13 23:44:56.276999', '2023-06-13 23:45:09.015258', NULL, 'example5@example.com', (FLOOR(RAND() * 1000000) + 1), NULL, 'KAKAO', NULL, NULL, (FLOOR(RAND() * 6) + 5) * 10000, 5, NULL),
    ('2023-06-13 23:44:56.276999', '2023-06-13 23:45:09.015258', NULL, 'example6@example.com', (FLOOR(RAND() * 1000000) + 1), NULL, 'GOOGLE', NULL, NULL, (FLOOR(RAND() * 6) + 5) * 10000, 6, NULL),
    ('2023-06-13 23:44:56.276999', '2023-06-13 23:45:09.015258', NULL, 'example7@example.com', (FLOOR(RAND() * 1000000) + 1), NULL, 'NAVER', NULL, NULL, (FLOOR(RAND() * 6) + 5) * 10000, 7, NULL),
    ('2023-06-13 23:44:56.276999', '2023-06-13 23:45:09.015258', NULL, 'example8@example.com', (FLOOR(RAND() * 1000000) + 1), NULL, 'KAKAO', NULL, NULL, (FLOOR(RAND() * 6) + 5) * 10000, 8, NULL),
    ('2023-06-13 23:44:56.276999', '2023-06-13 23:45:09.015258', NULL, 'example9@example.com', (FLOOR(RAND() * 1000000) + 1), NULL, 'GOOGLE', NULL, NULL, (FLOOR(RAND() * 6) + 5) * 10000, 9, NULL),
    ('2023-06-13 23:44:56.276999', '2023-06-13 23:45:09.015258', NULL, 'example10@example.com', (FLOOR(RAND() * 1000000) + 1), NULL, 'NAVER', NULL, NULL, (FLOOR(RAND() * 6) + 5) * 10000, 10, NULL);
