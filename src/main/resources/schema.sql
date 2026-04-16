create or replace procedure add_card(_account_id integer, _card_number integer, _expiry_date timestamp(6), _card_type varchar(255), _cvv varchar(255))
language sql
as '
    insert into cards(account_id, card_number, expiry_date, card_type, cvv) values (_account_id, _card_number, _expiry_date, _card_type, _cvv);
';;

create or replace procedure add_account(_balance numeric(38,2), _branch_id bigint, created_at timestamp(6), _customer_id bigint, _account_number varchar(255), _account_type varchar(255))
language sql
as '
    insert into accounts(balance, branch_id, created_at, customer_id, account_number, account_type) values (_balance , _branch_id, created_at, _customer_id, _account_number, _account_type);
';;

create or replace procedure delete_account(_account_id bigint, _customer_id bigint)
    language plpgsql
as $$
begin
    if exists (
        select 1
        from accounts
        where account_id = _account_id
          and customer_id = _customer_id
    ) then
        delete from accounts
        where account_id = _account_id
          and customer_id = _customer_id;

        raise notice 'Account with id % deleted successfully!', _account_id;
    else
        raise exception 'Account not found for the given customer!';
    end if;
end;
$$;;

create or replace procedure freeze_card (_card_id bigint)
    language sql
as '
    update cards
    set active = false
    where card_id = _card_id;
';;

create or replace procedure un_freeze_card (_card_id bigint)
    language sql
as '
    update cards
    set active = true
    where card_id = _card_id;
';;

create or replace procedure new_card (_account_id bigint, _card_type varchar(255))
    language plpgsql
as $$
declare
    _card_number varchar(255) := '';
    _cvv varchar(255) := '';
begin
    for i in 1..24 loop
            _card_number= _card_number|| text(floor(random() * 10));
        end loop;

    for i in 1..3 loop
            _cvv := _cvv || text(floor(random() * 10));
        end loop;

    insert into cards (active, account_id, expiry_date, card_number, card_type, cvv)
    values (true, _account_id, now() + interval '10 years', _card_number, _card_type, _cvv);
end
$$;;

create or replace procedure delete_card(_card_id bigint)
    language sql
as $$
delete from cards where card_id = _card_id;
$$;;



create or replace procedure transaction(_source_number varchar(255), _target_number varchar(255), _amount numeric(38,2))
    language plpgsql
as $$
declare
    source_id bigint;
    source_owner bigint;
    target_id bigint;
    target_owner bigint;
    first_id bigint;
    second_id bigint;
    current_balance numeric(38,2);
begin
    select account_id into source_id from accounts where _source_number = account_number;
    select account_id into target_id from accounts where _target_number = account_number;

    if _source_number = _target_number then
        raise exception 'Accounts cannot be the same!';
    end if;

    if source_id is null or target_id is null then
        raise exception 'Account not found (Source: %, Target: %)!', _source_number, _target_number;
    end if;

    select customer_id into source_owner from accounts where account_id = source_id;
    select customer_id into target_owner from accounts where account_id = target_id;

    if source_id < target_id then
        first_id := source_id;
        second_id := target_id;
    else
        first_id := target_id;
        second_id := source_id;
    end if;

    -- Blocking
    perform * from accounts where account_id = first_id for update;

    perform * from accounts where account_id = second_id for update;
    --

    select balance into current_balance
    from accounts
    where account_id = source_id;

    if current_balance < _amount then
        raise exception 'Not enough money (Balance: %, AmountToTransfer: %)!', current_balance, _amount;
    end if;

    update accounts set balance = balance - _amount where account_id = source_id;
    update accounts set balance = balance + _amount where account_id = target_id;

    insert into transactions (amount, created_at, from_account_id, to_account_id, transaction_type)
    values (_amount, now(), source_id, target_id, case when source_owner = target_owner then 'internal_transfer' else 'external_transfer' end);
end;
$$




