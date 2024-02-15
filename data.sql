INSERT INTO account (iban, customer, balance) VALUES
('ES1234567890', '12345678Z', 1050.00);
INSERT INTO account (iban, customer, balance) VALUES
('GB9876543210', '12345678Z', 600.00);

INSERT INTO movement (amount, type, balance, date, account_iban) VALUES
(100.00, 'DEPOSIT', 1100.00, '2024-02-15 10:00:00', 'ES1234567890');
INSERT INTO movement (amount, type, balance, date, account_iban) VALUES
(50.00, 'WITHDRAWAL', 1050.00, '2024-02-16 14:00:00', 'ES1234567890');
INSERT INTO movement (amount, type, balance, date, account_iban) VALUES
(200.00, 'DEPOSIT', 700.00, '2024-02-15 11:00:00', 'GB9876543210');
INSERT INTO movement (amount, type, balance, date, account_iban) VALUES
(100.00, 'WITHDRAWAL', 600.00, '2024-02-16 15:00:00', 'GB9876543210');
