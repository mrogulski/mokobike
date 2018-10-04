CREATE TABLE app_role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  role_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE app_user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  username varchar(255) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE user_role (
  user_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL,
  CONSTRAINT FK859n2jvi8ivhui0rl0esws6o FOREIGN KEY (user_id) REFERENCES app_user (id),
  CONSTRAINT FKa68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES app_role (id)
);


create table orders (
  id serial,
  status varchar,
  created_date timestamp,
  date_from timestamp,
  date_to timestamp,
  user_id int ,
  adult_bike int,
  child_bike int,
  helmet int,
  lock int,
  pickup boolean,
  pickup_from varchar,
  pickup_to varchar,
  pickup_distance double precision,
  pickup_value double precision,
  initial_value double precision,
  final_value double precision
)

create table bikes (
  id serial,
  reg_number varchar,
  producer varchar,
  model varchar,
  bike_type varchar,
  bike_condition varchar,
  rental_price double precision,
  purchase_amount double precision,
  date_of_purchase timestamp,
  in_use boolean
)