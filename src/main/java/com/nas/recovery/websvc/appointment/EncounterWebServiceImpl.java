package com.nas.recovery.websvc.appointment;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.appointment.Encounter;

@WebService(endpointInterface = "com.nas.recovery.websvc.appointment.EncounterWebService", serviceName = "EncounterWebService")
@Name("encounterWebService")
public class EncounterWebServiceImpl implements EncounterWebService {

	@In(create = true)
	com.nas.recovery.web.action.appointment.EncounterAction encounterAction;

	public Encounter loadById(Long id) {
		return encounterAction.loadFromId(id);
	}

	public List<Encounter> findByExample(Encounter exampleEncounter) {
		return encounterAction.search(exampleEncounter);
	}

	public void save(Encounter encounter) {
		encounterAction.persist(encounter);
	}

}
