create database bdescuela;
use bdescuela;

create table Alumnos(
id int not null auto_increment primary key,
nombres nvarchar (50),
apellidos nvarchar (50)
);

insert into Alumnos (nombres,apellidos) values('Walter','Muñoz');

select * from Alumnos;



