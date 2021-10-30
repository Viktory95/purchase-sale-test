create schema if not exists purchase_sale;

CREATE TABLE IF NOT EXISTS purchase_sale.product (
   product_id SERIAL PRIMARY KEY,
   product_name VARCHAR ( 255 ) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS purchase_sale.town (
   town_id SERIAL PRIMARY KEY,
   town_name VARCHAR ( 255 ) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS purchase_sale.provider (
   provider_id SERIAL PRIMARY KEY,
   provider_name VARCHAR ( 255 ) UNIQUE NOT null,
   town_id INT not null,
   foreign key (town_id)
   	references purchase_sale.town (town_id)
);

CREATE TABLE IF NOT EXISTS purchase_sale.consumer (
   consumer_id SERIAL PRIMARY KEY,
   consumer_name VARCHAR ( 255 ) UNIQUE NOT null,
   town_id INT not null,
   foreign key (town_id)
   	references purchase_sale.town (town_id)
);

CREATE TABLE IF NOT EXISTS purchase_sale.selling (
   selling_id SERIAL PRIMARY KEY,
   product_id INT not null,
   provider_id INT not null,
   foreign key (product_id)
   	references purchase_sale.product (product_id),
   foreign key (provider_id)
   	references purchase_sale.provider (provider_id)
);

CREATE TABLE IF NOT EXISTS purchase_sale.purchase (
   purchase_id SERIAL PRIMARY KEY,
   product_id INT not null,
   consumer_id INT not null,
   foreign key (product_id)
   	references purchase_sale.product (product_id),
   foreign key (consumer_id)
   	references purchase_sale.consumer (consumer_id)
);


insert into purchase_sale.town (town_name) values('Санкт-Петербург');
insert into purchase_sale.town (town_name) values('Москва');
insert into purchase_sale.town (town_name) values('Воронеж');
insert into purchase_sale.town (town_name) values('Липецк');
insert into purchase_sale.town (town_name) values('Белгород');


insert into purchase_sale.product (product_name) values('Молоко');
insert into purchase_sale.product (product_name) values('Мясо');
insert into purchase_sale.product (product_name) values('Масло');
insert into purchase_sale.product (product_name) values('Сахар');
insert into purchase_sale.product (product_name) values('Соль');


insert into purchase_sale.provider (provider_name, town_id) values('ОАО Заря', 2);
insert into purchase_sale.provider (provider_name, town_id) values('ИП Путь Ильича', 2);
insert into purchase_sale.provider (provider_name, town_id) values('ИП Иванов', 3);
insert into purchase_sale.provider (provider_name, town_id) values('ИП Петров', 3);
insert into purchase_sale.provider (provider_name, town_id) values('Колхоз N 4', 3);
insert into purchase_sale.provider (provider_name, town_id) values('ОАО Ст', 4);
insert into purchase_sale.provider (provider_name, town_id) values('ОАО Закат', 5);


insert into purchase_sale.consumer (consumer_name, town_id) values('Магазин 5', 2);
insert into purchase_sale.consumer (consumer_name, town_id) values('Универсам 6', 2);
insert into purchase_sale.consumer (consumer_name, town_id) values('Супермаркет Зов', 2);
insert into purchase_sale.consumer (consumer_name, town_id) values('Мир Вкуса', 3);
insert into purchase_sale.consumer (consumer_name, town_id) values('Утюжок', 3);
insert into purchase_sale.consumer (consumer_name, town_id) values('Ларек N 2', 3);
insert into purchase_sale.consumer (consumer_name, town_id) values('Магазин 7', 4);
insert into purchase_sale.consumer (consumer_name, town_id) values('Магазин 4', 5);


insert into purchase_sale.selling (product_id, provider_id) values(1, 1);
insert into purchase_sale.selling (product_id, provider_id) values(1, 2);
insert into purchase_sale.selling (product_id, provider_id) values(2, 1);
insert into purchase_sale.selling (product_id, provider_id) values(2, 3);
insert into purchase_sale.selling (product_id, provider_id) values(2, 4);
insert into purchase_sale.selling (product_id, provider_id) values(3, 4);
insert into purchase_sale.selling (product_id, provider_id) values(3, 5);
insert into purchase_sale.selling (product_id, provider_id) values(4, 6);
insert into purchase_sale.selling (product_id, provider_id) values(4, 7);
insert into purchase_sale.selling (product_id, provider_id) values(5, 7);


insert into purchase_sale.purchase (product_id, consumer_id) values(1, 1);
insert into purchase_sale.purchase (product_id, consumer_id) values(1, 2);
insert into purchase_sale.purchase (product_id, consumer_id) values(2, 2);
insert into purchase_sale.purchase (product_id, consumer_id) values(2, 3);
insert into purchase_sale.purchase (product_id, consumer_id) values(2, 4);
insert into purchase_sale.purchase (product_id, consumer_id) values(3, 4);
insert into purchase_sale.purchase (product_id, consumer_id) values(3, 5);
insert into purchase_sale.purchase (product_id, consumer_id) values(3, 6);
insert into purchase_sale.purchase (product_id, consumer_id) values(4, 7);
insert into purchase_sale.purchase (product_id, consumer_id) values(5, 7);


select t.town_name, pr.product_name, p.provider_name, c.consumer_name
from purchase_sale.consumer c
join purchase_sale.purchase pu 
on c.consumer_id = pu.consumer_id
join purchase_sale.provider p 
on c.town_id = p.town_id
join purchase_sale.selling s 
on p.provider_id = s.provider_id
join purchase_sale.product pr 
on pr.product_id = pu.product_id
join purchase_sale.town t 
on t.town_id = c.town_id
where s.product_id = pu.product_id