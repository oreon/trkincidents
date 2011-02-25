package com.oreon.trkincidents.incidents;

public enum FieldType {

	YES_NO,

	TEXT,

	LARGE_TEXT,

	DATE,

	CHOICE,

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
