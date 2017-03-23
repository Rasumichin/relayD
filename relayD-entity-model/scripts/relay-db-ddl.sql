-- Alternative edition of table 'relay'
-- ------------------------------------------------
-- Drop Table relay2
-- ------------------------------------------------
DROP TABLE relay2;

-- ------------------------------------------------
-- Create Table relay2
-- ------------------------------------------------
CREATE TABLE relay2 (
	id								CHAR(36)		NOT NULL			COMMENT 'UUID as string representation.',
	eventId							CHAR(36) 		NOT NULL			COMMENT 'UUID from the Event string representation.',
	relayname						VARCHAR(256)	NOT NULL			COMMENT 'The name of the relay.',
	duration						BIGINT			NOT NULL DEFAULT 0 	COMMENT 'The duration for the run in milliseconds',
	CONSTRAINT fk_relay_relay_event
		FOREIGN KEY (eventId) REFERENCES relay_event (id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
) COMMENT 'relayD - The table for the relay entities.';


-- ------------------------------------------------
-- Create Index relay2_IDX
-- ------------------------------------------------
CREATE UNIQUE INDEX relay2_idx
        ON relay2
        (id);

-- ------------------------------------------------
-- Create Primary Key
-- ------------------------------------------------
ALTER TABLE relay2 ADD PRIMARY KEY (id);

-- ------------------------------------------------
-- Create Index relay2_IDX_EVENT_RELAY
-- ------------------------------------------------
-- CREATE UNIQUE INDEX relay2_IDX_EVENT_RELAY
--         ON relay2
--         (eventId, relayname);
-- Index cannot be created since the combination of both columns exceeds the maximum index bytes length.

-- ------------------------------------------------
-- Grant relay
-- ------------------------------------------------
-- Does not work, probably due to unsufficient rights: GRANT ALL ON relay2 TO PUBLIC;
