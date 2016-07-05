package com.relayd.web.bridge;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Surename;
import com.relayd.ejb.RelayGateway;

@RunWith(MockitoJUnitRunner.class)
public class RelayBridgeImplTest {
	@InjectMocks
	private RelayBridgeImpl sut = new RelayBridgeImpl();

	@Mock
	private RelayGateway gateway;

	@Test
	public void testAll() {
		Mockito.doReturn(createResult()).when(gateway).getAll();

		List<RelayRow> all = sut.all();

		assertNotNull("Expected valid instance.", all);
		assertEquals(2, all.size());
	}

	private List<Relay> createResult() {
		List<Relay> relays = new ArrayList<Relay>();
		Relay relay = Relay.newInstance();
		relay.setRelayname(Relayname.newInstance("Staubwolke"));

		Person first = Person.newInstance();
		first.setForename(Forename.newInstance("Justus"));
		first.setSurename(Surename.newInstance("Jonas"));
		relay.add(first);

		relays.add(relay);
		return relays;
	}
}