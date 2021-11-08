CREATE TABLE public.department
(
    id          bigint       NOT NULL GENERATED ALWAYS AS IDENTITY,
    "name"      varchar(100) NOT NULL,
    daily_watch int          NOT NULL,
    manager_id  bigint       NOT NULL,
    CONSTRAINT department_pk PRIMARY KEY (id)
);

CREATE TABLE public.employee
(
    id            bigint       NOT NULL GENERATED ALWAYS AS IDENTITY,
    first_name    varchar(50)  NOT NULL,
    middle_name   varchar(50) NULL,
    last_name     varchar(50)  NOT NULL,
    phone         varchar(15)  NOT NULL,
    mail          varchar(254) NOT NULL,
    department_id bigint       NOT NULL,
    rank_id       bigint       NOT NULL,
    user_id       bigint       NOT NULL,
    CONSTRAINT employee_pk PRIMARY KEY (id)
);

CREATE TABLE public."rank"
(
    id            bigint       NOT NULL GENERATED ALWAYS AS IDENTITY,
    "name"        varchar(100) NOT NULL,
    mandatory_day int          NOT NULL,
    "level"       int          NOT NULL,
    CONSTRAINT rank_pk PRIMARY KEY (id)
);

CREATE TABLE public.manager
(
    id          bigint       NOT NULL GENERATED ALWAYS AS IDENTITY,
    first_name  varchar(50)  NOT NULL,
    middle_name varchar(50) NULL,
    last_name   varchar(50)  NOT NULL,
    phone       varchar(15)  NOT NULL,
    mail        varchar(254) NOT NULL,
    user_id     bigint       NOT NULL,
    CONSTRAINT manager_pk PRIMARY KEY (id)
);

CREATE TABLE public.preferred_day
(
    id          bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    "date"      date   NOT NULL,
    employee_id bigint NOT NULL,
    CONSTRAINT preferred_day_pk PRIMARY KEY (id)
);

CREATE TABLE public.off_day
(
    id          bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    "begin"     date   NOT NULL,
    "end"       date   NOT NULL,
    employee_id bigint NOT NULL,
    CONSTRAINT off_day_pk PRIMARY KEY (id)
);

CREATE TABLE public.watch
(
    id          bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    "date"      date   NOT NULL,
    employee_id bigint NOT NULL,
    CONSTRAINT watch_pk PRIMARY KEY (id)
);

CREATE TABLE public."user"
(
    id         bigint       NOT NULL GENERATED ALWAYS AS IDENTITY,
    username   varchar(152) NOT NULL,
    "password" varchar(60)  NOT NULL,
    role_id    bigint       NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id)
);

CREATE TABLE public."role"
(
    id     bigint      NOT NULL,
    "name" varchar(50) NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (id)
);
