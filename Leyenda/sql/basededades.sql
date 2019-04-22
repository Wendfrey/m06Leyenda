drop database if exists llegenda;
create database llegenda;
use llegenda;

create table Caballero(
	nombreCaballero varchar(25) primary key,
    vidaCaballero int,
    defensaCaballero int,
    nombrePrincesa varchar(25)
);

create table PrincesaDragon(
    nombrePrincesa varchar(25) primary key,
    colorMagia varchar(10),
    numeroConjuros int,
	nombreDragon varchar(25),
    ataqueDragon int,
    vidaDragon int,
    defensaDragon int,
    nombreRosa varchar(25)
);

create table Espada(
	nombreEspada varchar(25) primary key,
    ataqueEspada int
);

create table RosaConjuros(
	nombreRosa varchar(25) primary key,
    cantidadConjuros int
);

create table Tiene(
	nombreCaballero varchar(25),
    nombreEspada varchar(25),
    porcentageAtaque int,
    primary key(nombreCaballero, nombreEspada)
);

alter table Caballero add constraint fk_cab_prin foreign key(nombrePrincesa) references PrincesaDragon(nombrePrincesa);
alter table PrincesaDragon add constraint fk_prin_rosa foreign key(nombreRosa) references RosaConjuros(nombreRosa);
alter table Tiene add constraint fk_tien_cab foreign key(nombreCaballero) references Caballero(nombreCaballero);
alter table Tiene add constraint fk_tien_esp foreign key(nombreEspada) references Espada(nombreEspada);

insert into RosaConjuros values("EspinaAfilada", 10);
insert into RosaConjuros values("Gota de sangre", 200);
insert into RosaConjuros values("Seta", 2);

insert into PrincesaDragon values("Sonja", "Roja", 13, "FuegoInfernal", 30, 400, 60, "EspinaAfilada");
insert into PrincesaDragon values("Morgana", "Negra", 50, "Avalon", 80, 2000, 200, null);

insert into Espada values("Poesía", 100);
insert into Espada values("Tajo", 300);
insert into Espada values("Excalibur", 1000);

insert into Caballero values("SantJordi", 40, 60, "Sonja");
insert into Caballero values("Roland", 30, 50, "Sonja");
insert into Caballero values("Arturo", 80, 95, "Morgana"); 

insert into Tiene values("SantJordi", "Poesía", 75);
insert into Tiene values("Roland", "Poesía", 50);
insert into Tiene values("Roland", "Tajo", 20);
insert into Tiene values("Arturo", "Excalibur", 100);

drop user if exists LlegendaAdmin;
create user LlegendaAdmin identified by 'LlegendaAdmin';
grant ALL privileges on llegenda.* to LlegendaAdmin;
flush privileges;

select * from Caballero;
select * from Espada;
select * from PrincesaDragon;
select * from RosaConjuros;
select * from Tiene;