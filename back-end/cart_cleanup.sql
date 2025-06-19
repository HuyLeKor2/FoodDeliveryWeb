-- SQL script to check and fix cart data issues
-- Run this script in your MySQL database if you're experiencing cart-related errors

-- Check existing carts
SELECT * FROM cart;

-- Check for carts without customers
SELECT c.* FROM cart c 
LEFT JOIN user u ON c.customer_id = u.id 
WHERE u.id IS NULL;

-- Check for users without carts
SELECT u.* FROM user u 
LEFT JOIN cart c ON u.id = c.customer_id 
WHERE c.id IS NULL;

-- Check for duplicate carts per user
SELECT customer_id, COUNT(*) as cart_count 
FROM cart 
GROUP BY customer_id 
HAVING COUNT(*) > 1;

-- If there are duplicate carts, you can delete the duplicates (keep the one with the highest ID)
-- Replace 'user_id_here' with the actual user ID that has duplicates
-- DELETE FROM cart WHERE customer_id = 'user_id_here' AND id NOT IN (
--     SELECT MAX(id) FROM cart WHERE customer_id = 'user_id_here'
-- );

-- Check cart items
SELECT * FROM cart_item;

-- Check for orphaned cart items (items without a cart)
SELECT ci.* FROM cart_item ci 
LEFT JOIN cart c ON ci.cart_id = c.id 
WHERE c.id IS NULL;

-- Clean up orphaned cart items if needed
-- DELETE FROM cart_item WHERE cart_id NOT IN (SELECT id FROM cart);

-- Reset auto-increment if needed (be careful with this)
-- ALTER TABLE cart AUTO_INCREMENT = 1;

-- Check the current auto-increment value
SELECT AUTO_INCREMENT 
FROM information_schema.TABLES 
WHERE TABLE_SCHEMA = 'huyle_food' 
AND TABLE_NAME = 'cart'; 