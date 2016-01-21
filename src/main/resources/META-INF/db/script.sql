INSERT INTO role (id, name) VALUES ('00000000-0000-0001-0000-000000000000', 'ADMIN');
INSERT INTO role (id, name) VALUES ('00000000-0000-0002-0000-000000000000', 'PROVIDER');
INSERT INTO role (id, name) VALUES ('00000000-0000-0003-0000-000000000000', 'OWNER');
INSERT INTO role (id, name) VALUES ('00000000-0000-0004-0000-000000000000', 'ACCOUNTANT');
INSERT INTO role (id, name) VALUES ('00000000-0000-0005-0000-000000000000', 'SELLER');

INSERT INTO gender (id, name) VALUES ('00000000-0000-0001-0000-000000000000', 'MALE');
INSERT INTO gender (id, name) VALUES ('00000000-0000-0002-0000-000000000000', 'FEMALE');

INSERT INTO city (id, name) VALUES ('00000000-0000-0001-0000-000000000000', 'АСТАНА');
INSERT INTO city (id, name) VALUES ('00000000-0000-0002-0000-000000000000', 'АЛМАТЫ');
INSERT INTO city (id, name) VALUES ('00000000-0000-0003-0000-000000000000', 'АКТАУ');
INSERT INTO city (id, name) VALUES ('00000000-0000-0004-0000-000000000000', 'АКТОБЕ');
INSERT INTO city (id, name) VALUES ('00000000-0000-0005-0000-000000000000', 'АТЫРАУ');
INSERT INTO city (id, name) VALUES ('00000000-0000-0006-0000-000000000000', 'ЖАНАОЗЕН');
INSERT INTO city (id, name) VALUES ('00000000-0000-0007-0000-000000000000', 'КАРАГАНДА');
INSERT INTO city (id, name) VALUES ('00000000-0000-0008-0000-000000000000', 'КОКШЕТАУ');
INSERT INTO city (id, name) VALUES ('00000000-0000-0009-0000-000000000000', 'КОСТАНАЙ');
INSERT INTO city (id, name) VALUES ('00000000-0000-0010-0000-000000000000', 'КЫЗЫЛОРДА');
INSERT INTO city (id, name) VALUES ('00000000-0000-0011-0000-000000000000', 'ПАВЛОДАР');
INSERT INTO city (id, name) VALUES ('00000000-0000-0012-0000-000000000000', 'ПЕТРОПАВЛСК');
INSERT INTO city (id, name) VALUES ('00000000-0000-0013-0000-000000000000', 'СЕМЕЙ');
INSERT INTO city (id, name) VALUES ('00000000-0000-0014-0000-000000000000', 'ТАЛДЫКОРГАН');
INSERT INTO city (id, name) VALUES ('00000000-0000-0015-0000-000000000000', 'ТАРАЗ');
INSERT INTO city (id, name) VALUES ('00000000-0000-0016-0000-000000000000', 'ТЕМИРТАУ');
INSERT INTO city (id, name) VALUES ('00000000-0000-0017-0000-000000000000', 'ТУРКЕСТАН');
INSERT INTO city (id, name) VALUES ('00000000-0000-0018-0000-000000000000', 'УРАЛЬСК');
INSERT INTO city (id, name) VALUES ('00000000-0000-0019-0000-000000000000', 'УСТЬ-КАМЕНОГОРСК');
INSERT INTO city (id, name) VALUES ('00000000-0000-0020-0000-000000000000', 'ШЫМКЕНТ');
INSERT INTO city (id, name) VALUES ('00000000-0000-0021-0000-000000000000', 'ЭКИБАСТУЗ');

INSERT INTO area (id, name, city_id)
VALUES ('00000000-0000-0001-0000-000000000000', 'Есильский', '00000000-0000-0001-0000-000000000000');
INSERT INTO area (id, name, city_id)
VALUES ('00000000-0000-0002-0000-000000000000', 'Алматинский', '00000000-0000-0001-0000-000000000000');
INSERT INTO area (id, name, city_id)
VALUES ('00000000-0000-0003-0000-000000000000', 'Сарыаркинский', '00000000-0000-0001-0000-000000000000');

INSERT INTO area (id, name, city_id)
VALUES ('00000000-0000-0004-0000-000000000000', 'Юг', '00000000-0000-0002-0000-000000000000');
INSERT INTO area (id, name, city_id)
VALUES ('00000000-0000-0005-0000-000000000000', 'Центральный', '00000000-0000-0002-0000-000000000000');

INSERT INTO category (id, name) VALUES ('00000000-0000-0001-0000-000000000000', 'Напитки');
INSERT INTO category (id, name) VALUES ('00000000-0000-0002-0000-000000000000', 'Алкоголь');
INSERT INTO category (id, name) VALUES ('00000000-0000-0003-0000-000000000000', 'Печенье');

