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
	relayname						VARCHAR(256),
	participantOne					CHAR(36),
	participantTwo					CHAR(36),
	participantThree				CHAR(36),
	participantFour					CHAR(36)
);
COMMENT on TABLE relay 						is 'realy - The table of relay.'; -- Gehen alle nicht!
COMMENT on COLUMN relay.id 					is 'UUID as string representation.';
COMMENT on COLUMN relay.eventId 			is 'UUID from the Event string representation.';
COMMENT on COLUMN relay.relayname			is 'The name of the relay.';
COMMENT on COLUMN relay.participantOne		is 'The first participant of the relay.';
COMMENT on COLUMN relay.participantTwo		is 'The second participant of the relay.';
COMMENT on COLUMN relay.participantThree	is 'The third participant of the relay.';
COMMENT on COLUMN relay.participantFour		is 'The fourth participant of the relay.';


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

-- Alternative edition of table 'relay'
-- ------------------------------------------------
-- Drop Table relay2
-- ------------------------------------------------
DROP TABLE relay2;

-- ------------------------------------------------
-- Create Table relay2
-- ------------------------------------------------
CREATE TABLE relay2 (
	id								CHAR(36)		NOT NULL	COMMENT 'UUID as string representation.',
	eventId							CHAR(36) 		NOT NULL	COMMENT 'UUID from the Event string representation.',
	relayname						VARCHAR(256)	NOT NULL	COMMENT 'The name of the relay.',
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
