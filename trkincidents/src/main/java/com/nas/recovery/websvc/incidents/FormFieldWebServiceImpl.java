package com.nas.recovery.websvc.incidents;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.incidents.FormField;

@WebService(endpointInterface = "com.nas.recovery.websvc.incidents.FormFieldWebService", serviceName = "FormFieldWebService")
@Name("formFieldWebService")
public class FormFieldWebServiceImpl implements FormFieldWebService {

	@In(create = true)
	com.nas.recovery.web.action.incidents.FormFieldAction formFieldAction;

	public FormField loadById(Long id) {
		return formFieldAction.loadFromId(id);
	}

	public List<FormField> findByExample(FormField exampleFormField) {
		return formFieldAction.search(exampleFormField);
	}

	public void save(FormField formField) {
		formFieldAction.persist(formField);
	}

}
