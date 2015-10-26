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
VALUES (1, FALSE, 'yakovshmidt93@gmail.com', NULL, 'Yakov', 'yakov', 'Vladimirovich', NULL, 'Shmidt', 'yakov', 1, 1);

INSERT INTO model (id, name, image_id) VALUES (1, 'MODEL OF PRODUCT', NULL);

INSERT INTO product (id, model_id) VALUES (1, 1);

/*TODO product's history*/
INSERT INTO warehouse (id, arrival, import_date, residue, produt_id) VALUES (1, 100, null, 50, 1);

INSERT INTO shop (id, is_blocked, full_name, password, username, type_id, warehouse_id) VALUES (1, FALSE, 'Super Магаз', 'super', 'super', 1, 1);