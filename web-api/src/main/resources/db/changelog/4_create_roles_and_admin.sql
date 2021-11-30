INSERT INTO public."role" (id, "name")
	VALUES (1, 'ROLE_ADMIN');
INSERT INTO public."role" (id, "name")
	VALUES (2, 'ROLE_MANAGER');
INSERT INTO public."role" (id, "name")
	VALUES (3, 'ROLE_EMPLOYEE');

INSERT INTO public."user" (username, "password", role_id)
	VALUES ('jonsnow', 'ygritte', 1);
