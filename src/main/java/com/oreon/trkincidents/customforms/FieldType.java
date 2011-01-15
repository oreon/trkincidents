package com.oreon.trkincidents.customforms;

public enum FieldType {

	TEXT,

	LARGE_TEXT,

	CHOICE,

	YES_NO,

	DATE,

	REFERENCE,

	;

	FieldType() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
