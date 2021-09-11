--working only with empty database
insert into vh_worker (name, birthday, start_date, salary, role, department)
values ('Yarick Zema', '29-06-1999', '12-03-2017', 4500000, 'MANAGER', 'ACCOUNTING');

insert into vh_worker (name, birthday, start_date, salary, role, department, manager_id)
values ('Vadym Hera', '24-06-1999', '10-03-2020', 2000000, 'WORKER', 'ACCOUNTING', 1);

insert into vh_worker (name, birthday, start_date, salary, role, department, manager_id)
values ('Petya Prada', '02-11-1998', '24-05-2017', 50000000, 'OTHER', 'ACCOUNTING', 1);

insert into vh_worker (name, birthday, start_date, salary, role, department)
values ('Roma Vor', '08-03-2000', '02-03-2016', 7500000, 'MANAGER', 'COMMERCIAL');

insert into vh_worker (name, birthday, start_date, salary, role, department, manager_id)
values ('Sasha Kova', '12-08-2001', '17-03-2019', 1900000, 'WORKER', 'COMMERCIAL', 4);

insert into vh_worker (name, birthday, start_date, salary, role, department, manager_id)
values ('Artem Lui', '11-09-2002', '15-09-2018', 6000000, 'OTHER', 'COMMERCIAL', 4);

insert into vh_worker (name, birthday, start_date, salary, role, department)
values ('Leha Syag', '04-04-1999', '02-03-2016', 5500000, 'MANAGER', 'MANAGERIAL');

insert into vh_worker (name, birthday, start_date, salary, role, department, manager_id)
values ('Misha Sotnik', '14-01-1998', '17-03-2019', 4200000, 'WORKER', 'MANAGERIAL', 7);

insert into vh_worker (name, birthday, start_date, salary, role, department, manager_id)
values ('Oleg Dani', '02-01-2003', '15-09-2018', 1500000, 'OTHER', 'MANAGERIAL', 7);