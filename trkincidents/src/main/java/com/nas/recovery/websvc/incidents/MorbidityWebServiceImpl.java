package com.nas.recovery.websvc.incidents;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.incidents.Morbidity;

@WebService(endpointInterface = "com.nas.recovery.websvc.incidents.MorbidityWebService", serviceName = "MorbidityWebService")
@Name("morbidityWebService")
public class MorbidityWebServiceImpl implements MorbidityWebService {

	@In(create = true)
	com.nas.recovery.web.action.incidents.MorbidityAction morbidityAction;

	public Morbidity loadById(Long id) {
		return morbidityAction.loadFromId(id);
	}

	public List<Morbidity> findByExample(Morbidity exampleMorbidity) {
		return morbidityAction.search(exampleMorbidity);
	}

	public void save(Morbidity morbidity) {
		morbidityAction.persist(morbidity);
	}

}
