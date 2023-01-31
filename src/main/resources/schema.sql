DROP TABLE IF EXISTS customer_transaction;
DROP TABLE IF EXISTS customer;
create table customer
(
   id integer not null,
   first_name varchar(255) not null,
   last_name varchar(255) not null,
   primary key(id)
);
create table customer_transaction
(
   id integer not null,
   description varchar(255) not null,
   transaction_date timestamp not null,
   amount numeric(20,2) not null,
   customer_id integer not null,
   primary key(id),
   foreign key(customer_id) references customer(id)
);