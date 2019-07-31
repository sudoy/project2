
create table accounts(

	account_id int primary key auto_increment not null,
	name varchar(20) not null,
	mail varchar(100) not null,
	password varchar(32) not null,
	authority int default 0 not null,
	version int default 0 not null

);


insert into accounts(account_id, name, mail, password, authority)
values(1, 'Admin', 'admin@mail.tokyosystem.co.jp', md5('0000'), 10);

insert into accounts(account_id, name, mail, password, authority)
values(2, '吉田安崇', 'yoshida.yasutaka@mail.tokyosystem.co.jp', md5('0000'), 1);

insert into accounts(account_id, name, mail, password, authority)
values(3, '鈴木花穂', 'suzuki.kaho@mail.tokyosystem.co.jp', md5('0000'), 1);

insert into accounts(account_id, name, mail, password, authority)
values(4, '山本竜也', 'yamamoto.tatsuya@mail.tokyosystem.co.jp', md5('0000'), 1);

insert into accounts(account_id, name, mail, password, authority)
values(5, '稲田万穂', 'inada.maho@mail.tokyosystem.co.jp', md5('0000'), 1);

insert into accounts(account_id, name, mail, password, authority)
values(6, '小林彩夏', 'kobayashi.ayaka@mail.tokyosystem.co.jp', md5('0000'), 1);

insert into accounts(account_id, name, mail, password, authority)
values(7, '藤ノ木祥真', 'fujinoki.syoma@mail.tokyosystem.co.jp', md5('0000'), 1);

