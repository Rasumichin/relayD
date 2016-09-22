--------------------------------------------------
-- Drop Table person
--------------------------------------------------
DROP TABLE person;

--------------------------------------------------
-- Create Table person
--------------------------------------------------
CREATE TABLE person (
	id								CHAR(36)		NOT NULL,
	surename						VARCHAR(256),
	forename						VARCHAR(256),
	yearOfBirth						INTEGER,
    shirtsize						INTEGER,
    relayname						VARCHAR(256),
    pos								INTEGER, -- POSITION geht nicht!
    nationality						VARCHAR(256),
	email							VARCHAR(256),
	info							VARCHAR(1024) -- COMMENT geht nicht!
);
COMMENT on TABLE person is 'realyD - The table of Person.'; -- Gehen alle nicht!
COMMENT on COLUMN person.id is 'UUID as string representation.';
COMMENT on COLUMN person.surename is 'The Surename of a Person.';
COMMENT on COLUMN person.forename is 'The Forename of a Person.';
COMMENT on COLUMN person.yearOfBirth is 'The Year Of Birth of a Person.';
COMMENT on COLUMN person.shirtsize is 'TODO - Is this not a ref to a table?';
COMMENT on COLUMN person.relayname is 'TODO - A workaround-should be an own entity.';
COMMENT on COLUMN person.position is 'TODO - A workaround-should be an own entity.';
COMMENT on COLUMN person.nationality is 'The nationality of a Person.';
COMMENT on COLUMN person.email is 'The Email of a Person.';
COMMENT on COLUMN person.comment is 'A comment for a Person.';

--------------------------------------------------
-- Create Index person_IDX
--------------------------------------------------
CREATE UNIQUE INDEX person_idx
        ON person
        (id);

--------------------------------------------------
-- Create Primary Key
--------------------------------------------------
ALTER TABLE person ADD PRIMARY KEY (id);

--------------------------------------------------
-- Grant person
--------------------------------------------------
GRANT ALL ON person TO PUBLIC; -- geht nicht!
