use Bill_System;

create table meter_info (
	meter_number varchar(30),
    meter_location varchar(30),
    meter_type varchar(30),
    phase_code varchar(30),
    bill_type varchar(30),
    days varchar(20)    
);

select * from meter_info;

truncate table meter_info;
