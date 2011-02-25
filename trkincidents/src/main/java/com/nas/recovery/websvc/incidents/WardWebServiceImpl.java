package com.nas.recovery.websvc.incidents;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.incidents.Ward;

@WebService(endpointInterface = "com.nas.recovery.websvc.incidents.WardWebService", serviceName = "WardWebService")
@Name("wardWebService")
public class WardWebServiceImpl implements WardWebService {

	@In(create = true)
	com.nas.recovery.web.action.incidents.WardAction wardAction;

	public Ward loadById(Long id) {
		return wardAction.loadFromId(id);
	}

	public List<Ward> findByExample(Ward exampleWard) {
		return wardAction.search(exampleWard);
	}

	public void save(Ward ward) {
		wardAction.persist(ward);
	}

}
