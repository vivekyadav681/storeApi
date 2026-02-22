-- Insert 5 Profiles (one for each existing user)
-- Profile id must match user id (foreign key constraint)
INSERT INTO profiles (id, bio, phone_number, date_of_birth, loyalty_points) VALUES
(1, 'Tech enthusiast and coffee lover. Always on the lookout for the latest gadgets and innovations.', '555-0101', '1990-03-15', 250),
(2, 'Fitness coach and outdoor adventure seeker. Passionate about healthy living and exploring nature.', '555-0102', '1988-07-22', 180),
(3, 'Professional chef and food blogger. Love experimenting with new recipes and international cuisine.', '555-0103', '1992-11-08', 420),
(4, 'Software developer and music enthusiast. Guitar player in my free time and avid reader.', '555-0104', '1985-05-30', 350),
(5, 'Marketing specialist and new mom. Enjoying the journey of parenthood while building my career.', '555-0105', '1993-09-12', 500);

-- Insert 5 Addresses (distributed among existing users)
INSERT INTO addresses (street, city, state, zip, user_id) VALUES
('123 Main Street', 'San Francisco', 'California', '94102', 1),
('456 Oak Avenue', 'Seattle', 'Washington', '98101', 2),
('789 Pine Boulevard', 'Austin', 'Texas', '73301', 3),
('321 Elm Drive', 'Boston', 'Massachusetts', '02108', 4),
('654 Maple Lane', 'Portland', 'Oregon', '97201', 5);

-- Insert 5 Wishlist entries (users saving products they like)
INSERT INTO wishlist (product_id, user_id) VALUES
(1, 1),  -- Sarah wants Wireless Bluetooth Headphones
(2, 1),  -- Sarah also wants 4K Smart LED TV
(5, 3),  -- Elena wants Espresso Coffee Maker
(9, 4),  -- James wants Acoustic Guitar Starter Pack
(10, 5); -- Sophia wants Baby Monitor with Camera

-- Insert 5 Carts
INSERT INTO carts (id, date_created) VALUES
(UUID_TO_BIN(UUID()), CURDATE()),
(UUID_TO_BIN(UUID()), CURDATE()),
(UUID_TO_BIN(UUID()), CURDATE() - INTERVAL 1 DAY),
(UUID_TO_BIN(UUID()), CURDATE() - INTERVAL 2 DAY),
(UUID_TO_BIN(UUID()), CURDATE() - INTERVAL 3 DAY);

-- Insert 5 Cart Items (using the carts created above)
-- Note: We'll use variables to store cart IDs for easier reference
SET @cart1 = (SELECT id FROM carts ORDER BY date_created DESC LIMIT 1 OFFSET 0);
SET @cart2 = (SELECT id FROM carts ORDER BY date_created DESC LIMIT 1 OFFSET 1);
SET @cart3 = (SELECT id FROM carts ORDER BY date_created DESC LIMIT 1 OFFSET 2);
SET @cart4 = (SELECT id FROM carts ORDER BY date_created DESC LIMIT 1 OFFSET 3);
SET @cart5 = (SELECT id FROM carts ORDER BY date_created DESC LIMIT 1 OFFSET 4);

INSERT INTO cart_items (cart_id, product_id, quantity) VALUES
(@cart1, 1, 2),  -- 2 Wireless Bluetooth Headphones
(@cart2, 3, 1),  -- 1 Women's Running Shoes
(@cart3, 5, 1),  -- 1 Espresso Coffee Maker
(@cart4, 7, 3),  -- 3 Automatic Pet Feeders
(@cart5, 10, 1); -- 1 Baby Monitor with Camera
