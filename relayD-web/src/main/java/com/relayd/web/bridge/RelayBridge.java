package com.relayd.web.bridge;

import java.util.List;
import java.util.UUID;

import com.relayd.Person;
import com.relayd.Relay;

public interface RelayBridge {

	public List<RelayRow> all();

	public void update(Relay relay);

	public void create(Relay relay);

	public Relay get(UUID uuid);

	public void remove(Relay relay);

	public void remove(Person person);

	public void add(Person person);
}