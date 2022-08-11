CREATE SEQUENCE serial START 101;

create table users
(
    id          bigint
        default nextval('serial')
        primary key,
    discount    double precision,
    email       varchar(255) unique,
    first_name  varchar(255),
    is_enabled  boolean not null,
    last_name   varchar(255),
    middle_name varchar(255),
    password    varchar(255) not null ,
    role        varchar(255) not null
);

create table boxes
(
    id            bigint
        default nextval('serial')
        primary key,
    coefficient   double precision not null ,
    end_working   time not null ,
    is_worked     boolean not null,
    name          varchar(255),
    start_working time not null ,
    operator_id   bigint
        constraint box_operator
            references users
            on DELETE set null
);

create index boxes_operator_id_key on boxes using hash(operator_id);

create table car_wash_service
(
    id                    bigint
        default nextval('serial')
        primary key,
    car_wash_service_type varchar(255) not null ,
    cost                  double precision not null ,
    description           varchar(255),
    duration              integer not null
);


create table booking
(
    id                 bigint
        default nextval('serial')
        primary key,
    booking_status     varchar(255) not null ,
    cost               double precision not null ,
    create_date        timestamp not null  ,
    washing_end_time   timestamp not null ,
    washing_start_time timestamp not null ,
    box_id             bigint
        constraint booking_boxe
            references boxes
            on DELETE set null,
    car_wash_service_id    bigint
        constraint booking_car_wash_service
            references car_wash_service
            on DELETE set null,
    user_id            bigint
        constraint booking_user
            references users
            on DELETE set null
);

create index bookings_box_id_key on booking using hash(box_id);
create index bookings_user_id_key on booking using hash(user_id);
create index bookings_car_wash_service_id_key on booking using hash(car_wash_service_id);

create table payment_statistics
(
    id              bigint
        default nextval('serial')
        primary key,
    payment_time    timestamp,
    washing_cost    double precision,
    box_id          bigint
        constraint payment_statistic_box
            references boxes
            on DELETE set null,
    car_wash_service_id bigint
        constraint payment_statistic_car_wash_service
            references car_wash_service
            on DELETE set null,
    user_id         bigint
        constraint payment_statistic_user
            references users
            on DELETE set null
);

create index payment_statistics_payment_time_key on payment_statistics (payment_time);
create index payment_statistics_user_id_key on payment_statistics (user_id);