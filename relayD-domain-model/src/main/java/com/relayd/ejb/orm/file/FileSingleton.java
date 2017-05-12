package com.relayd.ejb.orm.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

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
			put(bigData);
		}
	}

	void clear() {
		put(BigData.newInstance());
	}

	private void put(BigData aBigData) {
		try {
			FileOutputStream fileOutputStream;
			fileOutputStream = new FileOutputStream(getFileName());
			SerializationUtils.serialize(aBigData, fileOutputStream);
			fileOutputStream.close();
		} catch (IOException e) {
			throw new RuntimeException("Error - IOException ", e);
		}

	}

	public void put(List<RelayEvent> someRelayEvents) {
		BigData bigData = getBigData();
		bigData.setRelayEvents(someRelayEvents);
		put(bigData);
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

	public List<RelayEvent> get() {
		List<RelayEvent> eventsAsList = new ArrayList<RelayEvent>();
		eventsAsList.addAll(getBigData().getRelayEvents());
		return eventsAsList;
	}
}
