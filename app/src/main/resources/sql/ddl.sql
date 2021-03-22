create user if not exists 'lms'@'%' identified by 'rseg127';

create database if not exists lms;
use lms;
grant all privileges on lms.* to 'lms'@'%';
flush privileges;

-- EDT, since it's march
set global time_zone = '-4:00';