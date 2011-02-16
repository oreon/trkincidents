package com.nas.recovery.websvc.incidents;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.incidents.ReferenceField;

@WebService(endpointInterface = "com.nas.recovery.websvc.incidents.ReferenceFieldWebService", serviceName = "ReferenceFieldWebService")
@Name("referenceFieldWebService")
public class ReferenceFieldWebServiceImpl implements ReferenceFieldWebService {

	@In(create = true)
	com.nas.recovery.web.action.incidents.ReferenceFieldAction referenceFieldAction;

	public ReferenceField loadById(Long id) {
		return referenceFieldAction.loadFromId(id);
	}

	public List<ReferenceField> findByExample(
			ReferenceField exampleReferenceField) {
		return referenceFieldAction.search(exampleReferenceField);
	}

	public void save(ReferenceField referenceField) {
		referenceFieldAction.persist(referenceField);
	}

}
