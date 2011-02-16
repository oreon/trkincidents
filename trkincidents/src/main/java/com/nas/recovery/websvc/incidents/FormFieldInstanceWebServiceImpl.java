package com.nas.recovery.websvc.incidents;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.incidents.FormFieldInstance;

@WebService(endpointInterface = "com.nas.recovery.websvc.incidents.FormFieldInstanceWebService", serviceName = "FormFieldInstanceWebService")
@Name("formFieldInstanceWebService")
public class FormFieldInstanceWebServiceImpl
		implements
			FormFieldInstanceWebService {

	@In(create = true)
	com.nas.recovery.web.action.incidents.FormFieldInstanceAction formFieldInstanceAction;

	public FormFieldInstance loadById(Long id) {
		return formFieldInstanceAction.loadFromId(id);
	}

	public List<FormFieldInstance> findByExample(
			FormFieldInstance exampleFormFieldInstance) {
		return formFieldInstanceAction.search(exampleFormFieldInstance);
	}

	public void save(FormFieldInstance formFieldInstance) {
		formFieldInstanceAction.persist(formFieldInstance);
	}

}
