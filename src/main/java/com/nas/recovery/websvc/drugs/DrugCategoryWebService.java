package com.nas.recovery.websvc.drugs;

import javax.jws.WebService;
import com.oreon.trkincidents.drugs.DrugCategory;
import java.util.List;

@WebService
public interface DrugCategoryWebService {

	public DrugCategory loadById(Long id);

	public List<DrugCategory> findByExample(DrugCategory exampleDrugCategory);

	public void save(DrugCategory drugCategory);

}
