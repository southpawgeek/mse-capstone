create table user(
    id int not null auto_increment,
    username varchar(75) not null unique,
    user_type char(3) not null,
    password_hash varchar(32) not null,
    patron_id varchar(32) not null,
    first_name varchar(75) not null,
    middle_name varchar(75),
    last_name varchar(75),
    home_address varchar(100),
    mail_address varchar(100),
    email_address varchar(100),
    phone_1 varchar(20),
    phone_2 varchar(20),
    phone_3 varchar(20),
    creation_date timestamp not null default current_timestamp,
    primary key (id)
);

create table asset(
    id int not null auto_increment,
    title varchar(100) not null,
    isbn varchar(50) not null,
    call_number varchar(50),
    primary key (id)
);

create table asset_copy(
    id int not null auto_increment,
    asset_id int not null,
    user_id int,
    status varchar(10),
    due_date timestamp not null default current_timestamp,
    primary key (id)
);

create table cart(
    id int not null auto_increment,
    user_id int not null,
    copy_id int not null,
    primary key (id)
);

create table author(
    id int not null auto_increment,
    first_name varchar(75) not null,
    middle_name varchar(75),
    last_name varchar(75),
    primary key (id)
);

create table asset_author(
    asset_id int not null,
    author_id int not null,
    primary key (asset_id, author_id)
);

create table audit(
    id int not null auto_increment,
    actor_user varchar(75),
    action varchar(10),
    target_user varchar(75),
    copy_id int,
    action_date timestamp not null default current_timestamp,
    primary key (id)
);

create view copy_asset_user_vw as 
select a.id as asset_id, a.title, a.isbn, u.id as user_id, u.username, ac.id as copy_id, ac.status, ac.due_date 
from asset_copy ac 
left join asset a on ac.asset_id = a.id 
left join user u on ac.user_id = u.id;

delimiter |
create trigger copy_cleanup_on_asset_delete 
after delete on asset
for each row
begin
    delete from asset_copy where asset_copy.asset_id = old.id;
end
|

create trigger cart_cleanup_on_copy_delete 
after delete on asset_copy 
for each row
begin
    delete from cart where cart.copy_id = old.id;
end
|

create trigger cart_cleanup_on_user_delete 
after delete on user
for each row
begin
    delete from cart where cart.user_id = old.id;
end
|

delimiter ;