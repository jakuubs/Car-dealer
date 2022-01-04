INSERT INTO `cars`.`brands` (`name_brand`, `origin_country`, `start_year`)
VALUES ('Audi', 'Germany', 1932);
INSERT INTO `cars`.`brands` (`name_brand`, `origin_country`, `start_year`)
VALUES ('Ferrari', 'Italy', 1947);
INSERT INTO `cars`.`brands` (`name_brand`, `origin_country`, `start_year`)
VALUES ('Tesla', 'USA', 2003);
INSERT INTO `cars`.`brands` (`name_brand`, `origin_country`, `start_year`)
VALUES ('Ford', 'USA', 1903);
INSERT INTO `cars`.`brands` (`name_brand`, `origin_country`, `start_year`)
VALUES ('Honda', 'Japan', 1948);
INSERT INTO `cars`.`brands` (`name_brand`, `origin_country`, `start_year`)
VALUES ('Mazda', 'Japan', 1920);
INSERT INTO `cars`.`brands` (`name_brand`, `origin_country`, `start_year`)
VALUES ('McLaren', 'Great Britain', 1985);

INSERT INTO `cars`.`models` (`name`, `release_date`, `brand_name`)
VALUES ('A4', 2016, 'Audi');
INSERT INTO `cars`.`models` (`name`, `release_date`, `brand_name`)
VALUES ('458 Italia', 2012, 'Ferrari');
INSERT INTO `cars`.`models` (`name`, `release_date`, `brand_name`)
VALUES ('SF90', 2019, 'Ferrari');
INSERT INTO `cars`.`models` (`name`, `release_date`, `brand_name`)
VALUES ('Model S', 2017, 'Tesla');
INSERT INTO `cars`.`models` (`name`, `release_date`, `brand_name`)
VALUES ('HR-V', 2017, 'Honda');
INSERT INTO `cars`.`models` (`name`, `release_date`, `brand_name`)
VALUES ('6', 2005, 'Mazda');
INSERT INTO `cars`.`models` (`name`, `release_date`, `brand_name`)
VALUES ('RX-7 Turbo', 1994, 'Mazda');
INSERT INTO `cars`.`models` (`name`, `release_date`, `brand_name`)
VALUES ('720S Coupe', 2018, 'McLaren');
INSERT INTO `cars`.`models` (`name`, `release_date`, `brand_name`)
VALUES ('Focus', 2015, 'Ford');

INSERT INTO `cars`.`cars` (`vin`, `mileage`, `engine_power`, `gearbox`, `fuel`, `model_id`)
VALUES ('4N2DN1113VD809649', 60000, 350, 'automatic', 'diesel', 1);
INSERT INTO `cars`.`cars` (`vin`, `mileage`, `engine_power`, `gearbox`, `fuel`, `model_id`)
VALUES ('WDBRF61J44E019240', 200000, 200, 'manual', 'diesel', 1);
INSERT INTO `cars`.`cars` (`vin`, `mileage`, `engine_power`, `gearbox`, `fuel`, `model_id`)
VALUES ('3GNEK12Z75G183425', 10000, 660, 'automatic', 'petrol', 2);
INSERT INTO `cars`.`cars` (`vin`, `mileage`, `engine_power`, `gearbox`, `fuel`, `model_id`)
VALUES ('JM1NB353820272058', 25000, 1000, 'automatic', 'hybrid', 3);
INSERT INTO `cars`.`cars` (`vin`, `mileage`, `engine_power`, `gearbox`, `fuel`, `model_id`)
VALUES ('JALC4J16967037962', 103000, 770, 'automatic', 'electric', 4);
INSERT INTO `cars`.`cars` (`vin`, `mileage`, `engine_power`, `gearbox`, `fuel`, `model_id`)
VALUES ('3C8FY68B52T224235', 146000, 120, 'manual', 'diesel', 5);
INSERT INTO `cars`.`cars` (`vin`, `mileage`, `engine_power`, `gearbox`, `fuel`, `model_id`)
VALUES ('5NPEB4AC4DH559530', 349000, 143, 'manual', 'diesel', 6);
INSERT INTO `cars`.`cars` (`vin`, `mileage`, `engine_power`, `gearbox`, `fuel`, `model_id`)
VALUES ('4S4BSAFC3F3275715', 171500, 240, 'manual', 'petrol', 7);
INSERT INTO `cars`.`cars` (`vin`, `mileage`, `engine_power`, `gearbox`, `fuel`, `model_id`)
VALUES ('WAUED28D9XA209963', 21610, 720, 'automatic', 'petrol', 8);
INSERT INTO `cars`.`cars` (`vin`, `mileage`, `engine_power`, `gearbox`, `fuel`, `model_id`)
VALUES ('JN1CV6FE6EM943498', 70000, 145, 'automatic', 'electric', 9);

INSERT INTO `cars`.`customers` (`e_mail`, `phone_number`)
VALUES ('john@example.com', '987654321');
INSERT INTO `cars`.`customers` (`e_mail`, `phone_number`)
VALUES ('anna@example.com', '123456789');
INSERT INTO `cars`.`customers` (`e_mail`, `phone_number`)
VALUES ('joe@example.com', '444555666');
INSERT INTO `cars`.`customers` (`e_mail`, `phone_number`)
VALUES ('michael@example.com', '101202303');
INSERT INTO `cars`.`customers` (`e_mail`, `phone_number`)
VALUES ('emma@example.com', '111222333');

INSERT INTO `cars`.`customer_details` (`first_name`, `last_name`, `address`, `id_customer`)
VALUES ('John', 'Doe', '3523  Wines Lane, Houston 77002', 1);
INSERT INTO `cars`.`customer_details` (`first_name`, `last_name`, `address`, `id_customer`)
VALUES ('Anna', 'Smith', '536  Brown Bear Drive, Millville 25432', 2);
INSERT INTO `cars`.`customer_details` (`first_name`, `last_name`, `address`, `id_customer`)
VALUES ('Joe', 'Williams', '2097  Grove Avenue, Oklahoma City 73109', 3);
INSERT INTO `cars`.`customer_details` (`first_name`, `last_name`, `address`, `id_customer`)
VALUES ('Michael', 'Brown', '1832  College Street, Parksville 12768', 4);
INSERT INTO `cars`.`customer_details` (`first_name`, `last_name`, `address`, `id_customer`)
VALUES ('Emma', 'Rodriguez', '3153  Conference Center Way, Pottsville 17901', 5);

INSERT INTO `cars`.`sales` (`sale_date`, `price`, `car_vin`, `customer_id`)
VALUES ('2020-12-04', 32000, '4N2DN1113VD809649', 1);
INSERT INTO `cars`.`sales` (`sale_date`, `price`, `car_vin`, `customer_id`)
VALUES ('2019-06-25', 780000, '3GNEK12Z75G183425', 3);
INSERT INTO `cars`.`sales` (`sale_date`, `price`, `car_vin`, `customer_id`)
VALUES ('2020-02-02', 25000, 'WDBRF61J44E019240', 2);
INSERT INTO `cars`.`sales` (`sale_date`, `price`, `car_vin`, `customer_id`)
VALUES ('2020-08-15', 285000, 'JALC4J16967037962', 1);
INSERT INTO `cars`.`sales` (`sale_date`, `price`, `car_vin`, `customer_id`)
VALUES ('2020-05-29', 56800, '3C8FY68B52T224235', 5);
INSERT INTO `cars`.`sales` (`sale_date`, `price`, `car_vin`, `customer_id`)
VALUES ('2021-01-12', 40900, 'JN1CV6FE6EM943498', 4);
