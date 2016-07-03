package com.relayd.ejb;

import java.util.List;

import com.relayd.Relay;

public interface RelayGateway {

	void set(Relay relay);

	List<Relay> getAll();

}
