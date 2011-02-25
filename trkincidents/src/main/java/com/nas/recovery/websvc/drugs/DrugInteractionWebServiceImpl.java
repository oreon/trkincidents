package com.nas.recovery.websvc.drugs;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.drugs.DrugInteraction;

@WebService(endpointInterface = "com.nas.recovery.websvc.drugs.DrugInteractionWebService", serviceName = "DrugInteractionWebService")
@Name("drugInteractionWebService")
public class DrugInteractionWebServiceImpl implements DrugInteractionWebService {

	@In(create = true)
	com.nas.recovery.web.action.drugs.DrugInteractionAction drugInteractionAction;

	public DrugInteraction loadById(Long id) {
		return drugInteractionAction.loadFromId(id);
	}

	public List<DrugInteraction> findByExample(
			DrugInteraction exampleDrugInteraction) {
		return drugInteractionAction.search(exampleDrugInteraction);
	}

	public void save(DrugInteraction drugInteraction) {
		drugInteractionAction.persist(drugInteraction);
	}

}
