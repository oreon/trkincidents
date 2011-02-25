package com.nas.recovery;

import com.oreon.trkincidents.incidents.FieldType;
import com.oreon.trkincidents.incidents.FormField;
import com.oreon.trkincidents.incidents.FormFieldInstance;

public class ProjectUtils {
	
	public static String getVal(FormFieldInstance ffi){
		FormField cf = ffi.getFormField();
		if(cf.getType().equals(FieldType.DATE))
			return ffi.getDateValue().toString();
		else if(cf.getType().equals(FieldType.YES_NO))
			return ffi.getBoolValue().toString();
		return ffi.getValue();
	}

}
