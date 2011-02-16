package com.nas.recovery.websvc.appointment;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.appointment.History;

@WebService(endpointInterface = "com.nas.recovery.websvc.appointment.HistoryWebService", serviceName = "HistoryWebService")
@Name("historyWebService")
public class HistoryWebServiceImpl implements HistoryWebService {

	@In(create = true)
	com.nas.recovery.web.action.appointment.HistoryAction historyAction;

	public History loadById(Long id) {
		return historyAction.loadFromId(id);
	}

	public List<History> findByExample(History exampleHistory) {
		return historyAction.search(exampleHistory);
	}

	public void save(History history) {
		historyAction.persist(history);
	}

}
