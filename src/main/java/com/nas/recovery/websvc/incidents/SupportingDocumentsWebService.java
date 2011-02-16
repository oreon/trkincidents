package com.nas.recovery.websvc.incidents;

import javax.jws.WebService;
import com.oreon.trkincidents.incidents.SupportingDocuments;
import java.util.List;

@WebService
public interface SupportingDocumentsWebService {

	public SupportingDocuments loadById(Long id);

	public List<SupportingDocuments> findByExample(
			SupportingDocuments exampleSupportingDocuments);

	public void save(SupportingDocuments supportingDocuments);

}
