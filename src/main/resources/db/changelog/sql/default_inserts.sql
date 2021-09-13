INSERT INTO  vh_worker (name, birthday, start_date, salary, role, department)
VALUES ('Yarick Zema', '29-06-1999', '12-03-2017', 3000000, 'MANAGER', 'ACCOUNTING');

INSERT INTO vh_worker (name, birthday, start_date, salary, role, department, manager_id)
VALUES ('Vadym Hera', '24-09-1999', '10-03-2020', 2000000, 'WORKER', 'ACCOUNTING',
        (SELECT id FROM vh_worker WHERE name = 'Yarick Zema'));

INSERT INTO vh_worker (name, birthday, start_date, salary, role, department, description, manager_id)
VALUES ('Petya Prada', '02-11-1998', '24-05-2017', 5000000, 'OTHER', 'ACCOUNTING', 'Other role for Petya',
        (SELECT id FROM vh_worker WHERE name = 'Yarick Zema'));

INSERT INTO vh_worker (name, birthday, start_date, salary, role, department)
VALUES ('Roma Vor', '08-03-2000', '02-03-2016', 7500000, 'MANAGER', 'COMMERCIAL');

INSERT INTO vh_worker (name, birthday, start_date, salary, role, department, manager_id)
VALUES ('Sasha Kova', '12-08-2001', '17-03-2019', 1900000, 'WORKER', 'COMMERCIAL',
        (SELECT id FROM vh_worker WHERE name = 'Roma Vor'));

INSERT INTO vh_worker (name, birthday, start_date, salary, role, department, description, manager_id)
VALUES ('Artem Lui', '11-09-2002', '15-09-2018', 6000000, 'OTHER', 'COMMERCIAL', 'Other role for Artem',
        (SELECT id FROM vh_worker WHERE name = 'Roma Vor'));

INSERT INTO vh_worker (name, birthday, start_date, salary, role, department)
VALUES ('Leha Syag', '04-04-1999', '02-03-2016', 5500000, 'MANAGER', 'MANAGERIAL');

INSERT INTO vh_worker (name, birthday, start_date, salary, role, department, manager_id)
VALUES ('Misha Sotnik', '14-01-1998', '17-03-2019', 4200000, 'WORKER', 'MANAGERIAL',
        (SELECT id FROM vh_worker WHERE name = 'Leha Syag'));

INSERT INTO vh_worker (name, birthday, start_date, salary, role, department, description, manager_id)
VALUES ('Oleg Dani', '02-01-2003', '15-09-2018', 1500000, 'OTHER', 'MANAGERIAL', 'Other role for Oleg',
        (SELECT id FROM vh_worker WHERE name = 'Leha Syag'));

SELECT * FROM vh_worker;