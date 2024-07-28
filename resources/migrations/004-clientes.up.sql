create table clientes (
  id int unsigned not null auto_increment primary key,
  nombre varchar(255),
  paterno varchar(255),
  materno varchar(255),
  celular varchar(255),
  email varchar(255),
  modelos_id int unsigned not null,
  distribuidor_id int unsigned not null,
  notas text
);
