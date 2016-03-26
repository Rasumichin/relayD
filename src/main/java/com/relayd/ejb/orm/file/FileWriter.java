package com.relayd.ejb.orm.file;

import java.util.List;

import com.relayd.Person;

/**
 * Beispielhaftes lesen/schreiben auf eine Datei.
 * (Achtung, klappt nicht sauber da das Exception Handling an diesem Beispiel auﬂer acht gelassen wurde)
 *
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   26.03.2016
 * status   initial
 */
public class FileWriter {
	private static final String FILE_NAME = "Persons.relayD";

	public static List<Person> get() {
		//		FileInputStream fileInputStream;
		//		fileInputStream = new FileInputStream(FILE_NAME);
		//		List<Person> somePersons = (ArrayList<Person>) SerializationUtils.deserialize(fileInputStream);
		//		fileInputStream.close();
		// 		return somePersons;
		return null;
	}

	public static void set(Person aPerson) {
		//		List<Person> somePersons = get();
		//		somePersons.add(aPerson);
		//		FileOutputStream fileOutputStream;
		//		fileOutputStream = new FileOutputStream(FILE_NAME);
		//		SerializationUtils.serialize((Serializable) somePersons, fileOutputStream);
		//		fileOutputStream.close();
	}
}