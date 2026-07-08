-- RetroZone database (English) - generated from model-retrozone-en.mwb
CREATE SCHEMA IF NOT EXISTS `retrozone_db` DEFAULT CHARACTER SET utf8mb4;
USE `retrozone_db`;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` INT NOT NULL,
  `user_fullname` VARCHAR(100) NOT NULL,
  `user_name` VARCHAR(100) NOT NULL,
  `user_email` VARCHAR(100) NOT NULL,
  `user_phone` INT NOT NULL,
  `user_password_hash` VARCHAR(20) NOT NULL,
  `user_registration_date` DATE NOT NULL,
  `user_address` VARCHAR(45) NOT NULL,
  `addresses_address_id` INT NOT NULL,
  `cart_cart_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `cart_cart_id`),
  CONSTRAINT `fk_users_addresses1` FOREIGN KEY (`addresses_address_id`) REFERENCES `addresses` (`address_id`),
  CONSTRAINT `fk_users_cart1` FOREIGN KEY (`cart_cart_id`) REFERENCES `cart` (`cart_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `products` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(50) NOT NULL,
  `product_description` VARCHAR(100) NOT NULL,
  `product_price` DECIMAL(10,2) NOT NULL,
  `product_stock` INT NOT NULL,
  `product_status` VARCHAR(45) NOT NULL,
  `product_platform` VARCHAR(50) NOT NULL,
  `product_genre` VARCHAR(45) NOT NULL,
  `category_category_id` INT NOT NULL,
  PRIMARY KEY (`product_id`),
  CONSTRAINT `fk_products_category1` FOREIGN KEY (`category_category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `category` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `order_purchase_date` DATE NOT NULL,
  `order_unit_price` DECIMAL(10,2) NOT NULL,
  `order_total_price` DECIMAL(10,2) NOT NULL,
  `order_detail` VARCHAR(100) NOT NULL,
  `product_quantity` INT NOT NULL,
  `address_id` INT NOT NULL,
  `users_user_id` INT NOT NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `fk_orders_users` FOREIGN KEY (`users_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `addresses` (
  `address_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `address_street_and_number` VARCHAR(100) NOT NULL,
  `address_state` VARCHAR(45) NOT NULL,
  `address_country` VARCHAR(45) NOT NULL,
  `code` VARCHAR(45) NOT NULL,
  `address_postal` INT NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `cart` (
  `cart_id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `cart_total_price` DECIMAL(10,2) NOT NULL,
  `cart_unit_price` DECIMAL(10,2) NOT NULL,
  `cart_product_quantity` DECIMAL(10,2) NOT NULL,
  `products_product_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`cart_id`),
  CONSTRAINT `fk_cart_products1` FOREIGN KEY (`products_product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `products_has_orders` (
  `products_product_id` INT NOT NULL,
  `orders_order_id` INT NOT NULL,
  PRIMARY KEY (`products_product_id`, `orders_order_id`),
  CONSTRAINT `fk_products_has_orders_products1` FOREIGN KEY (`products_product_id`) REFERENCES `products` (`product_id`),
  CONSTRAINT `fk_products_has_orders_orders1` FOREIGN KEY (`orders_order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB;

SET FOREIGN_KEY_CHECKS = 1;
