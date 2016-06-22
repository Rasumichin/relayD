package com.relayd.ejb.orm.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.SerializationUtils;

import com.relayd.RelayEvent;
import com.relayd.ejb.RelayEventGateway;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 20.06.2016
 * status initial
 */
public class RelayEventGatewayFile implements RelayEventGateway {

	private String fileName = "relayEvent.relayD";

	public RelayEventGatewayFile() {
		initFile();
	}

	RelayEventGatewayFile(String aFileName) {
		setFileName(aFileName);
		initFile();
	}

	private void initFile() {
		File file = new File(getFileName());
		if (!file.exists()) {
			List<RelayEvent> relayEvents = new ArrayList<>();
			try {
				put(relayEvents);
			} catch (IOException e) {
				throw new RuntimeException("Error", e);
			}
		}
	}

	private String getFileName() {
		return fileName;
	}

	private void setFileName(String aFileName) {
		fileName = aFileName;
	}

	public void clean() {
		try {
			put(new ArrayList<>());
		} catch (IOException e) {
			throw new RuntimeException("Error", e);
		}
	}

	@Override
	public void set(RelayEvent aRelayEvent) {
		List<RelayEvent> someRelays = getAll();

		if (someRelays.contains(aRelayEvent)) {
			for (RelayEvent relayEvent : someRelays) {
				if (aRelayEvent.equals(relayEvent)) {
					relayEvent = aRelayEvent;
					break;
				}
			}
		} else {
			someRelays.add(aRelayEvent);
		}

		try {
			put(someRelays);
		} catch (IOException e) {
			throw new RuntimeException("Error - IOException ", e);
		}

	}

	@Override
	public List<RelayEvent> getAll() {
		FileInputStream fileInputStream;
		List<RelayEvent> relays = new ArrayList<>();
		try {
			fileInputStream = new FileInputStream(getFileName());
			relays = (ArrayList<RelayEvent>) SerializationUtils.deserialize(fileInputStream);
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error - FileNotFoundException", e);
		} catch (IOException e) {
			throw new RuntimeException("Error - IOException", e);
		}
		return relays;
	}

	@Override
	public RelayEvent get(UUID aUuid) {
		for (RelayEvent relayEvent : getAll()) {
			if (aUuid.equals(relayEvent.getUuid())) {
				return relayEvent;
			}
		}
		return null;
	}

	private void put(List<RelayEvent> someRelays) throws IOException {
		FileOutputStream fileOutputStream;
		fileOutputStream = new FileOutputStream(getFileName());
		SerializationUtils.serialize((Serializable) someRelays, fileOutputStream);
		fileOutputStream.close();
	}
	
	@Override
	public void remove(UUID aUuid) {
		RelayEvent dummyRelayEvent = new RelayEvent(null, null);
		dummyRelayEvent.setUuid(aUuid);
		List<RelayEvent> all = getAll();
		all.remove(dummyRelayEvent);
		try {
			put(all);
		} catch (IOException e) {
			throw new RuntimeException("Error", e);
		}
	}
}