create user if not exists 'lms'@'localhost' identified by 'rseg127';

create database if not exists lms;
use lms;
grant all privileges on lms.* to 'lms'@'localhost';
flush privileges;
set global time_zone = '+5:00';