INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'PROVIDER');
INSERT INTO role (id, name) VALUES (3, 'OWNER');
INSERT INTO role (id, name) VALUES (4, 'ACCOUNTANT');
INSERT INTO role (id, name) VALUES (5, 'SELLER');

INSERT INTO gender (id, name) VALUES (1, 'MALE');
INSERT INTO gender (id, name) VALUES (2, 'FEMALE');

/*TODO change types*/
INSERT INTO type (id, name) VALUES (1, 'A');
INSERT INTO type (id, name) VALUES (2, 'B');

INSERT INTO admin (id, is_blocked, email, end_work_date, name, password, patronymic, start_work_date, surname, username, role_id, gender_id)
VALUES (1, FALSE, 'yakovshmidt93@gmail.com', NULL, 'Yakov', '$2a$10$5/0IiLwNrICc3Dmq/7AWKO08qK13AKH2tWIytGe9a2.WZHIj2WwPa', 'Vladimirovich', '11-11-2011', 'Shmidt', 'yakov11', 1, 1);

INSERT INTO model (id, name, image_id) VALUES (1, 'MODEL OF PRODUCT', NULL);

INSERT INTO product (id, model_id) VALUES (1, 1);

/*TODO product's history*/
INSERT INTO warehouse (id, arrival, import_date, residue, produt_id) VALUES (1, 100, null, 50, 1);


INSERT INTO provider (id, is_blocked, email, end_work_date, name, password, patronymic, phone, start_work_date,
                      surname, username, role_id) VALUES (2, FALSE, 'yakov@gmail.com', null, 'yakov22',
                                                          '$2a$10$5/0IiLwNrICc3Dmq/7AWKO08qK13AKH2tWIytGe9a2.WZHIj2WwPa', 'adasd', null, '11-11-2011', 'asdasd', 'asdasdas', 2);
