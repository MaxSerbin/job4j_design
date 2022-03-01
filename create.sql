create table cars(
    id serial primary key,
    model varchar(255),
    year integer,
    used boolean
);
insert into cars (model, year, used) values ('Ford', 1965, true);
insert into cars (model, year, used) values ('GMC', 2021, false);
insert into cars (model, year, used) values ('Ferrari', 1998, true);
select * from cars;
update cars set model = 'Chevrolet';
select * from cars;
delete from cars;
select * from cars;
