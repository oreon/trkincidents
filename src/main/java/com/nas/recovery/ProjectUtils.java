package com.nas.recovery;

import org.drools.lang.dsl.DSLMapParser.mapping_file_return;

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
	
	public static String getSimpleName(String str){
		String arr[] = str.split("\\.");
		return arr[arr.length - 1];
	}
	
	public static void main(String[] args) {
		System.out.println(getSimpleName("ss.ll.aa.bb"));
	}

}
