package com.nas.recovery.websvc.facility;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.facility.Facility;

@WebService(endpointInterface = "com.nas.recovery.websvc.facility.FacilityWebService", serviceName = "FacilityWebService")
@Name("facilityWebService")
public class FacilityWebServiceImpl implements FacilityWebService {

	@In(create = true)
	com.nas.recovery.web.action.facility.FacilityAction facilityAction;

	public Facility loadById(Long id) {
		return facilityAction.loadFromId(id);
	}

	public List<Facility> findByExample(Facility exampleFacility) {
		return facilityAction.search(exampleFacility);
	}

	public void save(Facility facility) {
		facilityAction.persist(facility);
	}

}
