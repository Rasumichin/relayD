package com.relayd.web.pagebean.event;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * Die Sorge, was andere wohl von einem denken, verfliegt, wenn man merkt, wie selten sie an einen denken,
 *  - David Foster Wallace
 *
 * @author schmollc
 * @since 08.02.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class RelayEventEditPageBeanTest {

	@Spy
	private RelayEventEditPageBean sut;

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
	public void testOpenDialogForCreatePerson() {
		sut.openDialogForCreateRelayEvent();

		verify(sut).prepareNewRelayEvent();
		verify(sut).openDialog();
	}

	@Test
	public void testPrepareNewRelayEvent() {
		sut.prepareNewRelayEvent();

		assertNotNull("[workingRelayEvent] has not been created.", sut.workingRelayEvent);
	}
}