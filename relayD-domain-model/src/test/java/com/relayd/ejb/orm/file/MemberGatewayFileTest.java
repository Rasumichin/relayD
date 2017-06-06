package com.relayd.ejb.orm.file;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;
import com.relayd.ejb.MemberGateway;
import com.relayd.ejb.MemberGatewayTest;

/**
 * There is nothing in the programming field more despicable than an undocumented program.
 *  - Edward Yourdon
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   04.06.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberGatewayFileTest extends MemberGatewayTest {

	private MemberGatewayFile sut = new MemberGatewayFile("bigDataTest.relayD");

	@Before
	public void setUp() {
		RelayEvent metroMarathon = RelayEvent.newInstance(Eventname.newInstance("Metro Marathon"), EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30)));
		Relay relay = Relay.newInstance(metroMarathon);
		metroMarathon.addRelay(relay);
		FileSingleton.getInstance().clear();
		List<RelayEvent> someRelayEvents = new ArrayList<>();
		someRelayEvents.add(metroMarathon);

		FileSingleton.getInstance().setRelayEvents(someRelayEvents);
	}

	@Override
	public MemberGateway getSut() {
		return sut;
	}
}