create database Bill_System;
use Bill_System;

CREATE TABLE IF NOT EXISTS SignUp (
    meter_no VARCHAR(20) ,      -- Unique ID (used for both Admin & Customer)
    username VARCHAR(50),                  -- Can be NULL initially for customers
    name VARCHAR(100) NOT NULL,
    password VARCHAR(100),
    usertype VARCHAR(20)                   -- 'Admin' or 'Customer'
);

select * from SignUp;

delete from SignUp where meter_no = "92650";

-- SET SQL_SAFE_UPDATES = 0;

-- delete  from SignUp where meter_no = 123;

-- truncate table SignUp;
