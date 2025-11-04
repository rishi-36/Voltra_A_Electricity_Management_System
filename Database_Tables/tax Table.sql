use Bill_System;

create table tax (
	cost_per_unit varchar(20),
    meter_rent varchar(20),
    service_charge varchar(20),
    service_tax varchar(20),
    swachh_bharat_tax varchar(20),
    fixed_tax varchar(20)
);

insert into tax values ('10','45','20','58','5','18');

select * from tax;



-- SET SQL_SAFE_UPDATES = 0;
