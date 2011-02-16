package com.nas.recovery.websvc.patient;

import javax.jws.WebService;
import com.oreon.trkincidents.patient.Patient;
import java.util.List;

@WebService
public interface PatientWebService {

	public Patient loadById(Long id);

	public List<Patient> findByExample(Patient examplePatient);

	public void save(Patient patient);

}
