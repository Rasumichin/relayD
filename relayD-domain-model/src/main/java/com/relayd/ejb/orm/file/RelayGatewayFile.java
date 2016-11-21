package com.relayd.ejb.orm.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
			for (Relay relay : someRelays) {
				if (updateRelay.equals(relay)) {
					// TODO -schmollc- Mapper einbauen
					relay.setRelayname(updateRelay.getRelayname());
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
}