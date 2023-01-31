INSERT INTO customer (ID, first_name, last_name) 
VALUES(1,  'Test 1', 'Last 1');
INSERT INTO customer (ID, first_name, last_name) 
VALUES(2,  'Test 2', 'Last 2');
INSERT INTO customer (ID, first_name, last_name) 
VALUES(3,  'Test 3', 'Last 3');

INSERT INTO customer_transaction (ID, description, transaction_date, amount, customer_id)
VALUES(1, 'Transaction 1 Test 1', '2023-01-01 09:00:00', 150.00, 1);
INSERT INTO customer_transaction (ID, description, transaction_date, amount, customer_id)
VALUES(2, 'Transaction 2 Test 1', '2022-12-15 09:00:00', 250.00, 1);
INSERT INTO customer_transaction (ID, description, transaction_date, amount, customer_id)
VALUES(3, 'Transaction 3 Test 1', '2022-01-11 09:00:00', 200.00, 1);

INSERT INTO customer_transaction (ID, description, transaction_date, amount, customer_id)
VALUES(4, 'Transaction 1 Test 2', '2023-01-01 09:00:00', 300.00, 2);
INSERT INTO customer_transaction (ID, description, transaction_date, amount, customer_id)
VALUES(5, 'Transaction 2 Test 2', '2022-12-15 09:00:00', 400.00, 2);
INSERT INTO customer_transaction (ID, description, transaction_date, amount, customer_id)
VALUES(6, 'Transaction 3 Test 2', '2022-12-25 09:00:00', 1000.00, 2);

INSERT INTO customer_transaction (ID, description, transaction_date, amount, customer_id)
VALUES(7, 'Transaction 1 Test 3', '2022-11-01 09:00:00', 250.00, 3);
INSERT INTO customer_transaction (ID, description, transaction_date, amount, customer_id)
VALUES(8, 'Transaction 2 Test 3', '2022-12-15 09:00:00', 450.00, 3);
INSERT INTO customer_transaction (ID, description, transaction_date, amount, customer_id)
VALUES(9, 'Transaction 3 Test 3', '2023-01-25 09:00:00', 50.00, 3);