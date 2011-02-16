package com.nas.recovery.websvc.customforms;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.customforms.CustomForm;

@WebService(endpointInterface = "com.nas.recovery.websvc.customforms.CustomFormWebService", serviceName = "CustomFormWebService")
@Name("customFormWebService")
public class CustomFormWebServiceImpl implements CustomFormWebService {

	@In(create = true)
	com.nas.recovery.web.action.customforms.CustomFormAction customFormAction;

	public CustomForm loadById(Long id) {
		return customFormAction.loadFromId(id);
	}

	public List<CustomForm> findByExample(CustomForm exampleCustomForm) {
		return customFormAction.search(exampleCustomForm);
	}

	public void save(CustomForm customForm) {
		customFormAction.persist(customForm);
	}

}
