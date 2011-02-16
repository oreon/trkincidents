package com.nas.recovery.websvc.incidents;

import javax.jws.WebService;
import com.oreon.trkincidents.incidents.Incident;
import java.util.List;

@WebService
public interface IncidentWebService {

	public Incident loadById(Long id);

	public List<Incident> findByExample(Incident exampleIncident);

	public void save(Incident incident);

}
