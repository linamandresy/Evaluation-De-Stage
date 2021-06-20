create table users(
	idusers serial primary key , 
	fname varchar(50) not  null,
	lname varchar(50) ,
	logins varchar(50) not null,
	passwords varchar(40) not null
);

create table userstoken(
	userstoken varchar(40) primary key,
	idusers int not null,
	expiration timestamp not null,
	foreign key (idusers) references users(idusers)
);

create table admins(
	idadmins serial primary key,
	logins varchar(50) not null,
	passwords varchar(50) not null
);

create table adminstoken(
	adminstoken varchar(40) primary key,
	idadmins int not null,
	expiration timestamp not null,
	foreign key (idadmins) references admins(idadmins)
);

