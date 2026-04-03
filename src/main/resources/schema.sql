create or replace procedure add_card(_account_id integer, _card_number integer, _expiry_date timestamp(6), _card_type varchar(255), _cvv varchar(255))
language sql
as '
    insert into cards(account_id, card_number, expiry_date, card_type, cvv) values (_account_id, _card_number, _expiry_date, _card_type, _cvv);
';

create or replace procedure add_account(_balance numeric(38,2), _branch_id bigint, created_at timestamp(6), _customer_id bigint, _account_number varchar(255), _account_type varchar(255))
language sql
as '
    insert into accounts(balance, branch_id, created_at, customer_id, account_number, account_type) values (_balance , _branch_id, created_at, _customer_id, _account_number, _account_type);
';
