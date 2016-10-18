package com.relayd.web.pagebean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 21.09.2016
 *
 */
public class DialogOptionsBuilder {
	private boolean modal = true;
	private int width = 360;
	private int height = 480;
	private String contentWidth = "100%";
	private String contentHeight = "100%";
	private String headerElement = "customheader";

	public DialogOptionsBuilder width(int aWidth) {
		width = aWidth;
		return this;
	}

	public DialogOptionsBuilder height(int aHeight) {
		height = aHeight;
		return this;
	}

	public Map<String, Object> build() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", modal);
		options.put("width", width);
		options.put("height", height);
		options.put("contentWidth", contentWidth);
		options.put("contentHeight", contentHeight);
		options.put("headerElement", headerElement);

		return options;
	}
}