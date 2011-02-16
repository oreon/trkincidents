package com.nas.recovery.websvc.appointment;

import javax.jws.WebService;
import com.oreon.trkincidents.appointment.PrescribedTest;
import java.util.List;

@WebService
public interface PrescribedTestWebService {

	public PrescribedTest loadById(Long id);

	public List<PrescribedTest> findByExample(
			PrescribedTest examplePrescribedTest);

	public void save(PrescribedTest prescribedTest);

}
