package com.nas.recovery.websvc.incidents;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.incidents.SupportingDocuments;

@WebService(endpointInterface = "com.nas.recovery.websvc.incidents.SupportingDocumentsWebService", serviceName = "SupportingDocumentsWebService")
@Name("supportingDocumentsWebService")
public class SupportingDocumentsWebServiceImpl
		implements
			SupportingDocumentsWebService {

	@In(create = true)
	com.nas.recovery.web.action.incidents.SupportingDocumentsAction supportingDocumentsAction;

	public SupportingDocuments loadById(Long id) {
		return supportingDocumentsAction.loadFromId(id);
	}

	public List<SupportingDocuments> findByExample(
			SupportingDocuments exampleSupportingDocuments) {
		return supportingDocumentsAction.search(exampleSupportingDocuments);
	}

	public void save(SupportingDocuments supportingDocuments) {
		supportingDocumentsAction.persist(supportingDocuments);
	}

}
