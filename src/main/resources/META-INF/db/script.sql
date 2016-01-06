INSERT INTO role (id, name) VALUES ('00000000-0000-0001-0000-000000000000', 'ADMIN');
INSERT INTO role (id, name) VALUES ('00000000-0000-0002-0000-000000000000', 'PROVIDER');
INSERT INTO role (id, name) VALUES ('00000000-0000-0003-0000-000000000000', 'OWNER');
INSERT INTO role (id, name) VALUES ('00000000-0000-0004-0000-000000000000', 'ACCOUNTANT');
INSERT INTO role (id, name) VALUES ('00000000-0000-0005-0000-000000000000', 'SELLER');

INSERT INTO gender (id, name) VALUES ('00000000-0000-0001-0000-000000000000', 'MALE');
INSERT INTO gender (id, name) VALUES ('00000000-0000-0002-0000-000000000000', 'FEMALE');

INSERT INTO city (id, name) VALUES ('00000000-0000-0001-0000-000000000000', 'АСТАНА');
INSERT INTO city (id, name) VALUES ('00000000-0000-0002-0000-000000000000', 'КАРАГАНДА');

INSERT INTO area (id, name, city_id) VALUES ('00000000-0000-0001-0000-000000000000', 'Есильский', '00000000-0000-0001-0000-000000000000');
INSERT INTO area (id, name, city_id) VALUES ('00000000-0000-0002-0000-000000000000', 'Алматинский', '00000000-0000-0001-0000-000000000000');
INSERT INTO area (id, name, city_id) VALUES ('00000000-0000-0003-0000-000000000000', 'Сарыаркинский', '00000000-0000-0001-0000-000000000000');

INSERT INTO area (id, name, city_id) VALUES ('00000000-0000-0004-0000-000000000000', 'Офигенный', '00000000-0000-0002-0000-000000000000');

INSERT INTO category (id, name) VALUES ('00000000-0000-0001-0000-000000000000', 'Напитки');
INSERT INTO category (id, name) VALUES ('00000000-0000-0002-0000-000000000000', 'НеНапитки');

/*TODO change types*/
INSERT INTO type (id, name) VALUES ('00000000-0000-0001-0000-000000000000', 'A');
INSERT INTO type (id, name) VALUES ('00000000-0000-0002-0000-000000000000', 'B');

INSERT INTO admin (id, is_blocked, email, end_work_date, name, password, patronymic, start_work_date, surname, username, role_id, gender_id)
VALUES
  ('00000000-0000-0001-0000-000000000000', FALSE, 'yakovshmidt93@gmail.com', NULL, 'Yakov', '$2a$10$5/0IiLwNrICc3Dmq/7AWKO08qK13AKH2tWIytGe9a2.WZHIj2WwPa',
   'Vladimirovich', '11-11-2011', 'Shmidt', 'yakov11', '00000000-0000-0001-0000-000000000000', '00000000-0000-0001-0000-000000000000');

INSERT INTO unit (id, name, symbol) VALUES (1, 'Килограмм', 'кг');
INSERT INTO unit (id, name, symbol) VALUES (2, 'Литр', 'л');

INSERT INTO product (id, is_blocked, name, category_id, barcode, unit_id) VALUES (1, FALSE, 'Coca-Cola', 1, 123123123123, 1);
INSERT INTO product (id, is_blocked, name, category_id, barcode, unit_id) VALUES (2, FALSE, 'NeCoca-Cola', 1, 321321321321, 2);

INSERT INTO shop (id, address, is_blocked, name, area_id, type_id) VALUES (1, 'Туркистан 8/2', FALSE, 'Gal Mart', 1, 1);
/*TODO product's history*/

INSERT INTO warehouse (id, arrival, import_date, residue, product_id, shop_id) VALUES (1, 100, NULL, 50, 1, 1);
INSERT INTO warehouse (id, arrival, import_date, residue, product_id, shop_id) VALUES (2, 100, NULL, 50, 2, 1);

INSERT INTO provider (id, address, is_blocked, company_name, contact_person, email, end_work_date, password, start_work_date, username, city_id, role_id)
VALUES (1, 'ул. Ташенова 5/2', FALSE, 'COMPANY NAME', 'PERSON NAME', 'yakov@gmail.com', NULL,
        '$2a$10$5/0IiLwNrICc3Dmq/7AWKO08qK13AKH2tWIytGe9a2.WZHIj2WwPa', '11-11-20110',
        'username', 1, 2);

INSERT INTO product_provider (id, is_blocked, product_id, provider_id) VALUES (1, FALSE, 1, 1);
INSERT INTO product_provider (id, is_blocked, product_id, provider_id) VALUES (2, FALSE, 2, 1);

INSERT INTO EMPLOYEE (ID, IS_BLOCKED, FIRST_NAME, PASSWORD, SURNAME, USERNAME, ROLE_ID, SHOP_ID) VALUES
  (1, FALSE, 'FIRSTNAME', '$2a$10$5/0IiLwNrICc3Dmq/7AWKO08qK13AKH2tWIytGe9a2.WZHIj2WwPa', 'surname', 'owner', 3, 1);

-- INSERT INTO shop (id, is_blocked, full_name, password, username, type_id, warehouse_id) VALUES (1, FALSE, 'Super Магаз', 'super', 'super', 1, 1);