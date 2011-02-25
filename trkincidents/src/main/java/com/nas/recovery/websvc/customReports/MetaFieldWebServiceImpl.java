package com.nas.recovery.websvc.customReports;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.customReports.MetaField;

@WebService(endpointInterface = "com.nas.recovery.websvc.customReports.MetaFieldWebService", serviceName = "MetaFieldWebService")
@Name("metaFieldWebService")
public class MetaFieldWebServiceImpl implements MetaFieldWebService {

	@In(create = true)
	com.nas.recovery.web.action.customReports.MetaFieldAction metaFieldAction;

	public MetaField loadById(Long id) {
		return metaFieldAction.loadFromId(id);
	}

	public List<MetaField> findByExample(MetaField exampleMetaField) {
		return metaFieldAction.search(exampleMetaField);
	}

	public void save(MetaField metaField) {
		metaFieldAction.persist(metaField);
	}

}
