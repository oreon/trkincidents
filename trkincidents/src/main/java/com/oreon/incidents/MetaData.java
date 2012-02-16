package com.oreon.incidents;

public class MetaData {

	public static final String[][] ARR_FIELDS = {

	{"com.oreon.incidents.patient.Patient",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"address.streetAddress", "java.lang.String",

	"address.city", "java.lang.String",

	"address.state", "java.lang.String",

	"address.phone", "java.lang.String",

	"incidentsCount", "java.lang.Integer",

	"healthNumber", "java.lang.String",

	"dateOfBirth", "java.util.Date",

	"gender.name", "java.lang.String",

	"age", "java.lang.Integer",

	},

	{"com.oreon.incidents.incidents.Incident",

	"incidentType.displayName", "java.lang.String",

	"title", "java.lang.String",

	"patient.displayName", "java.lang.String",

	"createdBy.displayName", "java.lang.String",

	"department.displayName", "java.lang.String",

	"dateOfIncident", "java.util.Date",

	"document", "FileAttachment",

	"proccedure.displayName", "java.lang.String",

	"responsibleEmployee.displayName", "java.lang.String",

	"description", "java.lang.String",

	"severity.displayName", "java.lang.String",

	"ward.displayName", "java.lang.String",

	"reportedTo.displayName", "java.lang.String",

	"drug.displayName", "java.lang.String",

	"formFieldInstancesCount", "java.lang.Integer",

	"morbidity.displayName", "java.lang.String",

	"preventiveAction", "java.lang.String",

	},

	{"com.oreon.incidents.incidents.IncidentType",

	"name", "java.lang.String",

	"referenceFieldsCount", "java.lang.Integer",

	"formFieldsCount", "java.lang.Integer",

	},

	{"com.oreon.incidents.incidents.FormField",

	"name", "java.lang.String",

	"type.name", "java.lang.String",

	"required", "java.lang.Boolean",

	"choiceValues", "java.lang.String",

	"incidentType.displayName", "java.lang.String",

	},

	{"com.oreon.incidents.incidents.FormFieldInstance",

	"value", "java.lang.String",

	"formField.displayName", "java.lang.String",

	"boolValue", "java.lang.Boolean",

	"dateValue", "java.util.Date",

	"enumOrdinal", "java.lang.Integer",

	"description", "java.lang.String",

	"incident.displayName", "java.lang.String",

	},

	{"com.oreon.incidents.incidents.Severity",

	"name", "java.lang.String",

	},

	{"com.oreon.incidents.incidents.Proccedure",

	"name", "java.lang.String",

	"incidentsCount", "java.lang.Integer",

	},

	{"com.oreon.incidents.incidents.ReferenceField",

	"incidentType.displayName", "java.lang.String",

	"referencesEntity.name", "java.lang.String",

	"required", "java.lang.Boolean",

	},

	{"com.oreon.incidents.incidents.Ward",

	"name", "java.lang.String",

	"incidentsCount", "java.lang.Integer",

	},

	{"com.oreon.incidents.incidents.Morbidity",

	"name", "java.lang.String",

	"code", "java.lang.String",

	"incidentsCount", "java.lang.Integer",

	},

	{"com.oreon.incidents.employee.Employee",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"employeeNumber", "java.lang.String",

	"incidentsCreatedCount", "java.lang.Integer",

	"user.displayName", "java.lang.String",

	"department.displayName", "java.lang.String",

	"incidentsResponsibleForCount", "java.lang.Integer",

	"contactDetails.primaryPhone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.streetAddress", "java.lang.String",

	"contactDetails.city", "java.lang.String",

	"contactDetails.zip", "java.lang.String",

	"incidentsReportedCount", "java.lang.Integer",

	},

	{"com.oreon.incidents.employee.Department",

	"name", "java.lang.String",

	"employeesCount", "java.lang.Integer",

	"incidentsCount", "java.lang.Integer",

	},

	{"com.oreon.incidents.users.User",

	"userName", "java.lang.String",

	"password", "java.lang.String",

	"rolesCount", "java.lang.Integer",

	"email", "java.lang.String",

	"enabled", "java.lang.Boolean",

	},

	{"com.oreon.incidents.users.Role",

	"name", "java.lang.String",

	"usersCount", "java.lang.Integer",

	},

	{"com.oreon.incidents.facility.Facility",

	"name", "java.lang.String",

	},

	{"com.oreon.incidents.drugs.Drug",

	"name", "java.lang.String",

	"absorption", "java.lang.String",

	"biotransformation", "java.lang.String",

	"atcCodes", "java.lang.String",

	"contraIndication", "java.lang.String",

	"description", "java.lang.String",

	"dosageForm", "java.lang.String",

	"foodInteractions", "java.lang.String",

	"halfLife", "java.lang.String",

	"halfLifeNumberOfHours", "java.lang.Double",

	"indication", "java.lang.String",

	"mechanismOfAction", "java.lang.String",

	"patientInfo", "java.lang.String",

	"pharmacology", "java.lang.String",

	"drugInteractionsCount", "java.lang.Integer",

	"drugCategorysCount", "java.lang.Integer",

	"toxicity", "java.lang.String",

	"routeOfElimination", "java.lang.String",

	"volumeOfDistribution", "java.lang.String",

	"drugBankId", "java.lang.String",

	"categories", "java.lang.String",

	"incidentsCount", "java.lang.Integer",

	},

	{"com.oreon.incidents.drugs.DrugInteraction",

	"description", "java.lang.String",

	"drug.displayName", "java.lang.String",

	"interactingDrug.displayName", "java.lang.String",

	},

	{"com.oreon.incidents.drugs.DrugCategory",

	"name", "java.lang.String",

	"drugsCount", "java.lang.Integer",

	},

	{"com.oreon.incidents.customReports.CustomReport",

	"fieldsCount", "java.lang.Integer",

	"groupFieldsCount", "java.lang.Integer",

	"name", "java.lang.String",

	"metaEntity.displayName", "java.lang.String",

	"reportParametersCount", "java.lang.Integer",

	},

	{"com.oreon.incidents.customReports.MetaEntity",

	"name", "java.lang.String",

	"metaFieldsCount", "java.lang.Integer",

	},

	{"com.oreon.incidents.customReports.MetaField",

	"name", "java.lang.String",

	"metaEntity.displayName", "java.lang.String",

	"customReportsCount", "java.lang.Integer",

	"groupReportCount", "java.lang.Integer",

	"type", "java.lang.String",

	},

	{"com.oreon.incidents.customReports.ReportParameter",

	"customReport.displayName", "java.lang.String",

	"metaField.displayName", "java.lang.String",

	"comparison.name", "java.lang.String",

	"mandatory", "java.lang.Boolean",

	"defaultValue", "java.lang.String",

	},

	};

}
