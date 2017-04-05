package com.relayd;

/**
 * @author  schmollc (Christian@relayd.com)
 * @author           (Dirk@relayd.com)
 * @since   01.12.2016
 *
 */
public enum Version {
	//@formatter:off
	UNKNOWN		("UNKNOWN", "Unknown Description"),
	AUGUSTINER	("1.0", 	"Augustiner"),
	BITBURGER	("1.1", 	"Bitburger"),
	COELLNER	("1.2", 	"Cöllner Hofbräu Früh"),
	DUCKSTEIN	("1.3", 	"Duckstein"),
	ENGEL		("1.4", 	"Engel"),
	F			("1.5", 	"Frisches Augustiner"),
	G			("1.6", 	"Geiles Augustiner"),
	H			("1.7", 	"Helles Augustiner"),
	I			("1.8", 	"Intrinsisches Augustiner");

	//@formatter:on

	private String value;
	private String description;

	private Version(String aVersion, String aCodename) {
		value = aVersion;
		description = aCodename;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public static Version newInstance(String aVersion) {
		for (Version versionName : values()) {
			if (versionName.getValue().equals(aVersion)) {
				return versionName;
			}
		}
		return UNKNOWN;
	}
}