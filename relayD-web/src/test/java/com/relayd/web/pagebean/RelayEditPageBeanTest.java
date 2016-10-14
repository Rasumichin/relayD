package com.relayd.web.pagebean;

import static org.junit.Assert.*;

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
	public void testSave() {

		sut.save();

		verify(sut).persistRelay();
		verify(sut).closeDialog();

	}
}