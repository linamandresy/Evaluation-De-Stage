create table villes(
	idvilles serial primary key,
	nomvilles varchar(50) not null
);

create table routes(
	idroutes serial primary key,
	noRn int not null check(noRn>0),
	idVilleDepart int not null,
	idVilleArrive int not null,
	distance decimal(10,2) not null check(distance>0),
	foreign key (idVilleDepart) references villes(idvilles),
	foreign key  (idVilleArrive) references villes(idvilles),
	constraint ck_2town check(idVilleArrive!=idVilleDepart)
);

create table etats(
	idetats serial primary key,
	nometat varchar(50) not null,
	budget decimal(10,2) not null check(budget>=0),
	delai decimal(10,2) not null check(delai>=0),
	coef decimal(10,2) not null check(coef>=0 and coef<=100),
	unitesdistances decimal(10,2) not null check(unitesdistances>0)
);

create table portionsroutes(
	idportionsroutes serial primary key,
	idroutes int not null,
	distance decimal(10,2) not null check(distance>0),
	idetats int not null,
	foreign key (idetats) references etats(idetats)
);
