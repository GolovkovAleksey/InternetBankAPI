insert into users (id, balance, name, surname)
values (1, 74700.00, 'Aleksey', 'Golovkov');

insert into users (id, balance, name, surname)
values (2, 8890.00, 'Vladimir', 'Andreev');

insert into users (id, balance, name, surname)
values (3, 100.00, 'Andrey', 'Kuks');

insert into operations (id, date_of_operation, operation_type, sum, user_id)
values (200, '2023-04-20', 'PUT_MONEY', 300.0, 1);