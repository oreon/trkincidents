package com.nas.recovery.websvc.appointment;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.appointment.DxTest;

@WebService(endpointInterface = "com.nas.recovery.websvc.appointment.DxTestWebService", serviceName = "DxTestWebService")
@Name("dxTestWebService")
public class DxTestWebServiceImpl implements DxTestWebService {

	@In(create = true)
	com.nas.recovery.web.action.appointment.DxTestAction dxTestAction;

	public DxTest loadById(Long id) {
		return dxTestAction.loadFromId(id);
	}

	public List<DxTest> findByExample(DxTest exampleDxTest) {
		return dxTestAction.search(exampleDxTest);
	}

	public void save(DxTest dxTest) {
		dxTestAction.persist(dxTest);
	}

}
