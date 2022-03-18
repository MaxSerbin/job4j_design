create table departments (
id serial primary key,
d_name varchar(255)
);

create table emploers (
id serial primary key,
e_name varchar(255),
dept_id int references departments(id)
);

insert into departments(d_name) values ('dept1');
insert into departments(d_name) values ('dept2');
insert into departments(d_name) values ('dept3');
insert into departments(d_name) values ('dept4');

insert into emploers(e_name, dept_id) values ('Max', 1);
insert into emploers(e_name, dept_id) values ('Tom', 2);
insert into emploers(e_name, dept_id) values ('John', 3);
insert into emploers(e_name, dept_id) values ('Carl', null);

select * from emploers e left join departments d on e.dept_id = d.id;

select * from emploers e right join departments d on e.dept_id = d.id;

select * from emploers e full join departments d on e.dept_id = d.id;

select * from emploers e cross join departments d;

select * from departments d left join emploers e on e.dept_id = d.id
where dept_id is null;

select e.e_name, e.dept_id, d.d_name
from emploers e left join departments d on e.dept_id = d.id;

select e.e_name, e.dept_id, d.d_name
from departments d right join emploers e on e.dept_id = d.id;

create table teens (
id serial primary key,
t_name varchar(100),
gender text
);

insert into teens(t_name, gender) values ('Sam', 'male');
insert into teens(t_name, gender) values ('Tom', 'male');
insert into teens(t_name, gender) values ('Bob', 'male');
insert into teens(t_name, gender) values ('Alex', 'male');
insert into teens(t_name, gender) values ('Sara', 'female');
insert into teens(t_name, gender) values ('Ann', 'female');
insert into teens(t_name, gender) values ('Lisa', 'female');

select m.t_name as Male, f.t_name as Female
from teens m cross join teens f
where m.gender like 'male' and f.gender like 'female';