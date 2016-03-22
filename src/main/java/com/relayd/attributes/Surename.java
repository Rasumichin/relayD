package com.relayd.attributes;

import java.io.Serializable;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   22.03.2016
 * status   initial
 */
public class Surename implements Serializable {
	private static final long serialVersionUID = 1L;

	private String value = "";

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	static public Surename newInstance(String aSurename) {
		validate(aSurename);
		return new Surename(aSurename);

	}

	private Surename(String aSurename) {
		super();
		value = aSurename;
	}

	private static void validate(String aSurename) {
		if (aSurename == null) {
			throw new IllegalArgumentException("Darf nicht null sein");
		}
		// TODO -schmollc- Hier würde dann ggf Logik rein die dem Objekt Nachname entspricht.....
		//		if (aNachname.length() == 2) {
		//			throw new ArrayIndexOutOfBoundsException("Ungültige Länge.");
		//		}
		//		if (aNachname.matches("[0-9]")) {
		//			throw new IllegalArgumentException("Ungültige Zeichen.");
		//		}
	}

	@Override
	public String toString() {
		return value;
	}
}