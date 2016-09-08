package com.relayd.web.theme;

public class Theme {

	private int id;

	private String displayName;

	private String name;

	public Theme() {
	}

	public Theme(int anId, String aDisplayName, String aName) {
		id = anId;
		displayName = aDisplayName;
		name = aName;
	}

	public int getId() {
		return id;
	}

	public void setId(int anId) {
		id = anId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String aDisplayName) {
		displayName = aDisplayName;
	}

	public String getName() {
		return name;
	}

	public void setName(String aName) {
		name = aName;
	}

	@Override
	public String toString() {
		return name;
	}
}