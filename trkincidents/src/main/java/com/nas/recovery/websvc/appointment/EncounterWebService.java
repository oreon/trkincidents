package com.nas.recovery.websvc.appointment;

import javax.jws.WebService;
import com.oreon.trkincidents.appointment.Encounter;
import java.util.List;

@WebService
public interface EncounterWebService {

	public Encounter loadById(Long id);

	public List<Encounter> findByExample(Encounter exampleEncounter);

	public void save(Encounter encounter);

}
