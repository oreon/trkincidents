package com.nas.recovery.websvc.drugs;

import javax.jws.WebService;
import com.oreon.trkincidents.drugs.DrugInteraction;
import java.util.List;

@WebService
public interface DrugInteractionWebService {

	public DrugInteraction loadById(Long id);

	public List<DrugInteraction> findByExample(
			DrugInteraction exampleDrugInteraction);

	public void save(DrugInteraction drugInteraction);

}
