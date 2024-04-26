CREATE EXTENSION IF NOT EXISTS "uuid-ossp" SCHEMA public;

-- Tworzenie tabeli city
CREATE TABLE IF NOT EXISTS city (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    description TEXT ,
    name TEXT NOT NULL
);

-- Tworzenie tabeli place
CREATE TABLE IF NOT EXISTS place (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name TEXT NOT NULL,
    city_id UUID,
    FOREIGN KEY (city_id) REFERENCES city(id)
);

-- Tworzenie tabeli users
CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    active BOOLEAN,
    default_item BOOLEAN,
    email TEXT NOT NULL,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    token TEXT,
    token_expiration timestamp without time zone,
    user_type character varying
);

-- Tworzenie tabeli opinion
CREATE TABLE IF NOT EXISTS opinion (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nick TEXT NOT NULL,
    opinion TEXT NOT NULL,
    place_id UUID,
    user_id UUID,
    FOREIGN KEY (place_id) REFERENCES place(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);




