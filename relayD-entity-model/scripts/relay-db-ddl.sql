-- Alternative edition of table 'relay'
-- ------------------------------------------------
-- Drop Table relay
-- ------------------------------------------------
DROP TABLE relay;

-- ------------------------------------------------
-- Create Table relay
-- ------------------------------------------------
CREATE TABLE relay (
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
-- Create Index relay_IDX
-- ------------------------------------------------
CREATE UNIQUE INDEX relay_idx
        ON relay
        (id);

-- ------------------------------------------------
-- Create Primary Key
-- ------------------------------------------------
ALTER TABLE relay ADD PRIMARY KEY (id);

-- ------------------------------------------------
-- Create Index relay_IDX_EVENT_RELAY
-- ------------------------------------------------
-- CREATE UNIQUE INDEX relay_IDX_EVENT_RELAY
--         ON relay
--         (eventId, relayname);
-- Index cannot be created since the combination of both columns exceeds the maximum index bytes length.

-- ------------------------------------------------
-- Grant relay
-- ------------------------------------------------
-- Does not work, probably due to unsufficient rights: GRANT ALL ON relay TO PUBLIC;
