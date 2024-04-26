-- Wstawianie przykładowych danych dla tabeli city
INSERT INTO city (id, description, name) VALUES
    ('6c4b6077-1122-40f6-b781-0f93078b6a9b', 'Stolica Polski', 'Warsaw'),
    ('280ebb24-0d75-4d6b-845f-4cab3cd698ea', 'Historyczne Miasto', 'Krakow'),
    ('b86f4ca2-3599-4866-9926-c51ccb5da0a2', 'Jedno z miast nadmorskich', 'Gdansk'),
    ('7c4b6077-1122-40f6-b781-0f93078b6a9b', 'Miasto położone w górach', 'Zakopane'),
        ('380ebb24-0d75-4d6b-845f-4cab3cd698ea', 'Miasto portowe', 'Szczecin'),
        ('c86f4ca2-3599-4866-9926-c51ccb5da0a2', 'Miasto słynące z urzędu', 'Wroclaw'),
        ('1c4b6077-1122-40f6-b781-0f93078b6a9b', 'Katowice to taka trochę ziemia niczyja', 'Katowice'),
        ('480ebb24-0d75-4d6b-845f-4cab3cd698ea', 'Miasto młodych gangów oraz bezczynnej policji', 'Poznan')
    ;

-- Wstawianie przykładowych danych dla tabeli place
INSERT INTO place (id, name, openinghours, street, rating, city_id) VALUES
    ('b5b32817-c4ac-433f-9043-da8b4bf09c1d', 'Zamek', '8-16', 'Mickiewicza 6', 2,'6c4b6077-1122-40f6-b781-0f93078b6a9b'),
    ('7ea53360-5626-4a95-8281-7318ab043129', 'Zamek na Wawelu', '8-16', 'Słowackiego 2', 5,'280ebb24-0d75-4d6b-845f-4cab3cd698ea'),
    ('5b00e8c4-db34-413e-b1bf-efe4ad4872f4', 'Stare Miasto', '8-16', 'Batorego 4', 5, 'b86f4ca2-3599-4866-9926-c51ccb5da0a2'),
    ('c5b32817-c4ac-433f-9043-da8b4bf09c1d', 'Zamek Malbork', '9-17', 'Starościńska 1', 4.5,'7c4b6077-1122-40f6-b781-0f93078b6a9b'),
        ('8ea53360-5626-4a95-8281-7318ab043129', 'Ostrów Tumski', '10-18', 'Katedralna 2', 4.7,'380ebb24-0d75-4d6b-845f-4cab3cd698ea'),
        ('6b00e8c4-db34-413e-b1bf-efe4ad4872f4', 'Sopot-Molo', '8-20', 'Plac Zdrojowy 2', 4.3, 'c86f4ca2-3599-4866-9926-c51ccb5da0a2'),
        ('d5b32817-c4ac-433f-9043-da8b4bf09c1d', 'Spodek', '9-19', 'Aleja Korfantego 35', 4.6,'1c4b6077-1122-40f6-b781-0f93078b6a9b'),
        ('9ea53360-5626-4a95-8281-7318ab043129', 'Stary Market', '10-22', 'Stary Rynek 1', 4.8,'480ebb24-0d75-4d6b-845f-4cab3cd698ea')
    ;

-- Wstawianie przykładowych danych dla tabeli users
INSERT INTO users (id, active, default_item, email, name, password, token, token_expiration, user_type) VALUES
    ('08b4605f-ec0d-49df-9914-fa3ccce3ca19', true, true, 'john.doe@example.com', 'John Doe', 'hashed_password', NULL, NULL, 'admin'),
    ('031c9c5c-99c2-47be-b1b6-e4662b9a6682', true, false, 'jane.smith@example.com', 'Jane Smith', 'hashed_password', NULL, NULL, 'user'),
    ('18b4605f-ec0d-49df-9914-fa3ccce3ca19', true, true, 'adam.nowak@example.com', 'Adam Nowak', 'hashed_password', NULL, NULL, 'user'),
        ('131c9c5c-99c2-47be-b1b6-e4662b9a6682', true, false, 'anna.kowalska@example.com', 'Anna Kowalska', 'hashed_password', NULL, NULL, 'user'),
        ('28b4605f-ec0d-49df-9914-fa3ccce3ca19', true, true, 'tomasz.wisniewski@example.com', 'Tomasz Wiśniewski', 'hashed_password', NULL, NULL, 'admin'),
        ('231c9c5c-99c2-47be-b1b6-e4662b9a6682', true, false, 'agnieszka.dabrowska@example.com', 'Agnieszka Dąbrowska', 'hashed_password', NULL, NULL, 'user'),
        ('38b4605f-ec0d-49df-9914-fa3ccce3ca19', true, true, 'michal.wojcik@example.com', 'Michał Wójcik', 'hashed_password', NULL, NULL, 'admin');

-- Wstawianie przykładowych danych dla tabeli opinion
INSERT INTO opinion (id, opinion, place_id, user_id) VALUES
    ('e9153f65-429f-46b2-a829-c656b74fe5d6', 'Fajne miejsce, polacem', 'b5b32817-c4ac-433f-9043-da8b4bf09c1d', '08b4605f-ec0d-49df-9914-fa3ccce3ca19'),
    ('fcbd049a-2f5e-4b60-a9e9-2c1b74440b87', 'Super atmosfera!', '7ea53360-5626-4a95-8281-7318ab043129', '031c9c5c-99c2-47be-b1b6-e4662b9a6682'),
    ('f9153f65-429f-46b2-a829-c656b74fe5d6', 'Idealne na spacer!', 'c5b32817-c4ac-433f-9043-da8b4bf09c1d', '18b4605f-ec0d-49df-9914-fa3ccce3ca19'),
    ('237a055e-7d79-448a-839c-cc1f609b9c28', 'Paskudna architektura!', '8ea53360-5626-4a95-8281-7318ab043129', '131c9c5c-99c2-47be-b1b6-e4662b9a6682'),
    ('ba25a681-e0da-4d11-b314-bda34ec9441c', 'W słonczeny dzień szkoda sobie psuć na stroju zeby tu przychodzić!', '6b00e8c4-db34-413e-b1bf-efe4ad4872f4', '28b4605f-ec0d-49df-9914-fa3ccce3ca19'),
    ('65ab0162-675a-4df9-bd92-811a9d4c3683', 'Podobno organizowane są tutaj koncerty, chyba jedynie po to żeby zagłuszyć pijaną młodzież!', 'd5b32817-c4ac-433f-9043-da8b4bf09c1d', '231c9c5c-99c2-47be-b1b6-e4662b9a6682'),
    ('1afa50d2-5fa0-4bc1-b50f-deb14a64ea56', 'Przez cały czas coś okropnie śmierdzi', '9ea53360-5626-4a95-8281-7318ab043129', '38b4605f-ec0d-49df-9914-fa3ccce3ca19');
