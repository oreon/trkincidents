package com.nas.recovery;

public class MetaData {

	public static final String[][] ARR_FIELDS = {

	{"Patient",

	"firstName",

	"lastName",

	"address",

	"incidents",

	"documents",

	"healthNumber",

	"dateOfBirth",

	"gender",

	"age",

	},

	{"Document",

	"name",

	"file",

	"notes",

	"patient",

	},

	{"Incident",

	"incidentType",

	"title",

	"patient",

	"createdBy",

	"department",

	"dateOfIncident",

	"formFieldInstances",

	"reportedTo",

	"document",

	"drug",

	"proccedure",

	"responsibleEmployee",

	"description",

	"severity",

	"supportingDocumentses",

	},

	{"IncidentType",

	"name",

	"formFields",

	"referenceFields",

	},

	{"FormField",

	"name",

	"type",

	"required",

	"choiceValues",

	},

	{"FormFieldInstance",

	"value",

	"incident",

	"formField",

	"boolValue",

	"dateValue",

	"enumOrdinal",

	"description",

	},

	{"Drug",

	"incidents",

	"name",

	},

	{"Severity",

	"name",

	},

	{"Proccedure",

	"name",

	"incidents",

	},

	{"ReferenceField",

	"incidentType",

	"referencesEntity",

	"required",

	},

	{"SupportingDocuments",

	"file",

	"title",

	"incident",

	},

	{"Employee",

	"firstName",

	"lastName",

	"employeeNumber",

	"incidentsCreated",

	"user",

	"department",

	"incidentsResponsibleFor",

	"contactDetails",

	},

	{"Department",

	"name",

	"employees",

	"incidents",

	},

	{"User",

	"userName",

	"password",

	"enabled",

	"roles",

	"email",

	},

	{"Role",

	"name",

	"users",

	},

	{"Facility",

	"name",

	},

	{"Appointment",

	"start",

	"end",

	"patient",

	"remarks",

	},

	{"Encounter",

	"historys",

	"patient",

	"notes",

	"prescribedTests",

	},

	{"History",

	"encounter",

	"history",

	},

	{"DxTest",

	"name",

	"description",

	},

	{"PrescribedTest",

	"remarks",

	"dxTest",

	"encounter",

	},

	{"CustomForm",

	"customFields",

	"name",

	},

	{"CustomField",

	"customForm",

	"required",

	"type",

	"name",

	},

	{"FilledForm",

	"customForm",

	"filledFields",

	"entityId",

	},

	{"FilledField",

	"customField",

	"filledForm",

	"value",

	},

	{"CustomReport",

	"metaEntity",

	"fields",

	"groupFields",

	"name",

	},

	{"MetaEntity",

	"name",

	"metaFields",

	},

	{"MetaField",

	"name",

	"metaEntity",

	"customReports",

	"groupReport",

	},

	};

}
