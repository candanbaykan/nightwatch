ALTER TABLE public.watch
    ADD CONSTRAINT watch_fk FOREIGN KEY (employee_id) REFERENCES public.employee (id) ON DELETE CASCADE;

ALTER TABLE public."user"
    ADD CONSTRAINT user_fk FOREIGN KEY (role_id) REFERENCES public."role" (id);
ALTER TABLE public."user"
    ADD CONSTRAINT user_un UNIQUE (username);


ALTER TABLE public.preferred_day
    ADD CONSTRAINT preferred_day_fk FOREIGN KEY (employee_id) REFERENCES public.employee (id) ON DELETE CASCADE;

ALTER TABLE public.off_day
    ADD CONSTRAINT off_day_fk FOREIGN KEY (employee_id) REFERENCES public.employee (id) ON DELETE CASCADE;

ALTER TABLE public.manager
    ADD CONSTRAINT manager_fk FOREIGN KEY (user_id) REFERENCES public."user" (id) ON DELETE CASCADE;

ALTER TABLE public.employee
    ADD CONSTRAINT employee_fk FOREIGN KEY (department_id) REFERENCES public.department (id);
ALTER TABLE public.employee
    ADD CONSTRAINT employee_fk_1 FOREIGN KEY (rank_id) REFERENCES public."rank" (id);
ALTER TABLE public.employee
    ADD CONSTRAINT employee_fk_2 FOREIGN KEY (user_id) REFERENCES public."user" (id) ON DELETE CASCADE;

ALTER TABLE public.department
    ADD CONSTRAINT department_fk FOREIGN KEY (manager_id) REFERENCES public.manager (id);