/*TODO change types*/
INSERT INTO type (id, name) VALUES ('00000000-0000-0001-0000-000000000000', 'A');
INSERT INTO type (id, name) VALUES ('00000000-0000-0002-0000-000000000000', 'B');
INSERT INTO type (id, name) VALUES ('00000000-0000-0003-0000-000000000000', 'С');

INSERT INTO admin (id, is_blocked, email, end_work_date, name, password, patronymic, start_work_date, surname, username, role_id, gender_id)
VALUES
  ('00000000-0000-0001-0000-000000000000', FALSE, 'yakovshmidt93@gmail.com', NULL, 'Yakov',
                                           '$2a$10$5/0IiLwNrICc3Dmq/7AWKO08qK13AKH2tWIytGe9a2.WZHIj2WwPa',
                                           'Vladimirovich', '11-11-2011', 'Shmidt', 'yakov11',
                                           '00000000-0000-0001-0000-000000000000',
   '00000000-0000-0001-0000-000000000000');

INSERT INTO unit (id, name, symbol) VALUES ('00000000-0000-0001-0000-000000000000', 'Килограмм', 'кг');
INSERT INTO unit (id, name, symbol) VALUES ('00000000-0000-0002-0000-000000000000', 'Литр', 'л');
INSERT INTO unit (id, name, symbol) VALUES ('00000000-0000-0003-0000-000000000000', 'Штука', 'шт');

-- INSERT INTO product (id, is_blocked, name, category_id, barcode, unit_id) VALUES
--   ('00000000-0000-0001-0000-000000000000', FALSE, 'Coca-Cola', '00000000-0000-0001-0000-000000000000', 123123123123,
--    '00000000-0000-0001-0000-000000000000');
-- INSERT INTO product (id, is_blocked, name, category_id, barcode, unit_id) VALUES
--   ('00000000-0000-0002-0000-000000000000', FALSE, 'NeCoca-Cola', '00000000-0000-0001-0000-000000000000', 321321321321,
--    '00000000-0000-0002-0000-000000000000');

INSERT INTO shop (id, address, is_blocked, name, area_id, type_id, iin) VALUES
  ('00000000-0000-0001-0000-000000000000', 'Туркистан 8/2', FALSE, 'Gal Mart', '00000000-0000-0001-0000-000000000000',
   '00000000-0000-0001-0000-000000000000', 123213123);

INSERT INTO warehouse (id, arrival, import_date, residue, shop_id) VALUES
  ('00000000-0000-0001-0000-000000000000', 100, NULL, 50, '00000000-0000-0001-0000-000000000000');
--
-- INSERT INTO provider (id, address, is_blocked, company_name, contact_person, email, end_work_date, password, start_work_date, username, city_id, role_id)
-- VALUES
--   ('00000000-0000-0001-0000-000000000000', 'ул. Ташенова 5/2', FALSE, 'COMPANY NAME', 'PERSON NAME', 'yakov@gmail.com',
--                                            NULL,
--                                            '$2a$10$5/0IiLwNrICc3Dmq/7AWKO08qK13AKH2tWIytGe9a2.WZHIj2WwPa',
--                                            '11-11-20110',
--                                            'username', '00000000-0000-0001-0000-000000000000',
--    '00000000-0000-0002-0000-000000000000');

-- INSERT INTO product_provider (id, is_blocked, product_id, provider_id) VALUES
--   ('00000000-0000-0001-0000-000000000000', FALSE, '00000000-0000-0001-0000-000000000000',
--    '00000000-0000-0001-0000-000000000000');
-- INSERT INTO product_provider (id, is_blocked, product_id, provider_id) VALUES
--   ('00000000-0000-0002-0000-000000000000', FALSE, '00000000-0000-0002-0000-000000000000',
--    '00000000-0000-0001-0000-000000000000');

INSERT INTO EMPLOYEE (ID, IS_BLOCKED, FIRST_NAME, PASSWORD, SURNAME, USERNAME, ROLE_ID, SHOP_ID) VALUES
  ('00000000-0000-0001-0000-000000000000', FALSE, 'FIRSTNAME',
   '$2a$10$5/0IiLwNrICc3Dmq/7AWKO08qK13AKH2tWIytGe9a2.WZHIj2WwPa', 'surname', 'owner',
   '00000000-0000-0003-0000-000000000000', '00000000-0000-0001-0000-000000000000');

-- INSERT INTO shop (id, is_blocked, full_name, password, username, type_id, warehouse_id) VALUES (1, FALSE, 'Super Магаз', 'super', 'super', 1, 1);