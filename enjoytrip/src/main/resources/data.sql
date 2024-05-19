INSERT INTO members(username, id, password, nickname, email, created_date, last_modified_date)
VALUES ('admin', 'admin', 'admin', 'admin', 'admin', now(), now());
INSERT INTO roles(member_id, role_name) VALUES (1, 'ADMIN');