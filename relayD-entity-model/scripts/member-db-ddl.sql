-- ------------------------------------------------
-- Drop Table member
-- ------------------------------------------------
DROP TABLE member;

-- ------------------------------------------------
-- Create Table member
-- ------------------------------------------------
CREATE TABLE member (
	id								CHAR(36)		NOT NULL			COMMENT 'UUID as string representation.',
	personId						CHAR(36)		NOT NULL			COMMENT 'UUID of the referenced person, string representation.',
	relayId				 			CHAR(36) 		NOT NULL			COMMENT 'UUID of the referenced relay, string representation.',
	relayPosition					INTEGER			NOT NULL			COMMENT 'The relay position the person is member of.',
	duration						BIGINT			NOT NULL DEFAULT 0 	COMMENT 'The duration for the run in milliseconds',
	CONSTRAINT fk_member_person
		FOREIGN KEY (personId) REFERENCES person (id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT,
	CONSTRAINT fk_member_relay
		FOREIGN KEY (relayId) REFERENCES relay (id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
) COMMENT 'relayD - The table for the relay member entities.';


-- ------------------------------------------------
-- Create Index member_IDX
-- ------------------------------------------------
CREATE UNIQUE INDEX member_idx
        ON member
        (id);

-- ------------------------------------------------
-- Create Primary Key
-- ------------------------------------------------
ALTER TABLE member ADD PRIMARY KEY (id);

-- ------------------------------------------------
-- Create Index person_relay_position_IDX
-- ------------------------------------------------
CREATE UNIQUE INDEX person_relay_position_IDX
        ON member
        (personId, relayId, relayPosition);

-- ------------------------------------------------
-- Grant member
-- ------------------------------------------------
-- geht nicht, fehlende Rechte offenbar: GRANT ALL ON member TO PUBLIC;
