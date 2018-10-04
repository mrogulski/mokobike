INSERT INTO app_role (id, role_name, description) VALUES (1, 'STANDARD_USER', 'Standard User - Has no admin rights');
INSERT INTO app_role (id, role_name, description) VALUES (2, 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks');

-- USER
-- non-encrypted password: jwtpass
INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (1, 'John', 'Doe', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'john.doe');
INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (2, 'Admin', 'Admin', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'admin.admin');

INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (8, 'John8', 'Doe8', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'john.doe8');
INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (9, 'John8', 'Doe9', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'john.doe9');
INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (10, 'John10', 'Doe10', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'john.doe10');
INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (11, 'John8', 'Doe11', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'john.doe11');

insert into orders(
id,
  status,
  created_date,
  date_from,
  date_to,
  user_id ,
  adult_bike,
  child_bike,
  helmet,
  lock,
  pickup,
  pickup_from,
  pickup_to,
  pickup_distance,
  pickup_value,
  initial_value,
  final_value
)values(
12,
    'new',
	now(),
	now(),
	now(),
	1,
	2,
	1,
	3,
	3,
	true,
	'Wolka proszewska 1',
	'Wolka Proszewska 2',
	345.89,
	220.25,
	400.50,
	380.00
)

insert into orders(id,status,created_date,date_from,date_to,user_id ,adult_bike,child_bike,helmet,lock,pickup,pickup_from,pickup_to,pickup_distance,pickup_value,initial_value,final_value)values(13,'new',now(),now(),now(),1,2,1,3,3,true,'Wolka proszewska 1','Wolka Proszewska 2',345.89,220.25,400.50,380.00)

--insert inot bike
insert into bikes(
  reg_number,
  producer,
  bike_model,
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
)