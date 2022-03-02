create table number(
    id serial primary key,
    engine_number int
);
create table engine(
    id serial primary key,
    model varchar(255),
    number_id int references number(id) unique
);
insert into number(engine_number) values (12345);
insert into engine(model, number_id) values ('4G63T', 1);
select * from engine;
select * from number where id in (select id from engine);