create user if not exists 'lms'@'%' identified by 'rseg127';

create database if not exists lms;
use lms;
grant all privileges on lms.* to 'lms'@'%';
flush privileges;
set global time_zone = '+5:00';