DROP TABLE IF EXISTS quests;
DROP TABLE IF EXISTS characters;
DROP TABLE IF EXISTS worlds;

CREATE TABLE worlds (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        universe_type VARCHAR(50) NOT NULL
);

CREATE TABLE characters (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            power_level INT NOT NULL CHECK (power_level BETWEEN 1 AND 100),
                            type VARCHAR(20) NOT NULL,
                            world_id INT NOT NULL REFERENCES worlds(id) ON DELETE CASCADE
);

CREATE TABLE quests (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(120) NOT NULL,
                        required_power INT NOT NULL CHECK (required_power BETWEEN 1 AND 100),
                        access_type VARCHAR(20) NOT NULL,
                        world_id INT NOT NULL REFERENCES worlds(id) ON DELETE CASCADE
);

INSERT INTO worlds (name, universe_type) VALUES
                                             ('Land of Ooo (Prime)', 'Candy / Fantasy'),
                                             ('Farmworld Timeline', 'Alternate Timeline'),
                                             ('Nightosphere', 'Dark Dimension'),
                                             ('Ice Kingdom Variant', 'Frozen Realm');

INSERT INTO characters (name, power_level, type, world_id) VALUES
                                                               ('Finn', 55, 'HERO', 1),
                                                               ('Jake', 50, 'HERO', 1),
                                                               ('Princess Bubblegum', 70, 'HERO', 1),
                                                               ('Marceline', 75, 'HERO', 1),
                                                               ('Ice King', 45, 'VILLAIN', 4),
                                                               ('The Lich', 95, 'VILLAIN', 2);

INSERT INTO quests (title, required_power, access_type, world_id) VALUES
                                                                      ('Rescue a candy citizen', 25, 'HERO', 1),
                                                                      ('Stop an unauthorized spell in the Ice Kingdom', 40, 'HERO', 4),
                                                                      ('Survive a Nightosphere gate encounter', 60, 'HERO', 3),
                                                                      ('Corrupt a timeline artifact', 55, 'VILLAIN', 2),
                                                                      ('Spread chaos across the Candy Kingdom', 50, 'VILLAIN', 1);
