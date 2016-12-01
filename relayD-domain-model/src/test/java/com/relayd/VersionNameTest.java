package com.relayd;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 */
public class VersionNameTest {

	@Test
	public void testGetVersionNameForNumber_Bitburger() {
		VersionName versionName = VersionName.getVersionNameForNumber(VersionName.BITBURGER.getVersion());
		assertSame("Not Bitburger enum", VersionName.BITBURGER, versionName);
		assertEquals("", "Bitburger", versionName.getCodename());
	}

	@Test
	public void testGetVersionNameForNumber_Unknown() {
		VersionName versionName = VersionName.getVersionNameForNumber("Dummy");
		assertSame(VersionName.UNKNOWN, versionName);
		assertEquals("Not Unknown", "Unknown Codename", versionName.getCodename());
	}

}
