package com.nas.recovery;

public class MetaData {

	public static final String[][] ARR_FIELDS = {

	{"com.oreon.trkincidents.patient.Patient",

	"firstName",

	"lastName",

	"address",

	"incidents",

	"healthNumber",

	"dateOfBirth",

	"gender",

	"age",

	},

	{"com.oreon.trkincidents.incidents.Incident",

	"incidentType",

	"title",

	"patient",

	"createdBy",

	"department",

	"dateOfIncident",

	"document",

	"proccedure",

	"responsibleEmployee",

	"description",

	"severity",

	"ward",

	"reportedTo",

	"drug",

	"formFieldInstances",

	"morbidity",

	"preventiveAction",

	},

	{"com.oreon.trkincidents.incidents.IncidentType",

	"name",

	"referenceFields",

	"formFields",

	},

	{"com.oreon.trkincidents.incidents.FormField",

	"name",

	"type",

	"required",

	"choiceValues",

	"incidentType",

	},

	{"com.oreon.trkincidents.incidents.FormFieldInstance",

	"value",

	"formField",

	"boolValue",

	"dateValue",

	"enumOrdinal",

	"description",

	"incident",

	},

	{"com.oreon.trkincidents.incidents.Severity",

	"name",

	},

	{"com.oreon.trkincidents.incidents.Proccedure",

	"name",

	"incidents",

	},

	{"com.oreon.trkincidents.incidents.ReferenceField",

	"incidentType",

	"referencesEntity",

	"required",

	},

	{"com.oreon.trkincidents.incidents.Ward",

	"name",

	"incidents",

	},

	{"com.oreon.trkincidents.incidents.Morbidity",

	"name",

	"code",

	"incidents",

	},

	{"com.oreon.trkincidents.employee.Employee",

	"firstName",

	"lastName",

	"employeeNumber",

	"incidentsCreated",

	"user",

	"department",

	"incidentsResponsibleFor",

	"contactDetails",

	"incidentsReported",

	},

	{"com.oreon.trkincidents.employee.Department",

	"name",

	"employees",

	"incidents",

	},

	{"com.oreon.trkincidents.users.User",

	"userName",

	"password",

	"roles",

	"email",

	"enabled",

	},

	{"com.oreon.trkincidents.users.Role",

	"name",

	"users",

	},

	{"com.oreon.trkincidents.facility.Facility",

	"name",

	},

	{"com.oreon.trkincidents.drugs.Drug",

	"name",

	"absorption",

	"biotransformation",

	"atcCodes",

	"contraIndication",

	"description",

	"dosageForm",

	"foodInteractions",

	"halfLife",

	"halfLifeNumberOfHours",

	"indication",

	"mechanismOfAction",

	"patientInfo",

	"pharmacology",

	"drugInteractions",

	"drugCategorys",

	"toxicity",

	"routeOfElimination",

	"volumeOfDistribution",

	"drugBankId",

	"categories",

	"incidents",

	},

	{"com.oreon.trkincidents.drugs.DrugInteraction",

	"description",

	"drug",

	"interactingDrug",

	},

	{"com.oreon.trkincidents.drugs.DrugCategory",

	"name",

	"drugs",

	},

	{"com.oreon.trkincidents.customReports.CustomReport",

	"fields",

	"groupFields",

	"name",

	"metaEntity",

	},

	{"com.oreon.trkincidents.customReports.MetaEntity",

	"name",

	"metaFields",

	},

	{"com.oreon.trkincidents.customReports.MetaField",

	"name",

	"metaEntity",

	"customReports",

	"groupReport",

	},

	};

}
