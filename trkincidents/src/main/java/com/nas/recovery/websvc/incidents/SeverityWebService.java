package com.nas.recovery.websvc.incidents;

import javax.jws.WebService;
import com.oreon.trkincidents.incidents.Severity;
import java.util.List;

@WebService
public interface SeverityWebService {

	public Severity loadById(Long id);

	public List<Severity> findByExample(Severity exampleSeverity);

	public void save(Severity severity);

}
