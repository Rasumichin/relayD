package com.relayd;

/**
 * @author  schmollc (Christian@relayd.com)
 * @author           (Dirk@relayd.com)
 * @since   01.12.2016
 *
 */
public enum VersionName {
	//@formatter:off
	UNKNOWN("UNKNOWN","Unknown Codename"),
	AUGUSTINER("1.0", "Augustiner"),
	BITBURGER("1.1", "Bitburger"),
	COELLNER("1.2", "Cöllner Hofbräu Früh"),
	DUCKSTEIN("1.3", "Duckstein");
	//@formatter:on

	private String version;
	private String codename;

	private VersionName(String aVersion, String aCodename) {
		version = aVersion;
		codename = aCodename;
	}

	public String getVersion() {
		return version;
	}

	public String getCodename() {
		return codename;
	}

	public static VersionName newInstance(String aVersion) {
		for (VersionName versionName : values()) {
			if (versionName.getVersion().equals(aVersion)) {
				return versionName;
			}
		}
		return UNKNOWN;
	}
}