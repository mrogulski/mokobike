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