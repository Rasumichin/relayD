package com.relayd;

import java.io.Serializable;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 16.10.2016
 *
 */
public class Settings implements Serializable {
	private static final long serialVersionUID = 6060470764587750857L;

	private static final String TO_DO = "ToDo";

	private String theme = "cupertino";

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
		return TO_DO;
	}

	public String getGatewayType() {
		return TO_DO;
	}

	public String getClosingDate() {
		return "01.04.2017 (ToDo)";
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String aTheme) {
		theme = aTheme;
	}
}