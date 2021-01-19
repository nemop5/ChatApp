-- -----------------
-- ADDING TEST DATA
-- -----------------
-- Adding users
INSERT INTO users (id, first_name, last_name,  username, password, e_mail, gender, role, active, photo_url) VALUES(1, 'Miroslav', 'Simic', 'miroslav', '$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6', 'miroslav@gmail.com', 'male', 'ADMIN', false, '');
INSERT INTO users (id, first_name, last_name,  username, password, e_mail, gender, role, active, photo_url) VALUES(2, 'Tamara', 'Milosavljevic', 'tamara', '$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky', 'tamara@gmail.com', 'female', 'USER', false, '');
INSERT INTO users (id, first_name, last_name,  username, password, e_mail, gender, role, active, photo_url) VALUES(3, 'Petar', 'Jovic', 'petar', '$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC', 'petar@gmail.com', 'male', 'USER', false, '');
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Adding channels
INSERT INTO channels (id, name, details, user_id) VALUES (1, 'Food', 'Channel about food', 1);
INSERT INTO channels (id, name, details, user_id) VALUES (2, 'Sport', 'Channel about sport', 1);
INSERT INTO channels (id, name, details, user_id) VALUES (3, 'Programming', 'Channel about programming', 3);
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Adding messages
INSERT INTO messages (id, content, time_stamp, channel_id, user_id) VALUES (1, 'Hello Everyone, Welcome to Food Channel!!!', '2021-01-01 09:00:00', 1, 1);
INSERT INTO messages (id, content, time_stamp, channel_id, user_id) VALUES (2, 'Hiii', '2021-01-01 09:01:00', 1, 2);
INSERT INTO messages (id, content, time_stamp, channel_id, user_id) VALUES (3, 'Hello Everyone, Welcome to Sport Channel!!!', '2021-01-01 10:00:00', 2, 1);
INSERT INTO messages (id, content, time_stamp, channel_id, user_id) VALUES (4, 'Hello Everyone, Welcome to Programming Channel!!!', '2021-01-01 11:00:00', 3, 3);
