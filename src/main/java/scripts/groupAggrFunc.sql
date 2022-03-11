create table devices(
    id serial primary key,
    dev_name varchar(255),
    price real
);

create table people(
    id serial primary key,
    p_name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(dev_name, price) values ('смартфон', 4000.32), ('ноутбук', 10000.50),
('телевизор', 6000.10);
insert into people(p_name) values ('Иван'), ('Андрей'), ('Федор');
insert into devices_people(device_id, people_id) values (1, 1), (2, 1), (3, 1);
insert into devices_people(device_id, people_id) values (1, 2);
insert into devices_people(device_id, people_id) values (1, 3), (3, 3);

select avg(price) from devices;

select p.p_name, avg(d.price)
from devices_people as dp
join people as p
on dp.people_id = p.id
join devices as d
on dp.device_id = d.id
group by p.p_name;

select p.p_name, avg(d.price)
from devices_people as dp
join people as p
on dp.people_id = p.id
join devices as d
on dp.device_id = d.id
group by p.p_name
having avg(d.price) > 5000;