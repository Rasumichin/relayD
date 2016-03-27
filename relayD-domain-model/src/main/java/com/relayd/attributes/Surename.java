package com.relayd.attributes;

import java.io.*;

/**
 * @author schmollc (Christian@cloud.franke-net.com)
 * @since 22.03.2016 status initial
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
		// TODO -schmollc- Hier waere dann ggf Logik rein die dem Objekt Nachname entspricht.....
        //		if (aNachname.length() == 2) {
        //			throw new ArrayIndexOutOfBoundsException("Ungueltige Laenge.");
        //		}
        //		if (aNachname.matches("[0-9]")) {
        //			throw new IllegalArgumentException("Ungueltige Zeichen.");
        //		}
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    /**
     *
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Surename other = (Surename) obj;
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }
}
