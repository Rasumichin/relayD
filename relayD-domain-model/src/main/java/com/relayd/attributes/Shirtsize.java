package com.relayd.attributes;

/**
 * @author  schmollc (Christian@relayd.com)
 * @since   23.03.2016
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
	UNKNOWN		(-1, "Unknown"),
	DamenXS		( 1, "Damen XS"),
	DamenS		( 2, "Damen S"),
	DamenM		( 3, "Damen M"),
	DamenL		( 4, "Damen L"),
	DamenXL		( 5, "Damen XL"),
	HerrenS		( 6, "Herren S"),
	HerrenM		( 7, "Herren M"),
	HerrenL		( 8, "Herren L"),
	HerrenXL	( 9, "Herren XL"),
	HerrenXXL	(10, "Herren XXL");
	//@formatter:off

	private final Integer size;
	private final String description;

	Shirtsize(Integer aSize, String aDescription) {
		size = aSize;
		description = aDescription;
	}

	public Integer getSize() {
		return size;
	}

	public String getDescription() {
		return description;
	}

	public static Integer encode(Shirtsize param) {
		return param.getSize();
	}

	public static Shirtsize newInstance(Integer param) {
		for (Shirtsize shirtsize : values()) {
			if (shirtsize.getSize().equals(param)) {
				return shirtsize;
			}
		}
		return UNKNOWN;
	}

	public boolean isEmpty() {
		return this == UNKNOWN;
	}

	@Override
	public String toString() {
		return getDescription();
	}
}