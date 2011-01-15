package org.witchcraft.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class BoolConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg) {
		if(arg == "true")
			return Boolean.TRUE;
		
		return Boolean.FALSE;	
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg) {
		if (arg == Boolean.TRUE)
			return "true";
		return "false";
	}

}
