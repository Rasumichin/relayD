package com.relayd.web.converter;

import java.util.UUID;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.relayd.RelayEvent;

/**
 * Wird ggf. nicht gebraucht. Dient als Test für die Primefaces Comobobox
 *
 */
@FacesConverter("com.relayd.web.converter.RelayEventValueObjectConverter")
public class RelayEventValueObjectConverter implements Converter {

	@Override
	public Object getAsObject(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		RelayEvent relayEvent = RelayEvent.newInstance();
		relayEvent.setUuid(UUID.fromString(value));
		return relayEvent;
	}

	@Override
	public String getAsString(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, Object value) {
		return value.toString();
	}
}