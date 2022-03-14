create table type (
id serial primary key,
t_name varchar(255)
);

create table product (
id serial primary key,
p_name varchar(255),
expired_date timestamp,
price int,
type_id int references type(id)
);

insert into type(t_name) values ('сыр');
insert into type(t_name) values ('молоко');
insert into type(t_name) values ('мясо');
insert into type(t_name) values ('хлеб');

insert into product(p_name, expired_date, price, type_id)
values ('сыр плавленный', '2022-03-10', 220, 1);
insert into product(p_name, expired_date, price, type_id)
values ('сыр моцарелла', '2022-03-22', 320, 1);
insert into product(p_name, expired_date, price, type_id)
values ('сыр пармезан', '2022-04-11', 400, 1);
insert into product(p_name, expired_date, price, type_id)
values ('сыр гауда', '2022-04-20', 300, 1);
insert into product(p_name, expired_date, price, type_id)
values ('сыр сулугуни', '2022-04-15', 280, 1);
insert into product(p_name, expired_date, price, type_id)
values ('сыр рикотта', '2022-04-02', 350, 1);
insert into product(p_name, expired_date, price, type_id)
values ('сыр бри', '2022-05-01', 500, 1);
insert into product(p_name, expired_date, price, type_id)
values ('сыр чечил', '2022-04-18', 270, 1);
insert into product(p_name, expired_date, price, type_id)
values ('сыр пошехонский', '2022-04-09', 290, 1);
insert into product(p_name, expired_date, price, type_id)
values ('сыр плавленный', '2022-03-10', 220, 1);

insert into product(p_name, expired_date, price, type_id)
values ('молоко 3%', '2022-03-13', 100, 2);
insert into product(p_name, expired_date, price, type_id)
values ('молоко 1%', '2022-03-18', 90, 2);

insert into product(p_name, expired_date, price, type_id)
values ('мясо мороженое', '2022-10-10', 500.00, 3);
insert into product(p_name, expired_date, price, type_id)
values ('мясо охлажденное', '2022-03-20', 600, 3);

insert into product(p_name, expired_date, price, type_id)
values ('хлеб белый', '2022-03-15', 48, 4);
insert into product(p_name, expired_date, price, type_id)
values ('хлеб черный', '2022-03-15', 45, 4);

select p.p_name from product as p
join type as t
on p.type_id = t.id
where t.t_name = 'сыр';

select * from product
where p_name like '%мороженое%';

select * from product
where expired_date < '2022-03-14';

select * from product
where price < 1000 and price > 500;

select t.t_name, count(t.t_name)
from product as p
join type as t
on p.type_id = t.id
group by t.t_name;

select p.p_name
from product as p
join type as t
on p.type_id = t.id
where t.t_name = 'сыр' or t.t_name = 'молоко';


select t.t_name
from product as p
join type as t
on p.type_id = t.id
group by t.id
having count(t.id) < 10;

select p.p_name, t.t_name
from product as p
join type as t
on p.type_id = t.id;

