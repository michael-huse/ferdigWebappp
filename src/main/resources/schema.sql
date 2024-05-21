-- Create the 'Bilett' table to store ticket information
CREATE TABLE Bilett(
    -- 'id' is an auto-incrementing integer and the primary key of the table
                       id INTEGER AUTO_INCREMENT NOT NULL,
                       film VARCHAR(255) NOT NULL,
    -- 'film' is a varchar column to store the name of the film (max 255 characters), cannot be NULL
                       antall INTEGER NOT NULL,
                       fornavn VARCHAR(255) NOT NULL,
                       etternavn VARCHAR(255) NOT NULL,
                       telefonnummer VARCHAR(25) NOT NULL,
                       epost VARCHAR(100),
    -- Define 'id' as the primary key of the 'Bilett' table
                       PRIMARY KEY (id)

);

-- Create the 'Kunde' table to store customer information
CREATE TABLE Kunde(
    -- 'KundeNr' is an auto-incrementing integer and the primary key of the table
                        KundeNr INTEGER AUTO_INCREMENT NOT NULL,
                        navn VARCHAR(255) NOT NULL,
                        adresse VARCHAR(255) NOT NULL,
    -- 'passord' is a varchar column to store the customer's password (max 255 characters), cannot be NULL
                        passord VARCHAR(255) NOT NULL,
    -- Define 'KundeNr' as the primary key of the 'Kunde' table
                        PRIMARY KEY (KundeNr)
);