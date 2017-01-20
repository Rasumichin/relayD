package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Locale;

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
		List<GatewayType> result = sut.getGatewayTypes();

		assertNotNull("[result] not correct!", result);

		int size = result.size();

		assertEquals("[result] size not correct!", 3, size);
	}

	@Test
	public void testGetShirtsizes() {
		List<Shirtsize> result = sut.getShirtsizes();

		assertNotNull("[result] not correct!", result);

		int size = result.size();

		assertEquals("[result] size not correct!", 11, size);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testModifyShirtsizes() {
		sut.getShirtsizes().add(Shirtsize.DamenL);
	}

	@Test
	public void testGetPositions() {
		List<Position> result = sut.getPositions();

		assertNotNull("[result] not correct!", result);

		int size = result.size();

		assertEquals("[result] size not correct!", 5, size);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testPositionsModify() {
		sut.getPositions().add(Position.FIRST);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testModifyNationalities() {
		sut.getNationalities().add(Locale.CANADA);
	}

}