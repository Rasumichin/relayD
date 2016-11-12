package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import com.relayd.Settings;
import com.relayd.web.theme.Theme;
import com.relayd.web.theme.ThemeService;

import static org.mockito.Mockito.*;

/**
 * The unit tests are documents.
 * They describe the lowest-level design of the system.
 *  - Robert C. Martin
 *
 * @author schmollc (Christian@relayd.de)
 * @since 08.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SettingsPageBeanTest {

	private SettingsPageBean sut = new SettingsPageBean();

	@Test
	public void testIsSerializable() {
		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testService() {
		ThemeService actual = sut.service;
		assertNull("[Theme] should be not valid!", actual);

		ThemeService expected = new ThemeService();
		sut.setService(expected);

		ThemeService actualAfterSetter = sut.service;

		assertEquals("[Theme] not correct!", expected, actualAfterSetter);
	}

	@Test
	public void testInit() {
		ThemeService serviceMock = Mockito.mock(ThemeService.class);
		sut.service = serviceMock;

		sut.init();

		verify(serviceMock).getThemes();
	}

	@Test
	public void testGetThemes() {
		ThemeService service = new ThemeService();
		service.init();
		sut.service = service;
		sut.init();

		List<Theme> actual = sut.getThemes();
		List<Theme> expected = service.getThemes();

		assertEquals("[themes] not correct!", expected, actual);
	}

	@Test
	public void getVersion() {
		Settings settingsMock = Mockito.mock(Settings.class);
		sut.settings = settingsMock;
		String expected = "Version 0.0";
		Mockito.doReturn(expected).when(settingsMock).getVersion();

		String actual = sut.getVersion();

		Mockito.verify(settingsMock).getVersion();
		assertEquals("[version] not correct!", expected, actual);

	}
}