
CREATE USER jim WITH PASSWORD 'ex1stgl0bal';
 
CREATE DATABASE mydatabase OWNER jim;

USE mydatabase;

CREATE SEQUENCE hibernate_sequence;
CREATE TABLE employees (
	employee_id int primary key,

	last_name varchar(30),
	first_name varchar(30),
	middle_name varchar(30),
	suffix varchar(30),
	title varchar(30),

	street_no int,
	street varchar(20),
	brgy varchar(30),
	city varchar(30),
	zipcode varchar(10),

	birthday date,
	gwa float,
	date_hired date,
	currently_hired boolean
);
	
CREATE TABLE contacts (
	employee_id int,
	contact_type varchar(20),
	contact varchar(20),
	foreign key (employee_id) references employees(employee_id)
);

CREATE TABLE roles (
	role_id int primary key,
	role varchar(20)
);

CREATE TABLE employee_role (
	employee_id int,
	role_id int,
	primary key(employee_id,role_id),
	constraint fk_employee_id foreign key(employee_id) references employees(employee_id),
	constraint fk_role_id foreign key(role_id) references roles(role_id)
);

