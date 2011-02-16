package com.nas.recovery.websvc.patient;

import javax.jws.WebService;
import com.oreon.trkincidents.patient.Document;
import java.util.List;

@WebService
public interface DocumentWebService {

	public Document loadById(Long id);

	public List<Document> findByExample(Document exampleDocument);

	public void save(Document document);

}
