-- Entity relay_event
insert into relay_event (id, eventName, eventDay) values ("5697d710-8967-4b2d-9ab2-8fc50ddc6138", "Metro Group Marathon Düsseldorf", "2017-04-30");

-- Entity person
insert into person(id, surename, forename, yearOfBirth, shirtsize, relayname, pos, email, info) values("89d7134b-2326-4f52-7bd7-901e71723f31", "Jonas", "Justus", 1971, 1, "Die 4 ????", 1, "Justus.Jonas@RockyBeach.com", null);
insert into person(id, surename, forename, yearOfBirth, shirtsize, relayname, pos, email, info) values("90d71de4-bc13-4f52-7bd7-901b71723c22", "Peter", "Shaw",   1972, 1, "Die 4 ????", 1, "Peter.Shaw@RockyBeach.com", null);

-- Entity relay
insert into relay(id, relayname) values("132f23d0-8ac7-422d-9132-8abb35261778", "Die 4 ????");
