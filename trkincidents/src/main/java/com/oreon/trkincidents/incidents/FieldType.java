package com.oreon.trkincidents.incidents;

public enum FieldType {

	TEXT,

	LARGE_TEXT,

	CHOICE,

	BOOLEAN,

	DATE,

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
