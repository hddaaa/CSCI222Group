DROP TABLE IF EXISTS seatmap;


CREATE TABLE seatmap(
id INT NOT NULL AUTO_INCREMENT,
scheduleId INT NOT NULL ,
map VARCHAR(10000) NOT NULL,
fClass INT NOT NULL ,
bClass INT NOT NULL ,
peClass INT NOT NULL ,
eClass INT NOT NULL ,
PRIMARY KEY(id)

);


DROP TABLE IF EXISTS ticket;

CREATE TABLE ticket(
  id INT NOT NULL AUTO_INCREMENT,
  customerId INT NOT NULL ,
  username VARCHAR(255) NOT NULL ,
  scheduleId INT NOT NULL ,
  fareClass VARCHAR(15) NOT NULL ,
  seat INT NOT NULL ,
  flightCost INT NOT NULL ,
  serviceCost INT NOT NULL ,
  total INT NOT NULL ,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS service;

CREATE TABLE service(
  id INT NOT NULL AUTO_INCREMENT,
  ticketId INT NOT NULL,
  customerId INT NOT NULL,
  item VARCHAR(45) NOT NULL,
  cost INT NOT NULL ,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS user;

CREATE TABLE user(
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL,
  authority VARCHAR(20) NOT NULL,
  pwd VARCHAR(45) NOT NULL,
  availability TINYINT(1) NOT NULL ,
  PRIMARY KEY (id)
);

INSERT INTO customer (title,first_name,last_name,gender,DOB,Phone,email,street_address,state,city,country,credit_card_type,credit_card_num,frequent_flier_points_,passport_holder,is_fly,Travel_Agent) VALUE ('Mr','Siyuan','Hou','Male','1989-07-25','5-(218)776-8651','hhh@ggg.com','213 Northwestern Court',NULL,'Qinghai','China','visa','3544701816091840',0,1,NULL,NULL);
INSERT INTO user (username,pwd,authority,availability) VALUE ('hhh@ggg.com',MD5('hhh'),'Customer',1);
INSERT INTO customer (title,first_name,last_name,gender,DOB,Phone,email,street_address,state,city,country,credit_card_type,credit_card_num,frequent_flier_points_,passport_holder,is_fly,Travel_Agent) VALUE ('Mr','Steven','Jobs','Male','1989-07-25','5-(218)776-8651','ddd@ggg.com','213 Northwestern Court',NULL,'Qinghai','China','visa','3544701816091840',0,1,NULL,NULL);
INSERT INTO user (username,pwd,authority,availability) VALUE ('ddd@ggg.com',MD5('hhh'),'Customer',1);
INSERT INTO user (username,pwd,authority,availability) VALUE ('bbanks9@simplemachines.org',MD5('hhh'),'Agent',1);
INSERT INTO user (username,pwd,authority,availability) VALUE ('st',MD5('hhh'),'Staff',1);
INSERT INTO user (username,pwd,authority,availability) VALUE ('sm',MD5('hhh'),'ServiceManager',1);
INSERT INTO user (username,pwd,authority,availability) VALUE ('fm',MD5('hhh'),'FlightManager',1);
INSERT INTO user (username,pwd,authority,availability) VALUE ('ad',MD5('hhh'),'Admin',1);