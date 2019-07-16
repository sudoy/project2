create table categories(

	category_id int primary key auto_increment not null,
	category_name varchar(50) not null,
	active_flg int not null default 1
);


insert into categories(category_id, category_name, active_flg)
values(1, '水産・畜産・農産加工品', 1);
insert into categories(category_id, category_name, active_flg)
values(2, '生鮮・チルド・冷凍食品', 1);
insert into categories(category_id, category_name, active_flg)
values(3, '乳油製品・調味料・調味食品', 1);
insert into categories(category_id, category_name, active_flg)
values(4, '麺類', 1);
insert into categories(category_id, category_name, active_flg)
values(5, 'スープ類', 1);
insert into categories(category_id, category_name, active_flg)
values(6, '菓子類', 1);
insert into categories(category_id, category_name, active_flg)
values(7, '嗜好飲料', 1);
insert into categories(category_id, category_name, active_flg)
values(8, '飲料', 1);
insert into categories(category_id, category_name, active_flg)
values(9, '酒類', 1);
insert into categories(category_id, category_name, active_flg)
values(10, '氷・アイスクリーム類', 1);

insert into categories(category_id, category_name, active_flg)
values(11, 'デザート類', 1);
insert into categories(category_id, category_name, active_flg)
values(12, '健康サポート', 1);
insert into categories(category_id, category_name, active_flg)
values(13, '化粧品', 1);
insert into categories(category_id, category_name, active_flg)
values(14, 'トイレタリー', 1);
insert into categories(category_id, category_name, active_flg)
values(15, '文具・仏具・雑貨', 1);
insert into categories(category_id, category_name, active_flg)
values(16, 'たばこ', 1);
insert into categories(category_id, category_name, active_flg)
values(17, '水産', 0);
insert into categories(category_id, category_name, active_flg)
values(18, '畜産', 0);
insert into categories(category_id, category_name, active_flg)
values(19, '農産加工品', 0);
insert into categories(category_id, category_name, active_flg)
values(20, '生鮮', 0);
insert into categories(category_id, category_name, active_flg)
values(21, 'チルド', 0);
insert into categories(category_id, category_name, active_flg)
values(22, '冷凍食品', 0);