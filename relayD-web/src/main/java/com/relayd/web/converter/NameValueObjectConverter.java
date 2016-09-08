package com.relayd.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 01.09.2016
 * status initial
 * 
 */
public abstract class NameValueObjectConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String nameValue) {
		Object name = null;
		if (!nameValue.trim().isEmpty()) {
			name = getName(nameValue);
		}
		
		return name;
	}

	abstract Object getName(String nameValue);

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object nameValue) {
		return nameValue.toString();
	}
}