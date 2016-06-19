DROP TABLE
  RELAY_EVENT;
CREATE TABLE RELAY_EVENT(
  id CHAR(36) NOT NULL PRIMARY KEY,
  eventName CHAR(64) NOT NULL,
  eventDay DATE NOT NULL,
  lastUpdate Timestamp not null,
  updateUser CHAR(32)
);