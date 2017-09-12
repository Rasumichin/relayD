-- ------------------------------------------------
-- Drop Table participant
-- ------------------------------------------------
DROP TABLE participant;

-- ------------------------------------------------
-- Create Table participant
-- ------------------------------------------------
CREATE TABLE participant (
	id								CHAR(36)		NOT NULL			COMMENT 'UUID as string representation.',
	personId						CHAR(36)		NOT NULL			COMMENT 'UUID of the referenced person, string representation.',
	relayEventId		 			CHAR(36) 		NOT NULL			COMMENT 'UUID of the referenced relayEvent, string representation.',
	info							VARCHAR(1024) 						COMMENT on COLUMN participant.info is 'A comment for a Participant.' -- COMMENT geht nicht
	CONSTRAINT fk_participant_person
		FOREIGN KEY (personId) REFERENCES person (id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT,
	CONSTRAINT fk_participant_relay
		FOREIGN KEY (relayEventId) REFERENCES relay_Event (id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
) COMMENT 'relayD - The table for the relay participant entities.';


-- ------------------------------------------------
-- Create Index participant_IDX
-- ------------------------------------------------
CREATE UNIQUE INDEX participant_idx
        ON participant
        (id);

-- ------------------------------------------------
-- Create Primary Key
-- ------------------------------------------------
ALTER TABLE participant ADD PRIMARY KEY (id);

-- ------------------------------------------------
-- Grant participant
-- ------------------------------------------------
-- geht nicht, fehlende Rechte offenbar: GRANT ALL ON participant TO PUBLIC;
