--------------------------------------------------
-- Drop Table person
--------------------------------------------------
DROP TABLE relay_event;

--------------------------------------------------
-- Create Table relay_event
--------------------------------------------------
CREATE TABLE relay_event(
  id 			CHAR(36) 		NOT NULL,
  eventname 	VARCHAR(256)	NOT NULL,
  eventDay 		DATE 			NOT NULL
);

--------------------------------------------------
-- Create Index relay_event_idx
--------------------------------------------------
CREATE UNIQUE INDEX relay_event_idx
        ON relay_event
        (id);

--------------------------------------------------
-- Create Primary Key
--------------------------------------------------
ALTER TABLE relay_event ADD PRIMARY KEY (id);

--------------------------------------------------
-- Grant person
--------------------------------------------------
GRANT ALL ON relay_event TO PUBLIC; -- geht nicht!

