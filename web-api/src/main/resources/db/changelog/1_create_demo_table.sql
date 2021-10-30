CREATE TABLE public.demo
(
    id     bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    "text" text NULL,
    CONSTRAINT demo_pk PRIMARY KEY (id)
);
