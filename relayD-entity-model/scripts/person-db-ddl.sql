--------------------------------------------------
-- Drop Table PERSON
--------------------------------------------------
Drop table PERSON;

--------------------------------------------------
-- Create Table PERSON
--------------------------------------------------
Create table PERSON (
	ID								CHAR(36)		NOT NULL,
	SURENAME						VARCHAR(256),
	FORENAME						VARCHAR(256),
	BIRTHYEAR						INTEGER,
    SHIRTSIZE						INTEGER,
    RELAYNAME						VARCHAR(256),
    POSITION						INTEGER,
    NATIONALITY						VARCHAR(256),
	EMAIL							VARCHAR(256),
	COMMENT							VARCHAR(1024)
);
Comment on Table PERSON is 'realyD - The table of Person.';
Comment on Column PERSON.ID is 'UUID as string representation.';
Comment on Column PERSON.SURENAME is 'The Surename of a Person.';
Comment on Column PERSON.FORENAME is 'The Forename of a Person.';
Comment on Column PERSON.BIRTHYEAR is 'The Birthyear of a Person.';
Comment on Column PERSON.SHIRTSIZE is 'TODO - Is this not a ref to a table?';
Comment on Column PERSON.RELAYNAME is 'TODO - A workaround-should be an own entity.';
Comment on Column PERSON.POSITION is 'TODO - A workaround-should be an own entity.';
Comment on Column PERSON.NATIONALITY is 'The nationality of a Person.';
Comment on Column PERSON.EMAIL is 'The Email of a Person.';
Comment on Column PERSON.COMMENT is 'A comment for a Person.';

--------------------------------------------------
-- Create Index PERSON_IDX
--------------------------------------------------
CREATE UNIQUE INDEX PERSON_IDX
        ON PERSON
        (ID);

--------------------------------------------------
-- Create Primary Key
--------------------------------------------------
Alter table PERSON add primary key (ID);

--------------------------------------------------
-- Grant PERSON
--------------------------------------------------
Grant ALL on PERSON to PUBLIC;
