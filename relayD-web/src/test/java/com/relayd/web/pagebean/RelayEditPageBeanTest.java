package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.Relay;
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
	public void testOpenDialogForCreateRelay() {
		sut.openDialogForCreateRelay();

		verify(sut).prepareNewRelay();
		verify(sut).openDialog();
	}

	@Test
	public void testCreateNewRelay() {
		Relay actual = sut.createNewRelay();
		assertNotNull("Relay is not initialized.", actual);
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
}