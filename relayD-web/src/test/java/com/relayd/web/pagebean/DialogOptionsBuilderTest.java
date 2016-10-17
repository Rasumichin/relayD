package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Testen ist wie Rudern gegen den Strom. Hört man damit auf, treibt man zurück.
 *  - Laotse
 *
 * @author schmollc (Christian@relayd.de)
 * @since 21.09.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DialogOptionsBuilderTest {
	private DialogOptionsBuilder sut = new DialogOptionsBuilder();

	@Test
	public void testBuild_ForStandarParameter() {
		Map<String, Object> result = sut.build();

		int size = result.size();
		assertEquals("[optionMap] size not correct!", 6, size);

		assertNotNull("[modal] does not exist in map!", result.get("modal"));
		Boolean modal = (Boolean) result.get("modal");
		assertTrue("[modal] not correct!", modal);

		assertNotNull("[width] does not exist in map!", result.get("width"));
		Integer width = (Integer) result.get("width");
		assertEquals("[width] not correct!", 360, width.intValue());

		assertNotNull("[height] does not exist in map!", result.get("height"));
		Integer height = (Integer) result.get("height");
		assertEquals("[height] not correct!", 520, height.intValue());

		assertNotNull("[contentWidth] does not exist in map!", result.get("contentWidth"));
		String contentWidth = (String) result.get("contentWidth");
		assertEquals("[contentWidth] not correct!", "100%", contentWidth);

		assertNotNull("[contentHeight] does not exist in map!", result.get("contentHeight"));
		String contentHeight = (String) result.get("contentHeight");
		assertEquals("[contentHeight] not correct!", "100%", contentHeight);

		assertNotNull("[headerElement] does not exist in map!", result.get("headerElement"));
		String headerElement = (String) result.get("headerElement");
		assertEquals("[headerElement] not correct!", "customheader", headerElement);
	}

	@Test
	public void testBuild_ForWidth() {
		int expected = 200;
		Map<String, Object> result = sut.width(expected).build();

		assertNotNull("[width] does not exist in map!", result.get("width"));
		Integer width = (Integer) result.get("width");
		assertEquals("[width] not correct!", expected, width.intValue());
	}

	@Test
	public void testBuild_ForHeight() {
		int expected = 400;
		Map<String, Object> result = sut.height(expected).build();

		assertNotNull("[height] does not exist in map!", result.get("height"));
		Integer height = (Integer) result.get("height");
		assertEquals("[height] not correct!", expected, height.intValue());
	}
}