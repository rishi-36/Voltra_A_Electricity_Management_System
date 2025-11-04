create table new_customer (
	meter_no VARCHAR(20),
    name VARCHAR(50),
    address VARCHAR(100),
    city VARCHAR(30),
    state VARCHAR(30),
    email VARCHAR(50),
    phone VARCHAR(15)
);

select * from new_customer;

truncate table new_customer;