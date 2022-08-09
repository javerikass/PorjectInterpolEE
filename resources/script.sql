CREATE SCHEMA interpolEE_storage;

SET search_path = interpolEE_storage;

create table users
(
    id      bigserial constraint users_pkey primary key,
    password varchar(255),
    role     varchar(255),
    username varchar(255)
);

alter table users owner to postgres;

create table requests
(
    id          bigserial constraint requests_pkey primary key,
    age         integer,
    approved    boolean not null,
    details     varchar(255),
    firstname   varchar(255),
    gender      varchar(255),
    lastname    varchar(255),
    nationality varchar(255),
    reward      integer,
    status      varchar(255),
    user_id     bigint constraint fkpxoim61vv7jyu63sh15gy501l references users
);

alter table requests owner to postgres;

create table news
(
    id               bigserial constraint news_pkey primary key,
    headline         varchar(255),
    publication_date date,
    news_text        varchar(5000)
);

alter table news owner to postgres;

create table tag
(
    id  bigserial constraint tag_pkey primary key,
    tag varchar(255)
);

alter table tag owner to postgres;

create table news_tags
(
    news_id bigint not null constraint fk30ksuvjcbsrdaxr2oc0p8mqwp references news,
    tag_id  bigint not null constraint fkil4rhcc5067jlerat4mx46tdq references tag
);

alter table news_tags owner to postgres;