package com.oreon.trkincidents.patient;

public enum Route {

	PO,

	IM,

	IV,

	TOPICAL,

	;

	Route() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
