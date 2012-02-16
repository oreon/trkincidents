package com.oreon.incidents;



import com.oreon.incidents.incidents.FieldType;
import com.oreon.incidents.incidents.FormField;
import com.oreon.incidents.incidents.FormFieldInstance;

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
