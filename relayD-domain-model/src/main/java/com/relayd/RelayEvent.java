package com.relayd;

import com.relayd.attributes.EventDay;
import com.relayd.attributes.EventName;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 19.05.2016
 * status initial
 * 
 */
public class RelayEvent {

	private EventName name;
	private EventDay eventDay;

	public RelayEvent(EventName anEventName, EventDay anEventDay) {
		super();
		
		name = anEventName;
		eventDay = anEventDay;
	}

	public EventName getName() {
		return name;
	}

	public EventDay getEventDay() {
		return eventDay;
	}
}