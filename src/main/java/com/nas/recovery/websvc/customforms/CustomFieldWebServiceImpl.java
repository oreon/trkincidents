package com.nas.recovery.websvc.customforms;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.customforms.CustomField;

@WebService(endpointInterface = "com.nas.recovery.websvc.customforms.CustomFieldWebService", serviceName = "CustomFieldWebService")
@Name("customFieldWebService")
public class CustomFieldWebServiceImpl implements CustomFieldWebService {

	@In(create = true)
	com.nas.recovery.web.action.customforms.CustomFieldAction customFieldAction;

	public CustomField loadById(Long id) {
		return customFieldAction.loadFromId(id);
	}

	public List<CustomField> findByExample(CustomField exampleCustomField) {
		return customFieldAction.search(exampleCustomField);
	}

	public void save(CustomField customField) {
		customFieldAction.persist(customField);
	}

}
