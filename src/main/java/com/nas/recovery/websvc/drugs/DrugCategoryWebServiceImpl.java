package com.nas.recovery.websvc.drugs;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.drugs.DrugCategory;

@WebService(endpointInterface = "com.nas.recovery.websvc.drugs.DrugCategoryWebService", serviceName = "DrugCategoryWebService")
@Name("drugCategoryWebService")
public class DrugCategoryWebServiceImpl implements DrugCategoryWebService {

	@In(create = true)
	com.nas.recovery.web.action.drugs.DrugCategoryAction drugCategoryAction;

	public DrugCategory loadById(Long id) {
		return drugCategoryAction.loadFromId(id);
	}

	public List<DrugCategory> findByExample(DrugCategory exampleDrugCategory) {
		return drugCategoryAction.search(exampleDrugCategory);
	}

	public void save(DrugCategory drugCategory) {
		drugCategoryAction.persist(drugCategory);
	}

}
