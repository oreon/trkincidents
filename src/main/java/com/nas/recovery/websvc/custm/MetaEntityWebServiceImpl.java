package com.nas.recovery.websvc.custm;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.custm.MetaEntity;

@WebService(endpointInterface = "com.nas.recovery.websvc.custm.MetaEntityWebService", serviceName = "MetaEntityWebService")
@Name("metaEntityWebService")
public class MetaEntityWebServiceImpl implements MetaEntityWebService {

	@In(create = true)
	com.nas.recovery.web.action.custm.MetaEntityAction metaEntityAction;

	public MetaEntity loadById(Long id) {
		return metaEntityAction.loadFromId(id);
	}

	public List<MetaEntity> findByExample(MetaEntity exampleMetaEntity) {
		return metaEntityAction.search(exampleMetaEntity);
	}

	public void save(MetaEntity metaEntity) {
		metaEntityAction.persist(metaEntity);
	}

}
