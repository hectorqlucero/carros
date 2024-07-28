create table modelos (
  id int unsigned not null auto_increment primary key,
  nombre varchar(255),
  precio varchar(255),
  distribuidor_id int unsigned,
  FOREIGN KEY (distribuidor_id) REFERENCES distribuidor(id)
); 
