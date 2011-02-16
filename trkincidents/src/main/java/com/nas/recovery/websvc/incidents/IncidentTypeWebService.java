package com.nas.recovery.websvc.incidents;

import javax.jws.WebService;
import com.oreon.trkincidents.incidents.IncidentType;
import java.util.List;

@WebService
public interface IncidentTypeWebService {

	public IncidentType loadById(Long id);

	public List<IncidentType> findByExample(IncidentType exampleIncidentType);

	public void save(IncidentType incidentType);

}
