create table fauna (
    id serial primary key,
    animal_name text,
    avg_age int,
    discovery_date date
);

insert into fauna(animal_name, avg_age, discovery_date)
values ('Swordfish', 10000, '1841-03-01');
insert into fauna(animal_name, avg_age, discovery_date)
values ('Bear', 15000, '1839-05-02');
insert into fauna(animal_name, avg_age, discovery_date)
values ('lion', 22000, '1951-07-07');
insert into fauna(animal_name, avg_age, discovery_date)
values ('Monkey', 11000, null );

select * from fauna;

select * from fauna where animal_name like '%fish%';

select * from fauna where avg_age > 9000 and avg_age < 22000;

select * from fauna where discovery_date is null ;

select * from fauna where discovery_date < '1950-01-01';

