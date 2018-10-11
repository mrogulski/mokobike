--select latest item
select * from orders order by id desc limit 1

--pagination
select * from orders order by id limit 2 offset 1

--update order
update orders set
	status = 'in progress',
	date_from = '2018-09-18 14:26:31',
	date_to = '2018-09-18 14:26:31',
	adult_bike = 1,
	child_bike = 1,
	helmet = 1,
	lock = 1,
	pickup = false,
	pickup_from = null,
	pickup_to = null,
	pickup_distance = null,
	pickup_value = null,
	final_value = 300
where id = 3

--SQL_SELECT_ACTIVE_BIKES_COUNT
select * from bikes where bike_type = 'ADULT' and bike_condition not in ('TRASHED')

--return whole record
insert into bikes(
  reg_number,
  producer,
  model,
  bike_type,
  bike_condition,
  rental_price,
  purchase_amount,
  date_of_purchase,
  in_use
)values(
'WW9898',
'Cross',
'AMD-500',
'ADULT',
'NEW',
50,
1200,
now(),
false
) RETURNING *;


--SQL_SELECT_RENT_BIKES_COUNT
select sum(adult_bike) from orders where date_from >= '2012-04-23 20:25:00' and date_to <= '2012-04-24 20:25:43'

