package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.web.bridge.RelayEventBridge;

import static org.mockito.Mockito.*;

/**
 * Betrachte das, was du tust, als die wichtigste Sache der Welt.
 * Sei dir aber bewusst, dass es andere nicht für die wichtigste Sache der Welt halten.
 *  - Bruce Springsteen
 *
 * @author  schmollc (Christian@relayd.com)
 * @since   13.09.2018
 *
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DockPageBeanTest {

	@InjectMocks
	private DockPageBean sut = new DockPageBean();

	@Mock
	private RelayEventBridge relayEventBridge;

	@Test
	public void testNoEventExist_ForRelayEventDoesNotExist() {

		boolean condition = sut.noEventExist();

		assertTrue("[noEventExist] was not correct!", condition);
	}

	@Test
	public void testNoEventExist_ForRelayEventExist() {
		List<RelayEventDisplay> someRelayEventDisplays = new ArrayList<>();
		someRelayEventDisplays.add(RelayEventDisplay.newInstance(UUID.randomUUID(), "Dummy Event"));

		doReturn(someRelayEventDisplays).when(relayEventBridge).allDisplays();

		boolean condition = sut.noEventExist();

		assertFalse("[noEventExist] was not correct!", condition);
	}

}
