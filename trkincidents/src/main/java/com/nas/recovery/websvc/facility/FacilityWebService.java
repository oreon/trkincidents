package com.nas.recovery.websvc.facility;

import javax.jws.WebService;
import com.oreon.trkincidents.facility.Facility;
import java.util.List;

@WebService
public interface FacilityWebService {

	public Facility loadById(Long id);

	public List<Facility> findByExample(Facility exampleFacility);

	public void save(Facility facility);

}
