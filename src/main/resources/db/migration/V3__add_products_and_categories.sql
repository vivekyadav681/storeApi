-- Insert additional categories
INSERT INTO categories (name) VALUES
('Kitchen Appliances'),
('Pet Supplies'),
('Office Supplies'),
('Music Instruments'),
('Baby Products');

-- Insert 10 products with proper category mappings
INSERT INTO products (name, price, description, category_id) VALUES
('Wireless Bluetooth Headphones', 79.99, 'Premium noise-canceling wireless headphones with 30-hour battery life and superior sound quality', 1),
('4K Smart LED TV 55 inch', 599.99, 'Ultra HD 4K Smart TV with HDR support, built-in streaming apps, and voice control', 1),
('Women''s Running Shoes', 89.99, 'Lightweight breathable running shoes with cushioned sole and arch support for maximum comfort', 2),
('Men''s Leather Wallet', 34.99, 'Genuine leather bifold wallet with RFID blocking, multiple card slots and bill compartment', 2),
('Espresso Coffee Maker', 249.99, 'Professional-grade espresso machine with milk frother, 15-bar pressure pump and programmable settings', 11),
('Stainless Steel Blender', 89.99, 'High-power blender with 1000W motor, 6 stainless steel blades and multiple speed settings', 11),
('Automatic Pet Feeder', 59.99, 'Smart programmable pet feeder with portion control, voice recording and smartphone app control', 12),
('Wireless Ergonomic Mouse', 29.99, 'Rechargeable wireless mouse with adjustable DPI, ergonomic design for comfortable all-day use', 13),
('Acoustic Guitar Starter Pack', 149.99, 'Complete beginner guitar package including acoustic guitar, gig bag, tuner, strings and picks', 14),
('Baby Monitor with Camera', 119.99, 'HD video baby monitor with night vision, two-way audio, temperature sensor and lullabies', 15);
