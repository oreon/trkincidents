package com.nas.recovery.websvc.incidents;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.incidents.Incident;

@WebService(endpointInterface = "com.nas.recovery.websvc.incidents.IncidentWebService", serviceName = "IncidentWebService")
@Name("incidentWebService")
public class IncidentWebServiceImpl implements IncidentWebService {

	@In(create = true)
	com.nas.recovery.web.action.incidents.IncidentAction incidentAction;

	public Incident loadById(Long id) {
		return incidentAction.loadFromId(id);
	}

	public List<Incident> findByExample(Incident exampleIncident) {
		return incidentAction.search(exampleIncident);
	}

	public void save(Incident incident) {
		incidentAction.persist(incident);
	}

}
