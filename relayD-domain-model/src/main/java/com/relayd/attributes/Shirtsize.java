package com.relayd.attributes;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   23.03.2016
 * status   initial<br/>
 *
 * Babysteps. Aktuelle Abbildung des Excelsheets. <br/>
 * Es sollte natuerlich nur die Groesse geben und das Geschlecht befindet sich in
 * der Person Klasse so das Geschlecht+Groesse die "wirkliche" Shirtsize ergibt.
 * <br/>
 * Nachtrag: Ist das denn wirklich so? Tragen nicht auch einige Herren DamenXL?
 * Und ist es nicht bei Mode so das eine Größe M für Herren anders geschnitten ist als
 * eine Größe M für Damen, also das Geschlecht eine Ausprägung der Größe ist?
 */
public enum Shirtsize {
	//@formatter:off
	Unknown		((short)-1, "Unknown"),
	DamenXS		((short) 1, "Damen XS"),
	DamenS		((short) 2, "Damen S"),
	DamenM		((short) 3, "Damen M"),
	DamenL		((short) 4, "Damen L"),
	DamenXL		((short) 5, "Damen XL"),
	HerrenS		((short) 6, "Herren S"),
	HerrenM		((short) 7, "Herren M"),
	HerrenL		((short) 8, "Herren L"),
	HerrenXL	((short) 9, "Herren XL"),
	HerrenXXL	((short)10, "Herren XXL");
	//@formatter:off

	private final Short size;
	private final String description;

	Shirtsize(Short aSize, String aDescription) {
		size = aSize;
		description = aDescription;
	}

	public Short getSize() {
		return size;
	}

	public String getDescription() {
		return description;
	}

	public static Short encode(Shirtsize param) {
		for (Shirtsize shirtsize : values()) {
			if (shirtsize.equals(param)) {
				return shirtsize.getSize();
			}
		}
		return param.getSize();
	}

	public static Shirtsize decode(Short param) {
		for (Shirtsize shirtsize : values()) {
			if (shirtsize.getSize().equals(param)) {
				return shirtsize;
			}
		}
		return Unknown;
	}
}