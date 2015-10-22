INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'PROVIDER');
INSERT INTO role (id, name) VALUES (3, 'OWNER');
INSERT INTO role (id, name) VALUES (4, 'ACCOUNTANT');
INSERT INTO role (id, name) VALUES (5, 'SELLER');

INSERT INTO gender (id, name) VALUES (1, 'MALE');
INSERT INTO gender (id, name) VALUES (2, 'FEMALE');

INSERT INTO admin (id, is_blocked, email, end_work_date, name, password, patronymic, start_work_date, surname, username, role_id, gender_id)
VALUES (1, FALSE, 'yakovshmidt93@gmail.com', NULL, 'Yakov', 'yakov', 'Vladimirovich', NULL, 'Shmidt', 'yakov', 1, 1);
