package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.RelayEvent;
import com.relayd.web.pagebean.event.RelayEventEditPageBean;

/**
 * Natürlich kostet Qualität, aber fehlende Qualität kostet mehr.
 *  - Prof. Dr. Hans-Jürgen Quadbeck-Seeger
 *
 * @author schmollc
 * @since 08.02.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEventBrowsePageBeanTest {
	private RelayEventBrowsePageBean sut = new RelayEventBrowsePageBean();

	@Test
	public void testSelectedRelayEvent() {
		RelayEvent expected = RelayEvent.duesseldorf();

		sut.setSelectedRelayEvent(expected);

		RelayEvent actual = sut.getSelectedRelayEvent();

		assertEquals("[selectedRelayEvent] nicht korrekt!", expected, actual);
	}

	@Test
	public void testRelayEventEditPageBean() {
		RelayEventEditPageBean expected = new RelayEventEditPageBean();

		sut.setRelayEventEditPageBean(expected);

		RelayEventEditPageBean actual = sut.getRelayEventEditPageBean();

		assertEquals("[relayEventEditPageBean] nicht korrekt!", expected, actual);
	}
}
