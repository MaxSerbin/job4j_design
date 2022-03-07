select * from students;

select stud_name, course, speciality from students;

select * from students where course = 2;

select * from students where course != 2;

select * from students where stud_name is null;

select * from students where stud_name is not null;

select * from students where enroll_date > '01.09.2019';

select * from students where course > 2;

select * from students where stud_name like 'D%';

select * from students where stud_name like 'D%' and course > 2;

select * from students where stud_name like 'D%' or course > 2;

select current_date;

select current_date > '10.09.2020';

select current_date + interval '1 month';

select * from students order by speciality asc;

select * from students order by speciality desc;

select distinct course from students;

select * from students limit 5;