package com.nas.recovery.websvc.incidents;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.incidents.Drug;

@WebService(endpointInterface = "com.nas.recovery.websvc.incidents.DrugWebService", serviceName = "DrugWebService")
@Name("drugWebService")
public class DrugWebServiceImpl implements DrugWebService {

	@In(create = true)
	com.nas.recovery.web.action.incidents.DrugAction drugAction;

	public Drug loadById(Long id) {
		return drugAction.loadFromId(id);
	}

	public List<Drug> findByExample(Drug exampleDrug) {
		return drugAction.search(exampleDrug);
	}

	public void save(Drug drug) {
		drugAction.persist(drug);
	}

}
