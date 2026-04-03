insert into customers(created_at, email, first_name, last_name, phone, user_password)
values ('2004-10-19 10:23:54', 'robert@wp.pl', 'Robert', 'Kowalski', '123456786', '123') on conflict do nothing;

insert into customers(created_at, email, first_name, last_name, phone, user_password)
values ('2016-11-29 1:00:00', 'jan@wp.pl', 'Jan', 'Kowalski', '624426726', 'qwerty') on conflict do nothing;

insert into customers(created_at, email, first_name, last_name, phone, user_password)
values ('2013-2-26 1:02:00', 'maciej123@gmail.com', 'Maciej', 'Nowak', '111222333', 'haslo123') on conflict do nothing;


insert into branches(address, city, name)
values ('ul. Majewska 24', 'Kraków', 'KRK');

insert into branches(address, city, name)
values ('ul. Jaworowa 12/3', 'Poznań', 'PZN');


insert into accounts(balance, branch_id, created_at, customer_id, account_number, account_type)
values (138.0, 1, '2004-10-11 10:20:00', 1, 'PL705637253647234876236492', 'transactions');

insert into accounts(balance, branch_id, created_at, customer_id, account_number, account_type)
values (98727234.0, 2, '2004-10-12 11:24:54', 1, 'PL345492253644554856766672', 'saving');

insert into accounts(balance, branch_id, created_at, customer_id, account_number, account_type)
values (20.0, 1, '2016-12-12 2:13:00', 2, 'PL567922536444447667618226', 'transactions');

insert into accounts(balance, branch_id, created_at, customer_id, account_number, account_type)
values (100000.0, 1, '2017-02-01 3:14:00', 2, 'PL128927266444423917619243', 'saving');

insert into accounts(balance, branch_id, created_at, customer_id, account_number, account_type)
values (17362.0, 2, '2013-2-26 10:20:00', 3, 'PL772495558716456957902848', 'transactions');

insert into accounts(balance, branch_id, created_at, customer_id, account_number, account_type)
values (24999.0, 2, '2013-2-26 11:33:00', 3, 'PL846862463939577875791872', 'saving');


insert into cards (account_id, card_number, expiry_date, card_type, cvv)
values (1, '2534169190652744', '2028-12-12 0:00:00', 'debit', '629');

insert into cards (account_id, card_number, expiry_date, card_type, cvv)
values (3, '4926546237967639', '2029-02-12 0:00:00', 'debit','123');

insert into cards (account_id, card_number, expiry_date, card_type, cvv)
values (5, '5773817871043697', '2030-02-12 0:00:00', 'debit','936');


insert into transactions (amount, from_account_id, to_account_id, created_at, transaction_type)
values (5000.0, 2, 1, '2024-03-01 12:00:00', 'internal_transfer');


insert into transactions (amount, from_account_id, to_account_id, created_at, transaction_type)
values (1200.0, 1, 3, '2024-03-05 14:30:00', 'external_transfer');


insert into transactions (amount, from_account_id, to_account_id, created_at, transaction_type)
values (200.0, 3, 4, '2024-03-10 09:15:00', 'saving_deposit');


insert into transactions (amount, from_account_id, to_account_id, created_at, transaction_type)
values (1500.0, 5, 1, '2024-03-12 18:45:00', 'external_transfer');


insert into transactions (amount, from_account_id, to_account_id, created_at, transaction_type)
values (50000.0, 2, 6, '2024-03-15 10:00:00', 'investment_transfer');


insert into transactions (amount, from_account_id, to_account_id, created_at, transaction_type)
values (100.0, 3, 5, '2024-03-16 21:00:00', 'atm_withdrawal');



