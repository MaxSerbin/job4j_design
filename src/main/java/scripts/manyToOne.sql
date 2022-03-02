create table purpose(
    id serial primary key,
    purp varchar(255)
);
create table weapon(
    id serial primary key,
    model varchar(255),
    purpose_id int references purpose(id)
);
insert into purpose(purp) values ('assault rifle');
insert into weapon(model, purpose_id) values ('AK-103', 1);
select * from weapon;
select * from purpose where id in (select id from weapon);
