create table body(
id serial primary key,
b_name varchar(100)
);

create table engine(
id serial primary key,
e_name varchar(100)
);

create table gear_box(
id serial primary key,
gb_name varchar(100)
);

create table car(
id serial primary key,
c_name varchar(100),
body_id int references body(id),
engine_id int references engine(id),
gb_id int references gear_box(id)
);

insert into body(b_name) values ('coupe');
insert into body(b_name) values ('sedan');
insert into body(b_name) values ('crossover');
insert into body(b_name) values ('cabrio');

insert into engine(e_name) values ('HEMI');
insert into engine(e_name) values ('4g63t');
insert into engine(e_name) values ('2.0TFSI');
insert into engine(e_name) values ('LS3');

insert into gear_box(gb_name) values ('automatic');
insert into gear_box(gb_name) values ('manual');
insert into gear_box(gb_name) values ('robotic');
insert into gear_box(gb_name) values ('sequential');

insert into car(c_name, body_id, engine_id, gb_id) values ('Challenger', 1, 1, 1);
insert into car(c_name, body_id, engine_id, gb_id) values ('Lancer EVO', 2, 2, 2);
insert into car(c_name, body_id, engine_id, gb_id) values ('Audi Q5', 3, 3, 3);
insert into car(c_name, body_id, engine_id, gb_id) values ('Scania P420', null , null , null);

select c.c_name as Модель, b.b_name as Кузов, e.e_name as Двигатель, g.gb_name as КПП
from car as c
left join body as b
on c.body_id = b.id
left join engine as e
on c.engine_id = e.id
left join gear_box as g
on c.gb_id = g.id;

select b.b_name as Кузов
from body as b left join car as c
on b.id = c.body_id
where c.body_id is null;

select e.e_name as Двигатель
from engine as e left join car as c
on e.id = c.engine_id
where c.engine_id is null;

select g.gb_name as КПП
from gear_box as g left join car as c
on g.id = c.gb_id
where c.gb_id is null;