package com.nas.recovery.websvc.appointment;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.appointment.PrescribedTest;

@WebService(endpointInterface = "com.nas.recovery.websvc.appointment.PrescribedTestWebService", serviceName = "PrescribedTestWebService")
@Name("prescribedTestWebService")
public class PrescribedTestWebServiceImpl implements PrescribedTestWebService {

	@In(create = true)
	com.nas.recovery.web.action.appointment.PrescribedTestAction prescribedTestAction;

	public PrescribedTest loadById(Long id) {
		return prescribedTestAction.loadFromId(id);
	}

	public List<PrescribedTest> findByExample(
			PrescribedTest examplePrescribedTest) {
		return prescribedTestAction.search(examplePrescribedTest);
	}

	public void save(PrescribedTest prescribedTest) {
		prescribedTestAction.persist(prescribedTest);
	}

}
