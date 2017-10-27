package com.relayd.web.converter;

import java.util.UUID;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.relayd.web.pagebean.RelayEventDisplay;

@FacesConverter("com.relayd.web.converter.RelayEventDisplayValueObjectConverter")
public class RelayEventDisplayValueObjectConverter implements Converter {

	@Override
	public Object getAsObject(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		RelayEventDisplay relayEventDisplay = RelayEventDisplay.newInstance(UUID.fromString(value), "");
		return relayEventDisplay;
	}

	@Override
	public String getAsString(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, Object value) {
		return value.toString();
	}
}
