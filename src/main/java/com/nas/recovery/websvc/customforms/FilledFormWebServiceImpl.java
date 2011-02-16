package com.nas.recovery.websvc.customforms;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.trkincidents.customforms.FilledForm;

@WebService(endpointInterface = "com.nas.recovery.websvc.customforms.FilledFormWebService", serviceName = "FilledFormWebService")
@Name("filledFormWebService")
public class FilledFormWebServiceImpl implements FilledFormWebService {

	@In(create = true)
	com.nas.recovery.web.action.customforms.FilledFormAction filledFormAction;

	public FilledForm loadById(Long id) {
		return filledFormAction.loadFromId(id);
	}

	public List<FilledForm> findByExample(FilledForm exampleFilledForm) {
		return filledFormAction.search(exampleFilledForm);
	}

	public void save(FilledForm filledForm) {
		filledFormAction.persist(filledForm);
	}

}
