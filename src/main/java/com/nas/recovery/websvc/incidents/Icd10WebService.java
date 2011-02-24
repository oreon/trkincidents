package com.nas.recovery.websvc.incidents;

import javax.jws.WebService;
import com.oreon.trkincidents.incidents.Icd10;
import java.util.List;

@WebService
public interface Icd10WebService {

	public Icd10 loadById(Long id);

	public List<Icd10> findByExample(Icd10 exampleIcd10);

	public void save(Icd10 icd10);

}
