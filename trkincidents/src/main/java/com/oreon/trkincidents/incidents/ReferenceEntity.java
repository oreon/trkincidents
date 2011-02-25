package com.oreon.trkincidents.incidents;

public enum ReferenceEntity {

	Patient,

	Drug,

	Procedure,

	Ward,

	Morbidity,

	;

	ReferenceEntity() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
