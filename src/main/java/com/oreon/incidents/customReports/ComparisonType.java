package com.oreon.incidents.customReports;

public enum ComparisonType {

	GREATER_THAN,

	LESS_THAN,

	EQUAL,

	NOT_EQUAL,

	;

	ComparisonType() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
