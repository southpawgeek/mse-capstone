insert into user (username, password_hash, patron_id, first_name) values ('admin', 'none', 'none', 'Administrator');

insert into audit (actor_user, action) values ('admin', 'initialize');