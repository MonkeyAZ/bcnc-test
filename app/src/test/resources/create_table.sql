DROP TABLE IF EXISTS Price;  
CREATE TABLE Price (  
PRICE_LIST INT AUTO_INCREMENT PRIMARY KEY,
BRAND_ID INT NOT NULL,  
START_DATE DATETIME NOT NULL,  
END_DATE DATETIME NOT NULL,
PRODUCT_ID INT NOT NULL,
PRIORITY INT NOT NULL,
PRICE DECIMAL(9,2) NOT NULL,
CURR VARCHAR(10) NOT NULL
); 

INSERT INTO Price VALUES 
    (1, 1, '2020-06-14 00:00:00.000', '2020-12-31 23:59:59.000', 35455, 0, 35.50, 'EUR'),
    (2, 1, '2020-06-14 15:00:00.000', '2020-06-14 18:30:00.000', 35455, 1, 25.45, 'EUR'),
    (3, 1, '2020-06-15 00:00:00.000', '2020-06-15 11:00:00.000', 35455, 1, 30.50, 'EUR'),
    (4, 1, '2020-06-15 16:00:00.000', '2020-12-31 23:59:59.000', 35455, 1, 38.95, 'EUR');