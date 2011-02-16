package com.nas.recovery.websvc.incidents;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.incidents.Severity;

@WebService(endpointInterface = "com.nas.recovery.websvc.incidents.SeverityWebService", serviceName = "SeverityWebService")
@Name("severityWebService")
public class SeverityWebServiceImpl implements SeverityWebService {

	@In(create = true)
	com.nas.recovery.web.action.incidents.SeverityAction severityAction;

	public Severity loadById(Long id) {
		return severityAction.loadFromId(id);
	}

	public List<Severity> findByExample(Severity exampleSeverity) {
		return severityAction.search(exampleSeverity);
	}

	public void save(Severity severity) {
		severityAction.persist(severity);
	}

}
