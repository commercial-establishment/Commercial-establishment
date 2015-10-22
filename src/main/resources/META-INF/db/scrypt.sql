INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'PROVIDER');
INSERT INTO role (id, name) VALUES (3, 'OWNER');
INSERT INTO role (id, name) VALUES (4, 'ACCOUNTANT');
INSERT INTO role (id, name) VALUES (5, 'SELLER');

INSERT INTO admin (id, is_blocked, email, end_work_date, name, password, patronymic, start_work_date, surname, username, role_id)
VALUES (1, false, 'yakovshmidt93@gmail.com', null, 'Yakov', 'yakov', 'Vladimirovich', null, 'Shmidt', 'yakov', 1);
