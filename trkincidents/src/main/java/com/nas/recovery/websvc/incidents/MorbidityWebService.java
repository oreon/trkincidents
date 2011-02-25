package com.nas.recovery.websvc.incidents;

import javax.jws.WebService;
import com.oreon.trkincidents.incidents.Morbidity;
import java.util.List;

@WebService
public interface MorbidityWebService {

	public Morbidity loadById(Long id);

	public List<Morbidity> findByExample(Morbidity exampleMorbidity);

	public void save(Morbidity morbidity);

}
