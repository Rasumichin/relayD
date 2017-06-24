package com.relayd.web.api.bridge;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.*;

import com.relayd.RelayEvent;
import com.relayd.client.jaxb.RelayEventDTO;
import com.relayd.ejb.RelayEventGateway;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  24.06.2017
 *
 */
public class RelayEventDTOBridgeImplTest {
	RelayEventDTOBridgeImpl sut;
	RelayEventGateway gatewayMock = mock(RelayEventGateway.class);

	@Before
	public void setUp() throws Exception {
		sut = (RelayEventDTOBridgeImpl) RelayEventDTOBridgeImpl.newInstance(gatewayMock);
	}

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_with_null() {
		RelayEventDTOBridgeImpl.newInstance(null);
	}
	
	@Test
	public void testGetGateway() {
		RelayEventGateway result = sut.getRelayEventGateway();
		
		assertNotNull("[gateway] must not be 'null'!", result);
	}

	@Test
	public void testAll() {
		List<RelayEvent> relayEvents = new ArrayList<>();
		RelayEvent event = RelayEvent.newInstance();
		relayEvents.add(event);
		int expected = relayEvents.size();
		when(gatewayMock.getAll()).thenReturn(relayEvents);
		
		List<RelayEventDTO> result = sut.all();
		
		int actual = result.size();
		assertEquals("Result is not correct!", expected, actual);
	}
}
