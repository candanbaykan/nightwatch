DROP TABLE public.demo;

UPDATE public."user"
SET password = '$2a$10$KMK5gthfDUNDpk7pfkaBkuH5BhCQfB5y9NoqfVOu1sWWtNcYB7Ni.'
WHERE id = 1;

UPDATE public.role
SET name = 'ADMIN'
WHERE id = 1;

UPDATE public.role
SET name = 'MANAGER'
WHERE id = 2;

UPDATE public.role
SET name = 'EMPLOYEE'
WHERE id = 3;
