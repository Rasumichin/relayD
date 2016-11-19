--------------------------------------------------
-- Drop Table relay
--------------------------------------------------
DROP TABLE relay;

--------------------------------------------------
-- Create Table relay
--------------------------------------------------
CREATE TABLE relay (
	id								CHAR(36)		NOT NULL,
	eventId							CHAR(36),
	relayname						VARCHAR(256)
);
COMMENT on TABLE relay 				is 'realy - The table of relay.'; -- Gehen alle nicht!
COMMENT on COLUMN relay.id 			is 'UUID as string representation.';
COMMENT on COLUMN relay.eventId 	is 'UUID from the Event string representation.';
COMMENT on COLUMN relay.relayname	is 'The name of the relay.';

--------------------------------------------------
-- Create Index relay_IDX
--------------------------------------------------
CREATE UNIQUE INDEX relay_idx
        ON relay
        (id);

--------------------------------------------------
-- Create Primary Key
--------------------------------------------------
ALTER TABLE relay ADD PRIMARY KEY (id);

--------------------------------------------------
-- Grant relay
--------------------------------------------------
GRANT ALL ON relay TO PUBLIC; -- geht nicht!
