package com.nas.recovery.websvc.incidents;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.incidents.Icd10;

@WebService(endpointInterface = "com.nas.recovery.websvc.incidents.Icd10WebService", serviceName = "Icd10WebService")
@Name("icd10WebService")
public class Icd10WebServiceImpl implements Icd10WebService {

	@In(create = true)
	com.nas.recovery.web.action.incidents.Icd10Action icd10Action;

	public Icd10 loadById(Long id) {
		return icd10Action.loadFromId(id);
	}

	public List<Icd10> findByExample(Icd10 exampleIcd10) {
		return icd10Action.search(exampleIcd10);
	}

	public void save(Icd10 icd10) {
		icd10Action.persist(icd10);
	}

}
