DROP TABLE
  RELAY_EVENT;
CREATE TABLE RELAY_EVENT(
  id CHAR(36) NOT NULL PRIMARY KEY,
  title CHAR(64) NOT NULL,
  yearHappened INTEGER,
  lastUpdate Timestamp not null,
  updateUser CHAR(32)
);