INSERT INTO topics(id, content) VALUES (1, 'Security');
INSERT INTO blog(id, name, topics) VALUES (1, 'My blog', 1);
INSERT INTO users(id, blog) VALUES (1, 1);
