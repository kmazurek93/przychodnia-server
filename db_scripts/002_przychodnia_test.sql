DROP USER IF EXISTS przychodnia_test;
;
DROP DATABASE IF EXISTS `przychodnia_test`;
;
CREATE DATABASE IF NOT EXISTS `przychodnia_test` DEFAULT CHARACTER SET = 'utf8' DEFAULT COLLATE = 'utf8_polish_ci';
;
CREATE USER IF NOT EXISTS 'przychodnia_test'@'localhost' IDENTIFIED BY 'przychodnia_test' PASSWORD EXPIRE NEVER;
;
USE przychodnia_test;
;
GRANT ALL PRIVILEGES ON `przychodnia_test`.* TO 'przychodnia_test'@'%' WITH GRANT OPTION;
;

CREATE TABLE `action_log`(
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
user_id BIGINT NOT NULL,
entity_id NVARCHAR(30) NOT NULL,
entity_type INT NOT NULL,
action_type INT NOT NULL,
action_date DATETIME(6) NOT NULL
);
;
CREATE TABLE `address`(
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
country NVARCHAR(50) NOT NULL,
province NVARCHAR(50) NOT NULL,
city NVARCHAR(75) NOT NULL,
street NVARCHAR(100) NOT NULL,
house NVARCHAR(8) NOT NULL,
apartment NVARCHAR(10) NULL DEFAULT NULL,
postcode NVARCHAR(15) NOT NULL
);
;
CREATE TABLE `id_type`(
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name NVARCHAR(100) NOT NULL,
UNIQUE(name)
);
;
CREATE TABLE `person`(
pesel NVARCHAR(11) NOT NULL PRIMARY KEY,
first_name NVARCHAR(50) NOT NULL,
middle_name NVARCHAR(100) NULL DEFAULT NULL,
last_name NVARCHAR(75) NOT NULL,
birth_date DATE NOT NULL,
birth_place NVARCHAR(100) NOT NULL,
id_number NVARCHAR(40) NOT NULL,
id_type INT,
address_id BIGINT,
mailing_address_id BIGINT NULL DEFAULT NULL,
sex_id INT NOT NULL
);
;
CREATE TABLE `role`(
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name NVARCHAR(50) NOT NULL,
UNIQUE(name)
)
;
CREATE TABLE `user`(
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
login NVARCHAR(50) UNIQUE NOT NULL,
password NVARCHAR(1000) NOT NULL,
email_address NVARCHAR(100) NOT NULL,
active BIT(1) NOT NULL DEFAULT b'0',
pesel NVARCHAR(11) NOT NULL,
UNIQUE(email_address)
)
;
CREATE TABLE `sex`(
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name NVARCHAR(50) NOT NULL,
UNIQUE(name)
)
;
CREATE TABLE `user_roles`(
user_id BIGINT,
role_id INT
)
;

CREATE TABLE `user_connection`(
parent_id BIGINT,
child_id BIGINT
)
;

ALTER TABLE `user_connection`
ADD CONSTRAINT fk_cn_parent_id
FOREIGN KEY (parent_id)
REFERENCES user(id);
;
ALTER TABLE `user_connection`
ADD CONSTRAINT fk_cn_child_id
FOREIGN KEY (child_id)
REFERENCES user(id);
;
ALTER TABLE `user_roles`
ADD CONSTRAINT fk_ur_user_id
FOREIGN KEY (user_id)
REFERENCES user(id);
;
ALTER TABLE `user_roles`
ADD CONSTRAINT fk_ur_role_id
FOREIGN KEY (role_id)
REFERENCES role(id);
;
ALTER TABLE `person`
ADD CONSTRAINT fk_p_adr_id
FOREIGN KEY (address_id)
REFERENCES address(id);
;
ALTER TABLE `person`
ADD CONSTRAINT fk_p_madr_id
FOREIGN KEY (mailing_address_id)
REFERENCES address(id);
;
ALTER TABLE `person`
ADD CONSTRAINT fk_p_sex_id
FOREIGN KEY (sex_id)
REFERENCES sex(id);
;
ALTER TABLE `person`
ADD CONSTRAINT fk_p_idt_id
FOREIGN KEY (id_type)
REFERENCES id_type(id);
;
ALTER TABLE `user`
ADD CONSTRAINT fk_u_pesel
FOREIGN KEY (pesel)
REFERENCES person(pesel);

USE przychodnia_test;

INSERT INTO address(id, country, province, city, street, house, apartment, postcode)
VALUES
(1, 'Polska', 'Wielkopolskie', 'Poznań', 'Umultowska', '87', NULL, '61-600')
;
INSERT INTO sex(id, name) VALUES
(1, 'M'), (2, 'K');
INSERT INTO id_type(id, name) VALUES
(1, 'Dowód osobisty RP'),(2, 'Paszport');

INSERT INTO person(pesel, first_name, last_name, birth_place, birth_date, id_number, id_type, address_id, sex_id)
VALUES
('XXXXXXXXXXX', 'Super', 'Admin', 'Poznań', '1970-01-01', 'AAA000000', 1, 1, 1);

INSERT INTO role(id, name) VALUES
(1, 'ROLE_ADMIN'), (2, 'ROLE_USER'), (3, 'ROLE_DOCTOR'), (4, 'ROLE_PATIENT'), (5, 'ROLE_STAFF');

INSERT INTO user(id, login, password, email_address, active, pesel) VALUES
(1, 'admin', 'JRqnd8lI1tMhnt3it8gY4w==', 'admin@przychodnia.com', b'1', 'XXXXXXXXXXX');

INSERT INTO user_roles(user_id, role_id) VALUES
(1, 1), (1, 2), (1, 5);
