package com.nas.recovery.websvc.incidents;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.incidents.Proccedure;

@WebService(endpointInterface = "com.nas.recovery.websvc.incidents.ProccedureWebService", serviceName = "ProccedureWebService")
@Name("proccedureWebService")
public class ProccedureWebServiceImpl implements ProccedureWebService {

	@In(create = true)
	com.nas.recovery.web.action.incidents.ProccedureAction proccedureAction;

	public Proccedure loadById(Long id) {
		return proccedureAction.loadFromId(id);
	}

	public List<Proccedure> findByExample(Proccedure exampleProccedure) {
		return proccedureAction.search(exampleProccedure);
	}

	public void save(Proccedure proccedure) {
		proccedureAction.persist(proccedure);
	}

}
