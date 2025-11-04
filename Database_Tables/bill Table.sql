use bill_system;

create table bill (
	meter_no varchar(20),
    month varchar(20),
    unit varchar(20),
    total_bill varchar(20),
    status varchar(20)
);

select * from bill;

truncate table bill;
