package com.nas.recovery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.oreon.trkincidents.custm.MetaEntity;
import com.oreon.trkincidents.custm.MetaField;

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

	"reportedTo",

	"document",

	"drug",

	"proccedure",

	"responsibleEmployee",

	"description",

	"severity",

	"supportingDocumentses",

	"icd10",

	"formFieldInstances",

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

	{"com.oreon.trkincidents.incidents.Drug",

	"incidents",

	"name",

	},

	{"com.oreon.trkincidents.incidents.Severity",

	"name",

	},

	{"com.oreon.trkincidents.incidents.Proccedure",

	"name",

	"incidents",

	"description",

	},

	{"com.oreon.trkincidents.incidents.ReferenceField",

	"incidentType",

	"referencesEntity",

	"required",

	},

	{"com.oreon.trkincidents.incidents.SupportingDocuments",

	"file",

	"title",

	"incident",

	},

	{"com.oreon.trkincidents.incidents.Icd10",

	"code",

	"description",

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

	},

	{"com.oreon.trkincidents.employee.Department",

	"name",

	"employees",

	"incidents",

	},

	{"com.oreon.trkincidents.users.User",

	"userName",

	"password",

	"enabled",

	"roles",

	"email",

	},

	{"com.oreon.trkincidents.users.Role",

	"name",

	"users",

	},

	{"com.oreon.trkincidents.facility.Facility",

	"name",

	},

	{"com.oreon.trkincidents.custm.CustomReport",

	"metaEntity",

	"fields",

	"groupFields",

	"name",

	},

	{"com.oreon.trkincidents.custm.MetaEntity",

	"name",

	"metaFields",

	},

	{"com.oreon.trkincidents.custm.MetaField",

	"name",

	"metaEntity",

	"customReports",

	"groupReport",

	},

	};

}
