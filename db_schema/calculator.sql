DROP TABLE IF EXISTS user;
CREATE TABLE user (
	id INT NOT NULL AUTO_INCREMENT,
	login VARCHAR(128) NOT NULL,
	password VARCHAR(128) NOT NULL,
	detailsId INT NOT NULL,
	roleId INT NOT NULL,
	PRIMARY KEY (id)
)ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS userDetails;
CREATE TABLE userDetails (
	detailsId INT NOT NULL AUTO_INCREMENT,
	firstName VARCHAR(128) NOT NULL,
	lastName VARCHAR(128) NOT NULL,
	age INT NOT NULL,
	PRIMARY KEY (detailsId)
)ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS userRole;
CREATE TABLE userRole (
	id INT NOT NULL AUTO_INCREMENT,
	role VARCHAR(20) NOT NULL,
	PRIMARY KEY (id)
)ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS leaseTypeAgeMargin;
CREATE TABLE leaseTypeAgeMargin (
	id INT NOT NULL AUTO_INCREMENT,
	objectTypeId INT NOT NULL,
	objectAge INT NOT NULL,
	margin FLOAT NOT NULL,
	PRIMARY KEY (id)
)ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS leaseObjectType;
CREATE TABLE leaseObjectType (
	id INT NOT NULL AUTO_INCREMENT,
	objectType VARCHAR(20) NOT NULL,
	PRIMARY KEY (id)
)ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS currency;
CREATE TABLE currency (
	id INT NOT NULL AUTO_INCREMENT,
	currencyName VARCHAR(3) NOT NULL,
	PRIMARY KEY (id)
)ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS leaseCurrencyRate;
CREATE TABLE leaseCurrencyRate (
	id INT NOT NULL AUTO_INCREMENT,
	currencyId INT NOT NULL,
	currencyRate float NOT NULL,
	PRIMARY KEY (id)
)ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS leaseTypeInsurance;
CREATE TABLE leaseTypeInsurance (
	id INT NOT NULL AUTO_INCREMENT,
	objectTypeId INT NOT NULL,
	insurance FLOAT NOT NULL,
	PRIMARY KEY (id)
)ENGINE=InnoDB CHARSET=utf8;


LOCK TABLES user WRITE, userDetails WRITE, userRole WRITE, leaseTypeAgeMargin WRITE, leaseObjectType WRITE, leaseCurrencyRate WRITE, currency WRITE, leaseTypeInsurance WRITE;

INSERT INTO user VALUES 
	('1', '4denver@mail.ru', 'PDhidatxaFssF7PWkm8y+f6kmMC9JnO7xSPyfXVWOUA=$8O0ZuJx3yK6gNLFeekGwRRW33kqSmhvkIvNZQmEUsQI=', '1', '2'),
	('2', '4sol@tut.by', 'PDhidatxaFssF7PWkm8y+f6kmMC9JnO7xSPyfXVWOUA=$8O0ZuJx3yK6gNLFeekGwRRW33kqSmhvkIvNZQmEUsQI=', '2', '3');

INSERT INTO userDetails VALUES 
	('1', 'Ivanov', 'Ivan', '30'),
	('2', 'Степанов', 'Джордж', '33');

INSERT INTO userRole VALUES 
	('1', 'ANONYMOUS'),
	('2', 'USER'),
	('3', 'ADMIN');

INSERT INTO leaseObjectType VALUES 
	('1', 'CAR'),
	('2', 'LORRY'),
	('3', 'TRUCK'),
	('4', 'BUILDING_MACHINERY'),
	('5', 'FARMING_MACHINERY'),
	('6', 'EQUIPMENT'),
	('7', 'REALESTATE');

INSERT INTO currency VALUES 
	('1', 'BYN'),
	('2', 'USD'),
	('3', 'EUR'),
	('4', 'RUB');

INSERT INTO leaseCurrencyRate VALUES 
	('1', '1', '0.18'),
	('2', '2', '0.09'),
	('3', '3', '0.09'),
	('4', '4', '0.16');

INSERT INTO leaseTypeAgeMargin VALUES 
	('1', '1', '0', '0.02'),
	('2', '1', '1', '0.022'),
	('3', '1', '2', '0.023'),
	('4', '1', '3', '0.024'),
	('5', '2', '0', '0.02'),
	('6', '2', '1', '0.022'),
	('7', '2', '2', '0.023'),
	('8', '2', '3', '0.024'),
	('9', '3', '0', '0.02'),
	('10', '3', '1', '0.021'),
	('11', '3', '2', '0.022'),
	('12', '3', '3', '0.023'),
	('13', '4', '0', '0.04'),
	('14', '4', '1', '0.043'),
	('15', '4', '2', '0.044'),
	('16', '4', '3', '0.046'),
	('17', '5', '0', '0.04'),
	('18', '5', '1', '0.043'),
	('19', '5', '2', '0.044'),
	('20', '5', '3', '0.046'),
	('21', '6', '0', '0.04'),
	('22', '6', '1', '0.045'),
	('23', '6', '2', '0.046'),
	('24', '6', '3', '0.047'),
	('25', '7', '0', '0.02'),
	('26', '7', '1', '0.02'),
	('27', '7', '2', '0.02'),
	('28', '7', '3', '0.02');
	
INSERT INTO leaseTypeInsurance VALUES 
	('1', '1', '0.03'),
	('2', '2', '0.025'),
	('3', '3', '0.02'),
	('4', '4', '0.01'),
	('5', '4', '0.01'),
	('6', '4', '0.005'),
	('7', '4', '0.004');

UNLOCK TABLES;