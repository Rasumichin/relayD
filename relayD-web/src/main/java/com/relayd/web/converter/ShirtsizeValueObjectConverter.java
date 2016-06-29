package com.relayd.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.relayd.attributes.Shirtsize;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   29.06.2016
 * status   initial
 */
@FacesConverter("com.relayd.web.converter.ShirtsizeValueObjectConverter")
public class ShirtsizeValueObjectConverter implements Converter {

	@Override
	public Object getAsObject(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, String value) {
		// TODO -schmollc- Decode/Encode für ShirtsizeEnum einführen um das ValueMapping vollziehen zu können
		Shirtsize size = Shirtsize.DamenL;
		return size;
	}

	@Override
	public String getAsString(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, Object value) {
		return value.toString();
	}
}
