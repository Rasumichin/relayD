package com.relayd;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import com.relayd.ejb.GatewayType;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 16.10.2016
 *
 */
public class Settings implements Serializable {
	private static final long serialVersionUID = 6060470764587750857L;

	private static final String TO_DO = "ToDo";
	private static final String CandA = " @ C&A";
	private static final String CLOSING_DATE = "01.04.2017 (ToDo)";

	private Properties applicationProperties;

	private String theme = "afterwork";

	private static GatewayType gatewayType = GatewayType.JPA;

	private Settings() {
		super();
	}

	public static Settings newInstance() {
		return new Settings();
	}

	public String getVersion() {
		String version = getProperty("application.version");
		Version versionName = Version.newInstance(version.substring(0, 3));
		return versionName.getValue() + " - Codename " + versionName.getDescription();
	}

	public String getEmailDomain() {
		return TO_DO;
	}

	public static GatewayType getGatewayType() {
		return gatewayType;
	}

	public static void setGatewayType(GatewayType aGatewayType) {
		gatewayType = aGatewayType;
	}

	public String getRelayAppendix() {
		return CandA;
	}

	public String getClosingDate() {
		return CLOSING_DATE;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String aTheme) {
		theme = aTheme;
	}

	String getProperty(String aKey) {
		if (!getApplicationProperties().containsKey(aKey)) {
			return "UNKNOWN";
		} else {
			return getApplicationProperties().getProperty(aKey);
		}
	}

	public Properties getApplicationProperties() {
		// TODO - REL-287 - Old code. clean up!
		if (applicationProperties == null) {
			try {
				applicationProperties = new Properties();
				InputStream resourceAsStream = Settings.class.getResourceAsStream("/application.properties");
				applicationProperties.load(resourceAsStream);
			} catch (IOException exception) {
				System.err.println("IOException: unable to read application.properties");
			}
		}
		return applicationProperties;
	}
}