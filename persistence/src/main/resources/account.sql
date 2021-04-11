create table if not exists account (
	account serial primary key,
	username varchar(74) unique not null,
	email text not null,
	salt int not null,
	password text not null
);

select * from account limit 5;
