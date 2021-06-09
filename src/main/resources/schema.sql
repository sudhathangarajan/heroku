DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS inspections;
DROP TABLE IF EXISTS site_parts;
DROP TABLE IF EXISTS sites;
DROP TABLE IF EXISTS spare_parts;
DROP TABLE IF EXISTS parts;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id int not null AUTO_INCREMENT PRIMARY KEY,
    username varchar(50) not null UNIQUE,
    password varchar(100) not null,
    current_session_id varchar(200) null,
    role varchar(10) not null
);

-- master table for all parts
CREATE TABLE parts (
    id int not null AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) not null UNIQUE
);

-- the available parts in the inventory
CREATE TABLE spare_parts (
    id int not null AUTO_INCREMENT PRIMARY KEY,
    part_id int not null UNIQUE,
    quantity int not null
);

-- all sites and their location
CREATE TABLE sites (
    id int not null AUTO_INCREMENT PRIMARY KEY,
    postal_code varchar(10) not null,
    description varchar(100)
);

-- parts being used in each site
CREATE TABLE site_parts (
    id int not null AUTO_INCREMENT PRIMARY KEY,
    site_id int not null,
    part_id int not null,
    integrity varchar(20) not null
);

-- main transaction table. list of inspections designated for the site and technician
CREATE TABLE inspections (
    id int not null AUTO_INCREMENT PRIMARY KEY,
    site_id int not null,
    scheduled_at datetime not null,
    completed_at datetime null,
    status varchar(10) not null,
    remarks varchar(255) null,
    technician_id int not null,
    previous_inspection_id int null
);

-- list of reserved parts for the inspection. will deduct immediately from spare_parts table once reserved.
CREATE TABLE IF NOT EXISTS reservations (
    id int not null AUTO_INCREMENT PRIMARY KEY,
    part_id int not null,
    inspection_id int not null,
    quantity int not null
);