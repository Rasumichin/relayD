package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;
import com.relayd.attributes.Relayname;
import com.relayd.web.bridge.RelayBridge;

import static org.mockito.Mockito.*;

/**
 * Good programmers write code for humans first and computers next.
 * - Anonymous
 *
 * @author schmollc (Christian@relayd.de)
 * @since 14.10.2016
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEditPageBeanTest {
	@Spy
	@InjectMocks
	private RelayEditPageBean sut;

	@Mock
	private RelayBridge relayBridge;

	@Before
	public void setUp() {
		doNothing().when(sut).openDialog();
		doNothing().when(sut).closeDialog();
	}

	@Test
	public void testIsSerializable() {
		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Klasse nicht Serializable!", condition);
	}

	@Test
	public void testOpenDialogForCreateRelay() {
		RelayEvent metroMarathon = RelayEvent.newInstance(Eventname.newInstance("Metro Marathon"), EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30)));

		sut.openDialogForCreateRelay(metroMarathon);

		verify(sut).createNewRelay(metroMarathon);
		verify(sut).openDialog();
	}

	@Test
	public void testCreateNewRelay() {
		RelayEvent metroMarathon = RelayEvent.newInstance(Eventname.newInstance("Metro Marathon"), EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30)));

		Relay actual = sut.createNewRelay(metroMarathon);
		assertNotNull("Relay is not initialized.", actual);
		assertEquals("[getRelayEvent] not correct!", metroMarathon, actual.getRelayEvent());
	}

	@Test
	public void testOpenDialogFor() {
		UUID uuid = UUID.fromString("12345-42-32-23-32");
		doReturn(Relay.newInstance()).when(sut).getRelay(uuid);

		assertNull("[workingRelay] not corret!", sut.workingRelay);

		sut.openDialogFor(uuid);

		assertNotNull("[workingRelay] not corret!", sut.workingRelay);
		verify(sut).getRelay(uuid);
		verify(sut).openDialog();
	}

	@Test
	public void testSave() {

		sut.save();

		verify(sut).persistRelay();
		verify(sut).closeDialog();

	}

	@Test
	public void testGetName() {
		Relay relay = Relay.newInstance();
		Relayname expected = Relayname.newInstance("Die 4 ????");
		relay.setRelayname(expected);
		sut.workingRelay = relay;

		Relayname actual = sut.getRelayname();

		assertEquals("[getRelayname] not correct!", expected, actual);
	}

	@Test
	public void testDuration() {
		Duration expected = Duration.ZERO;
		Relay relayMock = Mockito.mock(Relay.class);
		doReturn(expected).when(relayMock).getDuration();
		sut.workingRelay = relayMock;

		sut.setDuration(expected);
		verify(relayMock).setDuration(expected);

		Duration actual = sut.getDuration();
		verify(relayMock).getDuration();
		assertEquals("[duration] not correct!", expected, actual);
	}
}