--全部not null

create table accounts(

	account_id int primary key auto_increment not null,
	name varchar(20) not null,
	mail varchar(100) not null,
	password varchar(32) not null,
	authority int default 0 not null

);

insert into accounts(name, mail, password, authority)
values('テスト', 'test@test.com', md5('test'), 11);

insert into accounts(name, mail, password, authority)
values('テスト2', 'mail@mail.com', md5('test'), 10);

insert into accounts(name, mail, password, authority)
values('test3', 'test3@test3.com', md5('test'), 0);