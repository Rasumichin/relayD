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

import com.relayd.Relay;
import com.relayd.ejb.RelayGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   21.11.2016
 *
 */
public class RelayGatewayFile implements RelayGateway {

	private String fileName = "relay.relayD";

	private RelayToRelayMapper relayToRelayMapper = RelayToRelayMapper.newInstance();

	public RelayGatewayFile() {
		initFile();
	}

	RelayGatewayFile(String aFileName) {
		setFileName(aFileName);
		initFile();
	}

	private void initFile() {
		File file = new File(getFileName());
		if (!file.exists()) {
			List<Relay> relays = new ArrayList<>();
			try {
				put(relays);
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

	private void put(List<Relay> someRelays) throws IOException {
		FileOutputStream fileOutputStream;
		fileOutputStream = new FileOutputStream(getFileName());
		SerializationUtils.serialize((Serializable) someRelays, fileOutputStream);
		fileOutputStream.close();
	}

	private String getFileName() {
		return fileName;
	}

	private void setFileName(String aFileName) {
		fileName = aFileName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Relay> getAll() {
		FileInputStream fileInputStream;
		List<Relay> relays = new ArrayList<>();
		try {
			fileInputStream = new FileInputStream(getFileName());
			relays = (ArrayList<Relay>) SerializationUtils.deserialize(fileInputStream);
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error - FileNotFoundException", e);
		} catch (IOException e) {
			throw new RuntimeException("Error - IOException", e);
		}
		return relays;

	}

	@Override
	public void set(Relay updateRelay) {
		List<Relay> someRelays = getAll();

		if (someRelays.contains(updateRelay)) {
			for (Relay eachRelay : someRelays) {
				if (updateRelay.equals(eachRelay)) {
					// TODO (Christian, Version 1.4): Mapper einbauen
					eachRelay.setRelayname(updateRelay.getRelayname());
					getRelayToRelayMapper().mapRelayToRelay(updateRelay, eachRelay);
					break;
				}
			}
		} else {
			someRelays.add(updateRelay);
		}

		try {
			put(someRelays);
		} catch (IOException e) {
			throw new RuntimeException("Error - IOException ", e);
		}

	}

	@Override
	public Relay get(UUID uuid) {
		for (Relay relay : getAll()) {
			if (uuid.equals(relay.getUuid())) {
				return relay;
			}
		}
		return null;
	}

	private RelayToRelayMapper getRelayToRelayMapper() {
		return relayToRelayMapper;
	}
}