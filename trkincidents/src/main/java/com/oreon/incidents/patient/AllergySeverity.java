package com.oreon.incidents.patient;

public enum AllergySeverity {

	SEVERE,

	MODERATE,

	MILD,

	;

	AllergySeverity() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
