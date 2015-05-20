DROP TABLE IF EXISTS 'seatmap';


CREATE TABLE 'seatmap'(
'id' INT NOT NULL AUTO_INCREMENT,
'scheduleId' INT NOT NULL ,
'map' VARCHAR(1000) NOT NULL,
'fClass' INT NOT NULL ,
'bClass' INT NOT NULL ,
  'peClass' INT NOT NULL ,
  'eClass' INT NOT NULL ,
PRIMARY KEY('scheduleId')

);


DROP TABLE IF EXISTS 'ticket';

CREATE TABLE 'ticket'(
  'id' INT NOT NULL AUTO_INCREMENT,
  'customerId' INT NOT NULL ,
  'username' VARCHAR(255) NOT NULL ,
  'scheduleId' INT NOT NULL ,
  'fareClass' VARCHAR(15) NOT NULL ,
  'seat' INT NOT NULL ,
  'flightCost' INT NOT NULL ,
  'serviceCost' INT NOT NULL ,
  'total' INT NOT NULL ,
  PRIMARY KEY ('id')
);

DROP TABLE IF EXISTS 'service';

CREATE TABLE 'service'(
  'id' INT NOT NULL AUTO_INCREMENT,
  'ticketId' INT NOT NULL,
  'customerId' INT NOT NULL,
  'item' VARCHAR(45) NOT NULL,
  'cost' INT NOT NULL ,
  PRIMARY KEY ('id')
);