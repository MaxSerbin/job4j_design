create table cars(
    id serial primary key,
    car_model varchar(255)
);
create table engines(
    id serial primary key,
    eng_model varchar(255)
);
create table cars_engines(
    id serial primary key,
    car_id int references cars(id),
    engine_id int references engines(id)
);
insert into cars(car_model) values ('Wrangler');
insert into cars(car_model) values ('Charger');
insert into cars(car_model) values ('Challenger');

insert into engines(eng_model) values ('2.0 diesel');
insert into engines(eng_model) values ('HEMI 6.2');
insert into engines(eng_model) values ('HELLCAT');

insert into cars_engines(car_id, engine_id) values (1, 1);
insert into cars_engines(car_id, engine_id) values (1, 2);
insert into cars_engines(car_id, engine_id) values (1, 3);
insert into cars_engines(car_id, engine_id) values (2, 2);
insert into cars_engines(car_id, engine_id) values (2, 3);
insert into cars_engines(car_id, engine_id) values (3, 2);
insert into cars_engines(car_id, engine_id) values (3, 3);

select
  a.id,
  b.car_model cars,
  c.eng_model engines
from cars_engines a,
     cars b,
     engines c
where a.car_id = b.id
and a.engine_id = c.id;

