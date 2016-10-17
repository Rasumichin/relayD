package com.relayd.web.converter;

import javax.faces.convert.FacesConverter;

import com.relayd.attributes.Forename;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 20.06.2016
 *
 */
@FacesConverter("com.relayd.web.converter.ForenameValueObjectConverter")
public class ForenameValueObjectConverter extends NameValueObjectConverter {

	@Override
	Forename getName(String nameValue) {
		return Forename.newInstance(nameValue);
	}
}