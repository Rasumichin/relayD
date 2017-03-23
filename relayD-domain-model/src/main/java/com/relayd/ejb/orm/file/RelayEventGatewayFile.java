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
 * @author schmollc
 * @since 10.02.2016
 *
 */
public class RelayEventGatewayFile implements RelayEventGateway {

	private String fileName = "relayEvent.relayD";

	private RelayEventToRelayEventMapper relayEventToRelayEventMapper = RelayEventToRelayEventMapper.newInstance();

	public RelayEventGatewayFile() {
		initFile();
	}

	RelayEventGatewayFile(String aFileName) {
		setFileName(aFileName);
		initFile();
	}

	private String getFileName() {
		return fileName;
	}

	private void setFileName(String aFileName) {
		fileName = aFileName;
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

	void clear() {
		try {
			put(new ArrayList<>());
		} catch (IOException e) {
			throw new RuntimeException("Error", e);
		}
	}

	private void put(List<RelayEvent> someRelayEvents) throws IOException {
		FileOutputStream fileOutputStream;
		fileOutputStream = new FileOutputStream(getFileName());
		SerializationUtils.serialize((Serializable) someRelayEvents, fileOutputStream);
		fileOutputStream.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RelayEvent> getAll() {
		List<RelayEvent> eventsAsList = new ArrayList<RelayEvent>();

		eventsAsList.add(RelayEvent.duesseldorf());
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(getFileName());
			eventsAsList.addAll((ArrayList<RelayEvent>) SerializationUtils.deserialize(fileInputStream));
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error - FileNotFoundException", e);
		} catch (IOException e) {
			throw new RuntimeException("Error - IOException", e);
		}
		return eventsAsList;
	}

	@Override
	public void set(RelayEvent updateRelayEvent) {
		List<RelayEvent> someRelayEvents = getAll();

		someRelayEvents.remove(RelayEvent.duesseldorf());
		if (someRelayEvents.contains(updateRelayEvent)) {
			for (RelayEvent eachRelayEvent : someRelayEvents) {
				if (updateRelayEvent.equals(eachRelayEvent)) {
					getRelayEventToRelayEventMapper().mapRelayEventToRelayEvent(updateRelayEvent, eachRelayEvent);
					break;
				}
			}
		} else {
			someRelayEvents.add(updateRelayEvent);
		}

		try {
			put(someRelayEvents);
		} catch (IOException e) {
			throw new RuntimeException("Error - IOException ", e);
		}
	}

	private RelayEventToRelayEventMapper getRelayEventToRelayEventMapper() {
		return relayEventToRelayEventMapper;
	}

	@Override
	public RelayEvent get(UUID uuid) {
		for (RelayEvent eachRelayEvent : getAll()) {
			if (uuid.equals(eachRelayEvent.getUuid())) {
				return eachRelayEvent;
			}
		}
		return null;
	}
}