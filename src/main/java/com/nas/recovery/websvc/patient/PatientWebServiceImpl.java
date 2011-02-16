package com.nas.recovery.websvc.patient;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.patient.Patient;

@WebService(endpointInterface = "com.nas.recovery.websvc.patient.PatientWebService", serviceName = "PatientWebService")
@Name("patientWebService")
public class PatientWebServiceImpl implements PatientWebService {

	@In(create = true)
	com.nas.recovery.web.action.patient.PatientAction patientAction;

	public Patient loadById(Long id) {
		return patientAction.loadFromId(id);
	}

	public List<Patient> findByExample(Patient examplePatient) {
		return patientAction.search(examplePatient);
	}

	public void save(Patient patient) {
		patientAction.persist(patient);
	}

}
