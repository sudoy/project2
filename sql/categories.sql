create table categories(

	category_id int primary key auto_increment not null,
	category_name varchar(50) not null,
	active_flg int not null default 1
);

insert into categories(category_id, category_name, active_flg)
values(1, '�H���i', 1);