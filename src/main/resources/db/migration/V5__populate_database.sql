-- Insert categories
INSERT INTO categories (name)
VALUES ('Electronics'),
       ('Books'),
       ('Home & Kitchen'),
       ('Sports & Outdoors'),
       ('Clothing'),
       ('Beauty & Personal Care'),
       ('Toys & Games'),
       ('Groceries');

-- Insert products
INSERT INTO products (name, price, description, category_id)
VALUES ('Apple iPhone 14', 799.99,
        'Latest Apple smartphone with A15 Bionic chip, 128GB storage, and dual-camera system.', 1),
       ('Sony WH-1000XM5 Wireless Headphones', 398.00,
        'Noise-canceling over-ear headphones with premium sound and 30 hours battery life.', 1),
       ('The Midnight Library', 14.99, 'A novel by Matt Haig exploring life choices and alternate realities.', 2),
       ('Non-Stick Cookware Set', 89.50, '10-piece aluminum cookware set with non-stick coating and glass lids.', 3),
       ('Adidas Running Shoes', 75.00, 'Lightweight breathable running shoes for men with cushioned soles.', 4),
       ('Maybelline Lash Sensational Mascara', 8.99, 'Lengthening and volumizing mascara for defined eyelashes.', 6),
       ('LEGO Star Wars Millennium Falcon', 169.99,
        '1,351-piece LEGO set of the iconic Millennium Falcon from Star Wars.', 7),
       ('Organic Almonds - 1lb', 12.50, 'Non-GMO, raw organic almonds, perfect for healthy snacking.', 8),
       ('Camping Tent - 4 Person', 149.95, 'Waterproof, lightweight camping tent suitable for family outdoor trips.',
        4),
       ('Stainless Steel Water Bottle', 19.99,
        'Insulated water bottle keeps drinks cold for 24 hours or hot for 12 hours.', 4);
