create table purpose(
    id serial primary key,
    purp varchar(255),
    country text
);

create table weapon(
    id serial primary key,
    model varchar(255),
    purpose_id int references purpose(id)
);

insert into purpose(purp, country) values ('assault rifle', 'Russia');
insert into purpose(purp, country) values ('assault rifle', 'USA');
insert into purpose(purp, country) values ('shotgun', 'Russia');

insert into weapon(model, purpose_id) values ('AK-47', 1);
insert into weapon(model, purpose_id) values ('M-14', 2);
insert into weapon(model, purpose_id) values ('Saiga', 3);
insert into weapon(model) values ('Glock');
insert into weapon(model) values ('MP-5');

select w.model, p.purp, p.country
from weapon as w
join purpose as p
on w.purpose_id = p.id;

select w.model as Модель, p.purp as Класс, p.country as Страна
from weapon as w
join purpose as p
on w.purpose_id = p.id;

select w.model as Модель, p.purp as Класс
from weapon as w
join purpose as p
on w.purpose_id = p.id;