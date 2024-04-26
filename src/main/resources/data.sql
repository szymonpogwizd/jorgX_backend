-- Wstawianie przykładowych danych dla tabeli city
INSERT INTO city (id, description, name) VALUES
    ('6c4b6077-1122-40f6-b781-0f93078b6a9b', 'Capital city', 'Warsaw'),
    ('280ebb24-0d75-4d6b-845f-4cab3cd698ea', 'Historical city', 'Krakow'),
    ('b86f4ca2-3599-4866-9926-c51ccb5da0a2', 'Coastal city', 'Gdansk');

-- Wstawianie przykładowych danych dla tabeli place
INSERT INTO place (id, name, openinghours, street, rating, city_id) VALUES
    ('b5b32817-c4ac-433f-9043-da8b4bf09c1d', 'Royal Castle', '8-16', 'Mickiewicza 6', 2,'6c4b6077-1122-40f6-b781-0f93078b6a9b'),
    ('7ea53360-5626-4a95-8281-7318ab043129', 'Wawel Castle', '8-16', 'Słowackiego 2', 5,'280ebb24-0d75-4d6b-845f-4cab3cd698ea'),
    ('5b00e8c4-db34-413e-b1bf-efe4ad4872f4', 'Old Town', '8-16', 'Batorego 4', 5, 'b86f4ca2-3599-4866-9926-c51ccb5da0a2');

-- Wstawianie przykładowych danych dla tabeli users
INSERT INTO users (id, active, default_item, email, name, password, token, token_expiration, user_type) VALUES
    ('08b4605f-ec0d-49df-9914-fa3ccce3ca19', true, true, 'john.doe@example.com', 'John Doe', 'hashed_password', NULL, NULL, 'admin'),
    ('031c9c5c-99c2-47be-b1b6-e4662b9a6682', true, false, 'jane.smith@example.com', 'Jane Smith', 'hashed_password', NULL, NULL, 'user');

-- Wstawianie przykładowych danych dla tabeli opinion
INSERT INTO opinion (id, opinion, place_id, user_id) VALUES
    ('e9153f65-429f-46b2-a829-c656b74fe5d6', 'Nice place to visit!', 'b5b32817-c4ac-433f-9043-da8b4bf09c1d', '08b4605f-ec0d-49df-9914-fa3ccce3ca19'),
    ('fcbd049a-2f5e-4b60-a9e9-2c1b74440b87', 'Amazing atmosphere!', '7ea53360-5626-4a95-8281-7318ab043129', '031c9c5c-99c2-47be-b1b6-e4662b9a6682');
