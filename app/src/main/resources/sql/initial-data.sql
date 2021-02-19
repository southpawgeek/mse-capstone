insert into user (username, password_hash, patron_id, first_name) values ('admin', 'none', 'none', 'Administrator');

insert into audit (actor_user, action) values ('admin', 'initialize');

insert into asset (title, isbn, call_number) values ("Example Title", "123456789", "A 1 2021 c.1");