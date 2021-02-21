insert into user (username, user_type, password_hash, patron_id, first_name, email_address) values
("admin", "ADM", "5f4dcc3b5aa765d61d8327deb882cf99", "A1234", "Administrator", "admin@example.com"),
("librarian", "LIB", "5f4dcc3b5aa765d61d8327deb882cf99", "L2345", "Librarian", "librarian@example.com"),
("patron", "PAT", "5f4dcc3b5aa765d61d8327deb882cf99", "P3456", "Patron", "patron@example.com")
;


insert into audit (actor_user, action) values ('admin', 'initialize');

insert into asset
    (call_number, isbn, title)
values
    ("ANI 001", "9783836569088", "The Parrots"),
    ("ANI 002", "0810996537", "Dogs"),
    ("CMP 001", "1119235553", "Java For Dummies"),
    ("CMP 002", "0810456265", "Basic Apple Basic"),
    ("COM 001", "9781401238964", "Watchmen, Deluxe Edition"),
    ("COM 002", "0615314465", "xkcd: volume 0"),
    ("COO 001","1501169718", "Joy of Cooking: 2019 Edition"),
    ("COO 002", "0764566377", "Betty Crockers Cooky Book"),
    ("COM 003", "9781401223618", "Absolute V for Vendetta"),
    ("COM 004", "1401294707", "Sandman Box Set"),
    ("COM 005", "1401297099", "Swamp Thing"),
    ("ANI 003", "1119675928", "Raising Chickens For Dummies")
;