package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Position;
import com.relayd.attributes.Shirtsize;
import com.relayd.ejb.GatewayType;

/**
 * Testen ist wie Rudern gegen den Strom. Hört man damit auf, treibt man zurück.
 *  - Laotse
 *
 * @author schmollc (Christian@relayd.de)
 * @since 01.09.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SelectItemBeanTest {
	private SelectItemBean sut = new SelectItemBean();

	@Test
	public void testGetGatewayTypes() {
		List<GatewayType> actual = sut.getGatewayTypes();

		assertNotNull("[actual] not correct!", actual);

		int size = actual.size();

		assertEquals("[actual] size not correct!", 3, size);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testModifyGatewayTypes() {
		sut.getGatewayTypes().add(GatewayType.FILE);
	}

	@Test
	public void testGetShirtsizes() {
		List<Shirtsize> actual = sut.getShirtsizes();

		assertNotNull("[actual] not correct!", actual);

		int size = actual.size();

		assertEquals("[actual] size not correct!", 11, size);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testModifyShirtsizes() {
		sut.getShirtsizes().add(Shirtsize.DamenL);
	}

	@Test
	public void testGetPositions() {
		List<Position> actual = sut.getPositions();

		assertNotNull("[actual] not correct!", actual);

		int size = actual.size();

		assertEquals("[actual] size not correct!", 5, size);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testModifyPositions() {
		sut.getPositions().add(Position.FIRST);
	}

	@Test
	public void testGetEventYears() {
		List<EventYear> actual = sut.getEventYears();

		assertNotNull("[actual] not correct!", actual);

		int size = actual.size();

		assertEquals("[actual] size not correct!", 3, size);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testModifyEventYears() {
		sut.getEventYears().add(EventYear.YEAR_2016);
	}
}