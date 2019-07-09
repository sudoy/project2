--全部not null

create table accounts(

	account_id int primary key auto_increment not null,
	name varchar(20) not null,
	mail varchar(100) not null,
	password varchar(32) not null,
	authority int default 0 not null

);

insert into accounts(name, mail, password, authority)
values('テスト', 'test@test', 'test', 11);