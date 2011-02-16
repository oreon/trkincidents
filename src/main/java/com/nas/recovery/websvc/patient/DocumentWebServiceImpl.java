package com.nas.recovery.websvc.patient;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.patient.Document;

@WebService(endpointInterface = "com.nas.recovery.websvc.patient.DocumentWebService", serviceName = "DocumentWebService")
@Name("documentWebService")
public class DocumentWebServiceImpl implements DocumentWebService {

	@In(create = true)
	com.nas.recovery.web.action.patient.DocumentAction documentAction;

	public Document loadById(Long id) {
		return documentAction.loadFromId(id);
	}

	public List<Document> findByExample(Document exampleDocument) {
		return documentAction.search(exampleDocument);
	}

	public void save(Document document) {
		documentAction.persist(document);
	}

}
