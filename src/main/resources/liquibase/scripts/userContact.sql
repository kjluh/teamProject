--liquibase formatted sql

-- changeSet konstantin: 1
CREATE TABLE user_contact (
                       id bigint generated by default as identity primary key,
                       chat_id bigint,
                       name text,
                       phone_number bigint,
                       message text
);