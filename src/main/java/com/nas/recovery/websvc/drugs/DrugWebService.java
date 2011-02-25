package com.nas.recovery.websvc.drugs;

import javax.jws.WebService;
import com.oreon.trkincidents.drugs.Drug;
import java.util.List;

@WebService
public interface DrugWebService {

	public Drug loadById(Long id);

	public List<Drug> findByExample(Drug exampleDrug);

	public void save(Drug drug);

}
