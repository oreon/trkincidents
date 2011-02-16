package com.nas.recovery.websvc.incidents;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.incidents.IncidentType;

@WebService(endpointInterface = "com.nas.recovery.websvc.incidents.IncidentTypeWebService", serviceName = "IncidentTypeWebService")
@Name("incidentTypeWebService")
public class IncidentTypeWebServiceImpl implements IncidentTypeWebService {

	@In(create = true)
	com.nas.recovery.web.action.incidents.IncidentTypeAction incidentTypeAction;

	public IncidentType loadById(Long id) {
		return incidentTypeAction.loadFromId(id);
	}

	public List<IncidentType> findByExample(IncidentType exampleIncidentType) {
		return incidentTypeAction.search(exampleIncidentType);
	}

	public void save(IncidentType incidentType) {
		incidentTypeAction.persist(incidentType);
	}

}
