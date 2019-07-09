create table sales(

	sale_id int primary key auto_increment not null,
	sale_date date not null,
	account_id int not null,
	category_id int not null,
	trade_name varchar(100) not null,
	unit_price int not null,
	sale_number int not null,
	note text default null,
	foreign key (account_id)
	references accounts(account_id),
	foreign key (category_id)
	references categories(category_id)

);

insert into sales(sale_date, account_id, category_id, trade_name,
unit_price, sale_number, note)
values('2019-07-09', 1, 1, '‚©‚ç‚ ‚°•Ù“–', 450, 1, '¡“ú‚©‚ç‚ÌV¤•i');