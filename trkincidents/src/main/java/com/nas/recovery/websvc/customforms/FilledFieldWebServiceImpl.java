package com.nas.recovery.websvc.customforms;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.customforms.FilledField;

@WebService(endpointInterface = "com.nas.recovery.websvc.customforms.FilledFieldWebService", serviceName = "FilledFieldWebService")
@Name("filledFieldWebService")
public class FilledFieldWebServiceImpl implements FilledFieldWebService {

	@In(create = true)
	com.nas.recovery.web.action.customforms.FilledFieldAction filledFieldAction;

	public FilledField loadById(Long id) {
		return filledFieldAction.loadFromId(id);
	}

	public List<FilledField> findByExample(FilledField exampleFilledField) {
		return filledFieldAction.search(exampleFilledField);
	}

	public void save(FilledField filledField) {
		filledFieldAction.persist(filledField);
	}

}
