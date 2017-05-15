package com.relayd.ejb.orm.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

import com.relayd.Person;
import com.relayd.RelayEvent;

/**
 * https://de.wikibooks.org/wiki/Muster:_Java:_Singleton
 */
public class FileSingleton {

	private String fileName = "data.relayD";

	private static final class InstanceHolder {
		static final FileSingleton INSTANCE = new FileSingleton();
	}

	private FileSingleton() {
		initFile();
	}

	public static FileSingleton getInstance() {
		return InstanceHolder.INSTANCE;
	}

	public void setFileName(String aFileName) {
		fileName = aFileName;
	}

	private String getFileName() {
		return fileName;
	}

	private void initFile() {
		File file = new File(getFileName());
		if (!file.exists()) {
			BigData bigData = BigData.newInstance();
			List<RelayEvent> relayEvents = new ArrayList<>();
			bigData.setRelayEvents(relayEvents);
			set(bigData);
		}
	}

	void clear() {
		set(BigData.newInstance());
	}

	private void set(BigData aBigData) {
		try {
			FileOutputStream fileOutputStream;
			fileOutputStream = new FileOutputStream(getFileName());
			SerializationUtils.serialize(aBigData, fileOutputStream);
			fileOutputStream.close();
		} catch (IOException e) {
			throw new RuntimeException("Error - IOException ", e);
		}

	}

	public List<RelayEvent> getRelayEvents() {
		List<RelayEvent> eventsAsList = new ArrayList<RelayEvent>();
		eventsAsList.addAll(getBigData().getRelayEvents());
		return eventsAsList;
	}

	public void setRelayEvents(List<RelayEvent> someRelayEvents) {
		BigData bigData = getBigData();
		bigData.setRelayEvents(someRelayEvents);
		set(bigData);
	}

	public List<Person> getPersons() {
		List<Person> personsAsList = new ArrayList<Person>();
		personsAsList.addAll(getBigData().getPersons());
		return personsAsList;
	}

	public void setPersons(List<Person> somePersons) {
		BigData bigData = getBigData();
		bigData.setPersons(somePersons);
		set(bigData);
	}

	private BigData getBigData() {
		FileInputStream fileInputStream;
		BigData bigData;
		try {
			fileInputStream = new FileInputStream(getFileName());
			bigData = (BigData) SerializationUtils.deserialize(fileInputStream);
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error - FileNotFoundException", e);
		} catch (IOException e) {
			throw new RuntimeException("Error - IOException", e);
		}
		return bigData;
	}
}