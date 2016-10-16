package com.relayd;

import java.io.Serializable;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 16.10.2016
 *
 */
public class Settings implements Serializable {
	private static final long serialVersionUID = 6060470764587750857L;

	private Settings() {
		super();
	}

	public static Settings newInstance() {
		return new Settings();
	}

	public String getVersion() {
		return "1.0 - Codename Augustiner";
	}

	public String getEmailDomain() {
		return "ToDo";
	}
}