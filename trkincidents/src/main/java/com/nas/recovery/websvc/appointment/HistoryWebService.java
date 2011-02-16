package com.nas.recovery.websvc.appointment;

import javax.jws.WebService;
import com.oreon.trkincidents.appointment.History;
import java.util.List;

@WebService
public interface HistoryWebService {

	public History loadById(Long id);

	public List<History> findByExample(History exampleHistory);

	public void save(History history);

}
